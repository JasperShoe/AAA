package Entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Anna extends Entity implements KeyListener {
    private boolean move_east, move_west, can_move_east, can_move_west;

    public static boolean damaged;

    public static int WIDTH = 32, HEIGHT = 32;

    public static int startingHealth, health;

    public Anna(Point startingPos){
        super(startingPos, WIDTH, HEIGHT,2, 4, EAST, null, "anna");
        move_east = false;
        move_west = false;
        can_move_east = true;
        can_move_west = true;
        startingHealth = 100;
        health = 100;
        damaged = false;
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
        jumpOrFall();

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

        super.draw(g2);
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

            moveVx(WEST);

            move_west = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_D && can_move_east){
            if(getDir() == WEST){
                changeDirection();
            }

            moveVx(EAST);
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