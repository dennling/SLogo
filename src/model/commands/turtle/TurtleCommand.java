package model.commands.turtle;

import controller.Controller;
import model.commands.Command;
import utils.Point;
import view.visualization.Turtle;

public abstract class TurtleCommand extends Command {

	protected TurtleCommand() {
		super();
	}

	public abstract double execute(double[] parameters, Turtle turtle, Controller view);
	
    protected Point endLocation(double parameters, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * parameters);
        double y = (Math.sin(rad) * parameters);

        return new Point(t.getLocation().getX() + x, t.getLocation().getY() + y);
    }
    
    protected double distance(double[] parameters, Turtle t) {
    	double x = parameters[0]-t.getLocation().getX();
		double y = parameters[1]-t.getLocation().getY();
		return Math.sqrt(x*x+y*y);
    }
    
    protected double distance(Point point1, Point point2) {
    	return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
    
}
