package Entities;

import Client.Images;
import World.Object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends Object {
    public static final int EAST = 1, WEST = -1;
    private int vx, vy;
    private int start_speed;
    private int speed;
    private int max_speed;
    private int dir;
    private boolean falling;
    private int falling_speed, max_falling_speed;
    private int jump_speed, jump_speed_max;
    private boolean jumping;
    private String img_name;

    public Entity(Point pos, int WIDTH, int HEIGHT, int start_speed, int max_speed, int dir, BufferedImage img, String img_name){
        super(pos, WIDTH, HEIGHT, img);
        this.start_speed = start_speed;
        this.max_speed = max_speed;
        this.dir = dir;
        vx = 0;
        vy = 0;
        speed = start_speed;
        falling = true;
        falling_speed = 0;
        max_falling_speed = 4;
        jumping = false;
        jump_speed_max = 12;
        jump_speed = jump_speed_max;
        this.img_name = img_name;
    }

    public void jump(){
        if(jump_speed > 0){
            jump_speed --;
            vy = -jump_speed;
        } else {
            jumping = false;
        }
    }

    public void fall(){
        if (falling_speed < max_falling_speed) {
            falling_speed++;
        }

        vy = falling_speed;
    }

    public void jumpOrFall(){
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
    }

    public void move(){
        setPos(new Point(getPos().x + vx, getPos().y + vy));
    }

    public void moveVx(int dir){
        increaseSpeed();
        setVx(dir * getSpeed());
    }

    @Override
    public void draw(Graphics2D g2){
        if(img_name != null) {
            if (getDir() == EAST) {
                g2.drawImage(Images.list.get(img_name + "_east"), null, getPos().x, getPos().y);
            } else {
                g2.drawImage(Images.list.get(img_name + "_west"), null, getPos().x, getPos().y);
            }
        } else {
            super.draw(g2);
        }
    }

    public void changeDirection(){
        dir *= -1;
        vx = 0;
        resetSpeed();
    }

    public void increaseSpeed(){
        if(speed < max_speed){
            speed ++;
        }
    }

    public void resetSpeed(){
        speed = start_speed;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getDir() {
        return dir;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getJump_speed() {
        return jump_speed;
    }

    public void setJump_speed(int jump_speed) {
        this.jump_speed = jump_speed;
    }

    public void setFalling_speed(int falling_speed) {
        this.falling_speed = falling_speed;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void doJump() {
        if(isCollisionDown() && !jumping) {
            jump_speed = jump_speed_max;
            jumping = true;
        }
    }

    public void setDir(int dir){
        this.dir = dir;
    }

    public void resetJump(){
        jumping = false;
        jump_speed = jump_speed_max;
    }

    public void reset(){
        setVx(0);
        setVy(0);
        setDir(EAST);
        resetSpeed();
        resetJump();
    }
}
