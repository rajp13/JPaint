package controller;

import view.interfaces.PaintCanvasBase;

public class CreateShapeCommand implements Command, IUndoable {

    private PaintCanvasBase paintCanvasBase;
    private ShapeInfo shapeInfo;
    private IShape shape;
    private final ShapeList shapeLst;

    public CreateShapeCommand (PaintCanvasBase paintCanvasBase,ShapeInfo shapeInfo, ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeList;
    }

    @Override
    public void execute() {
        System.out.println("Create Shape " + shapeInfo.toString());
        ShapeFactory shapeFactory = new ShapeFactory();
        shape = shapeFactory.create(paintCanvasBase,shapeInfo);
        // Add on the shape list
        shapeLst.setPaintCanvasBase(paintCanvasBase);
        shapeLst.addShape(shape);
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
        shapeLst.removeShape(shape);
    }

    @Override
    public void redo() {
        shapeLst.addShape(shape);
    }
}
