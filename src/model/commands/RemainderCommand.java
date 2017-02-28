package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class RemainderCommand extends MathCommand {
	
	public RemainderCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] % parameters[1];
	}
}
