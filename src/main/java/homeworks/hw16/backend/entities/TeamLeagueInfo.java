package homeworks.hw16.backend.entities;

import homeworks.hw16.util.Printer;

public class TeamLeagueInfo {
	private String team;
	private int numberOfWins;
	private int numberOfDraws;
	private int numberOfLoss;
	private int numberOfScoredGoals;
	private int numberOfReceiedGoals;
	private int goalsDiff;
	private int score;

	public TeamLeagueInfo(String team, int numberOfWins, int numberOfDraws, int numberOfLoss, int numberOfScoredGoals,
			int numberOfReceiedGoals, int score) {
		this.team = team;
		this.numberOfWins = numberOfWins;
		this.numberOfDraws = numberOfDraws;
		this.numberOfLoss = numberOfLoss;
		this.numberOfScoredGoals = numberOfScoredGoals;
		this.numberOfReceiedGoals = numberOfReceiedGoals;
		this.score = score;
		this.goalsDiff = numberOfScoredGoals - numberOfReceiedGoals;
	}

	public String getTeam() {
		return team;
	}

	public int getNumberOfWins() {
		return numberOfWins;
	}

	public int getNumberOfDraws() {
		return numberOfDraws;
	}

	public int getNumberOfLoss() {
		return numberOfLoss;
	}

	public int getNumberOfScoredGoals() {
		return numberOfScoredGoals;
	}

	public int getNumberOfReceiedGoals() {
		return numberOfReceiedGoals;
	}

	public int getScore() {
		return score;
	}

	public int getGoalsDiff() {
		return goalsDiff;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(Printer.formatter(team,13));
		result.append(" | ");
		result.append(Printer.formatter(numberOfWins));
		result.append(" | ");
		result.append(Printer.formatter(numberOfDraws));
		result.append(" | ");
		result.append(Printer.formatter(numberOfLoss));
		result.append(" | ");
		result.append(Printer.formatter(numberOfScoredGoals));
		result.append(" | ");
		result.append(Printer.formatter(numberOfReceiedGoals));
		result.append(" | ");
		result.append(Printer.formatter(goalsDiff));
		result.append(" | ");
		result.append(Printer.formatter(score));
		return result.toString();
	}

}
