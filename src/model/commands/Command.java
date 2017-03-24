// This entire file is part of my masterpiece.
// Dennis Ling

// This class is meant to supply the framework for all of the commands; every single command will extend this class or a subclass of this class.
// This class is well designed because it encapsulates the methods that can be hidden, and only supplies the necessary evaluate, setup, and numParameters methods as public.
// This is the first class along the chain of commands that make up my masterpiece.

package model.commands;

import controller.Controller;
import model.State;
import model.parser.Argument;
import model.parser.nodes.Node;

/*
 * Command superclass that evaluates its function, and accesses the controller to send actions to the front end.
 */
public abstract class Command extends Node {

	private Controller myController;
	private State myState;

	protected Controller getController() {
		return myController;
	}

	protected State getState() {
		return myState;
	}
	
	public void setup(Controller controller, State state) {
		this.myController = controller;
		this.myState = state;
	}

	public Argument evaluate() {
		try {
			return execute();
		} catch (Exception e) {
			getController().getView().showMessage(myController.getResources().getString("CantEvaluate"));;
		}
		return new Argument();
	}
	
	protected abstract Argument execute();
	
	protected abstract int internalNumParameters();
	
	public int numParameters() {
		return internalNumParameters();
	}
	
}
