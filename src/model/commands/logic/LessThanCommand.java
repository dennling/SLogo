package model.commands.logic;

import model.parser.Argument;

public class LessThanCommand extends LogicCommand {

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		return new Argument(super.booleanToInt(getParameter(0).getDouble() < getParameter(1).getDouble()));
	}
}
