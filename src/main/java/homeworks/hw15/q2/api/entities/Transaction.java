package homeworks.hw15.q2.api.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "source_account_id", nullable = false)
	private Account srcAccount;
	@ManyToOne
	@JoinColumn(name = "destination_account_id")
	private Account destAccount;
	private double amount;
	private Date date;

	public Transaction(int id, Account srcAccount, Account destAccount, double amount) {
		this.id = id;
		this.srcAccount = srcAccount;
		this.destAccount = destAccount;
		this.amount = amount;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getSrcAccount() {
		return srcAccount;
	}

	public void setSrcAccount(Account srcAccount) {
		this.srcAccount = srcAccount;
	}

	public Account getDestAccount() {
		return destAccount;
	}

	public void setDestAccount(Account destAccount) {
		this.destAccount = destAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
