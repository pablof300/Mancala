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
	
	public boolean isEmpty() {
		return marbles.isEmpty();
	}
	
	public Marble[] clearMarbels() {
		Marble[] marblesArray = marbles.toArray(new Marble[marbles.size()]);
		marbles.clear();
		return marblesArray;
	}
	
	public void addMarble(Marble marble, int time) {
		marbles.add(marble);
		marble.moveTo(getPosition().getSimilarPosition(), time);
	}
	
}
