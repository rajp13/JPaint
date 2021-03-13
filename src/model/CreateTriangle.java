package model;

import controller.Point;
import controller.commands.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.EnumMap;

public class CreateTriangle implements IShape {

    private ApplicationState appState;
    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endPoint;
    private ShapeShadingType shapeShadingType;
    private ShapeColor activePrimaryColor;
    private Color primaryColor;
    private ShapeColor activeSecondaryColor;
    private Color secondaryColor;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];
    private int rectWidth;
    private int rectHeight;
    private final EnumMap<ShapeColor, Color> colorMap;



    public CreateTriangle(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endPoint = shapeInfo.getEndPoint();
        appState = shapeInfo.getApplicationState();
        // Lazy Loading saving Cache to colorMap
        colorMap = ShapeColorSingleton.getInstance().setColorMap();
        activePrimaryColor = appState.getActivePrimaryColor();
        primaryColor = colorMap.get(activePrimaryColor);
        activeSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = colorMap.get(activeSecondaryColor);
        shapeShadingType = appState.getActiveShapeShadingType();

        // set the x points in the array
        xPoints[0] = startingPoint.getX();
        xPoints[1] = endPoint.getX();
        xPoints[2] = startingPoint.getX();

        // set the y points in the array
        yPoints[0] = startingPoint.getY();
        yPoints[1] = endPoint.getY();
        yPoints[2] = endPoint.getY();
        rectWidth = Math.abs(startingPoint.getX() - endPoint.getX());
        rectHeight = Math.abs(startingPoint.getY() - endPoint.getY());
    }

    @Override
    public void draw(PaintCanvasBase paintCanvasBase) {
        System.out.println("Drawing Triangle now");
        Graphics2D graphics2D = paintCanvasBase.getGraphics2D();
        graphics2D.setColor(primaryColor);
        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            System.out.println("Drawing FILLED_IN Triangle now");
            graphics2D.fillPolygon(xPoints,yPoints,3);
        } else if(shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            System.out.println("Drawing OUTLINE Triangle now");
            // sets the stroke tot 5 to allow the user to see a better outline of the shape
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.drawPolygon(xPoints,yPoints,3);
        } else {
            System.out.println("Drawing OUTLINE_AND_FILLED_IN Triangle");
            graphics2D.fillPolygon(xPoints,yPoints,3);
            graphics2D.setStroke(new BasicStroke(5));
            // Only need the secondaryColor if the user is drawing OUTLINE_AND_FILLED_IN
            graphics2D.setColor(secondaryColor);
            graphics2D.drawPolygon(xPoints,yPoints,3);
        }
    }

    /*
        Code taken from https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
    */

    @Override
    public boolean checkCollisions(Point otherStartingPoint, Point otherEndingPoint) {
        int width = Math.abs(otherStartingPoint.getX() - otherEndingPoint.getY());
        int height = Math.abs(otherStartingPoint.getY() - otherEndingPoint.getY());
        if(startingPoint.getX() < otherStartingPoint.getX() + width && startingPoint.getX() + rectWidth > otherStartingPoint.getX() && startingPoint.getY() < otherStartingPoint.getY() + height &&
                startingPoint.getY() + rectHeight > otherStartingPoint.getY()) {
            return true;
        }
      return false;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        startingPoint = new Point(startingPoint.getX()+deltaX, startingPoint.getY()+deltaY);
        endPoint = new Point(endPoint.getX()+deltaX, endPoint.getY()+deltaY);
        xPoints[0] = startingPoint.getX();
        xPoints[1] = endPoint.getX();
        xPoints[2] = startingPoint.getX();
        yPoints[0] = startingPoint.getY();
        yPoints[1] = endPoint.getY();
        yPoints[2] = endPoint.getY();
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
        return ShapeType.TRIANGLE;
    }

}
