package controller;

public class ShapeInfo {

    private Point endPoint;
    private Point startingPoint;


    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public String toString() {
        return "ShapeInfo:  " + this.startingPoint + " " + this.endPoint;
    }


}
