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
		/*TranslateTransition tt = new TranslateTransition(Duration.millis(2000), slot);
		// tt.setByX(200f);
		tt.setToX(-305);
		tt.setToY(136);
		tt.setCycleCount(1);
		tt.setAutoReverse(true);
		tt.play();*/

		Timer t = new Timer();

		final int[] yPos = { 136, 136, 136, 136, 136, 136, 223, 330, 330, 330, 330, 330, 330, 242 };
		final int[] xPos = { 300, 182, 62, -64, -186, -305, -425, -305, -186, -64, 62, 182, 300, 420 };

		int time = 0;
		for (int i = 0; i < xPos.length; i++) {
			final int counter = i;
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					TranslateTransition tt = new TranslateTransition(Duration.millis(1000), slot);
					// tt.setByX(200f);
					tt.setToX(xPos[counter]);
					tt.setToY(yPos[counter]);

					tt.setCycleCount(1);
					tt.setAutoReverse(true);
					tt.play();
				}
			}, time++ * 1000);
		}
	}

}
