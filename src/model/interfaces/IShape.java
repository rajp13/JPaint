package model.interfaces;

import controller.Point;
import view.interfaces.PaintCanvasBase;

public interface IShape {

    void draw(PaintCanvasBase paintCanvasBase);

    boolean checkCollisions(Point p1, Point p2);

    void move(int deltaX, int deltaY);

}
