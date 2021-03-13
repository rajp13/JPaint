package model.interfaces;

import controller.Point;
import model.ShapeInfo;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public interface IShape {

    void draw(PaintCanvasBase paintCanvasBase);

    boolean checkCollisions(Point p1, Point p2);

    void move(int deltaX, int deltaY);

    ShapeInfo getShapeInfo();

    /*
        These will return the startingPoint and End Point when the shape was drawn
        (Mouse Mode is on Draw)
        The compiler is getting confused with the points in SELECT and MOVE mode
    */

    Point getShapeStartingPoint();

    Point getShapeEndingPoint();

    ShapeType getCurrentShapeType();

}
