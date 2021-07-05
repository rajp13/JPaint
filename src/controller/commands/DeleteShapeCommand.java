package controller.commands;

import model.ShapeList;

import java.util.ArrayList;

public class DeleteShapeCommand implements Command, IUndoable {

    private ShapeList mainShapeList;
    private ArrayList<IShape> selectedShapeList;
    private ArrayList<IShape> deletedShapesList;

    public DeleteShapeCommand(ShapeList mainShapeList) {
        this.mainShapeList = mainShapeList;
        deletedShapesList = new ArrayList<>();
    }

    /*
        Have a referece to the main shape list
        Iterate over the selectedShapeList it's going to remove the shapes in the mainShapeList
    */

    @Override
    public void execute() {
        selectedShapeList = mainShapeList.getSelectedShapeLst();
        for(IShape shape : selectedShapeList) {
            deletedShapesList.add(shape);
            mainShapeList.removeShape(shape);
        }
        CommandHistory.add(this);
    }

    /*
        Delete Shape Command will be the exact opposite of the undo/redo functionality in the CreateShapeCommand
        Your Delete Shape Command will maintain a reference to the shapes that were removed.
    */

    @Override
    public void undo() {

        for(IShape shape: deletedShapesList) {
            mainShapeList.addShape(shape);
        }

    }

    @Override
    public void redo() {

        for(IShape shape: deletedShapesList) {
            mainShapeList.removeShape(shape);
        }

    }

}
