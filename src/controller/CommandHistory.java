package controller;

import java.util.Stack;

class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<>();
	private static final Stack<IUndoable> redoStack = new Stack<>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
		System.out.println("Calling Command History add");
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
			System.out.println("Calling Command History undo method!");
		}

		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
			System.out.println("Calling Command History redo method!");
		}
		return result;
	}
}
