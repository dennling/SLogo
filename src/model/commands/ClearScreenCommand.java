package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class ClearScreenCommand extends TurtleCommand {

	public ClearScreenCommand(int numParameters, String name) {
		super(0, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		view.clearDisplay();
		view.moveTo(new Point(0, 0));
		return super.distance(parameters, myTurtle);
	}
}
