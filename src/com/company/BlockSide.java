package com.company;

import com.company.GameObject;
import com.company.ID;

import java.awt.*;

public class BlockSide extends GameObject {
    public BlockSide(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }


    public Rectangle getBoundsLeft() {
        return new Rectangle((int)getX(),(int)getY(),16,32);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)getX()+16,(int)getY(),16,32);
    }

    @Override
    public Rectangle getBoundsBottom() {
        return null;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawRect((int)getX(),(int)getY(),16,32);
        g.drawRect((int)getX()+16,(int)getY(),16,32);

    }
}
