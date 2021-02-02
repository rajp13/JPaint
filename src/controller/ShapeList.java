package controller;

import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {

    private final ArrayList<IShape> shapeLst;
    private int size;
    private PaintCanvasBase paintCanvasBase;

    public ShapeList() {
        shapeLst = new ArrayList<>();
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

    public int getSize() {
        return size;
    }

    public ArrayList<IShape> getShapeLst() {
        return shapeLst;
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
