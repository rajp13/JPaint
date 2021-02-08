package model;

import controller.IShape;
import controller.Point;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.EnumMap;

public class CreateRectangle implements IShape {

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


    public CreateRectangle(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endPoint = shapeInfo.getEndPoint();
        appState = shapeInfo.getApplicationState();
        map = shapeInfo.getColorMap();
        activePrimaryColor = appState.getActivePrimaryColor();
        primaryColor = map.get(activePrimaryColor);
        activeSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = map.get(activeSecondaryColor);
        shapeShadingType = appState.getActiveShapeShadingType();
    }


    @Override
    public void draw(PaintCanvasBase paintCanvasBase) {
        System.out.println("Drawing a Rectangle now");
        int height = Math.abs(startingPoint.getY() - endPoint.getY());
        int width = Math.abs(startingPoint.getX() - endPoint.getX());
        Graphics2D graphics2d = paintCanvasBase.getGraphics2D();
        graphics2d.setColor(primaryColor);
        if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            System.out.println("Drawing Filled Rectangle");
            graphics2d.fillRect(startingPoint.getX(), startingPoint.getY(), width, height);
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            System.out.println("Drawing Outlined Rectangle");
            // sets the stroke tot 5 to allow the user to see a better outline of the shape
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.drawRect(startingPoint.getX(),startingPoint.getY(),width,height);
        } else {
            System.out.println("Drawing OUTLINE_AND_FILLED_IN Rectangle");
            graphics2d.fillRect(startingPoint.getX(), startingPoint.getY(), width, height);
            graphics2d.setStroke(new BasicStroke(5));
            // Only need the secondaryColor if the user is drawing OUTLINE_AND_FILLED_IN Shape
            graphics2d.setColor(secondaryColor);
            graphics2d.drawRect(startingPoint.getX(), startingPoint.getY(), width, height);
        }
    }

}
