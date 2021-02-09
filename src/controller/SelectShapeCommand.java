package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class SelectShapeCommand implements Command {

    private PaintCanvasBase paintCanvasBase;
    private ShapeInfo shapeInfo;
    private IShape shape;
    private ApplicationState appState;
    private ArrayList<IShape> selectedShapeList;
    private ArrayList<IShape> mainShapeList;
    private ShapeList shapeLst;
    private Point startingPoint;
    private Point endingPoint;

    public SelectShapeCommand(PaintCanvasBase paintCanvasBase, ApplicationState appState,ShapeInfo shapeInfo,ShapeList shapeLst) {
        this.paintCanvasBase = paintCanvasBase;
        this.appState = appState;
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeLst;
        startingPoint = shapeInfo.getStartingPoint();
        endingPoint = shapeInfo.getEndPoint();

    }
    /*
        1.clear out the selectedShapeList
        2.iterate over each shape in the main shapeList
        3.Say is this one selected
        4.for each shape run it through collision detection algorithm
        5.if it collides it will add it to the selectedShapeList
     */
    @Override
    public void execute() {
        selectedShapeList = shapeLst.getSelectedShapeLst();
        mainShapeList = shapeLst.getShapeLst();
        // clear out the list
        selectedShapeList.clear();
        for(IShape shape: mainShapeList) {
            if(shape.checkCollisions(startingPoint,endingPoint)) {
                System.out.println("Collision " + startingPoint.toString() + " " + endingPoint.toString());
                shapeLst.addSelectShape(shape);
                System.out.println(selectedShapeList.toString());
            } else {
                System.out.println("No Collision");
            }
        }
    }


}
