package me.pabloestrada.Controllers;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import me.pabloestrada.MancalaGame.board.Board;
import me.pabloestrada.MancalaGame.slots.PlayerType;

public class GameplayController {

	@FXML
	private ImageView slot;

	@FXML
	private StackPane marbleholder;
	
	private Board board;

	@FXML
	private void initialize() {
		board = new Board();
		board.populateMarbels(marbleholder);
		
		Timer t = new Timer();
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					board.processTurn(3, PlayerType.HUMAN);
				}
			}, 5000);
	}

}
