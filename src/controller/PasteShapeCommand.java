package controller;

import model.ShapeFactory;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class PasteShapeCommand implements Command, IUndoable {


    private ShapeList shapeLst;
    private ArrayList<IShape> clipboard;
    private ArrayList<IShape> pasteShape;
    private ShapeFactory shapeFactory;
    private IShape copyShape;

//    private Point startingPoint;
//    private Point endingPoint;

    public PasteShapeCommand(ShapeList shapeLst) {
        this.shapeLst = shapeLst;
        pasteShape = new ArrayList<>();

//        startingPoint = shapeInfo.getStartingPoint();
//        endingPoint = shapeInfo.getEndPoint();
    }

    /*
        Iterate over each shape in the copied shape list and pass and
        make a call to the Shape Factory for each of the shapes

        for each shape call get shape info, make update points take shape info

     */

    @Override
    public void execute() {

        System.out.println("Paste Command Pressed ");
        clipboard = shapeLst.getClipboardLst();
        System.out.println("Clipboard size is " + clipboard.size());
        System.out.println("Paste Array size is " + pasteShape.size());
        shapeFactory = new ShapeFactory();
        for(IShape shape:clipboard) {
            updatePoints(shape,shape.getShapeInfo());
            copyShape = shapeFactory.create(shape.getShapeInfo());
            shapeLst.addShape(copyShape);
        }
        CommandHistory.add(this);
    }



    /*
        updatePoints takes in IShape and ShapeInfo and will modify the shapeInfo that is passed in
        Each IShape shape can return the specific startingPoint and endingPoint when the mouse was on draw mode
        This will offset it by 50;
     */
    private void updatePoints(IShape shape,ShapeInfo shapeInfo) {
        System.out.println("Updating Points ");
        Point startingPoint = new Point(shape.getShapeStartingPoint().getX() + 100, shape.getShapeStartingPoint().getY() + 100);
        Point endingPoint = new Point(shape.getShapeEndingPoint().getX() + 100, shape.getShapeEndingPoint().getY() + 100);
        shapeInfo.setEndPoint(endingPoint);
        shapeInfo.setStartingPoint(startingPoint);
    }


    @Override
    public void undo() {
        shapeLst.removeShape(copyShape);
    }

    @Override
    public void redo() {
        shapeLst.addShape(copyShape);
    }
}
