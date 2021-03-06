package controller.commands;

import controller.Point;
import model.ShapeDetector;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class GroupShapeCommand implements Command {

    private ShapeList shapeList;
    private ArrayList<IShape> groupShapeList;
    private ArrayList<IShape> selectedShapeList;
    private ArrayList<IShape> mainShapeList;
    private ShapeInfo shapeInfo;

    public GroupShapeCommand(ShapeList shapeList,ShapeInfo shapeInfo) {
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    /*
        Pressing the Group Button should display a box
        and not see the individual shapes been selected with the dashed line;
     */

    @Override
    public void execute() {
        System.out.println("Group Button Pressed");
        mainShapeList = shapeList.getShapeLst();
        selectedShapeList = shapeList.getSelectedShapeLst();
        groupShapeList = shapeList.getGroupShapesList();
        ShapeComposite shapeComposite = new ShapeComposite();
        ShapeDetector shapeDetector = new ShapeDetector(shapeInfo,shapeList);
        for(IShape shape: selectedShapeList) {
            shapeComposite.addChild(shape);
            groupShapeList.add(shape);
        }
        Point maxGroupPoint = shapeComposite.getMaxPoint();
        Point minGroupPoint = shapeComposite.getMinPoint();
        shapeInfo.setMaxGroupPoint(maxGroupPoint.getX(),maxGroupPoint.getY());
        shapeInfo.setMinGroupPoint(minGroupPoint.getX(), minGroupPoint.getY());
        System.out.println("Max Points " + maxGroupPoint.toString());
        System.out.println("Min Points " + minGroupPoint.toString());
        shapeDetector.outlineShapeGroup();
    }
}
