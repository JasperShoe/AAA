package Entities;

import World.Object;

import java.awt.*;

public class Gorilla extends Entity {
    public Gorilla(Point pos){
        super(pos, 64, 64, 0, 2, WEST, null, "gorilla");
    }

    @Override
    public void draw(Graphics2D g2){
        interact();

        super.draw(g2);
    }

    public void interact() {
        if(isCollision()){
            Anna.damage(20);
        }
    }
}
