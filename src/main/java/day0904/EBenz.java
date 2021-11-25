package day0904;

import javax.persistence.Entity;

@Entity
public class EBenz extends Benz {
	private int capacity;

	public EBenz() {
	}

	public EBenz(int cap) {
		this.capacity = cap;
	}

	public int getCapacity() {
		return capacity;
	}

//	public void setCapacity(int capacity) {
//		this.capacity = capacity;
//	}
}
