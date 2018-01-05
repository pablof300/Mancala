package me.pabloestrada.MancalaGame.slots;

import java.util.ArrayList;

import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.marbles.Position;

public class Slot {

	private Position position;
	private ArrayList<Marble> marbles;
	
	private boolean isBank;
	
	public Slot(Position position, boolean isBank) {
		this.position = position;
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
	
}
