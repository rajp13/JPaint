package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintMouseHandler extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released");

    }


}
