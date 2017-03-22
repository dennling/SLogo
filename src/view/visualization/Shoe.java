package view.visualization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import utils.Point;

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
	
	protected void adjustStepSizeForPath(double displacementX, double displacementY, double speed) {
		double stepsToDestination = getNumStepsAlongPath(displacementX, displacementY, speed);
		
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
