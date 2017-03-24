// This entire file is part of my masterpiece.
// Dennis Ling

// This class implements the functionality of the "to" command that creates user commands.
// This class is well designed because it utilizes the superclass methods and overrides them for the necessary functionality.
// It can also be noted that this class utilizes the public methods (as supplied by the API) in order to maintain full functionality.
// This is the last class along the chain of commands that make up my masterpiece.

package model.commands.control;

import java.util.ArrayList;
import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.ListNode;
import model.parser.nodes.Node;

public class MakeUserInstructionCommand extends Command {
	@Override
	protected int internalNumParameters() {
		return 3;
	}
	
	@Override
	protected Argument execute() {
		String name = getParameter(0).getString();		
		ArrayList<String> variableNames = new ArrayList<String>();
		for (Node variable : getChildren().get(1).getChildren()) {
			if (variable != null) {
				variableNames.add(":" + variable.evaluate().getString());
			}
		}
		String expression = ((ListNode) getChildren().get(2)).getExpression();
		getState().setCommand(new UserCommand(name, variableNames, expression), name);
		return new Argument(1);
	}
}