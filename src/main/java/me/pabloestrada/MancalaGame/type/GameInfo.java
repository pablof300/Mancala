package me.pabloestrada.MancalaGame.type;

import me.pabloestrada.MancalaGame.slots.PlayerType;

public class GameInfo {

	private GameType type;
	
	private String playerOneName;
	private String playerTwoName;
	
	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public void setPlayerOneName(String playerOneName) {
		this.playerOneName = playerOneName;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

	public void setPlayerTwoName(String playerTwoName) {
		this.playerTwoName = playerTwoName;
	}

	public GameInfo(GameType type) {
		this.type = type;
	}
	
	public GameType getGameType() {
		return type;
	}
	
	public String getName(PlayerType type) {
		if(type == PlayerType.PLAYER_ONE)
			return playerOneName;
		if(type == PlayerType.PLAYER_TWO)
			return playerTwoName;
		return "CPU";
	}
	
	public boolean isMultiplayer() {
		if(type == GameType.MULTIPLAYER)
			return true;
		return false;
	}
	public boolean isSingleplayer() {
		if(type == GameType.SINGLEPLAYER)
			return true;
		return false;
	}
	
}
