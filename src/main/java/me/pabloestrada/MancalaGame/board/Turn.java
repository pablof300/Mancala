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
		int currentMarbel = 0;
		int currentIndex = selectedSlot + 1;
		while (currentMarbel < marbels.length) {
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
			board[currentIndex].addMarble(marbels[currentMarbel], 1);
			currentMarbel++;
			currentIndex++;
		}
	}

}
