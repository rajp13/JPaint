package controller.commands;

import controller.Point;
import model.ShapeDetector;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;
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
    private ShapeDetector shapeDetector;

    public SelectShapeCommand(PaintCanvasBase paintCanvasBase, ApplicationState appState,ShapeInfo shapeInfo,ShapeList shapeLst) {
        this.paintCanvasBase = paintCanvasBase;
        this.appState = appState;
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeLst;
        startingPoint = shapeInfo.getStartingPoint();
        endingPoint = shapeInfo.getEndPoint();
        shapeDetector = new ShapeDetector(this.shapeInfo,this.shapeLst);

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
        ArrayList<IShape> groupList = shapeLst.getGroupShapesList();
        // clear out the list
        selectedShapeList.clear();
        for(IShape shape: mainShapeList) {
            System.out.println(startingPoint.toString() + " " + endingPoint.toString());
            if(shape.checkCollisions(startingPoint,endingPoint)) {
                if(groupList.size()>0) {
                   selectedShapeList.addAll(groupList);
                }
                System.out.println("Collision " + startingPoint.toString() + " " + endingPoint.toString());
                shapeLst.addSelectShape(shape);
                shapeDetector.outlineShape(shape);
                System.out.println(selectedShapeList.toString());
            } else {
                groupList.clear();
                System.out.println("No Collision");
            }
        }

    }


}
