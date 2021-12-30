package homeworks.hw16.backend.entities;

public enum PlayerPosition {
	GK("gol keeper"), LB("left full back"), CB("center full back"), RB("right back"), LM("left midfielder"),
	CM("center midfielder"), RM("right midfielder"), CF("center forward"), SF("second forward"), S("striker");

	private String value;

	PlayerPosition(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static PlayerPosition get(int positionNumber) {
		return switch (positionNumber) {
		case 1 -> GK;
		case 2 -> LB;
		case 3, 4 -> CB;
		case 5 -> RB;
		case 6 -> LM;
		case 7 -> CM;
		case 8 -> RM;
		case 9 -> SF;
		case 10 -> S;
		case 11 -> CF;
		default -> null;
		};
	}
}
