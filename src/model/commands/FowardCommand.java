package model.commands;

import controller.Controller;
import utils.Point;
import view.Turtle;

public class FowardCommand extends TurtleCommand {

	public FowardCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		Point loc = super.endLocation(parameters[0], myTurtle);
		view.moveTo(loc);
		return parameters[0];
	}
}
