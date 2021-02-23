package controller;

import model.MouseMode;
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
    private ShapeList selectedShapeList;
    private Point startingPoint;

    public PaintMouseHandler(PaintCanvasBase paintCanvasBase, ApplicationState appState,ShapeInfo shapeInfo, ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.appState = appState;
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startingPoint = new Point(e.getX(),e.getY());
        System.out.println("Starting Point " + startingPoint.toString());
    }

    /*
         Get the endPoint in the same way by overriding the mouseReleased event.
         Calculate the height and width by doing math on startPoint and endPoint
       */
    @Override
    public void mouseReleased(MouseEvent e) {
        // gets the end Points
        Point endPoint = new Point(e.getX(),e.getY());
        System.out.println("Ending Point " + endPoint.toString());
        shapeInfo.setStartingPoint(startingPoint);
        shapeInfo.setEndPoint(endPoint);
        shapeInfo.setApplicationState(appState);
        MouseMode mouseMode = appState.getActiveMouseMode();
        if(mouseMode.equals(MouseMode.DRAW)) {
            shapeInfo.setStartingPoint(startingPoint);
            shapeInfo.setEndPoint(endPoint);
            Command createShape = new CreateShapeCommand(paintCanvasBase, appState, shapeInfo, shapeList);
            createShape.execute();
        } else if(mouseMode.equals(MouseMode.SELECT)) {
            System.out.println("Mouse mode is on SELECT");
            Command selectShapeCommand = new SelectShapeCommand(paintCanvasBase,appState,shapeInfo,shapeList);
            selectShapeCommand.execute();
        } else {
            System.out.println("Mouse mode is on MOVE");
            Command moveShapeCommand = new MoveShapeCommand(shapeInfo,shapeList);
            moveShapeCommand.execute();
        }
    }




}
