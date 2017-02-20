Design Specifications - Team 10
=============

Written by Elliott Bolzan (eab91), Jay Doherty (jld60), Dennis Ling (dl186), and Alex Zapata (az73).

### Introduction

### Design Overview

The front-end and back-end of our design is divided in the following way:

![](images/overview.png)

The basic idea of our organization is that the front-end will detect user input, and the back-end will handle it. When the front-end detects a new input, it will pass it to the the back-end as a String to be parsed. Once the back-end has finished parsing, it will pass back a Command object. This Command object has a single public method that can be called to execute it. Under this structure, the API used by the Console and the back-end parser can be incredibly simple because it involves this simple handing back and forth of objects. The front-end provides Strings and the back-end provides ready-made Commands, and the front-end doesn't need to know or care about how these commands get made, it just knows that all commands can be executed.

The rest of the front-end external API is provided to make Commands powerful. They should be able to do interesting stuff like printing math results or moving the Turtle around screen, so methods that support this behavior must be provided by the front-end. 

The rest of the back-end external API is provided to make the UI powerful. While `parse(String input)` provides the main functionality in terms of executing commands, other kinds of input might need to be handled differently. The UI should be able to see the history of commands and the current state of variables, so methods that support this behavior must be provided by the back-end.

The front-end internal API is focused on providing features that do not require any processing by the back-end. This should include changing the color or formating of the display, changing the input language, and viewing lists of old commands or variables. 

The back-end internal API is once again focused on providing features and ways of changing the back-end processing that the front-end does not care about. Part of this extensibility is built into the Command interface, which allows an obvious way for new commands to added to the Slogo language. 

### User Interface

Our user interface for this project will look as follows:

![](images/ui.jpg)

Three major components are visible on this image:

1. **The State**: this section, on the left, contains the user's variables and the commands he has defined. The user is able to edit the variables by clicking on them, or run commands by clicking on them. Both of these displays, the variable-display and the command-display, will be implemented as table views. 
2. **The Console**: this section, in the center, accepts the user's text input. By default, the cursor is active in the console. When the user types, the output is displayed in the console. When the user presses enter, his text is parsed as a command and the results are displayed in the **TurtleDisplay**. Pressing the `UP` key on the keyboard will let the user view, and optionally run, his past commands.
3. **The TurtleDisplay**: this section, on the right, contains the turtle and its drawings. The user should be able to view the turtle's movement in this section. In the top right of this section, there is a **Settings** button: clicking this button loads a modal window, from which the user can modify the color of the pen, the turtle's image, and the background of the **TurtleDisplay**.

There are several possible errors that the program can encounter when an individual is using it. These errors will all be displayed using a JavaFX Dialog. The following errors will be handled:

- **Bad input**: this type of error occurs when the user incorrectly forms a command and seeks to run it. The program catches this error, and informs the user of it using a Dialog. 
- **Invalid operation**: this type of error occurs when the user seeks to run a problematic command, like `QUOTIENT 5 0`. Generally speaking, this type of error will be raised when the user seeks to run an expression that is mathematically prohibited. In that scenario, the user will be informed using a Dialog.
- **Out of bounds**: this type of error occurs when the user seeks to move the turtle out of bounds. If the user calls `FORWARD 3000` and the execution of the command would result in the disappearance of the turtle, the user would be notified using a Dialog.

An example of such a Dialog, for a **bad input** error, would be:

![](images/error.jpg)

We chose to group all the customizations options under a **Settings** button in the top right of the **TurtleDisplay**. When the user clicks on this button, a modal view with all possible customization options appears. We had several reasons for designing our program this way:

- To prevent our **TurtleDisplay** from becoming cluttered, and providing our Turtle with the most possible real estate.
- To accomodate further customization options in a more flexible manner. 

### API Details

Here are the details for our four APIs:

#### Front-end External:

This API's methods are those that the back-end needs to have access to. Here are the methods made available by the front-end external API: 

```
public void print(String string);
public void clearConsole();
public void moveTo(Point point);
public void turn(double degrees);
public void setPenDown(boolean down);
public void setTurtleVisible(boolean visible);
public void clearDisplay();
public Dimension getDisplaySize();
public boolean isPointInBounds(Point point);
```

**How does this API support features from the assignment specification?**

This API supports these features:

- *Enter commands to the turtle interactively by entering text commands*: the APIs `print(String string)` helps accomplish this task. Indeed, the user's text must be printed to the screen, and the output of his commands must be displayed as well.
- *See the results of the turtle executing commands displayed visually*: the APIs `moveTo(Point point)`, `turn(double degrees)`, `setPenDown(boolean down)`, `setTurtleVisible(boolean visible)`, and `clearDisplay()` functions are used to accomplish this task.

Some features, like *clearing the console*, are included here because we think they might be useful down the road – even though they aren't part of our current assignment specifications.

Other features, like *set a background color for the turtle's display area*, are handled by the front-end's internal API. Most customization features, as well as features that are purely related to the GUI, are in the internal API, because the back-end need not know of their functioning or existence. 

**What resources does this API use?**

This API will make use of the following resources:

- The **Console**: printing to and clearing the console will necessarily require a reference to the Console object.
- The **TurtleDisplay**: moving or turning the turtle, for instance, will require a reference to the TurtleDisplay object. 
- The **Turtle**: setting whether the pen is active, or hiding the turtle, will require a reference to the Turtle object.

**How is this API intended to be used?**

This API is intended to be used from the backend.

We expect the following, general use-case:

- The front-end receives a **Command** object from the back-end.
- The front-end calls the `execute()` function on this **Command** object. 
- The `execute()` function, located in the back-end, makes use of methods from this API. For instance, if the back-end intends to move the turtle forward by 50, it calculates the new position for the turtle, and calls `moveTo(Point point)` on the front-end. 

**Note**: this last example makes it clear that this API is not intended to compute new destination points or do math of any sort. It is simply meant to be used to display the results of calculations done in the back-end.

**How can this API be extended?**

This API can be extended in the following ways:

- By **making use of** current methods. Consider the following scenario: a coder needs to implement a new feature, in which the **TurtleDisplay** is cleared and the **Turtle** is moved to the bottom left of the screen – not the center. The coder could construct this feature using the `clearDisplay()` and `moveTo(Point point)` methods in conjunction.
- By **adding** methods. While this method is not ideal because it requires an update to the entire API, it does remain a possibility.

Ideally, we would like this API's methods to be closed for modification: they were designed to fulfill a role, and they fulfill it. New features should be constructed by either combining functionality available in the current methods, or, less ideally, by creating new methods entirely in the API. 

**Why are we introducing these classes?**

In discussing this front-end external API, we mentionned several classes: the **Console**, the **TurtleDisplay** and the **Turtle**. Here, we justify the inclusion and creation of these classes.

- **Console**: intuitively, a program like SLogo that requires a user's input to a shell will need a Console class. This API needs this class to exist, in order to be able to print to the console and clear it, eventually. Additionally, it makes sense for this class to be its own abstraction: the user interface classes will get large and cluttered if they are not split into several functional modules.
- **TurtleDisplay**: likewise, a program that wants to visualize the movements of a turtle across a screen will need a class to control that space. This API contains `moveTo(Point point)` and `turn(double degrees)` methods, so it makes sense to create a standalone class in which these events can take place. 
- **Turtle**: finally, it is normal for a program that wishes to control a turtle to have au **Turtle** class. Why is the turtle not simply an image controlled from the **TurtleDisplay**? This API has methods like `setPenDown(boolean down)`; those methods control instance variables that conceptually belong to the turtle. As a consequence, it makes sense to have a **Turtle** class to contain those variables.


#### Front-end Internal:

The purpose of this API is to provide methods that the front-end will use to implement features that are exclusively handled within the front-end. These methods should provide areas for extension, and they should be able to be overrode without impacting the back-end.

```
public void showMessage(String message);
public void setBackgroundColor(Color color);
public void setTurtleImage(String path);
public void setPenColor(Color color);
public void setLanguage(String language);
public void showHelp();
public void showHistory();
public void updateVariables();
public void updateCommands();
public void runCommand(String command);
```

**How does this API support features from the assignment specification?**

The following features are listed with the commands that implement them:

* *See errors that may result from entered commands in a user friendly way* --implemented by: `showMessage(String message)` which will display a JavaFX Alert with the message.

* *Set a background color for the turtle's display area* -- implemented by: `setBackgroundColor(Color color)`

* *Set an image to use for the turtle* -- implemented by: `setTurtleImage(String path)` where `path` specifies a new image file.

* *Set a color to use for the pen* -- implemented by: `setPenColor(Color color)`

* *See commands previously run in the environment (even better, make them directly clickable to execute)* -- implemented by: `showHistory()` (which will be called by the user pressing the up arrow-key to look through a scrollable list of old commands) and `runCommand(String command)` (which will be called on clicking one of those old commands).

* *See variables currently available in the environment* -- implemented by: `updateVariables()` which will be called to refresh the permanent sidebar that shows variables and their values.

* *See user-defined commands currently available in the environment* -- implemented by: `updateCommands()` which similarly refreshes the sidebar that shows user-defined commands.

* *Choose the language in which SLogo commands are understood* -- implemented by: `setLanguage(String language)` which simply changes the properties file used to interpret commands.

* *Access an HTML formatted help page* -- implemented by: `showHelp()`

**What resources does this API use?**

Since this API is contained internally within the front-end, it should only use front-end resources. Methods that modify the Turtle Display should for example not need to know the turtle's coordinates since that is data held by the back-end. `showMessage(String message)` might need a reference to the Console if it is printing a message there, or it may be implemented purely with JavaFX Alerts. `showHistory()`, `updateVariables()`, and `updateCommands()` will need references to their particular GUI elements, and they will make use of the back-end API to refresh the displays with the most up-to-date variables and commands. `runCommand(String command)` will probably invoke `parse(String string)`, which is another back-end method made available by the back-end API.

**How is this API intended to be used?**

This API is intended to specify the methods that are internal to the front-end, so it should be used to implement front-end specific features and provide an extendible interface. 

We would expect this API to be used in the following scenarios:
* The front-end catches an exception and needs to display an error to the user
* The user wants to change the visuals of the Turtle Display (change colors or images)
* The user wants to see old commands
* The user wants to add a variable and see its value on screen
* The user wants to add a custom command
* The user wants to change languages
* The user wants to see help from a web page

**How can this API be extended?**

This API can be extended to include more UI-specific features, like more sidebars to display different kinds of information about the state of the environment. To make this very easy to extend, it may be that certain methods like `updateVariables()` and `updateCommands()` could be combined in a superclass `update()` method, with VariableDisplay and CommandDisplay objects each overriding this method to check the appropriate data structures in the back-end. This is an example of a feature that maybe isn't the best to reason about as a Java interface but rather as an inheritance hierarchy.

**Why are we introducing these classes?**

In this section we have focused on discussing the interface for the API in terms of an actual Java interface which cannot be instantiated like a class. However, we will likely not keep this as an interface in the final implementation since these methods are unlikely to all be implemented by a single object. As mentioned above, one idea would be to have a superclass Display that is extended by VariableDisplay, CommandDisplay, HistoryDisplay (even ConsoleDisplay?). This way the process of updating all of the displays could be streamlined, and the path to add new sidebars or other display elements to the UI would be very obvious.

#### Back-end External:
#### Back-end Internal:


### API Example Code

We seek to go through the following use-case: 

*The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.*

Here are the steps necessary to complete that task:

- When the user presses `Enter`, the **Console** calls `parse(String string)` (in CLASSNAME) using the user's input.
- The `parse(String string)` method returns a **Command** object to the **Console**. 
- Simultaneously, the command is added to the environment's history. HOW
- The `execute()` method (in **Command**) is called on the **Command** object from the **Console**.
- From the `execute()` method, the destination point for the turtle is computed from the current point (obtained using `Point getTurtleLocation()` (in CLASSNAME) and the 50 pixel advance. The `moveTo(Point point)` method (in **TurtleDisplay**) is called with the new destination. 
- The user sees the turtle move to its new point, leaving behind a trail.

More use cases follow, two for each API. 

#### Front-end External:

1\. *The user runs three commands in succession: he hides the turtle, moves it left by 50, and shows it again. How should this API be used during these commands?*

- After the back-end has parsed the first command into a **Command** object, that object's `execute()` method should call `setTurtleVisible(false)` on its reference to the front-end.
- After the back-end has parsed the second command into a **Command** object, that object's `execute()` method should call `moveTo(destinationPoint)` on its reference to the front-end, passing the point the **Turtle** should move to as a parameter.
- After the back-end has parsed the third command into a **Command** object, that object's `execute()` method should call `setTurtleVisible(true)` on its reference to the front-end.

2\. *The back-end wishes to determine whether a point is within the bounds of the **TurtleDisplay**. If the point is not in bounds, the back-end wishes to inform the user. How would the back-end use this API to achieve this task?*

The back-end should follow this sequence of steps:

- The back-end should call the `boolean isPointInBounds(Point point)` method with the point in question.
- Based on the result, the back-end can either continue, or raise an `OutOfBounds` exception.
- In the event an exception is raised, the **Console** will catch it, and the front-end will display a Dialog informing the user of the error.

#### Front-end Internal:
1\. **UNFINISHED**
2\. **UNFINISHED**
#### Back-end External:
#### Back-end Internal:

### Design Considerations

* The main design considerations at this stage of developing the interactions between the front and back-end APIs resides in deciding how the two interact and communicate to parse information and manipulate the visual/informational components of the
program. In regards to communication between the front-end and back-end, the team immediately bonded to the idea of the user
inputting a string into the console, that will be sent to the back-end and understood by means of a parser for all possible commands. Then, once the command (in which each object will become its own class) has been parsed, it will be sent back to the 
front-end to be executed. 
* It was clear from the beginning that the input of the user would have to be parsed and made into some kind of executable object, but other design considerations arose in the aspect of how that object would be passed from the front-end to the back-end and what that meant for the command object. One other consideration was to have only an explicit flow of information from the front-end to the back-end. Although pretty enticing because of the limitation of dependencies, this was decided against because it made direct manipulations to the display an unnatural event. In other words, commands like CLEARSCREEN would have to use a completely different mechanism, because after parsing there would be no communication to the front-end to tell it to for example clear the group.
* The other main consideration was for the front-end to call the back-end to parse, then have the front-end execute the method to change states in itself, versus getting the back-end to do so. The problem with this method is that the internal states of objects like the turtle should remain hidden, and the methods that manipulate the turtle's data protected by varying levels of privacy.
* What needs to be discussed about the design is how the rather generic execute() methods within our command classes will be able to access the objects within the front-end whose properties need to be changed. One possibility, although not particularly pleasing is to instantiate a Method superclass that hold the current command that will already have been instantiated with the necessary instance variables of the turtle to get its location and other states. 
* Overall, the method of Command management aforementioned offers a low amount of dependencies while still allowing for a wide variety of types of commands to take place, in regard to the screen that the user sees and the private variables of the object on the screen. Once the execute command has been decided upon, the communication between the front-end and back-end will effectively be protected as a series of calls to objects versus the unnecessary passing around of objects not involved with the needed command. 

### Team Responsibilities

Our team is split-up into two groups: the **front-end** group (Elliott Bolzan and Jay Doherty) and the **back-end** group (Dennis Ling and Alexander Zapata). 

All members of the group are responsible for documenting and refactoring their subgroup's code, in addition to their own.

Within the back-end group:

- Elliott will take primary responsbility for the **Console** and its components, as well as the **State** display (variables and user-defined commands). He will take secondary responsibility for the **TurtleDisplay** and its components.
- Jay will take primary responsibility for the **TurtleDisplay**, the **Turtle**, and their components. He will take secondary responsibility for the **Console**.

Both Elliott and Jay will tie in the different front-end elements as part of their primary responsibility.

Within the back-end group:

FILL IN BACK-END GUYS