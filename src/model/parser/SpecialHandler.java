package model.parser;

import java.util.ArrayList;
import java.util.Arrays;

import controller.Controller;
import model.State;
import model.commands.Command;
import model.commands.Commands;
import model.parser.nodes.Node;

public class SpecialHandler {

	private Controller controller;
	private State state;
	private Commands commands;
	
	public SpecialHandler(Controller c, State s, Commands comm){
		controller = c;
		
	}
	
	public Node handleCmdError(String word){
		Node child = null;
		try{
			if(!controller.getUserDefinedCommands().contains(word)){
				child = commands.get(word);
				((Command) child).setup(controller, state);
			}
			controller.getView().showMessage(controller.getResources().getString("InvalidArguments"));
		}
		catch (Exception e){
			controller.getView().showMessage(String.format(controller.getResources().getString("CommandDoesNotExist"),word));
		}
		return child;
	}
	
	public String handleComment(String s) {
		ArrayList<String> commentFinder = new ArrayList<>(Arrays.asList(s.split("\\n")));
		StringBuilder sb = new StringBuilder();
		commentFinder.stream().filter(e -> !e.equals("")).filter(e -> e.trim().charAt(0) != '#')
		.forEach(e -> sb.append(e + " "));
		String result = sb.toString();
		if (result.contains("#"))
			controller.getView().showMessage(controller.getResources().getString("CommentError"));
		result.replace(",", "");
		return result;
	}
	
}
