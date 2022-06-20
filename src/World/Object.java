package World;

import Entities.Drawable;

import java.awt.*;

public class Object implements Drawable {
    private Point pos;
    private int WIDTH, HEIGHT;
    private Color color;

    private boolean collision, collision_up, collision_down, collision_left, collision_right;

    public Object(Point pos, int WIDTH, int HEIGHT, Color color){
        this.pos = pos;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = color;
        collision = false;
        collision_up = false;
        collision_down = false;
        collision_left = false;
        collision_right = false;
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillRect(pos.x, pos.y, WIDTH, HEIGHT);
    }

    public void collisionDetection(Object other){
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
                    pos.y = o_bottom_edge;
                    collision_up = true;
                    collision = true;
                }

                //Collision Down
                if (o_top_edge <= bottom_edge && o_top_edge >= top_edge + HEIGHT / 2) {
                    pos.y = o_top_edge - HEIGHT;
                    collision_down = true;
                    collision = true;
                }
            }

            // Horizontal Collisions
            if (o_top_edge < bottom_edge && o_bottom_edge > top_edge) {
                // Collision Left
                if (o_right_edge >= left_edge && o_right_edge <= left_edge + WIDTH / 2) {
                    pos.x = o_right_edge;
                    collision_left = true;
                    collision = true;
                }

                // Collision Right
                if (o_left_edge <= right_edge && o_left_edge >= left_edge + WIDTH / 2) {
                    pos.x = o_left_edge - WIDTH;
                    collision_right = true;
                    collision = true;
                }
            }
        }
    }

    public void resetCollisions(){
        collision = false;
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

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isCollision() {
        return collision;
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
}
