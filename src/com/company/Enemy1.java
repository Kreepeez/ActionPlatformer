package com.company;

import java.awt.*;

public class Enemy1 extends GameObject {

    private float hp;
    private Handler handler;
    private short dir = 1;
    private boolean hit;
    Texture tex = Game.getInstance();

    private boolean shoot = false;

    private boolean shootNow;

    private boolean fall = true;

    private Animation idleR;
    private Animation idleL;
    private Animation walkR;
    private Animation walkL;
    private Animation shootR;
    private Animation shootL;

    private boolean takeDmg;

    private short hitTimer = 10;

    private short shootTimer = 50;

    public Enemy1(float x, float y, ID id, int hp,Handler handler) {
        super(x, y, id);
        this.hp = hp;
        this.handler = handler;

        idleR = new Animation(1, tex.enemy1IdleR[0]);
        idleL = new Animation(1, tex.enemy1IdleL[0]);

        walkR = new Animation(5,tex.enemy1walkR[0],tex.enemy1walkR[1],tex.enemy1walkR[2],tex.enemy1walkR[3]);
        walkL = new Animation(5,tex.enemy1walkL[0],tex.enemy1walkL[1],tex.enemy1walkL[2],tex.enemy1walkL[3]);

        shootR = new Animation(3, tex.enemy1ShootR[0], tex.enemy1ShootR[1], tex.enemy1ShootR[1]
                , tex.enemy1ShootR[1], tex.enemy1ShootR[1], tex.enemy1ShootR[2], tex.enemy1ShootR[1]
                , tex.enemy1ShootR[2]);
        shootL = new Animation(3, tex.enemy1ShootL[0], tex.enemy1ShootL[1], tex.enemy1ShootL[1]
                , tex.enemy1ShootL[1], tex.enemy1ShootL[1], tex.enemy1ShootL[2], tex.enemy1ShootL[1]
                , tex.enemy1ShootL[2]);

    }

    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.id == ID.Player){
                if(getBounds().intersects(tempObject.atkBounds())) {
                    if(hitTimer>9){
                        hit();
                        hitTimer = 0;
                    }
                }
            }else if(tempObject.id == ID.Projectile){
                if(getBounds().intersects(tempObject.getBounds())){
                    if(hitTimer>9){
                        StatsController.dmg = 5;
                        this.hp -= StatsController.dmg;
                        hit();
                        hitTimer = 0;
                    }
                }
            }
        }
    }

    public void hit(){

        if (KeyInput.atk1 || KeyInput.atk2) {
            if(KeyInput.dash)
            this.hp -= StatsController.dmg*2;
            else this.hp -= StatsController.dmg;

        }else if (KeyInput.atk3) {
            this.hp -= StatsController.dmg;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y-15,32,79);
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

    private void checkPlayer(){
        for(int i = 0; i<handler.object.size(); i++){
            if(handler.object.get(i).id == ID.Player) {

                if (handler.object.get(i).x > getX() - 650 && handler.object.get(i).x < x
                        && handler.object.get(i).y > y-250 && handler.object.get(i).y < y+300) {
                    dir = 0;
                    shootTimer++;

                    if(hitTimer > 2)
                    if (shoot) {
                           //  shoot = true;
                        setVelX(0);
                    } else if (shootTimer < 50 && shootTimer > 20) {
                        walkL.runAnimation();
                        if(!dmgCollision()){
                            setVelX(-3.0f);
                        }else setVelX(0);
                    } else setVelX(0);

                } else if (handler.object.get(i).x < getX() + 600 && handler.object.get(i).x > x
                        && handler.object.get(i).y > y-250 && handler.object.get(i).y < y+300) {
                    dir = 1;
                    shootTimer++;


                    if(hitTimer > 2)
                    if (shoot) {
                            //  shoot = true;
                        setVelX(0);
                    } else if (shootTimer < 50 && shootTimer > 20) {
                        walkR.runAnimation();
                        setVelX(3.0f);
                    } else setVelX(0);


                } else setVelX(0);
            }
        }
    }

    private void collisionBottom() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.Collidable) {
                if (getBounds().intersects(tempObject.getBounds())) {

                    y = tempObject.getY() - 63;

                    velY = 0;

                    fall = false;

                } else {
                    fall = true;
                }
            }
        }
    }

    private boolean dmgCollision(){
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).id == ID.Player){
                if(getBounds().intersects(handler.object.get(i).atkBounds())){
                    if(dir == 1){
                        takeDmg = true;
                      //  setVelX(-5.0f);
                    }else if(dir == 0) {
                      takeDmg = true;
                       // setVelY(5.0f);
                    }
                }else takeDmg = false;
            }
        }
        return takeDmg;
    }
  /*  private void collisionSide(){
        for (int i = 0; i < handler.object.size(); i++){


            if(handler.object.get(i).id == ID.Collidable){

                if(getBounds().intersects(handler.object.get(i).getBounds())) {


                    if(velX >= 0) {
                        x = handler.object.get(i).getX() - 70;
                        //  velX = 0;
                    }
                    else if(velX <= 0) {
                        x = handler.object.get(i).getX() + 25;
                        //  velX = 0;
                    }
                }
            }
        }
    } */


  @Override
  public void tick() {

      if(hitTimer < 10){
          if(dir == 1){
              setVelX(-2);
          }else setVelX(2);
      }

      if(shootTimer >95){
          shoot = true;
      }

      checkPlayer();
      dmgCollision();
      collision();

   //   if(velX>0 || velX <0)collisionSide();

     /* if(dmgCollision() ){
          if(dir == 1){
              setVelX(-3.0f);
          }else setVelX(3.0f);
      }  */

      if(velY >0)collisionBottom();

        x += velX;
        y += velY;

      if(fall ){
          if(velY < 15) velY += 0.5f;
      }

      idleR.runAnimation();
      idleL.runAnimation();

      if(shoot){
          if(dir == 1) shootR.runAnimation();
          else shootL.runAnimation();
          if(shootR.getCount() == 8  && hitTimer>9 ){
              handler.addObject(new EnemyProjectile(x+60,y+10,ID.EnemyProjectile, handler, dir));
          }
          else if(shootL.getCount() == 8 && hitTimer >9 ){
              handler.addObject(new EnemyProjectile(x-50,y+10,ID.EnemyProjectile, handler, dir));
          }
      }

   /*   if(shootNow){
          if(dir == 1){
              handler.addObject(new EnemyProjectile(x+60,y+10,ID.EnemyProjectile, handler, dir));
          }else handler.addObject(new EnemyProjectile(x-50,y+10,ID.EnemyProjectile, handler, dir));
      } */

      if(shootR.getCount() == 8 || shootL.getCount() == 8){
          shoot = false;
          shootTimer = 0;
          shootR.setCount(0);
          shootL.setCount(0);
      }

        if(this.hp <= 0){
            handler.removeObject(this);
        }

        collision();

        if(hitTimer == 0){
            handler.addObject(new DamageText(x,y,ID.Text,handler,0.07f));
           // hit = false;
        }
        this.hp = Game.clamp((int)hp, 0,30);
        hitTimer++;



    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.red);
       // g.fillRect((int)x,(int)y-15,32,79);

        g.fillRect((int)x,(int)y-40,(int)this.hp,7);

        if(dir == 1) {
            if(shoot) shootR.drawAnimation(g,(int)x-25,(int)y-27,100,90);
            else if(velX>0) walkR.drawAnimation(g,(int)x-30,(int)y-25,100,90);
            else  idleR.drawAnimation(g,(int)x-30,(int)y-25,100,90);
        }

        else if(dir == 0){
            if(shoot) shootL.drawAnimation(g,(int)x-45,(int)y-27,100,90);
            else if(velX<0) walkL.drawAnimation(g,(int)x-35,(int)y-25,100,90);
            else idleL.drawAnimation(g,(int)x-35,(int)y-25,100,90);

        }

    }
}
