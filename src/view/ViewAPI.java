package view;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author Elliott Bolzan
 *
 */
public interface ViewAPI {
	
	public void print(String string);
	
	public void clearConsole();
	
	public void moveTo(Point point);
	
	public void turn(double degrees);
	
	public void setPenDown(boolean down);
	
	public void setTurtleVisible(boolean visible);
	
	public void clearDisplay();
	
	public Dimension getDisplaySize();
	
	public boolean isPointInBounds(Point point);

}