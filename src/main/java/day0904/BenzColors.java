package day0904;

public enum BenzColors {

	BLACK(0), BLUE(3), WHITE(2), RED(1);

	private int value;

	BenzColors(int value) {
		this.value = value;
	}

	public int getAsInt() {
		return this.value;
	}

}
