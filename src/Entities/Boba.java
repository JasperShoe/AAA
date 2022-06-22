package Entities;

import Client.Images;
import World.Object;

import java.awt.*;

public class Boba extends Object {
    private boolean used;

    public Boba(Point pos){
        super(pos, 32, 32, Images.list.get("boba"));
        setCollidable(false);
        used = false;
    }

    @Override
    public void draw(Graphics2D g2){
        if(!used) {
            super.draw(g2);
        }
    }

    @Override
    public void interact() {
        if(!used){
            used = true;
            Anna.addHealth(20);
        }
    }
}
