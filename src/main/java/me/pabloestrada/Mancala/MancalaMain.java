package me.pabloestrada.Mancala;

import javafx.application.Application;
import javafx.stage.Stage;
import me.pabloestrada.MancalaGame.type.GameInfo;
import me.pabloestrada.MancalaGame.type.GameType;
import me.pabloestrada.Util.MenuLoader;

public class MancalaMain extends Application {

	private static Stage mainStage;
	private static GameInfo info;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;
		new MenuLoader("main_menu").load();
		mainStage.setResizable(false);
		mainStage.show();
		mainStage.setTitle("Mancala");
	}

	public static Stage getMainStage() {
		return mainStage;
	}

	public static GameInfo getGameInfo() {
		return info;
	}

	public static void setGameInfo(GameInfo gameInfo) {
		info = gameInfo;
	}
}
