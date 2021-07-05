package model;

import controller.Point;
import controller.commands.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class ShapeComposite implements IShape {

    public final ArrayList<IShape> children;
    private ShapeInfo shapeInfo;
    private ShapeList shapeList;
    private ArrayList<IShape> selectedShapes;
    private int deltaX;
    private int deltaY;
    private Point maxPoint;
    private Point minPoint;

    public ShapeComposite(ShapeInfo shapeInfo, ShapeList shapeList) {
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
        children = new ArrayList<>();
    }

    public void addChild(IShape shape) {
        children.add(shape);
    }

    public void clearChildList() {
        children.clear();
    }

    public ArrayList<IShape> getChildrenArray() {
        return children;
    }

     /*
        Math Calculations
        1.Calculate the right-most point
        2.Calculate the bottom-most point
        3.Calculate the top-most point
     */

    // gets Min starting Point
    public Point getMinPoint() {
        int minX = children.get(0).getShapeStartingPoint().getX();
        int minY = children.get(0).getShapeStartingPoint().getY();
        for(IShape shape: children) {
            if(minX > shape.getShapeStartingPoint().getX()) {
                minX = shape.getShapeStartingPoint().getX();
            }
            if(minY > shape.getShapeStartingPoint().getY()) {
                minY = shape.getShapeStartingPoint().getY();
            }
        }
        minPoint = new Point(minX,minY);
        return minPoint;
    }

    // gets max Ending Point
    public Point getMaxPoint() {
        int maximumX = children.get(0).getShapeEndingPoint().getX();
        int maximumY = children.get(0).getShapeEndingPoint().getY();
        for(IShape shape: children) {
            if(shape.getShapeEndingPoint().getX() > maximumX) {
                maximumX = shape.getShapeEndingPoint().getX();
            }
            if(shape.getShapeEndingPoint().getY() > maximumY) {
                maximumY = shape.getShapeEndingPoint().getY();
            }
        }
        maxPoint = new Point(maximumX,maximumY);
        return maxPoint;
    }

    public void setMaxPoint(Point maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void setMinPoint(Point minPoint) {
        this.minPoint = minPoint;
    }

    @Override
    public void draw(PaintCanvasBase paintCanvasBase) {
        paintCanvasBase = shapeInfo.getPaintCanvasBase();
        System.out.println("In composite Draw");
        for (IShape shape : children) {
            shape.draw(paintCanvasBase);
        }
    }
    //iterate over each child and see if there is a Collisions
    @Override
    public boolean checkCollisions(Point p1, Point p2) {
        for(IShape child : children) {
            if(child.checkCollisions(p1,p2)) {
                return true;
            }
        }
        return false;
    }

    public void moveChildren() {
        maxPoint = shapeInfo.getMaxGroupPoint();
        minPoint = shapeInfo.getMinGroupPoint();
        int dX = maxPoint.getX() - minPoint.getY();
        int dY = maxPoint.getY() - minPoint.getY();
        move(dX,dY);
    }

    //iterate over the child and call deltaX and deltaY
    @Override
    public void move(int deltaX, int deltaY) {

        for(IShape shape : children) {
           shape.move(deltaX,deltaY);
        }
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return null;
    }

    @Override
    public Point getShapeStartingPoint() {
        return null;
    }

    @Override
    public Point getShapeEndingPoint() {
        return null;
    }

    @Override
    public ShapeType getCurrentShapeType() {
        return null;
    }

}
