package com.company;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs;
    private BufferedImage blockSheet = null;

    public BufferedImage[] block = new BufferedImage[20];
    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            blockSheet = loader.loadImage("/blockSheet.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        bs = new SpriteSheet(blockSheet);

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

    }
}