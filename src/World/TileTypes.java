package World;

import Client.Images;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileTypes {
    public static HashMap<Integer, BufferedImage> types = new HashMap<Integer, BufferedImage>(){{
        put(1, Images.list.get("grass"));
        put(2, Images.list.get("rock"));
    }};
}