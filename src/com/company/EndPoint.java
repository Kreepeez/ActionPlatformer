package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class EndPoint extends GameObject implements ImageObserver {

   // private Handler handler;
    public EndPoint(float x, float y, ID id) {
        super(x, y, id);
      //  this.handler = handler;

    }

    public  Image img;
    private ImageIcon iconRight = new ImageIcon("res/projectileRight.gif");
    private ImageIcon iconLeft = new ImageIcon("res/projectileLeft.gif");


    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,64,64);
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
    public void tick() {


    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g. fillRect((int)x,(int)y,64,64);

      //  g.drawImage(img, (int)x,(int)y, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;

    }
}
