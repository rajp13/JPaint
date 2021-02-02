package controller;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateRectangle implements IShape {

    private PaintCanvasBase paintCanvas;
    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endPoint;

    public CreateRectangle(PaintCanvasBase paintCanvas,ShapeInfo shapeInfo) {
        this.paintCanvas = paintCanvas;
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endPoint = shapeInfo.getEndPoint();
    }


    @Override
    public void draw(PaintCanvasBase paintCanvasBase) {
        System.out.println("Drawing a Rectangle now");
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        int height = Math.abs(startingPoint.getY() - endPoint.getY());
        int width = Math.abs(startingPoint.getX() - endPoint.getX());
        graphics2d.fillRect(startingPoint.getX(),startingPoint.getY(),width,height);
    }
}
