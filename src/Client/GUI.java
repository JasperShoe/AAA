package Client;

import Entities.Anna;
import Entities.Drawable;

import java.awt.*;

public class GUI implements Drawable {
    private Anna anna;

    private Point healthBarPos;
    private int healthbarWidth = 200, healthBarHeight = 30;

    public GUI (Anna anna)  {
        this.anna = anna;
        healthBarPos = new Point(20, 700);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(healthBarPos.x, healthBarPos.y, healthbarWidth, healthBarHeight);
        g2.setColor(Color.GREEN);
        g2.fillRect(healthBarPos.x, healthBarPos.y, (int) (healthbarWidth * (1.0 * anna.getHealth() / anna.getStartingHealth())), healthBarHeight);
    }
}
