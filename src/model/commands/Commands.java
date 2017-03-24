// This entire file is part of my masterpiece.
// Alexander Zapata
// This is an awesome class that was originally created by Jay and I and later worked on by Elliot and the rest of the crew. This class is incredible because it is
// the container for all of the pre-made commands for the entire program. This is great design because originally, the class was made to have explicit instances 
// and names of all of the commands, but with the usage of carefully created properties files and reflection this class has been contained to about 70 lines.  
package model.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Elliot Bolzan, Jay Doherty, and Alex Zapata
 *
 */
public class Commands {

	private String path = "resources/languages/";
	private HashMap<String, String> commandNames = new HashMap<String, String>();
	private ResourceBundle myResources;
	private String packageRoot = "model.commands.";
	private ArrayList<String> packages = new ArrayList<>(Arrays.asList("control", "logic", "math", "turtle", "multiple", "display"));

	public Commands() {
		updateLanguage("English");
	}

	public void updateLanguage(String language) {
		myResources = ResourceBundle.getBundle(path + language);
		setCommandNames();
	}
	
	public void setCommandNames() {
		commandNames = new HashMap<String, String>();
		Enumeration<String> iter = myResources.getKeys();
		while (iter.hasMoreElements()) {
			addCommand(iter.nextElement());
		}
	}
	
	private void addCommand(String string) {
		String[] names = myResources.getString(string).split("[|]");
		for (String name : names) {
			name = name.replace("\\", "");
			commandNames.put(name, string);
		}
	}

	public Command get(String input) {
		try {
			Class<?> c = getClassName(commandNames.get(input.toLowerCase()) + "Command");
			Object object = c.getConstructor().newInstance();
			return (Command) object;
		} catch (Exception e) {
			return null;
		}
	}
	
	private Class<?> getClassName(String input) {
		for (String pkg : packages) {
		    String name = packageRoot + pkg + "." + input;
		    try {
		        return Class.forName(name);
		    } catch (ClassNotFoundException e) {}
		}
		return null;
	}

}