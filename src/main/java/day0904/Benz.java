package day0904;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
public class Benz extends Vehicle {
	private double price;
	private BenzColors color;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BenzColors getColor() {
		return color;
	}

	public void setColor(BenzColors color) {
		this.color = color;
	}
}
