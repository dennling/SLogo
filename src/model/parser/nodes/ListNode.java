package model.parser.nodes;
import java.util.stream.Stream;

import model.parser.Argument;
import model.parser.Input;
import model.parser.TreeParser;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;

public class ListNode extends Node {
		
	public ListNode(TreeParser parser, Node parent, Input input) {
		super(parser, parent);
		int openBrackets = 0;
		String newExpression = "";
		//System.out.println(input.getWords().toString());
		while (openBrackets >= 0) {
			String word = input.getWords().get(input.getIndex());
			Token token = Tokenize.determineType(word);
			input.addToIndex(1);
			if (token == Token.LIST_START) {
				openBrackets++;
			}
			else if (token == Token.LIST_END) {
				openBrackets--;
				if (openBrackets == -1) {
					break;
				}
			}
			newExpression += word + " ";
		}
		try {
			getChildren().addAll(parser.parseInternal(newExpression.trim()).getChildren());
		} catch (Exception e) {
		}
	}

	@Override
	public Argument evaluate() {
		Argument result = new Argument();
		for (Node child: getChildren()) {
			if(child != null){
				result = child.evaluate();
			}
		}
		return result;
	}

}
