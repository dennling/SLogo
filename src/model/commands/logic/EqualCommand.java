package model.commands.logic;

import controller.Controller;
import view.visualization.Turtle;

public class EqualCommand extends LogicCommand {

	public EqualCommand() {
		super();
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] == parameters[1]);
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return super.booleanToInt(this.getParameterList().get(0) == this.getParameterList().get(1));
	}
}
