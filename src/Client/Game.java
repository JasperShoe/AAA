package Client;

import Entities.Anna;
import Entities.Drawable;
import Entities.Indigo;
import World.Level;
import World.Levels;
import World.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    private Anna anna;
    private Drawable[] drawables;
    private Levels levels;
    public static Level current_level;
    public static int current_level_index;
    private Indigo indigo;
    private GUI gui;

    public Game(){
        setFocusable(true);

        drawables = new Drawable[4];

        levels = new Levels();
        current_level_index = 4;
        reloadLevel();

        anna = new Anna(current_level.getStartingPos());
        addKeyListener(anna);
        drawables[1] = anna;

        indigo = new Indigo(anna, current_level.getIndigoStartingPos());
        drawables[2] = indigo;

        gui = new GUI();
        drawables[3] = gui;

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

        g2.drawImage(Images.list.get("sky"), null, 0, 0);

        anna.resetCollisions();
        indigo.resetCollisions();
        for(Object o : current_level.getTiles()){
            anna.collisionDetection(o);
            indigo.collisionDetection(o);
        }

        for(Object i : current_level.getInteractables()){
            anna.collisionDetection(i);
        }

        for(Object e : current_level.getEntities()){
            anna.collisionDetection(e);
        }

        for(Drawable d : drawables){
            d.draw(g2);
        }

        checkNextLevel();
    }

    public void changeLevel(int indexChange, boolean forward){
        current_level_index += indexChange;

        reloadLevel();

        anna.setPos(forward ? current_level.getStartingPos() : current_level.getEndingPos());
        anna.reset();

        indigo.setPos(forward ? current_level.getIndigoStartingPos() : new Point(current_level.getEndingPos().x + indigo.getReturnDist(), current_level.getEndingPos().y));
        indigo.reset();
    }

    public void reloadLevel(){
        current_level = levels.getLevel(current_level_index);
        drawables[0] = current_level;
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