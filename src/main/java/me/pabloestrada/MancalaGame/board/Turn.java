package me.pabloestrada.MancalaGame.board;

import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.slots.PlayerType;
import me.pabloestrada.MancalaGame.slots.Slot;

public class Turn {

	private Slot[] board;
	private int selectedSlot;
	private PlayerType type;

	public Turn(Slot[] board, int selectedSlot, PlayerType type) {
		this.selectedSlot = selectedSlot;
		this.board = board;
		this.type = type;
	}

	public void run() {
		Slot currentSlot = board[selectedSlot];
		Marble[] marbels = currentSlot.clearMarbels();
		currentSlot.updateMarbleLabel();
		int currentMarble = 0;
		int currentIndex = selectedSlot + 1;
		while (currentMarble < marbels.length) {
			if (currentIndex == 14) {
				currentIndex = 0;
			}
			if (type == PlayerType.CPU && currentIndex == 13) {
				currentIndex++;
				continue;
			}
			if (type == PlayerType.HUMAN && currentIndex == 6) {
				currentIndex++;
				continue;
			}
			if (isLastMarbleInTurn(currentMarble, marbels.length))
				processCapture(currentIndex);

			board[currentIndex].addMarble(marbels[currentMarble], 1);
			currentMarble++;
			currentIndex++;
		}
	}

	private boolean isLastMarbleInTurn(int currentMarble, int totalMarbles) {
		if (currentMarble == totalMarbles - 1)
			return true;
		return false;
	}

	private void processCapture(int currentSlot) {
		Slot currentSlotObject = board[currentSlot];
		if (!currentSlotObject.isEmpty())
			return;
		if (!currentSlotObject.isMySide(type))
			return;
		getBank().addMarbles(getOppositeSlot(currentSlotObject).clearMarbels(), 1);

	}

	private Slot getOppositeSlot(Slot slot) {
		if (slot.isSlotABank())
			return null;
		int slotId = slot.getId();
		int multiplier = 1;
		if (slotId > 6)
			multiplier = -1;
		return board[((2 * (slotId - 6)) * multiplier) + slotId];
	}

	private Slot getBank() {
		if (type == PlayerType.HUMAN)
			return board[13];
		return board[6];
	}

}
