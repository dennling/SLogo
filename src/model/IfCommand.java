package model;

import controller.Controller;
import model.commands.LogicCommand;
import view.visualization.Turtle;

public class IfCommand extends LogicCommand {

	public IfCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] != 0);
	}
}