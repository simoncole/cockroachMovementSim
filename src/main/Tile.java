package main;
import java.util.ArrayList;
import java.util.HashMap;

public class Tile {
    private int x;
    private int y;
    // private boolean visited = false;
    private HashMap<String, Boolean> walls = new HashMap<String, Boolean>();

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.walls.put("top", false);
        this.walls.put("right", false);
        this.walls.put("bottom", false);
        this.walls.put("left", false);
    }

    public void setWalls(String wall) {
        walls.put(wall, true);
    }

    public ArrayList<String> getAvailibleDirections() {
        //returns an arraylist of ints of the walls that are present
        //wher 0 is top, 1 is right, 2 is bottom, 3 is left
        ArrayList<String> wallsArr = new ArrayList<String>();
        if (!walls.get("top")) {
            wallsArr.add("top");
        }
        if (!walls.get("right")) {
            wallsArr.add("right");
        }
        if (!walls.get("bottom")) {
            wallsArr.add("bottom");
        }
        if (!walls.get("left")) {
            wallsArr.add("left");
        }

        return wallsArr;
    }

    // public void setVisited() {
    //     this.visited = true;
    // }

    // public boolean isVisited() {
    //     return this.visited;
    // }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

}
