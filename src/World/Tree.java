package World;

import Client.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tree extends Object {
    private int type;
    private BufferedImage img;

    public Tree(Point pos){
        super(pos, 0, 0, null);

        type = new Random().nextInt(2) + 1;
        String dir =  new Random().nextInt(2) == 1 ? "left" : "right";

        img = Images.list.get("tree_" + type + "_" + dir);
        setImg(img);
        setWIDTH(img.getWidth());
        setHEIGHT(img.getHeight());
        setY(getPos().y + Tile.WIDTH - getHeight());

        if(type == 2){
            setX(getPos().x - getWidth()/2);
        }
    }
}
