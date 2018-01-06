package me.pabloestrada.Mancala;

import javafx.application.Application;
import javafx.stage.Stage;
import me.pabloestrada.Util.MenuLoader;

public class MancalaMain extends Application {

	private static Stage mainStage;
	
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
}
