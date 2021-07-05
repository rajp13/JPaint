package model;

import controller.Point;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.EnumMap;

public class ShapeInfo {

    private Point endPoint;
    private Point startingPoint;
    private ApplicationState appState;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private PaintCanvasBase paintCanvasBase;

    /*
        Group
     */
    private Point maxGroupPoint;
    private Point minGroupPoint;

    private EnumMap<ShapeColor,java.awt.Color> colorMap = new EnumMap<>(ShapeColor.class);

    public ShapeInfo() {

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


    public void setColorMap(EnumMap<ShapeColor,java.awt.Color> colormap) {
        this.colorMap = colormap;
    }

    public EnumMap<ShapeColor,java.awt.Color> getColorMap() {
        return colorMap;
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

    public void setPaintCanvasBase(PaintCanvasBase paintCanvasBase) {
        this.paintCanvasBase = paintCanvasBase;
    }

    public PaintCanvasBase getPaintCanvasBase() {
        return paintCanvasBase;
    }

}
