package homeworks.hw16.backend.entities;

public class PlayerMatchInfo {
	private Player player;
	private Match match;
	private int numberOfScoredGoals;
	private int numberOfAssists;

	public PlayerMatchInfo(Player player, Match match, int numberOfScoredGoals, int numberOfAssists) {
		this.player = player;
		this.match = match;
		this.numberOfScoredGoals = numberOfScoredGoals;
		this.numberOfAssists = numberOfAssists;
	}

	public Player getPlayer() {
		return player;
	}

	public Match getMatch() {
		return match;
	}

	public int getNumberOfScoredGoals() {
		return numberOfScoredGoals;
	}

	public int getNumberOfAssists() {
		return numberOfAssists;
	}

}
