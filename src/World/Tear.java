package World;

import Client.Images;
import Entities.Anna;

import java.awt.*;

public class Tear extends Object {
    public static int WIDTH = 15, HEIGHT = 9;

    private int dir, speed;

    public Tear(Point pos, int dir){
        super(pos, WIDTH, HEIGHT, null);
        this.dir = dir;
        speed = 5;

        if(dir == Anna.EAST){
            setImg(Images.list.get("tear_east"));
        } else {
            setImg(Images.list.get("tear_west"));
        }
    }

    public void move(){
        setX(getPos().x + speed * dir);
    }

    @Override
    public void draw(Graphics2D g2){
        move();
        g2.drawImage(getImg(), null, getPos().x, getPos().y);
    }
}
