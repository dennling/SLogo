// This entire file is part of my masterpiece.
// Jay Doherty
// note: Pen.java is also part of my masterpiece

package view.visualization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import utils.Point;

/**
 * @author Jay Doherty
 * This class encapsulates the methods used to determine if the turtle is moving and what
 * direction it should step in. Note all of the methods and constructor are protected because
 * this class is purely implementation details for the turtle and outside classes don't need to
 * worry about it.
 * 
 * Every turtle has a shoe object that it relies on to implement movement. When a turtle gets a 
 * new location to travel to, it calls "Shoe.adjustStepSizeForPath" and passes in the distance 
 * it wants to travel. Then it makes repeated calls to "Shoe.takeStep" until the Shoe runs out 
 * of steps remaining. In this way, the turtle doesn't need to worry about the implementation that 
 * splices up the path into little steps. This is needed to support animation.
 */
public class Shoe {

	private SimpleBooleanProperty isMovingProperty;
	private int myStepsRemaining;
	private Point myStepSize;
	
	protected Shoe() {
		isMovingProperty = new SimpleBooleanProperty(false);
		myStepsRemaining = 0;
		myStepSize = new Point(0,0);
	}
	
	protected BooleanProperty movingProperty() {
		return isMovingProperty;
	}

	protected void setMoving(boolean moving) {
		isMovingProperty.set(moving);
	}
	
	protected int numStepsRemaining() {
		return myStepsRemaining;
	}
	
	protected Point takeStep() {
		myStepsRemaining--;
		return myStepSize;
	}
	
	/**
	 * This method sets the step size and number of steps for the turtle to take when given a certain
	 * distance to cover and the stride length at which to step.
	 * @param displacementX : displacement to cover in the x-direction
	 * @param displacementY : displacement to cover in the y-direction
	 * @param stepLength : length of the steps taken by the turtle
	 */
	protected void adjustStepSizeForPath(double displacementX, double displacementY, double stepLength) {
		double stepsToDestination = getNumStepsAlongPath(displacementX, displacementY, stepLength);
		
		myStepsRemaining = (int)(stepsToDestination);
		double myStepSizeX = displacementX / stepsToDestination;
		double myStepSizeY = displacementY / stepsToDestination;
		myStepSize = new Point(myStepSizeX, myStepSizeY);
	}
	
	private double getNumStepsAlongPath(double distanceX, double distanceY, double stepLength) {
		if (Math.abs(distanceY) >= Math.abs(distanceX)) {
			return Math.abs(distanceY / stepLength);
		} else {
			return Math.abs(distanceX / stepLength);
		}
	}
}
