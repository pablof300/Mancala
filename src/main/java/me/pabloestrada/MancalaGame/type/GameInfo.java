package me.pabloestrada.MancalaGame.type;

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
	
}
