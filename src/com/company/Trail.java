package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Trail extends GameObject implements ImageObserver {

    private Handler handler;
    private float alpha = 1;
    private ImageIcon trailDashRight = new ImageIcon("res/dashTrailRight.gif");
    private ImageIcon trailDashLeft = new ImageIcon("res/dashTrailLeft.gif");
    private ImageIcon trailUpRight = new ImageIcon("res/trailUpRight.gif");
    private ImageIcon trailUpLeft = new ImageIcon("res/trailUpLeft.gif");
    private ImageIcon trailDownRight = new ImageIcon("res/trailDownRight.gif");
    private ImageIcon trailDownLeft = new ImageIcon("res/trailDownLeft.gif");
    private Image trail;
    private float life;

    public Trail(float x, float y, ID id,Handler handler,float life) {
        super(x, y, id);
        this.handler = handler;
        this.life = life;
    }


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

    public void tick() {

        for(int i = 0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Player){

                if(KeyInput.dir == 1){
                    if(tempObject.velY < 0){
                        trail = trailUpRight.getImage();
                    }else if(tempObject.velY > 0){
                        trail = trailDownRight.getImage();
                    }else trail = trailDashRight.getImage();
                    }


                    else if(KeyInput.dir == 0){
                        if(tempObject.velY < 0){
                            trail = trailUpLeft.getImage();
                        }else if (tempObject.velY > 0){
                            trail = trailDownLeft.getImage();
                        }else
                            trail = trailDashLeft.getImage();
                    }
            }
        }

        if(alpha>life){
            alpha -= life - 0.001f;
        }else handler.removeObject(this);
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void render(Graphics g) {
        // g.drawImage(trail,(int) getX(),(int)getY(),this);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setComposite(makeTransparent(alpha));

       // if(KeyInput.dir == 1){

       // }
        g.drawImage(trail,(int) getX(),(int)getY(),this);
        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}

