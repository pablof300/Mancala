package me.pabloestrada.MancalaGame.marbles;

public enum MarbleColor {
	RED(1), BLUE(2), GREEN(3), YELLOW(4);

	private String address;

	private MarbleColor(int colorValue) {
		this.address = "ball" + colorValue;
	}

	public String getAddress() {
		return address;
	}
}
