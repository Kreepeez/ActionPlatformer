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
    Camera camera;
    private ImageIcon icon = new ImageIcon("src/background.jpg");
    private Image bkg = icon.getImage();
    private BufferedImage level = null;

    public Game(){

        BufferedImageLoader loader = new BufferedImageLoader();

        level = loader.loadImage("/level1.png");

        camera = new Camera(0,0);
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler,this));

        loadImageLevel(level);

      //  handler.addObject(new Block(-150,HEIGHT-600, ID.Block, handler));
      //  handler.addObject(new Block(WIDTH/2 + 200,HEIGHT-700, ID.Block, handler));
      //  handler.addObject(new Block( -100,HEIGHT-500, ID.Block, handler));
      //  handler.addObject(new Block(WIDTH/2 +150,HEIGHT-450, ID.Block, handler));

      /*  for (int i = 0; i<WIDTH; i+=500){
            handler.addObject(new Block(i,HEIGHT- 300, ID.Block, handler));
        }

        handler.addObject(new Player(500,0,ID.Player, handler)); */

        setFocusable(true);
        new Window(WIDTH, HEIGHT, "Spacer", this);


    }

    public synchronized void start(){
                             /* pri startu aplikacije, inicijalizira se thread sa targetom na ovu klasu i pokrece se */

        thread = new Thread(this);
        thread.start();
        running = true;
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

    private void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx <h; xx++){
            for(int yy = 0; yy <w; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Block(xx*32,yy*32, ID.Collidable));
                }
                if(red == 0 && green == 0 && blue == 255){
                    handler.addObject(new Player(xx*32,yy*32, ID.Player, handler));
                }
                if(red == 0 && green == 255 && blue == 0){
                    handler.addObject(new BlockSide(xx*32,yy*32, ID.CollidableSide));
                }
            }
        }

    }

    private void tick(){

        handler.tick();

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
        g.drawImage(bkg,-150,0,this);


        g2d.translate(camera.getX(),camera.getY());

        handler.render(g);

        g2d.translate(-camera.getX(),-camera.getY());


        g.setColor(Color.WHITE);
        g.fillRect(0,HEIGHT-32,WIDTH,32);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {

                                                       /* kreiranje novog game objekta */
        new Game();


    }
}
