public class Target {
    private int x;
    private int y;
    private boolean found = false;

    public Target(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getFound() {
        return this.found;
    }

    public void setFound() {
        this.found = true;
    }
}
