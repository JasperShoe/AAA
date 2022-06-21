package Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Images {
    public static HashMap<String, BufferedImage> list;
    static {
        list = new HashMap<>();
        String[] loader = {
                "anna_east",
                "anna_west",
                "indigo_east",
                "indigo_west",
                "grass",
                "rock",
                "sky",
                "boba",
                "gorilla_east",
                "gorilla_west",
        };

        for (String s : loader) {
            list.put(s, readImg(s));
        }
    }

    public static URL buildImageFile(String file){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource("res/" + file + ".png");
    }

    public static BufferedImage readImg(String file){
        BufferedImage ans = null;
        try {
            URL url = buildImageFile(file);
            ans =  ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
        return ans;
    }
}