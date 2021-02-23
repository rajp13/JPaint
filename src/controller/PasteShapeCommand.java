package controller;

import model.ShapeFactory;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class PasteShapeCommand implements Command {


    private ShapeList shapeLst;
    private ArrayList<IShape> clipboard;
    private ArrayList<IShape> pasteShape;
    private ShapeInfo shapeInfo;
    private PaintCanvasBase paintCanvasBase;
    private ShapeFactory shapeFactory;

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
        for(IShape shape:clipboard) {
            updatePoints(shape,shape.getShapeInfo());
            shapeFactory = new ShapeFactory();
            IShape copyShape = shapeFactory.create(shape.getShapeInfo());
            shapeLst.addShape(copyShape);
        }
    }



    /*
        modify the shape info passed in

     */
    private void updatePoints(IShape shape,ShapeInfo shapeInfo) {
        System.out.println("Updating Points ");
        Point startingPoint = new Point(shape.getShapeStartingPoint().getX() + 200, shape.getShapeStartingPoint().getY() + 200);
        Point endingPoint = new Point(shape.getShapeEndingPoint().getX() + 200, shape.getShapeEndingPoint().getY() + 200);
        shapeInfo.setEndPoint(endingPoint);
        shapeInfo.setStartingPoint(startingPoint);
    }


}
