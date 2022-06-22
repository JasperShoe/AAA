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
        super(startingPos, WIDTH, HEIGHT, 2, 5, WEST, null, "indigo", true, 12);
        this.anna = anna;
        runaway_dist = 256;
        return_dist = 384;
    }

    @Override
    public void move(){
        if(isCollisionRight() || isCollisionLeft()){
            doJump();
        }

        if(Math.abs(anna.getPos().x - getPos().x) < runaway_dist){
            if(getDir() != EAST){
                changeDirection();
            }
            updateVx(EAST);
        } else if(Math.abs(anna.getPos().x - getPos().x) > return_dist){
            if(getDir() != WEST){
                changeDirection();
            }
            updateVx(WEST);
        } else {
            setVx(0);
            resetSpeed();
        }

        if(getPos().x <= Main.WIDTH) {
            setGravity(true);
        } else {
            setY(Game.current_level.getEndingPos().y);
            setGravity(false);
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
