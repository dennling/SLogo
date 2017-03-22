package view.visualization;

import utils.Point;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Jay Doherty
 * This class encapsulates all of the Turtle's behavior into an interface. Methods called on this
 * class can move the turtle around the display.
 */
public class Turtle {
	private TurtleDisplay myDisplay;
	private int myID;
	private SimpleBooleanProperty isActiveProperty;

	private Graphic myTurtleGraphic;
	private Pen myPen;
	private Compass myCompass;
	private Schedule mySchedule;
	private Shoe myShoe;
	
	protected Turtle(TurtleDisplay home, int ID, Image image) {
		myDisplay = home;
		myID = ID;
		isActiveProperty = new SimpleBooleanProperty(true);
		
		myTurtleGraphic = new Graphic(myDisplay, image, 1);
		myTurtleGraphic.bindOpacityTo(isActiveProperty);
		
		myPen = new Pen(myDisplay, true, Color.BLACK, 1, 1.0);
		myCompass = new Compass();
		mySchedule = new Schedule();
		myShoe = new Shoe();
		
		this.setLocation(new Point(0, 0));
		this.setRotation(90.0);
	}

	public int getID() {
		return myID;
	}

	public void moveTo(Point point) {
		myDisplay.moveTurtle(this, point);
	}
	
	public void turn(double degrees) {
		this.setRotation(myCompass.getHeading() + degrees);
	}

	public Point getDestination() {
		return mySchedule.getDestination();
	}

	public double getRotation() {
		return myCompass.getHeading();
	}

	public boolean isPenDown() {
		return myPen.isDown();
	}
	
	public void setPenDown(boolean down) {
		myPen.setDown(down);
	}

	public Color getPenColor() {
		return myPen.getColor();
	}
	
	public void setPenColor(Color color) {
		myPen.setColor(color);
	}
	
	public int getPenColorIndex() {
		return myPen.getColorIndex();
	}
	
	public void setColorIndex(int index) {
		myPen.setColorIndex(index);
	}

	public void setPenWidth(double width) {
		myPen.setThickness(width);
	}
	
	public boolean isVisible() {
		return myTurtleGraphic.isVisible();
	}
	
	public void setVisible(boolean visible) {
		myTurtleGraphic.setVisible(visible);
	}
	
	public void setImage(String url) {
		myTurtleGraphic.setImage(url);
		myTurtleGraphic.setCenter(myCompass.getLocation());
	}

	public int getShapeIndex() {
		return myTurtleGraphic.getIndex();
	}
	
	public void setShapeIndex(int index) {
		myTurtleGraphic.setIndex(index);;
	}

	protected ImageView getView() {
		return myTurtleGraphic.getView();
	}
	
	protected BooleanProperty activeProperty() {
		return isActiveProperty;
	}
	
	protected BooleanProperty isMovingProperty() {
		return myShoe.movingProperty();
	}

	protected ReadOnlyBooleanProperty readOnlyPenDownProperty() {
		return myPen.readOnlyIsDownProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyXProperty() {
		return myCompass.readOnlyXProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyYProperty() {
		return myCompass.readOnlyYProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyRotationProperty() {
		return myCompass.readOnlyRotationProperty();
	}

	protected Schedule getSchedule() {
		return mySchedule;
	}
	
	protected void setDestination(Point destination, double speed) {
		double distX = destination.getX() - myCompass.getX();
		double distY = destination.getY() - myCompass.getY();
		
		myShoe.setMoving(true);
		myShoe.adjustStepSizeForPath(distX, distY, speed);

		mySchedule.setDestination(destination);
	}

	protected void updateMovement() {
		if (myShoe.numStepsRemaining() > 0) {
			this.stepTowardsDestination();
		} else {
			myShoe.setMoving(false);
		}
	}
	
	private void stepTowardsDestination() {
		Point step = myShoe.takeStep();
		Point nextLocation = new Point(myCompass.getX() + step.getX(), myCompass.getY() + step.getY());

		Point adjustedStart = myCompass.getLocation();
		Point adjustedFinish = nextLocation;
		if (!myDisplay.isInBounds(adjustedStart) && !myDisplay.isInBounds(adjustedFinish)) {
			adjustedStart = myDisplay.wrapIntoView(adjustedStart);
			adjustedFinish = myDisplay.wrapIntoView(adjustedFinish);
		}
		myPen.drawLine(adjustedStart, adjustedFinish);

		this.setLocation(nextLocation);
	}

	private void setLocation(Point point) {
		myCompass.setX(point.getX());
		myCompass.setY(point.getY());
		myTurtleGraphic.setCenter(point);
	}
	
	private void setRotation(double degrees) {
		myCompass.setHeading(degrees % 360);
		myTurtleGraphic.setRotation(degrees);
	}
}
