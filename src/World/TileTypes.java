package World;

import java.awt.*;
import java.util.HashMap;

public class TileTypes {
    public static HashMap<Integer, Color> types = new HashMap<Integer, Color>(){{
        put(1, new Color(39, 168, 42));
        put(2, new Color(88, 66, 16));
        put(3, new Color(92, 92, 92));
    }};
}