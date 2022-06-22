package Entities;

import Client.Main;

import java.awt.*;

public class Gorilla extends Entity {
    public Gorilla(Point pos){
        super(pos, 64, 64, 0, 2, WEST, null, "gorilla", true, 4);
    }

    @Override
    public void move(){
        if(getPos().x > Main.WIDTH - getWidth()){
            setDir(WEST);
            setVx(0);
            resetSpeed();
        } else if(getPos().x < 0){
            setDir(EAST);
            setVx(0);
            resetSpeed();
        }
        increaseSpeed();

        super.move();
    }

    @Override
    public void draw(Graphics2D g2){
        move();

        super.draw(g2);
    }

    @Override
    public void interact() {
        Anna.damage(20);
    }
}
