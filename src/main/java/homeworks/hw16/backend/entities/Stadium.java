package homeworks.hw16.backend.entities;

public class Stadium {
	private City city;
	private String name;
	private int capacity;

	public Stadium(City city, String name, int capacity) {
		this.city = city;
		this.name = name;
		this.capacity = capacity;
	}

	public City getCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

}
