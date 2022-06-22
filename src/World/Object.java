package World;

import Entities.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object implements Drawable {
    private Point pos;
    private int WIDTH, HEIGHT;
    private BufferedImage img;

    private boolean collidable, collision_up, collision_down, collision_left, collision_right;

    public Object(Point pos, int WIDTH, int HEIGHT, BufferedImage img){
        this.pos = pos;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.img = img;
        collision_up = false;
        collision_down = false;
        collision_left = false;
        collision_right = false;
        collidable = true;
    }

    @Override
    public void draw(Graphics2D g2){
        if(img != null){
            g2.drawImage(img, null, pos.x, pos.y);
        } else {
            g2.setColor(Color.red);
            g2.fillRect(pos.x, pos.y, WIDTH, HEIGHT);
        }
    }

    public void interact(){
        // Do something
    }

    public boolean collisionDetection(Object other){
        if(other != this) {
            int o_x = other.getPos().x;
            int o_y = other.getPos().y;
            int o_top_edge = o_y;
            int o_bottom_edge = o_y + other.getHeight();
            int o_left_edge = o_x;
            int o_right_edge = o_x + other.getWidth();

            int top_edge = pos.y;
            int bottom_edge = pos.y + HEIGHT;
            int left_edge = pos.x;
            int right_edge = pos.x + WIDTH;

            // Vertical Collisions
            if (o_right_edge >= left_edge && o_left_edge <= right_edge) {
                // Collision Up
                if (o_bottom_edge >= top_edge && o_bottom_edge <= top_edge + HEIGHT / 2) {
                    if(other.isCollidable()) {
                        collision_up = true;
                        pos.y = o_bottom_edge;
                    }
                    return true;
                }

                //Collision Down
                if (o_top_edge <= bottom_edge && o_top_edge >= top_edge + HEIGHT / 2) {
                    if(other.isCollidable()) {
                        collision_down = true;
                        pos.y = o_top_edge - HEIGHT;
                    }
                    return true;
                }
            }

            // Horizontal Collisions
            if (o_top_edge < bottom_edge && o_bottom_edge > top_edge) {
                // Collision Left
                if (o_right_edge >= left_edge && o_right_edge <= left_edge + WIDTH / 2) {
                    if(other.isCollidable()) {
                        collision_left = true;
                        pos.x = o_right_edge;
                    }
                    return true;
                }

                // Collision Right
                if (o_left_edge <= right_edge && o_left_edge >= left_edge + WIDTH / 2) {
                    if(other.isCollidable()) {
                        collision_right = true;
                        pos.x = o_left_edge - WIDTH;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public void resetCollisions(){
        collision_up = false;
        collision_down = false;
        collision_left = false;
        collision_right = false;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void setX(int x) {
        pos = new Point(x, pos.y);
    }

    public void setY(int y) {
        pos = new Point(pos.x, y);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isCollisionUp() {
        return collision_up;
    }

    public boolean isCollisionDown() {
        return collision_down;
    }

    public boolean isCollisionLeft() {
        return collision_left;
    }

    public boolean isCollisionRight() {
        return collision_right;
    }

    public void setCollidable(boolean collidable){
        this.collidable = collidable;
    }

    public boolean isCollidable(){
        return collidable;
    }
}
