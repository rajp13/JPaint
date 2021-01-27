package controller;

import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PaintMouseHandler extends MouseAdapter implements Shape {
    private Point startingPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvasBase;
    ArrayList shapeList = new ArrayList<Shape>();

    public PaintMouseHandler(PaintCanvasBase paintCanvasBase) {
        this.paintCanvasBase = paintCanvasBase;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startingPoint = new Point(e.getX(),e.getY());
    }

    /*
         Get the endPoint in the same way by overriding the mouseReleased event.
         Calculate the height and width by doing math on startPoint and endPoint
       */
    @Override
    public void mouseReleased(MouseEvent e) {
        // gets the end Points
        endPoint = new Point(e.getX(),e.getY());
        draw(paintCanvasBase);
    }


    @Override
    public void draw(PaintCanvasBase paintCanvasBase) {
        int width = Math.abs(endPoint.getX()-startingPoint.getX());
        int height = Math.abs(endPoint.getY()-startingPoint.getY());
        Graphics2D graphics2D = paintCanvasBase.getGraphics2D();
        graphics2D.setColor(Color.blue);
        graphics2D.fillRect(startingPoint.getX(),startingPoint.getY(),width,height);
    }




}
