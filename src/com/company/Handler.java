package com.company;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();

    LinkedList<BlockFill> blockFills = new LinkedList<>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }

        for(int j = 0; j < blockFills.size();j++){
            BlockFill tempBlock = blockFills.get(j);
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

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void addFill(BlockFill blockFill) {this.blockFills.add(blockFill);}
    public void removeFill(BlockFill blockFill) {this.blockFills.remove(blockFill);}
}
