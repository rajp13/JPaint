package controller.commands;

import controller.Point;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class MoveShapeCommand implements Command, IUndoable {

    private ShapeList shapeLst;
    private int deltaX;
    private int deltaY;
    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endingPoint;
    private ArrayList<IShape> selectedShapeList;
    private ArrayList<IShape> activeMoveShape = new ArrayList<>();


    public MoveShapeCommand(ShapeInfo shapeInfo, ShapeList shapeLst) {
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeLst;
        startingPoint = shapeInfo.getStartingPoint();
        endingPoint = shapeInfo.getEndPoint();
        // Make sure deltaX and deltaY is negative
        deltaX = endingPoint.getX() - startingPoint.getX();
        deltaY = endingPoint.getY() - startingPoint.getY();
        selectedShapeList = shapeLst.getSelectedShapeLst();
    }

    /*
        You have to call the shape drawer after you do the move
     */

    @Override
    public void execute() {
        ArrayList<IShape> groupList = shapeLst.getGroupShapesList();
        for(IShape shape: selectedShapeList) {
            // Check to see if the shapes are in a group, if not a normal move will happen
            if(groupList.size() > 0) {
                shape.move(deltaX,deltaY);
            }
            System.out.println("Moving Shape Now");
            shape.move(deltaX,deltaY);
            activeMoveShape.add(shape);
            shapeLst.drawAllShapes();
        }
        CommandHistory.add(this);
    }

    /*
        If you select some shapes and you move the shapes, then you select some different shapes
        and you undo the move, you want undo the shapes that were moved regradless if they are selected or not
        shape list should maintain a reference to those shapes
     */
    @Override
    public void undo() {
        int undoDeltaX = deltaX * -1;
        int undoDeltaY = deltaY * -1;
        for(IShape shape: activeMoveShape) {
            shape.move(undoDeltaX,undoDeltaY);
            shapeLst.drawAllShapes();
        }
    }

    @Override
    public void redo() {
        for(IShape shape: activeMoveShape) {
            shape.move(deltaX,deltaY);
            shapeLst.drawAllShapes();
        }
    }
}
