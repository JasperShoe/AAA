package World;

import Entities.Boba;
import Entities.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Level implements Drawable {
    private ArrayList<Tile> tiles;
    private ArrayList<Object> interactables;

    private Point startingPos, endingPos;
    private Point indigoStartingPos;

    public Level(int[][] tileTypes, Point startingPos, Point endingPos, Point indigoStartingPos){
        tiles = new ArrayList();
        interactables = new ArrayList<>();

        for(int i = 0; i < tileTypes.length; i++){
            for(int j = 0; j < tileTypes[i].length; j++){
                if(tileTypes[i][j] != 0) {
                    Point position = new Point(j * Tile.WIDTH, i * Tile.HEIGHT);
                    if(tileTypes[i][j] == 3){
                        interactables.add(new Boba(position));
                    } else {
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

        for(Object i : interactables){
            i.draw(g2);
        }
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public ArrayList<Object> getInteractables(){
        return interactables;
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