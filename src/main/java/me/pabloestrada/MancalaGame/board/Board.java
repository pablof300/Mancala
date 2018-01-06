package me.pabloestrada.MancalaGame.board;

import javafx.scene.layout.StackPane;
import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.marbles.MarbleColor;
import me.pabloestrada.MancalaGame.marbles.Position;
import me.pabloestrada.MancalaGame.slots.Bank;
import me.pabloestrada.MancalaGame.slots.PlayerType;
import me.pabloestrada.MancalaGame.slots.Slot;

public class Board {

	private Slot[] slots;

	public Board() {
		this.slots = getSlots();
	}

	public boolean canProcessTurn(int selectedSlot, PlayerType type) {
		Slot currentSlot = getSlot(selectedSlot);
		if (currentSlot.isSlotABank())
			return false;
		if (currentSlot.isEmpty())
			return false;
		if (type == PlayerType.HUMAN && selectedSlot < 7) {
			return false;
		}
		if (type == PlayerType.CPU && selectedSlot > 7) {
			return false;
		}
		return true;
	}

	public void processTurn(int selectedSlot, PlayerType type) {
		Turn currentTurn = new Turn(slots, selectedSlot, type);
		currentTurn.run();
	}

	private Slot getSlot(int selectedSlot) {
		return slots[selectedSlot];
	}

	private Slot[] getSlots() {
		final int[] xPos = { 300, 182, 62, -64, -186, -305, -425, -305, -186, -64, 62, 182, 300, 420 };
		final int[] yPos = { 112, 112, 112, 112, 112, 112, 206, 308, 308, 308, 308, 308, 308, 220 };
		Slot[] temporarySlots = new Slot[14];
		for (int i = 0; i < temporarySlots.length; i++) {
			Position pos = new Position(xPos[i], yPos[i]);
			if (i == 7) {
				temporarySlots[i] = new Bank(pos, true, PlayerType.CPU);
				continue;
			}
			if (i == 14) {
				temporarySlots[i] = new Bank(pos, true, PlayerType.HUMAN);
				continue;
			}
			temporarySlots[i] = new Slot(pos, false);
		}
		return temporarySlots;
	}

	public void populateMarbels(StackPane marbleHolder) {
		MarbleColor[] colors = MarbleColor.values();
		for (Slot slot : slots) {
			for (int i = 0; i < 4; i++) {
				int colorIndex = (int)(Math.random() * colors.length);
				Marble currentMarble = new Marble(colors[colorIndex]);
				marbleHolder.getChildren().add(currentMarble.getImageView());
				slot.addMarble(currentMarble, 3);
			}
		}
	}

}
