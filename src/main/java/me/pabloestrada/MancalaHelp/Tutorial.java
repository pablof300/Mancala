package me.pabloestrada.MancalaHelp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tutorial {
	private Dialog[] tutorialPages;

	public Tutorial() {
		tutorialPages = getPages();
	}
	
	public void showTutorial() {
		for(Dialog page : tutorialPages) {
			page.showAndWait();
		}
	}
	
	private Dialog[] getPages() {
		Dialog[] pages = new Dialog[5];
		String[] titles = {"Getting started", "How to score", "Free turn", "Capture", "Ending the game"};
		for(int i = 0; i < pages.length; i++) {
			pages[i] = getPage(i + 1, titles[i]);
		}
		return pages;
	}
	
	private Dialog getPage(int pageNumber, String title) {
		Dialog page = new Dialog();
		page.setTitle(title);
		page.setHeaderText(title);
		StackPane contents = new StackPane();
		page.getDialogPane().getButtonTypes().add(new ButtonType("Got it!", ButtonData.CANCEL_CLOSE));
		ImageView image = new ImageView(new Image(getClass().getResource("/" + pageNumber  + "_tutorial.png").toExternalForm()));
		image.setFitWidth(800);
		image.setFitHeight(496);
		contents.getChildren().add(image);
		page.getDialogPane().setContent(contents);
		return page;
	}
	/*		// Create the custom dialog.
		Dialog dialog = new Dialog();
		dialog.setTitle("" + i);
		dialog.setHeaderText("Look, a Custom Login Dialog");

		StackPane grid = new StackPane();
		dialog.getDialogPane().getButtonTypes().add(new ButtonType("Got it!", ButtonData.CANCEL_CLOSE));
		ImageView image = new ImageView(new Image(getClass().getResource("/" + "one" + ".png").toExternalForm()));
		image.setFitWidth(800);
		image.setFitHeight(496);
		grid.getChildren().add(image);
		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait();}
		
/*		final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL); 
		btOk.addEventFilter(ActionEvent.ACTION, (event) -> { 
			System.out.println("CAT");
		}); */
		

		
		

}
