package model;

import model.interfaces.IShape;
import controller.Point;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.EnumMap;

public class CreateEllipse implements IShape {

    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endPoint;
    private ApplicationState appState;
    private ShapeColor activePrimaryColor;
    private Color primaryColor;
    private ShapeColor activeSecondaryColor;
    private Color secondaryColor;
    private ShapeShadingType shapeShadingType;
    private EnumMap<ShapeColor, Color> map;
    private int ellipseWidth;
    private int ellipseHeight;


    public CreateEllipse(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endPoint = shapeInfo.getEndPoint();
        appState = shapeInfo.getApplicationState();
        activePrimaryColor = appState.getActivePrimaryColor();
        map = shapeInfo.getColorMap();
        activePrimaryColor = appState.getActivePrimaryColor();
        primaryColor = map.get(activePrimaryColor);
        activeSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = map.get(activeSecondaryColor);
        shapeShadingType = appState.getActiveShapeShadingType();
        ellipseWidth = Math.abs(startingPoint.getX() - endPoint.getX());
        ellipseHeight = Math.abs(startingPoint.getY() - endPoint.getY());
    }



    @Override
    public void draw(PaintCanvasBase paintCanvas) {
        System.out.println("Drawing an Ellipse now");
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(primaryColor);
        int height = Math.abs(startingPoint.getY() - endPoint.getY());
        int width = Math.abs(startingPoint.getX() - endPoint.getX());
        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            System.out.println("Drawing Filled Ellipse");
            graphics2d.fillOval(startingPoint.getX(), startingPoint.getY(), width, height);
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            System.out.println("Drawing Outlined Ellipse");
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.drawOval(startingPoint.getX(),startingPoint.getY(),width,height);
        } else {
            System.out.println("Drawing Outline and Filled in Ellipse");
            graphics2d.fillOval(startingPoint.getX(), startingPoint.getY(), width, height);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(secondaryColor);
            graphics2d.drawOval(startingPoint.getX(), startingPoint.getY(), width, height);
        }
    }

    /*
        Code taken from https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
    */

    @Override
    public boolean checkCollisions(Point otherStartingPoint, Point otherEndingPoint) {
        int width = Math.abs(otherStartingPoint.getX() - otherEndingPoint.getY());
        int height = Math.abs(otherStartingPoint.getY() - otherEndingPoint.getY());
        if(startingPoint.getX() < otherStartingPoint.getX() + width && startingPoint.getX() + ellipseWidth > otherStartingPoint.getX() && startingPoint.getY() < otherStartingPoint.getY() + height &&
                startingPoint.getY() + ellipseHeight > otherStartingPoint.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        startingPoint = new Point(startingPoint.getX()+deltaX, startingPoint.getY()+deltaY);
        endPoint = new Point(endPoint.getX()+deltaX, endPoint.getY()+deltaY);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return shapeInfo;
    }

    @Override
    public Point getShapeStartingPoint() {
        return startingPoint;
    }

    @Override
    public Point getShapeEndingPoint() {
        return endPoint;
    }


    @Override
    public ShapeType getCurrentShapeType() {
        return ShapeType.ELLIPSE;
    }

}
