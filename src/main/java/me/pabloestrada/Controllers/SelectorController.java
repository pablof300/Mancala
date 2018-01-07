package me.pabloestrada.Controllers;

import javafx.fxml.FXML;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.MancalaGame.type.GameInfo;
import me.pabloestrada.MancalaGame.type.GameType;
import me.pabloestrada.Util.MenuLoader;

public class SelectorController {

	@FXML
	private void launchSingleplayer() {
		askForName(GameType.SINGLEPLAYER);
	}
	
	@FXML
	private void launchMultiplayer() {
		askForName(GameType.MULTIPLAYER);
	}
	
	@FXML
	private void back() {
		new MenuLoader("main_menu").load();
	}
	
	private void askForName(GameType gameType) {
		MancalaMain.setGameInfo(new GameInfo(gameType));
		new MenuLoader("namesmenu").load();
	}
}
