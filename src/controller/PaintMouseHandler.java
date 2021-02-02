package controller;

import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintMouseHandler extends MouseAdapter {

    private PaintCanvasBase paintCanvasBase;
    private ShapeInfo shapeInfo;
    private final ShapeList shapeList;

    public PaintMouseHandler(PaintCanvasBase paintCanvasBase,ShapeInfo shapeInfo, ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point startingPoint = new Point(e.getX(),e.getY());
        System.out.println("Starting Point " + startingPoint.toString());
        shapeInfo.setStartingPoint(startingPoint);
    }

    /*
         Get the endPoint in the same way by overriding the mouseReleased event.
         Calculate the height and width by doing math on startPoint and endPoint
       */
    @Override
    public void mouseReleased(MouseEvent e) {
        // gets the end Points
        Point endPoint = new Point(e.getX(),e.getY());
        shapeInfo.setEndPoint(endPoint);
        Command createShape = new CreateShapeCommand(paintCanvasBase,shapeInfo,shapeList);
        createShape.execute();
    }




}
