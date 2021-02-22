package controller;

import model.ShapeList;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList shapeLst;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState,ShapeList shapeLst) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeLst = shapeLst;
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
    }
}
