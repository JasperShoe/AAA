package World;

import java.awt.*;

public class Tile extends Object {
    public static final int WIDTH = 32, HEIGHT = 32;

    public Tile(Point pos, int type){
        super(pos, WIDTH, HEIGHT, TileTypes.types.get(type));
    }
}