package me.pabloestrada.MancalaGame.marbles;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Marble {
	
	private ImageView imageView;
	
	public Marble(MarbleColor color) {
		imageView = new ImageView(getMarbleImage(color));
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
	}
	
	private Image getMarbleImage(MarbleColor color) {
		return new Image(getClass().getResource("/" + color.getAddress() + ".png").toExternalForm());
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public void moveTo(Position position, int time) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(time * 1000), imageView);
	    tt.setToX(position.getX());
	    tt.setToY(position.getY());
	    tt.setCycleCount(1);
	    tt.setAutoReverse(true);
	    tt.play();
	}
}
