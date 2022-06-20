package World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends Object {
    public static final int WIDTH = 32, HEIGHT = 32;

    public Tile(Point pos, BufferedImage img){
        super(pos, WIDTH, HEIGHT, img);
    }
}