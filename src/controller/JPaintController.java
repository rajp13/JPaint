package controller;

import controller.commands.*;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList shapeLst;
    private ShapeInfo shapeInfo;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeLst, ShapeInfo shapeInfo) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeLst = shapeLst;
        this.shapeInfo = shapeInfo;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, ()-> new UndoCommand().execute());
        uiModule.addEvent(EventName.REDO, ()-> new RedoCommand().execute());
        uiModule.addEvent(EventName.COPY,() -> new CopyShapeCommand(shapeLst).execute());
        uiModule.addEvent(EventName.PASTE, () -> new PasteShapeCommand(shapeLst).execute());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteShapeCommand(shapeLst).execute());
        uiModule.addEvent(EventName.GROUP, () -> new GroupShapeCommand(shapeLst,shapeInfo).execute());
    }
}
