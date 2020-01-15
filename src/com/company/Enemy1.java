package com.company;

import java.awt.*;

public class Enemy1 extends GameObject {

    private float hp;
    private Handler handler;
    private short dir = 1;
    private boolean hit;

    private short hitTimer = 10;

    public Enemy1(float x, float y, ID id, int hp,Handler handler) {
        super(x, y, id);
        this.hp = hp;
        this.handler = handler;

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
            this.hp -= StatsController.dmg;

        }else if (KeyInput.atk3) {
            this.hp -= StatsController.dmg;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,64);
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

        if(this.hp <= 0){
            handler.removeObject(this);
        }

        collision();

        if(hitTimer == 0){
            handler.addObject(new DamageText(x,y,ID.Text,handler,0.07f));
           // hit = false;
        }
        this.hp = Game.clamp((int)hp, 0,35);
        hitTimer++;

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.black);
        g.fillRect((int)x,(int)y,32,64);

        g.fillRect((int)x,(int)y-15,(int)this.hp,10);

    }
}
