package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1200, HEIGHT = WIDTH / 12*9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private StatsController statsController;
    private Camera camera;
    public BufferedImage level, level2, level3,level4;
    private KeyInput keyInput;
    static Texture texture;
    public static int lvl = 1;


    //private ImageIcon icon = new ImageIcon("res/foreground.png");
     BufferedImage background;
    private ImageIcon iconback = new ImageIcon("src/foggy.png");
     // private Image background = iconback.getImage();


    public Game(){

        new Window(WIDTH, HEIGHT, "Spacer", this);
        setFocusable(true);

    }
    public static Texture getInstance(){
        return texture;
    }

    public synchronized void start(){

        if(running)return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private void init(){
        texture = new Texture();
        keyInput = new KeyInput(handler);
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/level1.png");
        camera = new Camera(0,0);
        handler = new Handler();
        statsController = new StatsController();
        this.addKeyListener(new KeyInput(handler));
        handler.loadImageLevel(level);
        background = loader.loadImage("/foggy.png");


    }

    public synchronized void stop(){

        try{
            thread.join();
            running = false;

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

                                                    /* run() metoda implementirana iz Runnable interfejsa */
    public void run() {

        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 /amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer >1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    }
    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else return var;
    }


    static float fgx;
    // static int fgy;

    private void tick(){

        statsController.tick();
        handler.tick();


        for(int i = 0; i<handler.object.size(); i++){
            if(handler.object.get(i).id == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
        fgx-= 0.5f;

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        switch (lvl){
            case 1:{
                for (int b = 0; b < background.getWidth()*20; b += background.getWidth()){
                    g.drawImage(background,b + (int)fgx,0,this);
                }
            }case 2:{
                for (int b = 0; b < background.getWidth()*20; b += background.getWidth()){
                    g.drawImage(background,b + (int)fgx,0,this);
                }
            }
        }


      //  g.drawImage(background,(int)fgx,0,this);

        g2d.translate(camera.getX(),camera.getY());

        handler.render(g);

        handler.renderFills(g);

        g2d.translate(-camera.getX(),-camera.getY());

        statsController.render(g);

        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {

        new Game();

    }
}
