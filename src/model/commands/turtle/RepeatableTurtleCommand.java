// This entire file is part of my masterpiece.
// Dennis Ling

// This class is a subclass of TurtleCommand and is meant to utilize the command structure to perform commands on all active turtles.
// This class is well designed because it continues the hierarchy of commands with perfect encapsulation.
// This is the third class along the chain of commands that make up my masterpiece.

package model.commands.turtle;

import model.parser.Argument;
import view.visualization.TurtleManager;

public abstract class RepeatableTurtleCommand extends TurtleCommand {
	
	@Override
	protected Argument execute() {
		Argument result = new Argument();
		TurtleManager manager = getController().getTurtleManager();
		for (int i = 0; i < manager.getActiveTurtles().size(); i++) {
			setTurtle(manager.getTurtleByID(manager.getActiveTurtles().get(i).getID()));
			result = innerExecute();
		}
		return result;
	}

    protected abstract Argument innerExecute();

}
