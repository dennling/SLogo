package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PiCommand extends MathCommand {
	
	public PiCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.PI;
	}
}