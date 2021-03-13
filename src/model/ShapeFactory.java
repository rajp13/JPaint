package model;

import controller.commands.IShape;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;
import view.CreateEllipse;
import view.CreateRectangle;
import view.CreateTriangle;

public class ShapeFactory implements IShapeFactory {

    /*
        Create an Interface called IShapeFactory that has a method called create
     */

    @Override
    public IShape create(ShapeInfo shapeInfo) {
        IShape shape = null;
        ApplicationState applicationState = shapeInfo.getApplicationState();
        ShapeType shapeType = applicationState.getActiveShapeType();

        switch (shapeType)  {
            case ELLIPSE -> shape = new CreateEllipse(shapeInfo);
            case RECTANGLE -> shape = new CreateRectangle(shapeInfo);
            case TRIANGLE -> shape = new CreateTriangle(shapeInfo);
        }

        return shape;
    }

}
