package controller.commands;

import model.ShapeFactory;
import model.ShapeInfo;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class CreateShapeCommand implements Command, IUndoable {

    private PaintCanvasBase paintCanvasBase;
    private ShapeInfo shapeInfo;
    private IShape shape;
    private final ShapeList shapeLst;
    private ApplicationState appState;

    public CreateShapeCommand (PaintCanvasBase paintCanvasBase, ApplicationState appState,ShapeInfo shapeInfo, ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.appState = appState;
        this.shapeInfo = shapeInfo;
        this.shapeLst = shapeList;
    }

    @Override
    public void execute() {
        System.out.println("Create Shape Command " + shapeInfo.toString());
        ShapeFactory shapeFactory = new ShapeFactory();
        shape = shapeFactory.create(shapeInfo);
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
