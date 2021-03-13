package controller.commands;

import controller.Point;
import model.ShapeDetector;
import model.ShapeInfo;
import model.ShapeList;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class GroupShapeCommand implements Command, IUndoable {

    private ShapeList shapeList;
    private ShapeInfo shapeInfo;
    private ShapeComposite shapeComposite;
    private ShapeDetector shapeDetector;
    // deletedShape will save references to shapes that are part of a new group in undo/redo command
    private ArrayList<IShape> deletedChild;

    public GroupShapeCommand(ShapeList shapeList,ShapeInfo shapeInfo) {
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
        deletedChild = new ArrayList<>();
    }

    /*
        Pressing the Group Button should display a box
        and not see the individual shapes been selected with the dashed line;
     */

    @Override
    public void execute() {
        System.out.println("Group Button Pressed");
        ArrayList<IShape> mainShapeList = shapeList.getShapeLst();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeLst();
        ArrayList<IShape> groupList = shapeList.getGroupShapesList();
        PaintCanvasBase paintCanvasBase = shapeInfo.getPaintCanvasBase();

        if(selectedShapeList.isEmpty()) {
            System.out.println("No shapes selected");
        } else {


            shapeDetector = new ShapeDetector(shapeInfo, shapeList);
            shapeComposite = new ShapeComposite(shapeInfo, shapeList);

            for (IShape shape : selectedShapeList) {
                //shapeList.removeShape(shape);
                shapeComposite.addChild(shape);
                groupList.add(shape);
                shapeComposite.draw(paintCanvasBase);
            }

            for(IShape shape: groupList) {
                shapeList.addShape(shape);
            }

            System.out.println("Children array length " + shapeComposite.getChildrenArray().size());
            System.out.println("Main shapeList size " + mainShapeList.size());
            Point maxGroupPoint = shapeComposite.getMaxPoint();
            Point minGroupPoint = shapeComposite.getMinPoint();
            shapeComposite.setMaxPoint(maxGroupPoint);
            shapeComposite.setMinPoint(minGroupPoint);
            shapeInfo.setMaxGroupPoint(maxGroupPoint.getX(), maxGroupPoint.getY());
            shapeInfo.setMinGroupPoint(minGroupPoint.getX(), minGroupPoint.getY());
            System.out.println("Max Points " + maxGroupPoint.toString());
            System.out.println("Min Points " + minGroupPoint.toString());
            shapeDetector.outlineShapeGroup();
            selectedShapeList.clear();
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        System.out.println("Undo Group Button Pressed");
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeLst();
        ArrayList<IShape> childrenArray = shapeComposite.getChildrenArray();
        ArrayList<IShape> groupShapeList = shapeList.getGroupShapesList();


        if (childrenArray.size() != 0) {
            IShape shape = childrenArray.remove(childrenArray.size()-1);
            shapeList.addShape(shape);
            // save references to the child not part of the group, so I can use when the user presses the redo command
            deletedChild.add(shape);
            selectedShapeList.remove(shape);
        }

        if(selectedShapeList.size() > 1) {
            for (IShape shape : selectedShapeList) {
                shapeComposite.addChild(shape);
                groupShapeList.add(shape);
                //shapeList.drawAllShapes();
            }

            Point maxGroupPoint = shapeComposite.getMaxPoint();
            Point minGroupPoint = shapeComposite.getMinPoint();
            shapeInfo.setMaxGroupPoint(maxGroupPoint.getX(), maxGroupPoint.getY());
            shapeInfo.setMinGroupPoint(minGroupPoint.getX(), minGroupPoint.getY());

        }
    }

    @Override
    public void redo() {
        System.out.println("Group Redo Button Pressed");
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeLst();
        ArrayList<IShape> groupShapeList = shapeList.getGroupShapesList();
        // add child back to the selectedShapeList array, so I can redraw the bounding box
        selectedShapeList.addAll(deletedChild);
        if(deletedChild.size() > 0) {
            for (IShape shape : selectedShapeList) {
                shapeComposite.addChild(shape);
                groupShapeList.add(shape);
                deletedChild.remove(shape);
            }
            Point maxGroupPoint = shapeComposite.getMaxPoint();
            Point minGroupPoint = shapeComposite.getMinPoint();
            shapeInfo.setMaxGroupPoint(maxGroupPoint.getX(), maxGroupPoint.getY());
            shapeInfo.setMinGroupPoint(minGroupPoint.getX(), minGroupPoint.getY());
            shapeDetector.outlineShapeGroup();
        }

    }
}
