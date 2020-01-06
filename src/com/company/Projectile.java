package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class Projectile extends GameObject implements ImageObserver {

    private Handler handler;
    public Projectile(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        if(KeyInput.dir == 1){
            this.setVelX(17);
        }else if(KeyInput.dir == 0) this.setVelX(-17);

    }

    public  Image img;
    private ImageIcon iconRight = new ImageIcon("src/projectileRight.gif");
    private ImageIcon iconLeft = new ImageIcon("src/projectileLeft.gif");


    @Override
    public Rectangle getBounds() {
        return null;
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
    public Rectangle getBoundsBottom() {
        return null;
    }

    @Override
    public void tick() {

        x += velX;

        if(velX >0){
            img = iconRight.getImage();
        }else if(velX<0) {img = iconLeft.getImage();}


    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.WHITE);
      //  g. fillOval((int)x,(int)y,32,32);

        g.drawImage(img, (int)x,(int)y, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;

    }
}