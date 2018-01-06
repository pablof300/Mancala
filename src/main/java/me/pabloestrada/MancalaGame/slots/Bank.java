package me.pabloestrada.MancalaGame.slots;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import me.pabloestrada.MancalaGame.marbles.Position;

public class Bank extends Slot {
	
	private PlayerType type;
	
	public Bank(Position position, boolean isBank, PlayerType type, ImageView imageView, Label label, int id) {
		super(position, isBank, imageView, label, id);
		this.type = type;
	}
	
	public boolean isPlayer() {
		if(type == PlayerType.PLAYER_ONE || type == PlayerType.PLAYER_TWO)
			return true;
		return false;
	}
	
	public boolean isCPU() {
		if(type == PlayerType.CPU)
			return true;
		return false;
	}

}
