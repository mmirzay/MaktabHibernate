package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author MoMi
 *
 */
@Entity
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 16, nullable = false, unique = true)
	private String number;
	@OneToOne(optional = false)
	private Account account;

	public CreditCard(String number, Account account) {
		this.account = account;
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", number=" + number + ", account=" + account + "]";
	}

}
