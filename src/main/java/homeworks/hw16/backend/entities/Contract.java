package homeworks.hw16.backend.entities;

public class Contract {
	private int payment;
	private int year;
	private int length;

	public Contract(int payment, int year, int length) {
		this.payment = payment;
		this.year = year;
		this.length = length;
	}

	public int getPayment() {
		return payment;
	}

	public int getYear() {
		return year;
	}

	public int getLength() {
		return length;
	}

}
