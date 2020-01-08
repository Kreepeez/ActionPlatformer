package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player extends GameObject implements ImageObserver {

    private Handler handler;

    private ImageIcon iconWalkRight = new ImageIcon("res/walkRight.gif");
    private ImageIcon iconWalkLeft = new ImageIcon("res/walkLeft.gif");
    private ImageIcon iconStandRight = new ImageIcon("res/standRight.png");
    private ImageIcon iconStandLeft = new ImageIcon("res/standLeft.png");
    private ImageIcon iconJumpRightUp = new ImageIcon("res/jumpRightUp.gif");
    private ImageIcon iconJumpLeftUp = new ImageIcon("res/jumpLeftUp.gif");
    private ImageIcon iconJumpRightDown = new ImageIcon("res/jumpRightDown.gif");
    private ImageIcon iconJumpLeftDown = new ImageIcon("res/jumpLeftDown.gif");
    private ImageIcon iconDashRight = new ImageIcon("res/dashRight.gif");
    private ImageIcon iconDashLeft = new ImageIcon("res/dashLeft.gif");
    private ImageIcon iconShootRight = new ImageIcon("res/shootRight.gif");
    private ImageIcon iconShootLeft = new ImageIcon("res/shootLeft.gif");

    private Image img;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public static boolean fall = true;

    public static int dashTimer = 10;

    public static int dashCD = 0;

    public static int shootTimer = 0;

    public Rectangle getBounds() {

        return new Rectangle((int)getX()+15,(int)getY()+73,45, 13);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)getX()+10,(int)getY()+10, 30, 60);
    }

    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int)getX()+40,(int)getY()+10, 30, 60);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int)getX()+20,(int)getY(), 40,10);
    }

    private void collisionBottom() {
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
                if(getBoundsRight().intersects(tempObject.getBounds()) ||
                getBoundsLeft().intersects(tempObject.getBounds())){
                   // velX = 0;
                }
            }

        }
    }
    private void collisionSide(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);


            if(tempObject.id == ID.Collidable){

                if(getBoundsRight().intersects(tempObject.getBounds()) ||
                getBoundsRight().intersects(tempObject.getBounds())){

                    if(velX >= 0) {
                        x = tempObject.getX() - 70;
                      //  velX = 0;
                    }

                }
                if(getBoundsLeft().intersects(tempObject.getBounds()) ||
                getBoundsLeft().intersects(tempObject.getBounds())){
                    if(velX <= 0) {
                        x = tempObject.getX() + 25;
                      //  velX = 0;
                    }
                }
            }
        }
    }
    private void collisionTop(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Collidable){

                if(getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                }
            }
        }
    }


    public void tick() {

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
        if(velX > 0) Game.x--;
        else if(velX < 0) Game.x++;


        if(fall || KeyInput.jumped){
            if(velY < 15) velY += 0.5f;

            KeyInput.jumped = true;
        }

        collisionSide();


        if (velY<0){
            collisionTop();
        }
        else collisionBottom();


        if(velX > 5.0f && !KeyInput.dash && !KeyInput.jumped){
            velX = 5.0f;
        }else if(velX <-5.0f && !KeyInput.dash && !KeyInput.jumped){
            velX = -5.0f;
        }

        if(KeyInput.dash) {
            handler.addObject(new Trail(getX(), getY(), ID.Trail, handler, 0.15f));
        }
        if(shootTimer<15 && KeyInput.shoot ){
            //KeyInput.shoot = false;
            shootTimer++;
        }

        if(shootTimer == 15 ){
            shootTimer = 0;
            if(KeyInput.dir == 1){
                handler.addObject(new Projectile(getX()+65, getY()+20,ID.Projectile, handler));
            }else handler.addObject(new Projectile(getX()-15, getY()+20,ID.Projectile, handler));
        }
    }

    public void render(Graphics g) {


        if(KeyInput.dir == 1){

            if (getVelX()> 0 && !KeyInput.jumped){
                if(KeyInput.dash){
                    img = iconDashRight.getImage();

                }else
                    img = iconWalkRight.getImage();

            }
            if(KeyInput.shoot){
                img = iconShootRight.getImage();
            }else
            if(getVelX() == 0 && getVelY() == 0){
                img = iconStandRight.getImage();
            }
            if(KeyInput.jumped && velY<0){
                img = iconJumpRightUp.getImage();
            }
            if(KeyInput.jumped && velY>0){
                img = iconJumpRightDown.getImage();
            }

        }
        else if(KeyInput.dir == 0){

            if (getVelX()< 0 && !KeyInput.jumped){
                if(KeyInput.dash){
                    img = iconDashLeft.getImage();

                }else
                    img = iconWalkLeft.getImage();


            }
            if(KeyInput.shoot ){
                img = iconShootLeft.getImage();
            }else
            if(getVelX() == 0 && getVelY() == 0){
                img = iconStandLeft.getImage();
            }
            if(KeyInput.jumped  && velY<0){
                img = iconJumpLeftUp.getImage();
            }
            if(KeyInput.jumped  && velY>0){
                img = iconJumpLeftDown.getImage();
            }
        }

        g.drawImage(img,(int)getX(),(int)getY(),this);

       //  g.setColor(Color.WHITE);
       //  g.drawRect((int)getX()+15,(int)getY()+73,45, 13); //bottom
       //  g.drawRect((int)getX()+20,(int)getY(), 40,10); //top
       //  g.drawRect((int)getX()+10,(int)getY()+10, 30, 60); //left
       //  g.drawRect((int)getX()+40,(int)getY()+10, 30, 60); //right
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;
    }
}
