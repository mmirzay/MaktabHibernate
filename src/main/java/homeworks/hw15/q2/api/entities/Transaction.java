package homeworks.hw15.q2.api.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DiscriminatorFormula;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(value = "Null")
public abstract class Transaction {
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

	public Transaction(Account srcAccount, Account destAccount, double amount) {
		this.srcAccount = srcAccount;
		this.destAccount = destAccount;
		this.amount = amount;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public Account getSrcAccount() {
		return srcAccount;
	}

	public Account getDestAccount() {
		return destAccount;
	}

	public double getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

}
