package controller;

public class UndoCommand implements Command {

    //Command Pattern
    @Override
    public void execute() {
        System.out.println("Executing the UndoCommand");
        CommandHistory.undo();
    }
}
