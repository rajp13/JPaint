package model;

import controller.Point;
import model.persistence.ApplicationState;

import java.awt.*;
import java.util.EnumMap;

public class ShapeInfo {

    private Point endPoint;
    private Point startingPoint;
    private ApplicationState appState;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    /*
        Group
     */
    private Point maxGroupPoint;
    private Point minGroupPoint;

    private EnumMap<ShapeColor,java.awt.Color> map = new EnumMap<>(ShapeColor.class);

    public ShapeInfo() {
        setColorMap();
    }


    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }


    public void setApplicationState(ApplicationState applicationState) {
        this.appState = applicationState;
    }

    public ApplicationState getApplicationState() {
        return appState;
    }

    public void setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;
    }
    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    public String toString() {
        return "ShapeInfo:  Starting Point (" +  this.startingPoint + ") (" + this.endPoint + ") ";
    }


    private void setColorMap() {
        map.put(ShapeColor.BLACK, Color.BLACK);
        map.put(ShapeColor.BLUE,Color.BLUE);
        map.put(ShapeColor.CYAN,Color.CYAN);
        map.put(ShapeColor.DARK_GRAY,Color.DARK_GRAY);
        map.put(ShapeColor.GRAY,Color.GRAY);
        map.put(ShapeColor.GREEN,Color.GREEN);
        map.put(ShapeColor.LIGHT_GRAY,Color.LIGHT_GRAY);
        map.put(ShapeColor.MAGENTA,Color.MAGENTA);
        map.put(ShapeColor.ORANGE,Color.ORANGE);
        map.put(ShapeColor.PINK,Color.PINK);
        map.put(ShapeColor.RED,Color.RED);
        map.put(ShapeColor.WHITE,Color.WHITE);
        map.put(ShapeColor.YELLOW,Color.YELLOW);
    }

    public EnumMap<ShapeColor,java.awt.Color> getColorMap() {
        return map;
    }

    public void setMaxGroupPoint(int x,int y) {
        maxGroupPoint = new Point(x,y);
    }

    public Point getMaxGroupPoint() {
        return maxGroupPoint;
    }

    public void setMinGroupPoint(int x,int y) {
        minGroupPoint = new Point(x,y);
    }

    public Point getMinGroupPoint() {
        return minGroupPoint;
    }

}
