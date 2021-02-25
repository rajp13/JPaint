package model;

import controller.Point;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDetector {

    private ShapeList shapeList;
    private ShapeInfo shapeInfo;
    private controller.Point startingPoint;
    private Point endPoint;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];
    ArrayList<IShape> selectedShapeList;

    public ShapeDetector(ShapeInfo shapeInfo, ShapeList shapeList) {
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
    }


    public void outlineShape(IShape shape) {
        ShapeType shapeType = shape.getCurrentShapeType();
        PaintCanvasBase paintCanvasBase = shapeList.getPaintCanvasBase();
        Graphics2D graphics2d = paintCanvasBase.getGraphics2D();

        /*
            Set the dashed line
            Code taken from https://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
        */
        Stroke dashed = new BasicStroke(0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0);
        graphics2d.setStroke(dashed);

        startingPoint = shape.getShapeStartingPoint();
        endPoint = shape.getShapeEndingPoint();
        selectedShapeList = shapeList.getSelectedShapeLst();
        System.out.println(selectedShapeList.size());
        if (selectedShapeList.contains(shape) && shape.checkCollisions(startingPoint, endPoint)) {
            if (shapeType.equals(ShapeType.RECTANGLE)) {
                int height = Math.abs(startingPoint.getY() - endPoint.getY());
                int width = Math.abs(startingPoint.getX() - endPoint.getX());
                graphics2d.drawRect(startingPoint.getX() - 5, startingPoint.getY() - 5, width + 10, height + 10);
            } else if (shapeType.equals(ShapeType.ELLIPSE)) {
                int height = Math.abs(startingPoint.getY() - endPoint.getY());
                int width = Math.abs(startingPoint.getX() - endPoint.getX());
                graphics2d.drawOval(startingPoint.getX() - 5, startingPoint.getY() - 5, width + 10, height + 10);
            } else if (shapeType.equals(ShapeType.TRIANGLE)) {
                // set the x points in the array
                xPoints[0] = startingPoint.getX();
                xPoints[1] = endPoint.getX();
                xPoints[2] = startingPoint.getX();

                // set the y points in the array
                yPoints[0] = startingPoint.getY();
                yPoints[1] = endPoint.getY();
                yPoints[2] = endPoint.getY();
                graphics2d.drawPolygon(xPoints, yPoints, 3);
            }
        }
    }

}
