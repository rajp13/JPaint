package controller;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPoint() {
        return new Point(x,y);
    }

    public void setPoint(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public String toString(){
        return this.x + ", " + this.y;
    }
}
