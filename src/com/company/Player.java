package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Player extends GameObject implements ImageObserver {

    private Handler handler;

    private ImageIcon iconWalkRight = new ImageIcon("src/walkRight.gif");
    private ImageIcon iconWalkLeft = new ImageIcon("src/walkLeft.gif");
    private ImageIcon iconStandRight = new ImageIcon("src/standRight.png");
    private ImageIcon iconStandLeft = new ImageIcon("src/standLeft.png");
    private ImageIcon iconJumpRightUp = new ImageIcon("src/jumpRightUp.gif");
    private ImageIcon iconJumpLeftUp = new ImageIcon("src/jumpLeftUp.gif");
    private ImageIcon iconJumpRightDown = new ImageIcon("src/jumpRightDown.gif");
    private ImageIcon iconJumpLeftDown = new ImageIcon("src/jumpLeftDown.gif");
    private ImageIcon iconDashRight = new ImageIcon("src/dashRight.gif");
    private ImageIcon iconDashLeft = new ImageIcon("src/dashLeft.gif");
    private ImageIcon iconShootRight = new ImageIcon("src/shootRight.gif");
    private ImageIcon iconShootLeft = new ImageIcon("src/shootLeft.gif");


    public Image img;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public static boolean fall = true;

    public static int dashTimer = 10;

    public static int dashCD = 0;

    public static int shootTimer = 0;

    public Rectangle getBounds() {

        return new Rectangle((int)getX()+27,(int)getY()+73,20, 13);
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

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.Collidable) {
                if (getBounds().intersects(tempObject.getBounds())) {

                    y = tempObject.getY() - 85;

                    velY = 0;

                    KeyInput.jumped = false;
                    fall = false;

                }else{
                    fall = true;

                }
            }

        }
    }
    private void collisionSide(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.CollidableSide){
                if(getBounds().intersects(tempObject.getBoundsLeft())){

                    if(velX > 0){

                        velX = 0;
                        x = tempObject.getX() -1;

                    }else if(velX < 0){

                    }

                    velX = 0;

                }
            }
        }
    }


    public void tick() {

        if(velX <0 || velX >0){
            collisionSide();
        }

        if(dashCD < 100){
            dashCD++;
        }
        if(dashCD >= 25){
            KeyInput.dash = false;
        }

        if(dashTimer >0 ) {
            dashTimer--;
        }

        x += velX;
        y += velY;

        if(fall || KeyInput.jumped){
            velY+=0.5f;
            KeyInput.jumped = true;
        }
        if(velY>0){
        collision();
        }

        if(velX > 5.0f && !KeyInput.dash && !KeyInput.jumped){
            velX = 5.0f;
        }else if(velX <-5.0f && !KeyInput.dash && !KeyInput.jumped){
            velX = -5.0f;
        }

        if(KeyInput.dash) {
            handler.addObject(new Trail(getX(), getY(), ID.Trail, handler, 0.07f));
        }
        if(shootTimer<15 && KeyInput.shoot){
            //KeyInput.shoot = false;
            shootTimer++;
        }

        if(shootTimer == 15){
            shootTimer = 0;
            if(KeyInput.dir == 1){
                handler.addObject(new Projectile(getX()+65, getY()+20,ID.Projectile, handler));
            }else handler.addObject(new Projectile(getX()-15, getY()+20,ID.Projectile, handler));
        }

    }


    public void render(Graphics g) {


        if (getVelX()> 0 && !KeyInput.jumped){
            if(KeyInput.dash){
                img = iconDashRight.getImage();
            }else
            img = iconWalkRight.getImage();
        }else if (getVelX()< 0 && !KeyInput.jumped){
            if(KeyInput.dash){
                img = iconDashLeft.getImage();
            }else
            img = iconWalkLeft.getImage();
        }else if(KeyInput.shoot && KeyInput.dir == 1){
            img = iconShootRight.getImage();
        }else if(KeyInput.shoot && KeyInput.dir == 0){
            img = iconShootLeft.getImage();
        }

        else if(getVelX() == 0 && getVelY() == 0 && KeyInput.dir == 1 && !KeyInput.jumped){
            img = iconStandRight.getImage();
        }else if(getVelX() == 0 && getVelY() == 0 && KeyInput.dir == 0&& !KeyInput.jumped){
            img = iconStandLeft.getImage();
        }else if(KeyInput.jumped && KeyInput.dir == 1 && velY<0){
            img = iconJumpRightUp.getImage();
        }else if(KeyInput.jumped && KeyInput.dir == 0 && velY<0){
            img = iconJumpLeftUp.getImage();
        }else if(KeyInput.jumped && KeyInput.dir == 1 && velY>0){
            img = iconJumpRightDown.getImage();
        }else if(KeyInput.jumped && KeyInput.dir == 0 && velY>0){
            img = iconJumpLeftDown.getImage();
        }
       // g.fillRect((int)getX(),(int)getY(),200,200);
        g.drawImage(img,(int)getX(),(int)getY(),this);

        g.setColor(Color.WHITE);
        g.drawRect((int)getX()+27,(int)getY()+73,20, 13);
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;
    }
}
