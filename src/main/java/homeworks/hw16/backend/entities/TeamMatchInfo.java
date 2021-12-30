package homeworks.hw16.backend.entities;

public class TeamMatchInfo {
	private Team team;
	private Match match;
	private int score;
	private int scoredGoals;
	private int receivedGoals;

	public TeamMatchInfo(Team team, Match match, int score, int scoredGoals, int receivedGoals) {
		this.team = team;
		this.match = match;
		this.score = score;
		this.scoredGoals = scoredGoals;
		this.receivedGoals = receivedGoals;
	}

	public Team getTeam() {
		return team;
	}

	public Match getMatch() {
		return match;
	}

	public int getScore() {
		return score;
	}

	public int getScoredGoals() {
		return scoredGoals;
	}

	public int getReceivedGoals() {
		return receivedGoals;
	}

}
