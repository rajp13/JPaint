package controller;

import view.interfaces.PaintCanvasBase;

public interface IShape {

    void draw(PaintCanvasBase paintCanvasBase);

    boolean checkCollisions(Point p1,Point p2);

}
