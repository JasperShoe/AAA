package Client;

import Entities.Anna;
import Entities.Drawable;
import World.Level;
import World.Levels;
import World.Object;
import World.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JPanel {
    private Anna anna;
    private ArrayList<Drawable> drawables;
    private ArrayList<Object> objects;
    private Levels levels;
    private Level current_level;
    private int current_level_index;

    public Game(){
        setFocusable(true);

        drawables = new ArrayList<>();
        objects = new ArrayList<>();


        levels = new Levels();
        current_level_index = 0;
        current_level = levels.getLevel(current_level_index);
        drawables.add(current_level);

        anna = new Anna(current_level.getStartingPos());
        System.out.println(current_level.getStartingPos());

        addKeyListener(anna);
        drawables.add(anna);

        for(Tile t : current_level.getTiles()){
            objects.add(t);
        }

        update.start();
    }

    public Timer update = new Timer(1000/60, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(170, 226, 236));
        g2.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        anna.resetCollisions();
        for(Object o : objects){
            anna.collisionDetection(o);
        }

        for(Drawable d : drawables){
            d.draw(g2);
        }

        checkNextLevel();
    }

    public void changeLevel(int indexChange, boolean forward){
        current_level_index += indexChange;
        drawables.remove(current_level);

        for(Tile t : current_level.getTiles()){
            objects.remove(t);
        }

        current_level = levels.getLevel(current_level_index);
        drawables.add(current_level);
        for(Tile t : current_level.getTiles()){
            objects.add(t);
        }

        anna.setPos(forward ? current_level.getStartingPos() : current_level.getEndingPos());
        anna.reset();
    }

    public void checkNextLevel(){
        if(anna.getPos().x > Main.WIDTH){
            if(current_level_index < levels.getLevels().length - 1) {
                changeLevel(1, true);
            }
        } else if(anna.getPos().x < -anna.getWidth()){
            if(current_level_index > 0) {
                changeLevel(-1, false);
            }
        }
    }
}