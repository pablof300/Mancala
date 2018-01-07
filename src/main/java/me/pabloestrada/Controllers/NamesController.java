package me.pabloestrada.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.Util.MenuLoader;

public class NamesController {

	@FXML
	private TextField playerone;
	@FXML
	private TextField playertwo;
	@FXML
	private Label playertwolabel;
	@FXML
	private Label status;

	@FXML
	private void initialize() {
		if (MancalaMain.getGameInfo().isSingleplayer()) {
			playertwo.setOpacity(0);
			playertwolabel.setOpacity(0);
		}
	}

	@FXML
	private void start() {
		String playerOne = playerone.getText();
		String playerTwo = playertwo.getText();
		if (MancalaMain.getGameInfo().isSingleplayer())
			playerTwo = "CPU";
		if (!isUsernameValid(playerOne) || !isUsernameValid(playerTwo)) {
			addWarning();
			return;
		}
		MancalaMain.getGameInfo().setPlayerOneName(playerOne);
		MancalaMain.getGameInfo().setPlayerTwoName(playerTwo);
		new MenuLoader("gameplay_menu").load();
	}
	
	@FXML
	private void back() {
		new MenuLoader("selectormenu").load();
	}

	private boolean isUsernameValid(String username) {
		if (username.length() > 12)
			return false;
		if (username.isEmpty())
			return false;
		return true;
	}

	private void addWarning() {
		status.setText("All usernames must be within 1-12 characters");
	}
}
