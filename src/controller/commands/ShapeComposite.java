package controller.commands;

import controller.Point;
import model.interfaces.IShape;

import java.util.ArrayList;

public class ShapeComposite {

    ArrayList<IShape> children;

    public ShapeComposite() {
        children = new ArrayList<>();
    }

    public void addChild(IShape shape) {
        children.add(shape);
    }


    public ArrayList<IShape> getChildren() {
        return children;
    }

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
        return new Point(minX,minY);
    }


    public Point getMaxPoint() {
        int maximumX = children.get(0).getShapeEndingPoint().getX();
        int maximumY = children.get(0).getShapeEndingPoint().getY();
        for(IShape shape: children) {
            if(shape.getShapeStartingPoint().getX() > maximumX) {
                maximumX = shape.getShapeEndingPoint().getX();
            }
            if(shape.getShapeStartingPoint().getY() > maximumY) {
                maximumY = shape.getShapeEndingPoint().getY();
            }
        }
        return new Point(maximumX,maximumY);
    }

}