package model;

import controller.IShape;
import model.persistence.ApplicationState;

public class ShapeFactory {


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
