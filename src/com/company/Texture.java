package com.company;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs;
    SpriteSheet patk1;
    private BufferedImage blockSheet = null;
    private BufferedImage atk1Sheet = null;

    public BufferedImage[] block = new BufferedImage[20];
    public BufferedImage[] atkLeft1 = new BufferedImage[10];
    public BufferedImage[] atkRight1 = new BufferedImage[10];
    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            blockSheet = loader.loadImage("/blockSheet.png");
            atk1Sheet = loader.loadImage("/atk1Sprites.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        bs = new SpriteSheet(blockSheet);
        patk1 = new SpriteSheet(atk1Sheet);

        getTextures();
    }

    private void getTextures(){

       // for (int i=1; i<11; i++) block[i-1]=bs.grabImage(i, 1,32,32);

        block[0] = bs.grabImage(1,1,32,32);     // top left
        block[1] = bs.grabImage(2,1,32,32);     // top
        block[2] = bs.grabImage(3,1,32,32);     // top right
        block[3] = bs.grabImage(4,1,32,32);     // inner left
        block[4] = bs.grabImage(5,1,32,32);     // inner right
        block[5] = bs.grabImage(6,1,32,32);     // left
        block[6] = bs.grabImage(7,1,32,32);     // right
        block[7] = bs.grabImage(8,1,32,32);     // top left
        block[8] = bs.grabImage(9,1,32,32);     // mid
        block[9] = bs.grabImage(10,1,32,32);    // platform left
        block[10] = bs.grabImage(11,1,32,32);   // platform right

        //Atk animation left

        atkLeft1[0] = patk1.grabImage(1,1,74,46);
        atkLeft1[1] = patk1.grabImage(2,1,74,46);
        atkLeft1[2] = patk1.grabImage(3,1,74,46);
        atkLeft1[3] = patk1.grabImage(4,1,74,46);
        atkLeft1[4] = patk1.grabImage(5,1,74,46);
        atkLeft1[5] = patk1.grabImage(6,1,74,46);
        atkLeft1[6] = patk1.grabImage(7,1,74,46);
        atkLeft1[7] = patk1.grabImage(8,1,74,46);
        atkLeft1[8] = patk1.grabImage(9,1,74,46);
        atkLeft1[9] = patk1.grabImage(10,1,74,46);

        //Atk animation right
        atkRight1[0] = patk1.grabImage(10,2,74,46);
        atkRight1[1] = patk1.grabImage(9,2,74,46);
        atkRight1[2] = patk1.grabImage(8,2,74,46);
        atkRight1[3] = patk1.grabImage(7,2,74,46);
        atkRight1[4] = patk1.grabImage(6,2,74,46);
        atkRight1[5] = patk1.grabImage(5,2,74,46);
        atkRight1[6] = patk1.grabImage(4,2,74,46);
        atkRight1[7] = patk1.grabImage(3,2,74,46);
        atkRight1[8] = patk1.grabImage(2,2,74,46);
        atkRight1[9] = patk1.grabImage(1,2,74,46);


    }
}
