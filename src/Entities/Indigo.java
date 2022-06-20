package Entities;

import Client.Game;
import Client.Images;
import Client.Main;

import java.awt.*;

public class Indigo extends Entity {
    public static final int WIDTH = 32, HEIGHT = 32;
    private Anna anna;
    private int runaway_dist;
    private int return_dist;

    public Indigo(Anna anna, Point startingPos){
        super(startingPos, WIDTH, HEIGHT, 3, 5, EAST, null, "indigo");
        this.anna = anna;
        runaway_dist = 256;
        return_dist = 384;
    }

    @Override
    public void move(){
        if(isCollisionRight() || isCollisionLeft()){
            doJump();
        }

        if(getPos().x < Main.WIDTH) {
            jumpOrFall();
        } else {
            setY(Game.current_level.getEndingPos().y - 10);
        }

        if(Math.abs(anna.getPos().x - getPos().x) < runaway_dist){
            if(getDir() != EAST){
                changeDirection();
            }
            increaseSpeed();
            setVx(getSpeed());
        } else if(Math.abs(anna.getPos().x - getPos().x) > return_dist){
            if(getDir() != WEST){
                changeDirection();
            }
            increaseSpeed();
            setVx(-getSpeed());
        } else {
            setVx(0);
            resetSpeed();
        }

        super.move();
    }

    @Override
    public void draw(Graphics2D g2){
        move();
        super.draw(g2);
    }

    public int getReturnDist() {
        return return_dist;
    }
}
