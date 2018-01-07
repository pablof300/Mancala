package me.pabloestrada.Controllers;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.MancalaGame.type.GameInfo;
import me.pabloestrada.MancalaGame.type.GameType;
import me.pabloestrada.MancalaHelp.Tutorial;
import me.pabloestrada.Util.MenuLoader;

public class MainMenuController {

	@FXML
	private void launchStart() {
		new MenuLoader("selectormenu").load();
	}
	
	@FXML
	private void launchGithub() throws MalformedURLException {
		openWebpage(new URL("https://github.com/pablof300/Mancala"));
	}
	
	@FXML
	private void launchHelp() {
		new Tutorial().showTutorial();
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
