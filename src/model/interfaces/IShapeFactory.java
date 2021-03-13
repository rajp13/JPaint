package model.interfaces;

import model.ShapeInfo;

public interface IShapeFactory {
    IShape create(ShapeInfo shapeInfo);
}
