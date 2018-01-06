package me.pabloestrada.MancalaGame.slots;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.marbles.Position;

public class Slot {

	private Position position;
	private ArrayList<Marble> marbles;

	private boolean isBank;

	private ImageView imageView;
	private Label label;
	private int id;

	public Slot(Position position, boolean isBank, ImageView imageView, Label label, int id) {
		this.position = position;
		this.id = id;
		this.imageView = imageView;
		this.label = label;
		this.isBank = isBank;
		marbles = new ArrayList<Marble>();
	}

	public boolean isSlotABank() {
		return isBank;
	}

	public int getMarbleCount() {
		return marbles.size();
	}

	public Position getPosition() {
		return position;
	}

	public boolean isEmpty() {
		return marbles.isEmpty();
	}

	public Marble[] clearMarbels() {
		Marble[] marblesArray = marbles.toArray(new Marble[marbles.size()]);
		marbles.clear();
		updateMarbleLabel();
		return marblesArray;
	}

	public void addMarble(Marble marble, int time) {
		marbles.add(marble);
		marble.moveTo(getPosition().getSimilarPosition(), time);
		updateMarbleLabel();
	}
	public void addMarbles(Marble[] marbles, int time) {
		for(Marble marble : marbles) 
			addMarble(marble,time);
	}
	
	public boolean isMySide(PlayerType type) {
		if (isBank)
			return false;
		if (type == PlayerType.HUMAN && id < 6)
			return false;
		if (type == PlayerType.CPU && id > 6)
			return false;
		return true;
	}

	public void updateMarbleLabel() {
		label.setText("" + getMarbleCount());
	}

	public Label getLabel() {
		return label;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public int getId() {
		return id;
	}

}
