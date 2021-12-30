package homeworks.hw16.backend.entities;

public class Team {
	private String name;
	private Player capitan;
	private Coach coach;
	private City city;
	private Player[] players;

	public Team(String name, City city) {
		this.name = name;
		this.city = city;
	}

	public Player getCapitan() {
		return capitan;
	}

	public void setCapitan(Player capitan) {
		this.capitan = capitan;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public String getName() {
		return name;
	}

	public City getCity() {
		return city;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

}
