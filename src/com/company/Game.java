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
    private Camera camera;
    private BufferedImage level;
    private KeyInput keyInput;
    static Texture texture;
    private ImageIcon icon = new ImageIcon("res/bkg.jpg");
    private Image bkg = icon.getImage();


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
        this.addKeyListener(new KeyInput(handler));
        loadImageLevel(level);

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
   /* public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else return var;
    } */

    private void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx <h; xx++){
            for(int yy = 0; yy <w; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;


                if(red == 0 && green == 0 && blue == 255){
                    handler.addObject(new Player(xx*32,yy*32, ID.Player, handler)); //player
                }
                else if(red == 76 && green == 255 && blue == 0){
                    handler.addObject(new Block(xx*32,yy*32,1,ID.Collidable)); //top
                }
                else if(red == 100 && green == 100 && blue == 100){
                    handler.addObject(new Block(xx*32,yy*32,5,ID.Collidable)); //left
                }
                else if(red == 160 && green == 160 && blue == 160){
                    handler.addObject(new Block(xx*32,yy*32,6,ID.Collidable)); //right
                }
                else if(red == 91 && green == 127 && blue == 0){
                    handler.addObject(new Block(xx*32,yy*32,0,ID.Collidable)); //top left
                }
                else if(red == 0 && green == 19 && blue == 127){
                    handler.addObject(new Block(xx*32,yy*32,3,ID.Collidable)); //inner left
                }
                else if(red == 0 && green == 148 && blue == 255){
                    handler.addObject(new Block(xx*32,yy*32,4,ID.Collidable)); //inner right
                }
                else if(red == 0 && green == 127 && blue == 14){
                    handler.addObject(new Block(xx*32,yy*32,2,ID.Collidable)); //top right
                }
                else if(red == 178 && green == 0 && blue == 255){
                    handler.addObject(new Block(xx*32,yy*32,8,ID.Collidable)); //platform left
                }
                else if(red == 255 && green == 0 && blue == 220){
                    handler.addObject(new Block(xx*32,yy*32,9,ID.Collidable)); //platform mid
                }
                else if(red == 255 && green == 0 && blue == 110){
                    handler.addObject(new Block(xx*32,yy*32,10,ID.Collidable)); //platform right
                }
                else if(red == 255 && green == 255 && blue == 255){
                    handler.addFill(new BlockFill(xx*32,yy*32, ID.Fill)); //mid
                }
                else;
            }
        }

    }
    static int x;

    private void tick(){

      //  x--;
        handler.tick();
        keyInput.tick();

        for(int i = 0; i<handler.object.size(); i++){
            if(handler.object.get(i).id == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
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
        g.drawImage(bkg,x,0,this);

        g2d.translate(camera.getX(),camera.getY());

        handler.render(g);

        handler.renderFills(g);

        g2d.translate(-camera.getX(),-camera.getY());

        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {

        new Game();

    }
}
