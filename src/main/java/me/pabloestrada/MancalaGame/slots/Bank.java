package me.pabloestrada.MancalaGame.slots;

import me.pabloestrada.MancalaGame.marbles.Position;

public class Bank extends Slot {
	
	private PlayerType type;
	
	public Bank(Position position, boolean isBank, PlayerType type) {
		super(position, isBank);
		this.type = type;
	}
	
	public boolean isPlayer() {
		if(type == PlayerType.HUMAN)
			return true;
		return false;
	}
	
	public boolean isCPU() {
		if(type == PlayerType.CPU)
			return true;
		return false;
	}

}
