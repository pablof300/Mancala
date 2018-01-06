package me.pabloestrada.Controllers;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.MancalaGame.board.Board;
import me.pabloestrada.MancalaGame.slots.PlayerType;
import me.pabloestrada.MancalaGame.slots.Slot;

public class GameplayController {

	@FXML
	private ImageView selection_slot_0;
	@FXML
	private ImageView selection_slot_1;
	@FXML
	private ImageView selection_slot_2;
	@FXML
	private ImageView selection_slot_3;
	@FXML
	private ImageView selection_slot_4;
	@FXML
	private ImageView selection_slot_5;
	@FXML
	private ImageView selection_slot_6;
	@FXML
	private ImageView selection_slot_7;
	@FXML
	private ImageView selection_slot_8;
	@FXML
	private ImageView selection_slot_9;
	@FXML
	private ImageView selection_slot_10;
	@FXML
	private ImageView selection_slot_11;
	@FXML
	private ImageView selection_slot_12;
	@FXML
	private ImageView selection_slot_13;

	@FXML
	private ImageView slot_0;
	@FXML
	private ImageView slot_1;
	@FXML
	private ImageView slot_2;
	@FXML
	private ImageView slot_3;
	@FXML
	private ImageView slot_4;
	@FXML
	private ImageView slot_5;
	@FXML
	private ImageView slot_6;
	@FXML
	private ImageView slot_7;
	@FXML
	private ImageView slot_8;
	@FXML
	private ImageView slot_9;
	@FXML
	private ImageView slot_10;
	@FXML
	private ImageView slot_11;
	@FXML
	private ImageView slot_12;
	@FXML
	private ImageView slot_13;

	@FXML
	private Label label_0;
	@FXML
	private Label label_1;
	@FXML
	private Label label_2;
	@FXML
	private Label label_3;
	@FXML
	private Label label_4;
	@FXML
	private Label label_5;
	@FXML
	private Label label_6;
	@FXML
	private Label label_7;
	@FXML
	private Label label_8;
	@FXML
	private Label label_9;
	@FXML
	private Label label_10;
	@FXML
	private Label label_11;
	@FXML
	private Label label_12;
	@FXML
	private Label label_13;

	@FXML
	private StackPane marbleholder;

	private Board board;
	private HashMap<ImageView, Slot> selectionMap;

	@FXML
	private void initialize() {
		ImageView[] imageViews = { slot_0, slot_1, slot_2, slot_3, slot_4, slot_5, slot_6, slot_7, slot_8, slot_9,
				slot_10, slot_11, slot_12, slot_13 };
		Label[] labels = { label_0, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9,
				label_10, label_11, label_12, label_13 };
		ImageView[] selectionImageViews = { selection_slot_0, selection_slot_1, selection_slot_2, selection_slot_3,
				selection_slot_4, selection_slot_5, selection_slot_6, selection_slot_7, selection_slot_8,
				selection_slot_9, selection_slot_10, selection_slot_11, selection_slot_12, selection_slot_13 };

		selectionMap = new HashMap<ImageView, Slot>();
		board = new Board(imageViews, labels);
		board.populateMarbles(marbleholder);

		for (int i = 0; i < selectionImageViews.length; i++)
			selectionMap.put(selectionImageViews[i], board.getSlot(i));
		centerStage(MancalaMain.getMainStage(), 1000, 600);
	}

	@FXML
	private void slotClicked(MouseEvent e) {
		Slot selectedSlot = selectionMap.get((ImageView) e.getSource());
		if (board.canProcessTurn(selectedSlot.getId(), PlayerType.PLAYER_ONE))
			board.processTurn(selectedSlot.getId(), PlayerType.PLAYER_ONE);
	}

	@FXML
	private void slotEntered(MouseEvent e) {
		Slot selectedSlot = selectionMap.get((ImageView) e.getSource());
		fadeNode(selectedSlot.getImageView(), 0.5f);
	}

	@FXML
	private void slotExited(MouseEvent e) {
		Slot selectedSlot = selectionMap.get((ImageView) e.getSource());
		fadeNode(selectedSlot.getImageView(), 0.3f);
	}

	private void fadeNode(ImageView node, float scale) {
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
		ft.setToValue(scale);
		ft.setCycleCount(1);
		ft.play();
	}

	private void centerStage(Stage stage, double width, double height) {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - width) / 2);
		stage.setY((screenBounds.getHeight() - height) / 2);
	}

}
