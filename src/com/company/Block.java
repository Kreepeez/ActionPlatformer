package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Block extends GameObject implements ImageObserver {

    private Handler handler;

    public Block(float x, float y, ID id ) {
        super(x, y, id);

       // this.handler = handler;
    }



    public ImageIcon iconBlock = new ImageIcon("src/block1.png");

    private Image img = iconBlock.getImage();


    public void tick() {

    }

    public Rectangle getBounds() {

        return new Rectangle((int)x,(int)y,32, 10);


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

    public void render(Graphics g) {

        g.drawImage(img,(int)getX(),(int)getY(), this);
      //  g.setColor(Color.WHITE);
       // g.drawRect((int)x,(int)y,32, 10);

    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;
    }
}
