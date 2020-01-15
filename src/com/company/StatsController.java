package com.company;

import java.awt.*;
import java.awt.image.ImageObserver;

public class StatsController implements ImageObserver {

    public static float playerHP = 100;
    public static float maxHP = 100;
    public static int dmg = 0;

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

    public void tick(){
        playerHP = Game.clamp(playerHP,0,maxHP);
    }

    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect(15,15,(int)playerHP*2, 20);
        g.setColor(Color.black);
        g.drawRect(15,15, (int)maxHP*2,20);
    }
}
