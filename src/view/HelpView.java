/**
 * 
 */
package view;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class HelpView extends Stage {
	
	/**
	 * 
	 */
	protected HelpView(ResourceBundle resources) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource(resources.getString("HelpPath")).toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle(resources.getString("HelpTitle"));
		setScene(scene);
	}

}
