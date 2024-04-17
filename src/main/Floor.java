package main;
import java.util.ArrayList;
import java.util.HashMap;

public class Floor {
    private int width;
    private int height;
    private ArrayList<ArrayList<Tile>> tiles;
    private Target aspirin;
    private Target water;
    private Mover cockroach;
    private HashMap<ArrayList<Integer>, Tile> unvisitedTiles;

    public Floor(int width, int height, int[] aspirinCoords, int[] waterCoords) {
            this.aspirin = new Target(aspirinCoords[0], aspirinCoords[1]);
            this.water = new Target(waterCoords[0], waterCoords[1]);
            this.cockroach = new Mover((int) Math.floor(width / 2), (int) Math.floor(height / 2));
            this.width = width;
            this.height = height;

            this.tiles = generateTiles();

            //stack of unvisited tiles
            this.unvisitedTiles = new HashMap<ArrayList<Integer>, Tile>();
            for (ArrayList<Tile> row : this.tiles) {
                for (Tile tile : row) {
                    ArrayList<Integer> coords = new ArrayList<Integer>();
                    coords.add(tile.getX());
                    coords.add(tile.getY());
                    this.unvisitedTiles.put(coords, tile);
                }
            }




    }   

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Target getAspirin() {
        return this.aspirin;
    }

    public Target getWater() {
        return this.water;
    }

    public HashMap<ArrayList<Integer>, Tile> getUnvisitedTiles() {
        return this.unvisitedTiles;
    }

    public Tile getTile(int x, int y) {
        return tiles.get(x).get(y);
    }

    public Mover getCockroach() {
        return this.cockroach;
    }

    private ArrayList<ArrayList<Tile>> generateTiles() {
        ArrayList<ArrayList<Tile>> tilesArr = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < this.width; i++) {
            tilesArr.add(new ArrayList<Tile>());
            for (int j = 0; j < this.height; j++) {
                Tile tile = new Tile(i, j);
                if (i == 0) {
                    tile.setWalls("left");
                }
                if (i == this.width - 1) {
                    tile.setWalls("right");
                }
                if (j == 0) {
                    tile.setWalls("top");
                }
                if (j == this.height - 1) {
                    tile.setWalls("bottom");
                }

                tilesArr.get(i).add(tile);
            }
        }

        return tilesArr;
    }

}