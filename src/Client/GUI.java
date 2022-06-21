package Client;

import Entities.Anna;
import Entities.Drawable;

import java.awt.*;

public class GUI implements Drawable {
    private Point healthBarPos;
    private int healthbarWidth = 250, healthBarHeight = 30;

    public GUI ()  {
        healthBarPos = new Point(20, 700);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(healthBarPos.x, healthBarPos.y, healthbarWidth, healthBarHeight);
        g2.setColor(Color.GREEN);
        g2.fillRect(healthBarPos.x, healthBarPos.y, (int) (healthbarWidth * (1.0 * Anna.health / Anna.startingHealth)), healthBarHeight);
    }
}
