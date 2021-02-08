package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintMouseHandler extends MouseAdapter {

    private PaintCanvasBase paintCanvasBase;
    private ShapeInfo shapeInfo;
    private final ShapeList shapeList;
    private ApplicationState appState;

    public PaintMouseHandler(PaintCanvasBase paintCanvasBase, ApplicationState appState,ShapeInfo shapeInfo, ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.appState = appState;
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
        System.out.println("Ending Point " + endPoint.toString());
        shapeInfo.setApplicationState(appState);
        Command createShape = new CreateShapeCommand(paintCanvasBase,appState,shapeInfo,shapeList);
        createShape.execute();
    }




}
