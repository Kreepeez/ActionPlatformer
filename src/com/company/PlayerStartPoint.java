package com.company;

import java.awt.*;

public class PlayerStartPoint extends GameObject{

    public PlayerStartPoint(float x, float y, ID id) {
        super(x, y, id);
    }

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

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

       // g.setColor(Color.green);
      //  g.fillRect((int)x,(int)y,32,64);

    }
}
