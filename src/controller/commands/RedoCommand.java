package controller.commands;

public class RedoCommand implements Command{

    //Command Pattern
    @Override
    public void execute() {
        System.out.println("Executing the Redo Command");
        CommandHistory.redo();
    }
}
