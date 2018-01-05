package me.pabloestrada.Controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameplayController {

	@FXML
	private ImageView slot;
	
	@FXML
	private void initialize() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(2000), slot);
	     //tt.setByX(200f);
	     tt.setToX(-100);
	     tt.setCycleCount(1);
	     tt.setAutoReverse(true);
	     tt.play();
	}
	
}
