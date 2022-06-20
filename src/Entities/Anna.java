package Entities;

import Client.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Anna extends Entity implements KeyListener {
    private boolean move_east, move_west, can_move_east, can_move_west;

    public static int WIDTH = 32, HEIGHT = 32;

    public Anna(Point startingPos){
        super(startingPos, WIDTH, HEIGHT,2, 4, EAST, Color.PINK);
        move_east = false;
        move_west = false;
        can_move_east = true;
        can_move_west = true;
    }

    public void reset(){
        setVx(0);
        setVy(0);
        move_east = false;
        move_west = false;
        can_move_east = true;
        can_move_west = true;
        setDir(EAST);
        resetSpeed();
        resetJump();
    }

    @Override
    public void move() {
        if(isCollisionDown()) {
            setFalling(false);
            setVy(0);
            setFalling_speed(0);
        }

        if(isJumping()){
            jump();
        }

        if(!isCollisionDown() && !isJumping()){
            setFalling(true);
            fall();
        }

        if(isCollisionLeft()){
            can_move_west = false;
        } else {
            can_move_west = true;
        }

        if(isCollisionRight()){
            can_move_east = false;
        } else {
            can_move_east = true;
        }

        super.move();
    }

    @Override
    public void draw(Graphics2D g2){
        move();

        if(getDir() == EAST) {
            g2.drawImage(Images.list.get("anna_east"), null, getPos().x, getPos().y);
        } else {
            g2.drawImage(Images.list.get("anna_west"), null, getPos().x, getPos().y);
        }
    }

    public void stopMoving(){
        if(!move_west && !move_east){
            setVx(0);
            resetSpeed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A && can_move_west){
            if(getDir() == EAST){
                changeDirection();
            }

            increaseSpeed();

            setVx(-getSpeed());

            move_west = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_D && can_move_east){
            if(getDir() == WEST){
                changeDirection();
            }

            increaseSpeed();

            setVx(getSpeed());

            move_east = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            doJump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            move_west = false;
            stopMoving();
        }

        if(e.getKeyCode() == KeyEvent.VK_D){
            move_east = false;
            stopMoving();
        }
    }
}