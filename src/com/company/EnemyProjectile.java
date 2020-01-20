package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class EnemyProjectile extends GameObject implements ImageObserver {

    private Handler handler;

    private short direction;

    public EnemyProjectile(float x, float y, ID id, Handler handler, short direction) {
        super(x, y, id);
        this.handler = handler;
        this.direction = direction;

        if(direction ==1){
            setVelX(10.0f);
        }else setVelX(-10.0f);

    }

    public  Image img;
    private ImageIcon iconRight = new ImageIcon("res/projectileRight.gif");
    private ImageIcon iconLeft = new ImageIcon("res/projectileLeft.gif");


    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
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


    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Collidable )

                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            if(tempObject.id == ID.Player){
                if(getBounds().intersects(tempObject.dmgBounds()) && !KeyInput.dash){
                    handler.removeObject(this);
                }
                else if(this.getX() > tempObject.getX() +600 ||
                        this.getX() < tempObject.getX() - 600){
                    handler.removeObject(this);

                }
            }
        }
    }

  /* private void outOfBounds(){

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Player){
                if(this.getX() > tempObject.getX() +600 ||
                        this.getX() < tempObject.getX() - 600){
                    handler.removeObject(this);

                }
            }
        }
    } */

    @Override
    public void tick() {
        collision();
       // outOfBounds();
        x += velX;
    }

    @Override
    public void render(Graphics g) {

       // g.setColor(Color.WHITE);
        g. fillOval((int)x,(int)y,16,16);

       // g.drawImage(img, (int)x,(int)y, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;

    }
}
