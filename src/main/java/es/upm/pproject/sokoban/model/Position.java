package es.upm.pproject.sokoban.model;

public class Position {

    private int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Position p = (Position) o;
        if (p.getX() == this.x && p.getY() == this.y) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return ("_" + this.x + "_" + this.y + "_").hashCode();
    }
}
