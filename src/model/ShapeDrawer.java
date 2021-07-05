package model;

import model.interfaces.IObserver;
import controller.commands.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDrawer implements IObserver {

    private PaintCanvasBase paintCanvasBase;
    private ShapeList shapeList;
    private ArrayList<IShape> shapeLst;

    // ShapeDrawer implements my IObserver interface and will call update() which redraws the shapes

    public ShapeDrawer(PaintCanvasBase paintCanvasBase,ShapeList shapeList) {
        this.paintCanvasBase = paintCanvasBase;
        this.shapeList = shapeList;
        shapeLst = shapeList.getShapeLst();
    }

    @Override
    public void update() {
        Graphics2D graphics2d = paintCanvasBase.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0,0,paintCanvasBase.getWidth(),paintCanvasBase.getHeight());
        for(IShape shape: shapeLst) {
            System.out.println("Drew from shape list");
            shape.draw(paintCanvasBase);
        }
    }

}
