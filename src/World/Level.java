package World;

import Entities.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Level implements Drawable {
    private ArrayList<Tile> tiles;

    private Point startingPos, endingPos;

    public Level(int[][] tileTypes, Point startingPos, Point endingPos){
        tiles = new ArrayList();

        for(int i = 0; i < tileTypes.length; i++){
            for(int j = 0; j < tileTypes[i].length; j++){
                if(tileTypes[i][j] != 0) {
                    tiles.add(new Tile(new Point(j * Tile.WIDTH, i * Tile.HEIGHT), tileTypes[i][j]));
                }
            }
        }

        this.startingPos = startingPos;
        this.endingPos = endingPos;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Tile t : tiles){
            t.draw(g2);
        }
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public Point getStartingPos(){
        return startingPos;
    }

    public Point getEndingPos(){
        return endingPos;
    }
}