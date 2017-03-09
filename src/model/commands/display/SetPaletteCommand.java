package model.commands.display;

import javafx.scene.paint.Color;
import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPaletteCommand extends TurtleCommand{
	public SetPaletteCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 4;
	}

	@Override
	public Argument execute() {
		int index = (int)this.getParameter(0).getDouble();
		int r = (int)this.getParameter(1).getDouble();
		int g = (int)this.getParameter(2).getDouble();
		int b = (int)this.getParameter(3).getDouble();
		getController().getColorPalette().get(index-1).colorProperty().setValue(Color.rgb(r, g, b));
		return null;
	}
	
}