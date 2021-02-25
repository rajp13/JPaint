package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {

    private final ArrayList<IShape> shapeLst;
    private ArrayList<IShape> selectedShapeLst;
    private ArrayList<IShape> clipboard;
    private PaintCanvasBase paintCanvasBase;

    public ShapeList() {
        shapeLst = new ArrayList<>();
        selectedShapeLst = new ArrayList<>();
        clipboard = new ArrayList<>();
    }


    public void addShape(IShape shape) {
        shapeLst.add(shape);
        System.out.println("Added to Shape List");
        drawAllShapes();
    }

    public void removeShape(IShape shape) {
        shapeLst.remove(shape);
        System.out.println("Removed from Shape List");
        drawAllShapes();
    }

    public void addSelectShape(IShape shape) {
        selectedShapeLst.add(shape);
    }


    public void removeSelectShape(IShape shape) {
        selectedShapeLst.remove(shape);
    }

    public void clearSelectShapeList() {
        selectedShapeLst.clear();
    }

    public ArrayList<IShape> getShapeLst() {
        return shapeLst;
    }

    public ArrayList<IShape> getSelectedShapeLst() {
        return selectedShapeLst;
    }

    public ArrayList<IShape> getClipboardLst() {
        return clipboard;
    }

    public PaintCanvasBase getPaintCanvasBase() {
        return paintCanvasBase;
    }

    public void setPaintCanvasBase(PaintCanvasBase paintCanvasBase) {
        this.paintCanvasBase = paintCanvasBase;
    }



    public void drawAllShapes() {
        Graphics2D graphics2d = paintCanvasBase.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0,0,paintCanvasBase.getWidth(),paintCanvasBase.getHeight());
        for(IShape shape: shapeLst) {
            System.out.println("Drew from shape list");
            shape.draw(paintCanvasBase);
        }
    }



}
