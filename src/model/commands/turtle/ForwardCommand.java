// This entire file is part of my masterpiece.
// Dennis Ling

// This class implements the functionality of our favorite command, forward!
// This class is well designed because it utilizes the structure of the command super and subclasses.
// It also utilizes the methods in its TurtleCommand superclass.
// This is the fourth class along the chain of commands that make up my masterpiece.

package model.commands.turtle;

import model.parser.Argument;

public class ForwardCommand extends RepeatableTurtleCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}
	@Override
	protected Argument innerExecute() {
		Argument result = getParameter(0);
		getTurtle().moveTo(endLocation(result.getDouble(), getTurtle()));
		return result;
	}
	
}