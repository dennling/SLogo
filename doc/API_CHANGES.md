API Changes
===


##### Change 1: Point
We were originally going to use java.awt.Point as a class to encapsulate coordinates in the TurtleDisplay. We have decided to make our own class (utils/Point.java) with double precision coordinates to support very fine-tuned movement of the turtle.

##### Change 2: isPointInBounds
For now we have decided to handle bounds checking within TurtleDisplay, which means we no longer need to provide this method to the back-end.

##### Change 3: getTurtle
We decided to add a method to get the turtle object from the front end so that the back end can get the location and rotation of the Turtle. 