package model;

import controller.IShape;
import controller.Point;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.EnumMap;

public class CreateEllipse implements IShape {

    private ShapeInfo shapeInfo;
    private Point startingPoint;
    private Point endingPoint;
    private ApplicationState appState;
    private ShapeColor activePrimaryColor;
    private Color primaryColor;
    private ShapeColor activeSecondaryColor;
    private Color secondaryColor;
    private ShapeShadingType shapeShadingType;
    private EnumMap<ShapeColor, Color> map;


    public CreateEllipse(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        startingPoint = shapeInfo.getStartingPoint();
        endingPoint = shapeInfo.getEndPoint();
        appState = shapeInfo.getApplicationState();
        activePrimaryColor = appState.getActivePrimaryColor();
        map = shapeInfo.getColorMap();
        activePrimaryColor = appState.getActivePrimaryColor();
        primaryColor = map.get(activePrimaryColor);
        activeSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = map.get(activeSecondaryColor);
        shapeShadingType = appState.getActiveShapeShadingType();
    }



    @Override
    public void draw(PaintCanvasBase paintCanvas) {
        System.out.println("Drawing an Ellipse now");
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(primaryColor);
        int height = Math.abs(startingPoint.getY() - endingPoint.getY());
        int width = Math.abs(startingPoint.getX() - endingPoint.getX());
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
}
