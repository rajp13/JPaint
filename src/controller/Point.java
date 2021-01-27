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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y= y;
    }


    public Point getPoint() {
        return new Point(x,y);
    }

    public void setPoint(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public String toString(){
        return this.x + " " + this.y;
    }


    public void setPoint(java.awt.Point point) {
        this.x = point.x;
        this.y = point.y;
    }
}