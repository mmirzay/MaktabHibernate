package homeworks.hw16.backend.entities;

import java.util.List;

public class LeagueTable {
	private List<TeamLeagueInfo> teamsTable;

	public LeagueTable(List<TeamLeagueInfo> teamsTable) {
		this.teamsTable = teamsTable;
	}

	public List<TeamLeagueInfo> getTeamsTable() {
		return teamsTable;
	}

}
