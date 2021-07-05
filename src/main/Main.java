package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.PaintMouseHandler;
import model.ShapeDrawer;
import model.ShapeInfo;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class Main {
    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ShapeInfo and ShapeLst will be passed throughout the application
        ShapeInfo shapeInfo = new ShapeInfo();
        ShapeList shapeLst = new ShapeList();

        ShapeDrawer shapeDrawer = new ShapeDrawer(paintCanvas,shapeLst);
        shapeLst.registerObserver(shapeDrawer);

        IJPaintController controller = new JPaintController(uiModule, appState,shapeLst,shapeInfo);
        paintCanvas.addMouseListener(new PaintMouseHandler(paintCanvas, appState,shapeInfo,shapeLst));

        controller.setup();

    }
}
