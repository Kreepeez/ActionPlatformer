package com.company;

import java.awt.*;
import java.awt.image.ImageObserver;

public class BlockFill  implements ImageObserver {

    //private int type;
    Texture texture = Game.getInstance();

    private float x,y;
    ID id;

    public BlockFill(float x, float y, ID id ) {
       this.x = x;
       this.y = y;
       this.id = id;

       // this.handler = handler;
    }

    public void render(Graphics g) {
         g.drawImage(texture.block[7], (int)x,(int)y,this);     //mid

    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
