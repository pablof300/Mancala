package me.pabloestrada.Controllers;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameplayController {

	@FXML
	private ImageView slot;
	
	@FXML
	private StackPane marbleHolder;
	
	@FXML
	private void initialize() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(2000), slot);
	     //tt.setByX(200f);
	     tt.setToX(-305);
	     tt.setToY(136);
	     tt.setCycleCount(1);
	     tt.setAutoReverse(true);
	     tt.play();
	     
	     Timer t = new Timer();
	     t.schedule(new TimerTask() {

			@Override
			public void run() {
				TranslateTransition tt = new TranslateTransition(Duration.millis(2000), slot);
			     //tt.setByX(200f);
			     tt.setToX(-68);
			 
			     tt.setCycleCount(1);
			     tt.setAutoReverse(true);
			     tt.play();
			}}, 5000);
	}
	
}
