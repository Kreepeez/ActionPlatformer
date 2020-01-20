package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {

    public Handler(){
        BufferedImageLoader loader = new BufferedImageLoader();
        level2 = loader.loadImage("/level2.png");
        level = loader.loadImage("/level1.png");

    }

    LinkedList<GameObject> object = new LinkedList<>();

    LinkedList<BlockFill> blockFills = new LinkedList<>();
    public BufferedImage level, level2, level3,level4;

   // private BufferedImage level2 = null;

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }

        for(int j = 0; j < blockFills.size();j++){
           // BlockFill tempBlock = blockFills.get(j);

        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    public void renderFills(Graphics g){
        for(int i = 0; i <blockFills.size(); i++){
            BlockFill tempBlock = blockFills.get(i);
            tempBlock.render(g);
        }
    }

    private void clearLevel(){
        object.clear();
        blockFills.clear();
        if(StatsController.playerHP <= 0) StatsController.playerHP = StatsController.maxHP;
    }

    public void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx <h; xx++){
            for(int yy = 0; yy <w; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                 if(red == 255 && green == 127 && blue == 182){
                    addObject(new PlayerStartPoint(xx*32,yy*32, ID.StartPoint));
                }


                if(red == 76 && green == 255 && blue == 0){
                    addObject(new Block(xx*32,yy*32,1,ID.Collidable)); //top
                }
                else if(red == 100 && green == 100 && blue == 100){
                    addObject(new Block(xx*32,yy*32,5,ID.Collidable)); //left
                }
                else if(red == 160 && green == 160 && blue == 160){
                    addObject(new Block(xx*32,yy*32,6,ID.Collidable)); //right
                }
                else if(red == 91 && green == 127 && blue == 0){
                    addObject(new Block(xx*32,yy*32,0,ID.Collidable)); //top left
                }
                else if(red == 0 && green == 19 && blue == 127){
                    addObject(new Block(xx*32,yy*32,3,ID.Collidable)); //inner left
                }
                else if(red == 0 && green == 148 && blue == 255){
                    addObject(new Block(xx*32,yy*32,4,ID.Collidable)); //inner right
                }
                else if(red == 0 && green == 127 && blue == 14){
                    addObject(new Block(xx*32,yy*32,2,ID.Collidable)); //top right
                }
                else if(red == 178 && green == 0 && blue == 255){
                   addObject(new Block(xx*32,yy*32,8,ID.Collidable)); //platform left
                }
                else if(red == 255 && green == 0 && blue == 220){
                    addObject(new Block(xx*32,yy*32,9,ID.Collidable)); //platform mid
                }
                else if(red == 255 && green == 0 && blue == 110){
                    addObject(new Block(xx*32,yy*32,10,ID.Collidable)); //platform right
                }
                else if(red == 255 && green == 255 && blue == 255){
                    addFill(new BlockFill(xx*32,yy*32, ID.Fill)); //mid
                }else if(red == 255 && green == 216 && blue == 0){
                    addObject(new EndPoint(xx*32, yy*32, ID.EndPoint)); //End
                }else if(red == 255 && green == 0 && blue == 0){
                    addObject(new Enemy1(xx*32, yy*32, ID.Enemy,100, this));}
                else if(red == 0 && green == 0 && blue == 255){
                    addObject(new Player(xx*32,yy*32, ID.Player, this)); //player
                }

                else;
            }
        }

    }

    public void switchLevel(){
        clearLevel();

        switch (Game.lvl){
            case 1: {

                loadImageLevel(level);
            }
                break;
            case 2: {
                loadImageLevel(level2);
                break;
            }
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void addFill(BlockFill blockFill) {this.blockFills.add(blockFill);}
    public void removeFill(BlockFill blockFill) {this.blockFills.remove(blockFill);}
}
