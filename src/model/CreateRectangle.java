package model;

import model.interfaces.IShape;
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
    private int rectWidth;
    private int rectHeight;


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
        rectWidth = Math.abs(startingPoint.getX() - endPoint.getX());
        rectHeight = Math.abs(startingPoint.getY() - endPoint.getY());
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
    /*
            https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
		   if (rect1.x < rect2.x + rect2.width &&
               rect1.x + rect1.width > rect2.x &&
               rect1.y < rect2.y + rect2.height &&
               rect1.y + rect1.height > rect2.y) {
                // collision detected!
                rect1.x < rect2.x + rect2.width &&
                rect1.x + rect1.width > rect2.x &&
                 rect1.y < rect2.y + rect2.height &&
                  rect1.y + rect1.height > rect2.y
            }
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
    }


}
