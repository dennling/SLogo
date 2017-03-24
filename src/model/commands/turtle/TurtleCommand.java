// This entire file is part of my masterpiece.
// Dennis Ling

// This class is a superclass of Command that also acts as a superclass. It is meant to handle all of the commands that have to directly deal with Turtle objects.
// This class is well designed because it contains perfect encapsulation of useful methods to minimize duplicated code and maximize utility of said methods.
// All of these protected methods act as a useful way to stay open for extension, yet closed for modification.
// This is the second class along the chain of commands that make up my masterpiece.

package model.commands.turtle;

import model.commands.Command;
import model.parser.Argument;
import utils.Point;
import view.visualization.Turtle;

public abstract class TurtleCommand extends Command {

    protected Turtle getTurtle() {
    	return getController().getTurtleManager().getCurrentTurtle();
    }    
    
	protected void setTurtle(Turtle turtle) {
		getController().getTurtleManager().setCurrentTurtle(turtle);
	}
	
    protected Point endLocation(double parameters, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * parameters);
        double y = (Math.sin(rad) * parameters);
        return new Point(t.getDestination().getX() + x, t.getDestination().getY() + y);
    }
 
    protected double distance(Point point1, Point point2) {
    	return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
    
    protected abstract Argument execute();
    
}
