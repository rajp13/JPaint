package controller;

import view.interfaces.PaintCanvasBase;

public class ShapeFactory {


    public IShape create(PaintCanvasBase paintCanvas, ShapeInfo shapeInfo) {
        IShape shape = null;
        shape = new CreateRectangle(paintCanvas,shapeInfo);
        //shape.draw(paintCanvas);
        // return the new shape
        return shape;
    }

}
