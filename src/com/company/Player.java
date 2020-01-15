package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player extends GameObject implements ImageObserver {

    private Handler handler;

    private Animation playerAttack1L;
    private Animation playerAttack1R;
    private Animation playerAttack2L;
    private Animation playerAttack2R;
    private Animation playerAttack3L;
    private Animation playerAttack3R;

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

    Texture tex = Game.getInstance();

    private Image img;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        playerAttack1L = new Animation(1, tex.atkLeft1[0], tex.atkLeft1[1], tex.atkLeft1[2], tex.atkLeft1[3]
                , tex.atkLeft1[4], tex.atkLeft1[5], tex.atkLeft1[6], tex.atkLeft1[7], tex.atkLeft1[8], tex.atkLeft1[9]);

        playerAttack1R = new Animation(1, tex.atkRight1[0], tex.atkRight1[1], tex.atkRight1[2], tex.atkRight1[3]
                , tex.atkRight1[4], tex.atkRight1[5], tex.atkRight1[6], tex.atkRight1[7], tex.atkRight1[8], tex.atkRight1[9]);

        playerAttack2L = new Animation(1, tex.atkLeft2[0], tex.atkLeft2[1], tex.atkLeft2[2], tex.atkLeft2[3]
                , tex.atkLeft2[4], tex.atkLeft2[5], tex.atkLeft2[6], tex.atkLeft2[7], tex.atkLeft2[8], tex.atkLeft2[9]);

        playerAttack2R = new Animation(1, tex.atkRight2[0], tex.atkRight2[1], tex.atkRight2[2], tex.atkRight2[3]
                , tex.atkRight2[4], tex.atkRight2[5], tex.atkRight2[6], tex.atkRight2[7], tex.atkRight2[8], tex.atkRight2[9]);

        playerAttack3L = new Animation(1, tex.atkLeft3[0], tex.atkLeft3[1], tex.atkLeft3[2], tex.atkLeft3[3]
                , tex.atkLeft3[4], tex.atkLeft3[5], tex.atkLeft3[6], tex.atkLeft3[7], tex.atkLeft3[8], tex.atkLeft3[9]
                , tex.atkLeft3[10], tex.atkLeft3[11], tex.atkLeft3[12]);

        playerAttack3R = new Animation(1, tex.atkRight3[0], tex.atkRight3[1], tex.atkRight3[2], tex.atkRight3[3]
                , tex.atkRight3[4], tex.atkRight3[5], tex.atkRight3[6], tex.atkRight3[7], tex.atkRight3[8], tex.atkRight3[9]
                , tex.atkRight3[10], tex.atkRight3[11], tex.atkRight3[12]);

        this.handler = handler;
    }

    public static boolean fall = true;

    public static short dashTimer = 10;

    public static short dashCD = 0;

    public static short shootTimer = 0;

    public static boolean canAtkNext = false;

    public static boolean walk = false;

    private short hitTimer = 0;

    private Rectangle rect = new Rectangle(0,0,0,0);

    //public static int atkTimer = 0;

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

    @Override
    public Rectangle atkBounds() {

       if(KeyInput.dir == 1) {

            if(playerAttack1R.getCount() == 4 || playerAttack2R.getCount() == 4){
                rect = new Rectangle((int)getX()+55, (int)getY(), 100,80);
                StatsController.dmg = 10;
            }
            else if(playerAttack3R.getCount() == 7) {
                rect = new Rectangle((int) getX() + 55, (int) getY() - 20, 110, 100);
                StatsController.dmg = 15;
            }
            else rect = new Rectangle(0,0,0,0);
            }
        else if(KeyInput.dir == 0){
            if(playerAttack1L.getCount() == 4 || playerAttack2L.getCount() == 4) {
                rect = new Rectangle((int) getX() - 80, (int) getY(), 100, 80);
                StatsController.dmg = 10;
            }
            else if(playerAttack3L.getCount()==7){
                rect = new Rectangle((int)getX()-95, (int)getY()-20, 110,100);
                StatsController.dmg = 15;
            }
            else rect = new Rectangle(0,0,0,0);
            }else rect = new Rectangle(0,0,0,0);
        return rect;
    }

    @Override
    public Rectangle dmgBounds(){
        return new Rectangle((int)getX()+25, (int)getY(),25, 80);
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
            }else if(tempObject.id == ID.EndPoint){
                //switch level
                if(getBounds().intersects(tempObject.getBounds())){
                    Game.lvl++;
                    handler.switchLevel();
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

    private void dmgCollision(){

        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Enemy){
                if(dmgBounds().intersects(tempObject.getBounds()) && hitTimer > 20 && StatsController.playerHP >0) {
                    StatsController.playerHP -= 10;
                    hitTimer = 0;
                    handler.addObject(new PlayerDamageText((int)x,(int)y,ID.Text,handler,0.07f, 10));
                }
            }
        }

    }

    public void tick() {

        hitTimer++;
        dmgCollision();

        if(dashCD < 100){
            dashCD++;
        }
        if(dashCD >= 25){
            KeyInput.dash = false;
        }

        if(dashTimer >0 ) {
            dashTimer--;
        }
        if(playerAttack1R.getCount() >7)canAtkNext = true;
        else if(playerAttack1L.getCount() >7)canAtkNext = true;
        else if(playerAttack2L.getCount() >7)canAtkNext = true;
        else if(playerAttack2R.getCount() >7)canAtkNext = true;

        if(playerAttack1L.getCount() == 10){
            playerAttack1L.setCount(0);
            KeyInput.atk1 = false;
        }else if(playerAttack1R.getCount() == 10){
            playerAttack1R.setCount(0);
            KeyInput.atk1 = false;
        }
        else if(playerAttack2L.getCount() == 10){
            playerAttack2L.setCount(0);
            KeyInput.atk2 = false;
        }else if(playerAttack2R.getCount() == 10){
            playerAttack2R.setCount(0);
            KeyInput.atk2 = false;
        }else if(playerAttack3L.getCount() == 13){
            playerAttack3L.setCount(0);
            KeyInput.atk3 = false;
        }else if(playerAttack3R.getCount() == 13){
            playerAttack3R.setCount(0);
            KeyInput.atk3 = false;
        }

       /*if(velX > 0) Game.fgx-- ;
        else if(velX < 0) Game.fgx++ ;
        if(velY <0){
            Game.fgy += -velY;
        }else if (velY>0){
            Game.fgy-= velY;
        } */

        x += velX;
        y += velY;

        if(fall || KeyInput.jumped){
            if(velY < 15) velY += 0.5f;
            KeyInput.jumped = true;
        }

        if(walk && !KeyInput.dash && !KeyInput.atk1 && !KeyInput.atk2 && !KeyInput.atk3 ){
            if(KeyInput.dir == 1){
                if(velX<5)
                velX = 5.0f;
            }else if(KeyInput.dir == 0)
                if(velX>-5)
                velX = -5.0f;
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
            handler.addObject(new Trail(getX(), getY(), ID.Trail, handler, 0.18f));
        }
        if(shootTimer<15 && KeyInput.shoot ){
            //KeyInput.shoot = false;
            shootTimer++;
        }

        if(shootTimer == 15 ){
            shootTimer = 0;
            if(KeyInput.dir == 1){
                handler.addObject(new Projectile(getX()+85, getY()+22,ID.Projectile, handler));
            }else handler.addObject(new Projectile(getX()-35, getY()+22,ID.Projectile, handler));
        }

       // if(atkTimer == 10 && KeyInput.atk1)
           // KeyInput.atk1 = false;
       // if(KeyInput.atk1){
      //      velX = 0;
      //      atkTimer++;
      //  }
        if(KeyInput.atk1) {
            playerAttack1L.runAnimation();
            playerAttack1R.runAnimation();
        }else if(KeyInput.atk2){
            playerAttack2R.runAnimation();
            playerAttack2L.runAnimation();
        }else if(KeyInput.atk3){
            playerAttack3L.runAnimation();
            playerAttack3R.runAnimation();
        }else{
            playerAttack3R.setCount(0);
            playerAttack2R.setCount(0);
            playerAttack1R.setCount(0);
            playerAttack2L.setCount(0);
            playerAttack3L.setCount(0);
            playerAttack1L.setCount(0);
        }
    }


    public void render(Graphics g) {

        switch (KeyInput.dir){
            case 1:{
                if (getVelX()> 0 && !KeyInput.jumped){
                    if(KeyInput.dash){
                        img = iconDashRight.getImage();
                    }else
                        img = iconWalkRight.getImage();
                }
                if(KeyInput.shoot){
                    img = iconShootRight.getImage();
                }else
                if(getVelX() == 0 && !KeyInput.jumped){
                    img = iconStandRight.getImage();
                }
                if(KeyInput.jumped && velY<0){
                    img = iconJumpRightUp.getImage();
                }
                if(KeyInput.jumped && velY>0){
                    img = iconJumpRightDown.getImage();
                }
                break;
            }
            case 0:{
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
                break;
            }
        }


        if(KeyInput.atk1){

            if(KeyInput.dir==0) playerAttack1L.drawAnimation(g,(int)x-80,(int)y-7,155,94);
            else  playerAttack1R.drawAnimation(g,(int)x, (int) y-8,155,94);
        }
        else if(KeyInput.atk2){
            if(KeyInput.dir ==0) playerAttack2L.drawAnimation(g,(int)x-80,(int)y-2 , 155,95);
            else playerAttack2R.drawAnimation(g,(int)x,(int)y-2, 155,95);
        }
        else if(KeyInput.atk3){
            if(KeyInput.dir ==0) playerAttack3L.drawAnimation(g,(int)x-84,(int)y-23,165,110);
            else playerAttack3R.drawAnimation(g,(int)x-2,(int)y-23,165,110);
        }


        else if(KeyInput.shoot){
            g.drawImage(img,(int)getX(),(int)getY()+2,this);
        }

        else g.drawImage(img,(int)getX(),(int)getY(),this);

       //  g.setColor(Color.WHITE);
       //  g.drawRect((int)getX()+15,(int)getY()+73,45, 13); //bottom
       //  g.drawRect((int)getX()+20,(int)getY(), 40,10); //top
       //  g.drawRect((int)getX()+10,(int)getY()+10, 30, 60); //left
       //  g.drawRect((int)getX()+40,(int)getY()+10, 30, 60); //right

       //  g.drawRect((int)getX()+25, (int)getY(),25, 80);  //dmg
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.img = img;
        return true;
    }
}
