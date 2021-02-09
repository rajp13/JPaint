package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class MoveShapeCommand implements Command{

    private ShapeList shapeLst;
    private int deltaX;
    private int deltaY;
    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endingPoint;


    public MoveShapeCommand(ShapeInfo shapeInfo, ShapeList shapeLst) {
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeLst;
        startingPoint = shapeInfo.getStartingPoint();
        endingPoint = shapeInfo.getEndPoint();
        deltaX = endingPoint.getX() - startingPoint.getX();
        deltaY = endingPoint.getY() - startingPoint.getY();
    }


    @Override
    public void execute() {
        ArrayList<IShape> selectedShapeList = shapeLst.getSelectedShapeLst();
        for(IShape shape: selectedShapeList) {
            shape.move(deltaX,deltaY);
        }
    }

}
