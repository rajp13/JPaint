package controller;

import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class CopyShapeCommand implements Command {

    private ArrayList<IShape> clipboard;
    private ArrayList<IShape> selectedShapeList;
    private ShapeList shapeLst;

    public CopyShapeCommand(ShapeList shapeLst) {
        this.shapeLst = shapeLst;
    }

    /*
        Iterate over the selected shape list and is going
        to add the selected shape to the clipboard list

     */
    @Override
    public void execute() {
        System.out.println("Copy Button Pressed");
        clipboard = shapeLst.getClipboardLst();
        selectedShapeList = shapeLst.getSelectedShapeLst();
        clipboard.clear();
        for(IShape shape : selectedShapeList) {
            clipboard.add(shape);
            System.out.println("Added to clipboard " + clipboard.toString());
        }

        

    }

}
