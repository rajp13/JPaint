package model;

import model.interfaces.IObserver;
import controller.commands.IShape;
import model.interfaces.ISubject;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class ShapeList implements ISubject {

    private final ArrayList<IShape> shapeLst;
    private ArrayList<IShape> selectedShapeLst;
    private ArrayList<IShape> clipboard;
    private ArrayList<IShape> groupShapes;
    private ArrayList<IObserver> observers;
    private PaintCanvasBase paintCanvasBase;

    public ShapeList() {
        shapeLst = new ArrayList<>();
        selectedShapeLst = new ArrayList<>();
        clipboard = new ArrayList<>();
        groupShapes = new ArrayList<>();
        observers = new ArrayList<>();
    }


    public void addShape(IShape shape) {
        shapeLst.add(shape);
        System.out.println("Added to Shape List");
        //drawAllShapes();
        notifyObservers();
    }

    public void removeShape(IShape shape) {
        shapeLst.remove(shape);
        System.out.println("Removed from Shape List");
        //drawAllShapes();
        notifyObservers();
    }

    public void addSelectShape(IShape shape) {
        selectedShapeLst.add(shape);
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

    public ArrayList<IShape> getGroupShapesList() {
        return groupShapes;
    }


    public PaintCanvasBase getPaintCanvasBase() {
        return paintCanvasBase;
    }

    public void setPaintCanvasBase(PaintCanvasBase paintCanvasBase) {
        this.paintCanvasBase = paintCanvasBase;
    }

    /*
    public void drawAllShapes() {
        Graphics2D graphics2d = paintCanvasBase.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0,0,paintCanvasBase.getWidth(),paintCanvasBase.getHeight());
        for(IShape shape: shapeLst) {
            System.out.println("Drew from shape list");
            shape.draw(paintCanvasBase);
        }
    }

     */

    /*
        Lecture 4 Notes:
        When you add or remove a shape from your shapelist.
        ShapeList be a subject
        And have a class that draws your shapes ShapeDrawer
        ShapeDrawer will be an Observer
     */

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }


    private void notifyObservers() {
        for(IObserver observer: observers) {
            observer.update();
        }
    }

}
