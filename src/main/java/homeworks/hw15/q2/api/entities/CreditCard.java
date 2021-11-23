package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 16, nullable = false, unique = true)
	private String number;
	@OneToOne(optional = false)
	private Account account;
	private int expirationYear;
	private int expirationMonth;
	private short cvv;
	private boolean active;

	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public short getCvv() {
		return cvv;
	}

	public void setCvv(short cvv) {
		this.cvv = cvv;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
