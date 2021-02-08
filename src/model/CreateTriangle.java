package model;

import controller.IShape;
import controller.Point;
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



    public CreateTriangle(ShapeInfo shapeInfo) {
//        this.paintCanvas = paintCanvas;
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endPoint = shapeInfo.getEndPoint();
        appState = shapeInfo.getApplicationState();
        EnumMap<ShapeColor, Color> colorMap = shapeInfo.getColorMap();
        activePrimaryColor = appState.getActivePrimaryColor();
        primaryColor = colorMap.get(activePrimaryColor);
        activeSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = colorMap.get(activeSecondaryColor);
        shapeShadingType = appState.getActiveShapeShadingType();
        xPoints[0] = startingPoint.getX();
        xPoints[1] = endPoint.getX();
        xPoints[2] = startingPoint.getX();
        yPoints[0] = startingPoint.getY();
        yPoints[1] = endPoint.getY();
        yPoints[2] = endPoint.getY();
    }

    /*
    public void setX() {
        xPoints[0] = startingPoint.getX();
        xPoints[1] = endPoint.getX();
        xPoints[2] = startingPoint.getX();
    }

    public void setY() {
        yPoints[0] = startingPoint.getY();
        yPoints[1] = endPoint.getY();
        yPoints[2] = endPoint.getY();
    }
    */

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
}
