package model.commands;

import controller.Controller;
import view.Turtle;

public class LeftCommand extends TurtleCommand {

	public LeftCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		view.turn(parameters[0]);
		return parameters[0];
	}
}
