package me.pabloestrada.Controllers;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import me.pabloestrada.Util.MenuLoader;

public class MainMenuController {

	@FXML
	private void launchStart() {
		new MenuLoader("gameplay_menu").load();
	}
	
	@FXML
	private void launchGithub() throws MalformedURLException {
		openWebpage(new URL("https://github.com/pablof300/Mancala"));
	}
	
	@FXML
	private void launchHelp() {
		System.out.println("Help");
	}
	
	private boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	private boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
}
