package me.pabloestrada.MancalaGame.board;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import me.pabloestrada.Mancala.MancalaMain;
import me.pabloestrada.MancalaGame.marbles.Marble;
import me.pabloestrada.MancalaGame.marbles.MarbleColor;
import me.pabloestrada.MancalaGame.marbles.Position;
import me.pabloestrada.MancalaGame.slots.Bank;
import me.pabloestrada.MancalaGame.slots.PlayerType;
import me.pabloestrada.MancalaGame.slots.Slot;

public class Board {

	private Slot[] slots;
	private PlayerType currentPlayer;

	private ImageView playeroneavatar;
	private ImageView playertwoavatar;

	private Label status;

	public Board(ImageView[] slotImages, Label[] labels, Label status, ImageView playeroneavatar,
			ImageView playertwoavatar) {
		this.slots = getSlots(slotImages, labels);
		this.currentPlayer = PlayerType.PLAYER_ONE;
		this.status = status;
		this.playeroneavatar = playeroneavatar;
		this.playertwoavatar = playertwoavatar;
		updatePlayerTurnStatus(false);
		updatePlayerAvatars();
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
		if (MancalaMain.getGameInfo().isSingleplayer() && oldPlayer == PlayerType.PLAYER_ONE)
			currentPlayer = PlayerType.CPU;

		if (canGoAgain)
			currentPlayer = oldPlayer;
		// if(currentPlayer == PlayerType.CPU)
		// processBotTurn();

		int[] playerOneSlots = { 7, 8, 9, 10, 11, 12 };
		int[] playerTwoSlots = { 0, 1, 2, 3, 4, 5 };
		if (isGameOver(playerOneSlots, playerTwoSlots)) {
			processWinner(playerOneSlots, playerTwoSlots);
		} else {
			updatePlayerAvatars();
			updatePlayerTurnStatus(canGoAgain);
		}
	}

	private void processWinner(int[] playerOneSlots, int[] playerTwoSlots) {
		clearAllMarbles(PlayerType.PLAYER_ONE, playerOneSlots);
		clearAllMarbles(PlayerType.PLAYER_TWO, playerTwoSlots);

		PlayerType winner = PlayerType.PLAYER_ONE;
		if (getBank(PlayerType.PLAYER_TWO).getMarbleCount() > getBank(PlayerType.PLAYER_ONE).getMarbleCount()) {
			winner = PlayerType.PLAYER_TWO;
			if (MancalaMain.getGameInfo().isSingleplayer())
				winner = PlayerType.CPU;
		}

		status.setText(MancalaMain.getGameInfo().getName(winner) + " has won!");
	}

	private void clearAllMarbles(PlayerType type, int[] slotSet) {
		Slot bank = getBank(type);
		for (int slot : slotSet)
			bank.addMarbles(slots[slot].clearMarbels(), 1);
	}

	private void updatePlayerAvatars() {
		if (currentPlayer == PlayerType.PLAYER_ONE) {
			fadeNode(playeroneavatar, 1f);
			fadeNode(playertwoavatar, 0.3f);
		} else {
			fadeNode(playeroneavatar, 0.3f);
			fadeNode(playertwoavatar, 1f);
		}
	}

	private void fadeNode(ImageView node, float scale) {
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
		ft.setToValue(scale);
		ft.setCycleCount(1);
		ft.play();
	}

	private boolean isGameOver(int[] playerOneSlots, int[] playerTwoSlots) {
		if (isEmpty(playerOneSlots))
			return true;
		if (isEmpty(playerTwoSlots))
			return true;
		return false;
	}

	private boolean isEmpty(int[] slotSet) {
		for (int slot : slotSet) {
			if (!slots[slot].isEmpty())
				return false;
		}
		return true;
	}

	private void updatePlayerTurnStatus(boolean canGoAgain) {
		String defaultText = " is now playing!";
		if (canGoAgain)
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

	private Slot getBank(PlayerType type) {
		if (type == PlayerType.PLAYER_ONE)
			return slots[13];
		return slots[6];
	}

}
