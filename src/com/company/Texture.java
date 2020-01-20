package com.company;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs;
    SpriteSheet patk1;
    SpriteSheet patk2;
    SpriteSheet patk3;
    SpriteSheet phurt;
    SpriteSheet enemy1;

    private BufferedImage blockSheet = null;
    private BufferedImage atk1Sheet = null;
    private BufferedImage atk2Sheet = null;
    private BufferedImage atk3Sheet = null;
    private BufferedImage hurtSheet = null;
    private BufferedImage enemy1Sheet = null;

    public BufferedImage[] block = new BufferedImage[20];
    public BufferedImage[] atkLeft1 = new BufferedImage[10];
    public BufferedImage[] atkRight1 = new BufferedImage[10];
    public BufferedImage[] atkLeft2 = new BufferedImage[10];
    public BufferedImage[] atkRight2 = new BufferedImage[10];
    public BufferedImage[] atkLeft3 = new BufferedImage[13];
    public BufferedImage[] atkRight3 = new BufferedImage[13];
    public BufferedImage[] hurtLeft = new BufferedImage[4];
    public BufferedImage[] hurtRight = new BufferedImage[4];

    public BufferedImage[] enemy1IdleR = new BufferedImage[1];
    public BufferedImage[] enemy1IdleL = new BufferedImage[1];
    public BufferedImage[] enemy1walkR = new BufferedImage[4];
    public BufferedImage[] enemy1walkL = new BufferedImage[4];
    public BufferedImage[] enemy1ShootR = new BufferedImage[3];
    public BufferedImage[] enemy1ShootL = new BufferedImage[3];


    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            blockSheet = loader.loadImage("/blockSheet.png");
            atk1Sheet = loader.loadImage("/atk1Sprites.png");
            atk2Sheet = loader.loadImage("/atk2sprites.png");
            atk3Sheet = loader.loadImage("/atk3sprites.png");
            hurtSheet = loader.loadImage("/hurtSprites.png");
            enemy1Sheet = loader.loadImage("/enemy1sprites.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        bs = new SpriteSheet(blockSheet);
        patk1 = new SpriteSheet(atk1Sheet);
        patk2 = new SpriteSheet(atk2Sheet);
        patk3 = new SpriteSheet(atk3Sheet);
        phurt = new SpriteSheet(hurtSheet);
        enemy1 = new SpriteSheet(enemy1Sheet);

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

        // Atk2 animation left

        atkLeft2[0] = patk2.grabImage(1,1, 75, 48);
        atkLeft2[1] = patk2.grabImage(2,1, 75, 48);
        atkLeft2[2] = patk2.grabImage(3,1, 75, 48);
        atkLeft2[3] = patk2.grabImage(4,1, 75, 48);
        atkLeft2[4] = patk2.grabImage(5,1, 75, 48);
        atkLeft2[5] = patk2.grabImage(6,1, 75, 48);
        atkLeft2[6] = patk2.grabImage(7,1, 75, 48);
        atkLeft2[7] = patk2.grabImage(8,1, 75, 48);
        atkLeft2[8] = patk2.grabImage(9,1, 75, 48);
        atkLeft2[9] = patk2.grabImage(10,1, 75, 48);

        //Atk2 animation right
        atkRight2[0] = patk2.grabImage(10,2,75,48);

        atkRight2[1] = patk2.grabImage(9,2,75,48);
        atkRight2[2] = patk2.grabImage(8,2,75,48);
        atkRight2[3] = patk2.grabImage(7,2,75,48);
        atkRight2[4] = patk2.grabImage(6,2,75,48);
        atkRight2[5] = patk2.grabImage(5,2,75,48);
        atkRight2[6] = patk2.grabImage(4,2,75,48);
        atkRight2[7] = patk2.grabImage(3,2,75,48);
        atkRight2[8] = patk2.grabImage(2,2,75,48);
        atkRight2[9] = patk2.grabImage(1,2,75,48);

        //Atk3 animation left

        atkLeft3[0] = patk3.grabImage(1,1,80,59);
        atkLeft3[1] = patk3.grabImage(2,1,80,59);
        atkLeft3[2] = patk3.grabImage(3,1,80,59);
        atkLeft3[3] = patk3.grabImage(4,1,80,59);
        atkLeft3[4] = patk3.grabImage(5,1,80,59);
        atkLeft3[5] = patk3.grabImage(6,1,80,59);
        atkLeft3[6] = patk3.grabImage(7,1,80,59);
        atkLeft3[7] = patk3.grabImage(8,1,80,59);
        atkLeft3[8] = patk3.grabImage(9,1,80,59);
        atkLeft3[9] = patk3.grabImage(10,1,80,59);
        atkLeft3[10] = patk3.grabImage(11,1,80,59);
        atkLeft3[11] = patk3.grabImage(12,1,80,59);
        atkLeft3[12] = patk3.grabImage(13,1,80,59);

        //Atk3 animation right

        atkRight3[0] = patk3.grabImage(13,2,80,59);
        atkRight3[1] = patk3.grabImage(12,2,80,59);
        atkRight3[2] = patk3.grabImage(11,2,80,59);
        atkRight3[3] = patk3.grabImage(10,2,80,59);
        atkRight3[4] = patk3.grabImage(9,2,80,59);
        atkRight3[5] = patk3.grabImage(8,2,80,59);
        atkRight3[6] = patk3.grabImage(7,2,80,59);
        atkRight3[7] = patk3.grabImage(6,2,80,59);
        atkRight3[8] = patk3.grabImage(5,2,80,59);
        atkRight3[9] = patk3.grabImage(4,2,80,59);
        atkRight3[10] = patk3.grabImage(3,2,80,59);
        atkRight3[11] = patk3.grabImage(2,2,80,59);
        atkRight3[12] = patk3.grabImage(1,2,80,59);

        //Hurt left

        hurtLeft[0] = phurt.grabImage(1,1,35,42);
        hurtLeft[1] = phurt.grabImage(2,1,35,42);
        hurtLeft[2] = phurt.grabImage(1,1,35,42);
        hurtLeft[3] = phurt.grabImage(2,1,35,42);

        //Hurt right

        hurtRight[0] = phurt.grabImage(1,2,35,42);
        hurtRight[1] = phurt.grabImage(2,2,35,42);
        hurtRight[2] = phurt.grabImage(1,2,35,42);
        hurtRight[3] = phurt.grabImage(2,2,35,42);

        //Enemy 1

        enemy1IdleR[0] = enemy1.grabImage(1,1,35,41);
        enemy1IdleL[0] = enemy1.grabImage(5,3,35,41);

        enemy1walkR[0] = enemy1.grabImage(2,1,35,41);
        enemy1walkR[1] = enemy1.grabImage(3,1,35,41);
        enemy1walkR[2] = enemy1.grabImage(4,1,35,41);
        enemy1walkR[3] = enemy1.grabImage(5,1,35,41);

        enemy1walkL[0] = enemy1.grabImage(4,3,35,41);
        enemy1walkL[1] = enemy1.grabImage(3,3,35,41);
        enemy1walkL[2] = enemy1.grabImage(2,3,35,41);
        enemy1walkL[3] = enemy1.grabImage(1,3,35,41);

        enemy1ShootR[0] = enemy1.grabImage(1,2,36,41);
        enemy1ShootR[1] = enemy1.grabImage(2,2,36,41);
        enemy1ShootR[2] = enemy1.grabImage(3,2,36,41);

        enemy1ShootL[0] = enemy1.grabImage(5,4,37,41);
        enemy1ShootL[1] = enemy1.grabImage(4,4,37,41);
        enemy1ShootL[2] = enemy1.grabImage(3,4,37,41);



    }
}
