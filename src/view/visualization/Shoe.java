// This entire file is part of my masterpiece.
// Jay Doherty
// note: Pen.java is also part of my masterpiece

package view.visualization;

import javafx.beans.property.ReadOnlyBooleanProperty;
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
 * splices up the path into little steps, it just gets told where to go and goes.
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
	
	protected ReadOnlyBooleanProperty readOnlyMovingProperty() {
		return ReadOnlyBooleanProperty.readOnlyBooleanProperty(isMovingProperty);
	}

	protected void setMoving(boolean moving) {
		isMovingProperty.set(moving);
	}
	
	/**
	 * @return the number of steps the turtle has remaining to its current destination, if moving
	 */
	protected int numStepsRemaining() {
		return myStepsRemaining;
	}
	
	/**
	 * This method is called by the turtle when it plans to take a step. The number of
	 * steps away it is from the destination gets reduced, and this method returns how
	 * big of a step the turtle should take.
	 * @return a Point that represents the amount of distance the turtle should move in
	 * the x and y directions
	 */
	protected Point takeStep() {
		myStepsRemaining--;
		return myStepSize;
	}
	
	/**
	 * This method sets the step size and number of steps for the turtle to take when given a certain
	 * distance to cover and the stride length at which to step.
	 * @param start : starting point of the turtle's path
	 * @param finish : destination of the turtle's path
	 * @param stepLength : length of the steps taken by the turtle
	 */
	protected void adjustStepSizeForPath(Point start, Point finish, double stepLength) {
		double displacementX = finish.getX() - start.getX();
		double displacementY = finish.getY() - start.getY();
		
		double stepsToDestination = getNumStepsAlongPath(displacementX, displacementY, stepLength);
		
		myStepsRemaining = (int)(stepsToDestination);
		double myStepSizeX = displacementX / stepsToDestination;
		double myStepSizeY = displacementY / stepsToDestination;
		myStepSize = new Point(myStepSizeX, myStepSizeY);
	}
	
	/**
	 * This is a math function that, given an x and y distance, figures out how many steps
	 * the turtle should take to make the movement as smooth as possible.
	 * @param distanceX : distance to travel in the x-direction
	 * @param distanceY : distance to travel in the y-direction
	 * @param stepLength : approx how many pixels the turtle wants to move each step
	 * @return number of steps to take
	 */
	private double getNumStepsAlongPath(double distanceX, double distanceY, double stepLength) {
		if (Math.abs(distanceY) >= Math.abs(distanceX)) {
			return Math.abs(distanceY / stepLength);
		} else {
			return Math.abs(distanceX / stepLength);
		}
	}
}
