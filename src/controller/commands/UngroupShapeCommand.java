package controller.commands;

import controller.Point;
import model.ShapeComposite;
import model.ShapeDetector;
import model.ShapeInfo;
import model.ShapeList;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class UngroupShapeCommand implements Command, IUndoable {

    private ShapeList shapeList;
    private ShapeInfo shapeInfo;
    private ShapeComposite shapeComposite;
    private ShapeDetector shapeDetector;
    private ArrayList<IShape> undoUnGroupList;

    public UngroupShapeCommand(ShapeList shapeList, ShapeInfo shapeInfo) {
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
        undoUnGroupList = new ArrayList<>();
    }


    @Override
    public void execute() {
        System.out.println("Ungroup Button Pressed");


        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeLst();
        ArrayList<IShape> groupShapeList = shapeList.getGroupShapesList();


        if(groupShapeList.size() > 1) {
            for (IShape shape : groupShapeList) {
                shapeList.addShape(shape);
                undoUnGroupList.add(shape);
                //selectedShapeList.remove(shape);
            }

        }
        groupShapeList.clear();
        CommandHistory.add(this);


    }

    @Override
    public void undo() {
        ArrayList<IShape> groupList = shapeList.getGroupShapesList();
        PaintCanvasBase paintCanvasBase = shapeInfo.getPaintCanvasBase();
        shapeDetector = new ShapeDetector(shapeInfo, shapeList);
        shapeComposite = new ShapeComposite(shapeInfo, shapeList);
        for(IShape shape: undoUnGroupList) {
            shapeComposite.addChild(shape);
            groupList.add(shape);
            shapeComposite.draw(paintCanvasBase);
        }
        Point maxGroupPoint = shapeComposite.getMaxPoint();
        Point minGroupPoint = shapeComposite.getMinPoint();
        shapeInfo.setMaxGroupPoint(maxGroupPoint.getX(), maxGroupPoint.getY());
        shapeInfo.setMinGroupPoint(minGroupPoint.getX(), minGroupPoint.getY());
        System.out.println("Max Points " + maxGroupPoint.toString());
        System.out.println("Min Points " + minGroupPoint.toString());
        shapeDetector.outlineShapeGroup();
    }

    @Override
    public void redo() {
        ArrayList<IShape> groupShapeList = shapeList.getGroupShapesList();


        if(groupShapeList.size() > 1) {
            for (IShape shape : groupShapeList) {
                shapeList.removeShape(shape);
                undoUnGroupList.add(shape);
                //selectedShapeList.remove(shape);
            }
        }

    }
}
