package model;

import java.awt.*;
import java.util.EnumMap;

public class ShapeColorSingleton {

    // static variable to hold one instance of ShapeColorSingleton
    private static ShapeColorSingleton shapeColor;
    private final static EnumMap<ShapeColor, Color> map = new EnumMap<>(ShapeColor.class);

    //constructor is declared private so only ShapeColorSingleton can instantiate this class
    private ShapeColorSingleton() {

    }

    public static ShapeColorSingleton getInstance() {
        if(shapeColor == null) {
            shapeColor = new ShapeColorSingleton();
        }
        return shapeColor;
    }

    public EnumMap<ShapeColor, Color> setColorMap() {
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
        return map;
    }



}
