// This entire file is part of my masterpiece.
// Jay Doherty
// note: Shoe.java is also part of my masterpiece

package view.visualization;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import utils.Point;

/**
 * @author Jay Doherty
 * This class encapsulates the part of the turtle that draws lines on the display. Note all of the
 * methods and constructor are protected because this class is purely implementation details for 
 * the turtle and outside classes don't need to worry about it.
 * 
 * This component abstracts the "line-drawing" functionality of the the turtle, so the turtle 
 * can just tell its Pen object to draw a line, and the Pen will worry about whether or not it 
 * is pressed down, and whether or not the line will fit on the display, etc.
 */
public class Pen {

	private TurtleDisplay display;
	private SimpleBooleanProperty isDownProperty;
	private Color color;
	private double thickness;
	private int colorIndex;
	
	protected Pen(TurtleDisplay canvas, boolean isDown, Color initialColor, int initialColorIndex, double initialWidth) {
		display = canvas;
		isDownProperty = new SimpleBooleanProperty(isDown);
		color = initialColor;
		colorIndex = initialColorIndex;
		thickness = initialWidth;
	}

	/**
	 * This method draws a line to the display. If the line to draw is out of bounds it will try
	 * to wrap the line around in bounds, but sometimes this fails (ie the start point gets wrapped
	 * to the left side of the screen and the end point gets wrapped to the right side of the screen).
	 * Therefore it does an additional check to make sure the line doesn't get wrapped across the screen.
	 * @param start : the start point of the line to draw
	 * @param finish : the end point of the line to draw
	 */
	protected void drawLine(Point start, Point finish) {
		if(isDownProperty.get()) {
			double originalLineLength = distance(start,finish);
			if (!display.isInBounds(start) && !display.isInBounds(finish)) {
				start = display.wrapIntoView(start);
				finish = display.wrapIntoView(finish);
			}
			double newLineLength = distance(start,finish);
			
			if(originalLineLength == newLineLength) {
				Line line = this.makeLine(start, finish);
				display.addToDisplayArea(line);
			}
		}
	}
	
	protected ReadOnlyBooleanProperty readOnlyIsDownProperty() {
		return ReadOnlyBooleanProperty.readOnlyBooleanProperty(isDownProperty);
	}
	
	protected boolean isDown() {
		return isDownProperty.get();
	}
	
	protected void setDown(boolean down) {
		isDownProperty.set(down);
	}
	
	protected Color getColor() {
		return color;
	}
	
	protected void setColor(Color c) {
		color = c;
	}
	
	protected int getColorIndex() {
		return colorIndex;
	}
	
	protected void setColorIndex(int index) {
		colorIndex = index;
	}
	
	protected void setThickness(double width) {
		thickness = width;
	}
	
	private Line makeLine(Point start, Point finish) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		line.setStroke(color);
		line.setStrokeWidth(thickness);
		return line;
	}
	
	private double distance(Point a, Point b) {
		return Math.sqrt( (a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()) );
	}
}
