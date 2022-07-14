package Entities;

import Client.Images;
import World.Tear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Anna extends Entity implements KeyListener {
    private boolean move_east, move_west, can_move_east, can_move_west;

    public static boolean damaged;

    public static int WIDTH = 32, HEIGHT = 32;

    public static int startingHealth, health;

    private ArrayList<Tear> tears;

    public Anna(Point startingPos){
        super(startingPos, WIDTH, HEIGHT,2, 4, EAST, null, "anna", true, 12);
        move_east = false;
        move_west = false;
        can_move_east = true;
        can_move_west = true;
        startingHealth = 100;
        health = 100;
        damaged = false;
        tears = new ArrayList<Tear>();
    }

    @Override
    public void reset(){
        super.reset();
        move_east = false;
        move_west = false;
        can_move_east = true;
        can_move_west = true;
    }

    @Override
    public void move() {
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

        if(move_east || move_west){
            moveX();
        }
        moveY();
    }

    @Override
    public void draw(Graphics2D g2){
        move();

        super.draw(g2);

        if (getDir() == EAST) {
            g2.drawImage(Images.list.get("teargun_east"), null, getPos().x, getPos().y);
        } else {
            g2.drawImage(Images.list.get("teargun_west"), null, getPos().x, getPos().y);
        }

        for(Tear t : tears){
            t.draw(g2);
        }
    }

    public void stopMoving(){
        if(!move_west && !move_east){
            setVx(0);
            resetSpeed();
        }
    }

    public void shoot(){
        Point tearPos;

        if(getDir() == EAST){
            tearPos = new Point(getPos().x + getWidth(), getPos().y + 19);
        } else {
            tearPos = new Point(getPos().x - Tear.WIDTH, getPos().y + 19);
        }

        tears.add(new Tear(tearPos, getDir()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                if(can_move_west){
                    if(getDir() == EAST){
                        changeDirection();
                    }

                    updateVx(WEST);
                    move_west = true;
                }
                break;
            case KeyEvent.VK_D:
                if(can_move_east){
                    if(getDir() == WEST){
                        changeDirection();
                    }

                    updateVx(EAST);
                    move_east = true;
                }
                break;
            case KeyEvent.VK_W:
                doJump();
                break;
            case KeyEvent.VK_SPACE:
                shoot();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                move_west = false;
                stopMoving();
                break;
            case KeyEvent.VK_D:
                move_east = false;
                stopMoving();
                break;
            default:
                break;

        }
    }

    public int getHealth(){
        return health;
    }

    public int getStartingHealth(){
        return startingHealth;
    }

    public static void addHealth(int amt){
        health += amt;

        if(health > startingHealth) {
            health = startingHealth;
        }
    }

    public static Timer damageCoolDown = new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            damaged = false;
            damageCoolDown.stop();
        }
    });

    public static void damage(int amt){
        if(!damaged) {
            health -= amt;

            if(health < 0){
                health = 0;
            }

            damaged = true;
            damageCoolDown.start();
        }
    }
}