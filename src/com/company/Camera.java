package com.company;

public class Camera {

    private float x,y;

    public float getX() {
        return x;
    }

   /* public void setX(float x) {
        this.x = x;
    } */

    public float getY() {
        return y;
    }

   /* public void setY(float y) {
        this.y = y;
    } */

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player){

        x = -player.getX()-50 + Game.WIDTH/2;
        y = -player.getY()-50 + Game.HEIGHT/2;
    }
}
