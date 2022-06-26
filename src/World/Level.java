package World;

import Entities.Boba;
import Entities.Drawable;
import Entities.Entity;
import Entities.Gorilla;

import java.awt.*;
import java.util.ArrayList;

public class Level implements Drawable {
    private ArrayList<Tile> tiles;
    private ArrayList<Object> interactables;
    private ArrayList<Entity> entities;
    private ArrayList<Object> environment;

    private Point startingPos, endingPos;
    private Point indigoStartingPos;

    public Level(int[][] tileTypes, Point startingPos, Point endingPos, Point indigoStartingPos){
        tiles = new ArrayList();
        interactables = new ArrayList<>();
        entities = new ArrayList<>();
        environment = new ArrayList<>();

        for(int i = 0; i < tileTypes.length; i++){
            for(int j = 0; j < tileTypes[i].length; j++){
                if(tileTypes[i][j] != 0) {
                    Point position = new Point(j * Tile.WIDTH, i * Tile.HEIGHT);
                    switch(tileTypes[i][j]){
                        case 3:
                            interactables.add(new Boba(position));
                            break;
                        case 4:
                            entities.add(new Gorilla(position));
                            break;
                        case 5:
                            environment.add(new Tree(position));
                            break;
                        default:
                            tiles.add(new Tile(position, TileTypes.types.get(tileTypes[i][j])));
                    }
                }
            }
        }

        this.startingPos = startingPos;
        this.endingPos = endingPos;
        this.indigoStartingPos = indigoStartingPos;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Tile t : tiles){
            t.draw(g2);
        }

        for(Object e : environment){
            e.draw(g2);
        }

        for(Object i : interactables){
            i.draw(g2);
        }

        for(Entity e : entities){
            e.draw(g2);
        }
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public ArrayList<Object> getInteractables(){
        return interactables;
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public Point getStartingPos(){
        return startingPos;
    }

    public Point getEndingPos(){
        return endingPos;
    }

    public Point getIndigoStartingPos() {
        return indigoStartingPos;
    }
}