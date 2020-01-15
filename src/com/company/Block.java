package com.company;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Block extends GameObject implements ImageObserver {

    private int type;
    Texture texture = Game.getInstance();

    public Block(float x, float y, int type, ID id ) {
        super(x, y, id);

        this.type = type;
       // this.handler = handler;
    }

    public void tick() {

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x ,(int)y,32, 32);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return null;
    }

    @Override
    public Rectangle getBoundsRight() {
        return null;
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    @Override
    public Rectangle atkBounds() {
        return null;
    }

    @Override
    public Rectangle dmgBounds() {
        return null;
    }


    public void render(Graphics g) {


        if(type >= 0 && type <=10) g.drawImage(texture.block[type], (int)x,(int)y,this);
        /*
        if(type == 0) g.drawImage(texture.block[type], (int)x,(int)y,this);     //top left
        if(type == 1) g.drawImage(texture.block[type], (int)x,(int)y,this);     //top
        if(type == 2) g.drawImage(texture.block[type], (int)x,(int)y,this);     //top right
        if(type == 3) g.drawImage(texture.block[type], (int)x,(int)y,this);     //inner left
        if(type == 4) g.drawImage(texture.block[type], (int)x,(int)y,this);     //inner right
        if(type == 5) g.drawImage(texture.block[type], (int)x,(int)y,this);     //left
        if(type == 6) g.drawImage(texture.block[type], (int)x,(int)y,this);     //right
        if(type == 7) g.drawImage(texture.block[type], (int)x,(int)y,this);     //mid
        if(type == 8) g.drawImage(texture.block[type], (int)x,(int)y,this);     //platform left
        if(type == 9) g.drawImage(texture.block[type], (int)x,(int)y,this);     //platform mid
        if(type == 10) g.drawImage(texture.block[type], (int)x,(int)y,this);     //platform right*/
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
