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

	public boolean run() {
		Slot currentSlot = board[selectedSlot];
		Marble[] marbels = currentSlot.clearMarbels();
		currentSlot.updateMarbleLabel();
		int currentMarble = 0;
		int currentIndex = selectedSlot + 1;
		boolean canGoAgain = false;
		while (currentMarble < marbels.length) {
			if (currentIndex == 14) {
				currentIndex = 0;
			}
			Slot currentSlotObject = board[currentIndex];
			if (currentSlotObject.isSlotABank() && !isSlotMyBank(currentIndex)) {
				currentIndex++;
				continue;
			}
			if (!currentSlotObject.isSlotABank() && isLastMarbleInTurn(currentMarble, marbels.length))
				processCapture(currentIndex);
			if (isLastMarbleInTurn(currentMarble, marbels.length)) {
				if (currentSlotObject.isSlotABank() && isSlotMyBank(currentIndex)) {
					canGoAgain = true;
				}
				if (!currentSlotObject.isSlotABank()) {
					processCapture(currentIndex);
				}
			}
			currentSlotObject.addMarble(marbels[currentMarble], 1);
			currentMarble++;
			currentIndex++;
		}
		return canGoAgain;
	}

	private boolean isSlotMyBank(int slot) {
		int myBankId = getBank().getId();
		if (slot == myBankId)
			return true;
		return false;
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
		return board[((2 * (slotId - 6)) * -1) + slotId];
	}

	private Slot getBank() {
		if (type == PlayerType.PLAYER_ONE)
			return board[13];
		if (type == PlayerType.PLAYER_TWO)
			return board[6];
		if (type == PlayerType.CPU)
			return board[6];
		return null;
	}

}
