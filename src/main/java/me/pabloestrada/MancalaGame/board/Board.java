package me.pabloestrada.MancalaGame.board;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.marbles.MarbleColor;
import me.pabloestrada.MancalaGame.marbles.Position;
import me.pabloestrada.MancalaGame.slots.Bank;
import me.pabloestrada.MancalaGame.slots.PlayerType;
import me.pabloestrada.MancalaGame.slots.Slot;
import me.pabloestrada.MancalaGame.type.GameType;

public class Board {

	private Slot[] slots;
	private PlayerType currentPlayer;

	private Label status;

	public Board(ImageView[] slotImages, Label[] labels, Label status) {
		this.slots = getSlots(slotImages, labels);
		this.currentPlayer = PlayerType.PLAYER_ONE;
		this.status = status;
		updatePlayerTurnStatus(false);
	}

	public boolean canProcessTurn(int selectedSlot, PlayerType type) {
		Slot currentSlot = getSlot(selectedSlot);
		if (currentSlot.isSlotABank())
			return false;
		if (currentSlot.isEmpty())
			return false;
		if (type == PlayerType.PLAYER_ONE && selectedSlot < 6) {
			return false;
		}
		if (type == PlayerType.PLAYER_TWO && selectedSlot > 6) {
			return false;
		}
		if (type == PlayerType.CPU && selectedSlot > 6) {
			return false;
		}
		return true;
	}

	public void processTurn(int selectedSlot) {
		Turn currentTurn = new Turn(slots, selectedSlot, currentPlayer);
		boolean canGoAgain = currentTurn.run();

		PlayerType oldPlayer = currentPlayer;
		currentPlayer = getOppositeType(oldPlayer);
		if (MancalaMain.getGameInfo().getGameType() == GameType.SINGLEPLAYER && oldPlayer == PlayerType.PLAYER_ONE)
			currentPlayer = PlayerType.CPU;
		
		if(canGoAgain)
			currentPlayer = oldPlayer;
		
		updatePlayerTurnStatus(canGoAgain);
	}
	
	private void updatePlayerTurnStatus(boolean canGoAgain) {
		String defaultText = " is now playing!";
		if(canGoAgain)
			defaultText = " gets an extra turn for landing in his store!";
		status.setText(MancalaMain.getGameInfo().getName(currentPlayer) + defaultText);
	}

	private PlayerType getOppositeType(PlayerType type) {
		if (type == PlayerType.PLAYER_ONE)
			return PlayerType.PLAYER_TWO;
		if (type == PlayerType.PLAYER_TWO)
			return PlayerType.PLAYER_ONE;
		return PlayerType.PLAYER_ONE;
	}

	public Slot getSlot(int selectedSlot) {
		return slots[selectedSlot];
	}

	private Slot[] getSlots(ImageView[] slotImages, Label[] labels) {
		final int[] xPos = { 300, 182, 62, -64, -186, -305, -425, -305, -186, -64, 62, 182, 300, 420 };
		final int[] yPos = { 134, 134, 134, 134, 134, 134, 230, 335, 335, 335, 335, 335, 335, 240 };
		Slot[] temporarySlots = new Slot[14];
		for (int i = 0; i < temporarySlots.length; i++) {
			Position pos = new Position(xPos[i], yPos[i]);
			if (i == 6) {
				temporarySlots[i] = new Bank(pos, true, PlayerType.CPU, slotImages[i], labels[i], i);
				continue;
			}
			if (i == 13) {
				temporarySlots[i] = new Bank(pos, true, PlayerType.PLAYER_ONE, slotImages[i], labels[i], i);
				continue;
			}
			temporarySlots[i] = new Slot(pos, false, slotImages[i], labels[i], i);
		}
		return temporarySlots;
	}

	public void populateMarbles(StackPane marbleHolder) {
		MarbleColor[] colors = MarbleColor.values();
		for (Slot slot : slots) {
			if (slot.isSlotABank())
				continue;
			for (int i = 0; i < 4; i++) {
				int colorIndex = (int) (Math.random() * colors.length);
				Marble currentMarble = new Marble(colors[colorIndex]);
				marbleHolder.getChildren().add(currentMarble.getImageView());
				slot.addMarble(currentMarble, 3);
			}
		}
	}
	
	public PlayerType getCurrentPlayer() {
		return currentPlayer;
	}

}
