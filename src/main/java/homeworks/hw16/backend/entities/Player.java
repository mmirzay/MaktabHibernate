package homeworks.hw16.backend.entities;

public class Player {
	private int id;
	private String name;
	private PlayerPosition position;
	private int skillLevel;
	private Team team;
	private Contract contract;

	public Player(int id, String name, PlayerPosition position, int skillLevel, Team team) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.skillLevel = skillLevel;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public PlayerPosition getPosition() {
		return position;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public Team getTeam() {
		return team;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
