public class Mover {
    private int curX;
    private int curY;

    public Mover(int startingX, int startingY) {
        this.curX = startingX;
        this.curY = startingY;
    }

    public int getX() {
        return this.curX;
    }
    
    public int getY() {
        return this.curY;
    }

    public void incrementX() {
        this.curX++;
    }

    public void decrementX() {
        this.curX--;
    }

    public void decrementY() {
        this.curY--;
    }

    public void incrementY() {
        this.curY++;
    }
}
