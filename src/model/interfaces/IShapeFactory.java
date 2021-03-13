package model.interfaces;

import controller.commands.IShape;
import model.ShapeInfo;

public interface IShapeFactory {
    IShape create(ShapeInfo shapeInfo);
}
