package homeworks.hw16.backend.entities;

import java.sql.Date;

public class Match {
	private int id;
	private Team home;
	private Team away;
	private Stadium stadium;
	private Date date;

	public Match(int id, Team home, Team away, Stadium stadium, Date date) {
		this.id = id;
		this.home = home;
		this.away = away;
		this.stadium = stadium;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getHomeTeam() {
		return home;
	}

	public Team getAwayTeam() {
		return away;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public Date getDate() {
		return date;
	}

}
