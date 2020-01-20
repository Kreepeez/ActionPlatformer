package com.company;

import java.awt.*;

public class PlayerDamageText extends GameObject{

    private String dmgText;

    private int dmg;

    private float alpha = 1;

    private float life;

    private Handler handler;

    public PlayerDamageText(float x, float y, ID id, Handler handler, float life, int dmg) {
        super(x, y, id);
        this.handler = handler;
        this.dmg = dmg;
        this.life = life;
        dmgText = String.valueOf(dmg);
    }

    @Override
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

    @Override
    public void tick() {
        //dmgText = String.valueOf(dmg);
        y -= 1;

        if(alpha>life){
            alpha -= life - 0.001f;
        }else handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {

        Font font = new Font("arial", 1, 30);
        g.setColor(Color.yellow);
        g.setFont(font);

        g.drawString("-" + dmgText,(int)x,(int)y);
    }
}
