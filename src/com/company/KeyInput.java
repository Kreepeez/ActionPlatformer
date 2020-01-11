package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    private boolean[] keyDown = new boolean[10];

    public static short dir;

    public static boolean jumped = false;

    public static boolean dash = false;

    public static boolean shoot = false;

    public static boolean atk1= false;


    public KeyInput(Handler handler){

        this.handler = handler;

    }


    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId()== ID.Player) {

                if (key == KeyEvent.VK_Z  && !jumped && Player.dashCD >= 40 && Player.dashTimer < 10) {

                    keyDown[3] = true;

                if (dir == 1 && tempObject.velX > 0) {
                        tempObject.setVelX(9.0f);
                        Player.dashCD = 0;
                        dash = true;
                        Player.dashTimer = 10;

                    } if (dir == 0 && tempObject.velX < 0) {
                        tempObject.setVelX(-9.0f);
                        Player.dashCD = 0;
                        dash = true;
                        Player.dashTimer = 10;
                    }

                    keyDown[3] = true;

                }

                if (key == KeyEvent.VK_D) {

                    if(keyDown[4]) shoot = false;

                    if (dash) {
                        tempObject.setVelX(12.0f);
                    } else {
                        tempObject.setVelX(5.0f);
                    }
                    keyDown[0] = true;
                    dir = 1;

                }
                if (key == KeyEvent.VK_A) {
                    if(keyDown[4]) shoot = false;
                    if (dash) {
                        tempObject.setVelX(-12.0f);
                    } else {
                        tempObject.setVelX(-5.0f);
                    }
                    keyDown[1] = true;
                    dir = 0;

                }
                if (key == KeyEvent.VK_G && !jumped) {
                    if(keyDown[4]) shoot = false;
                    tempObject.setVelY(-15);
                    keyDown[2] = true;
                    jumped = true;

                }
                if (key == KeyEvent.VK_T && !jumped && !dash && !shoot) {

                    tempObject.setVelX(0);
                    shoot = true;
                    keyDown[4] = true;

                }
                if (key == KeyEvent.VK_F){
                    if(!atk1){
                       // tempObject.setVelX(0);
                      //  Player.atkTimer = 0;
                        atk1 = true;
                    }
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {


                if (key == KeyEvent.VK_D) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_A) {
                    keyDown[1] = false;
                }

                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_Z){

                    dash = false;
                    keyDown[3] = false;
                    Player.dashCD = 40;
                    Player.dashTimer = 0;

                    if(tempObject.velX >0){
                        tempObject.setVelX(5.0f);
                    }else if (tempObject.velX<0){
                        tempObject.setVelX(-5.0f);
                    }
                }
                if(key == KeyEvent.VK_T && shoot){
                    shoot = false;
                    if(keyDown[0]){
                        tempObject.setVelX(5.0f);
                    }else if (keyDown[1]){
                        tempObject.setVelX(-5.0f);
                    }
                }



            }

        }
    }

}
