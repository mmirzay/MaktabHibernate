package homeworks.hw16.backend.entities;

public class Coach {
	private int id;
	private String name;
	private Team team;
	private Contract contract;

	public Coach(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
