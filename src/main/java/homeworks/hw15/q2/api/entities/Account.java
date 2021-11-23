package homeworks.hw15.q2.api.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Account {
	@Id
	private long number;
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_customer_account"))
	private Customer customer;
	private double balance;
	@ManyToOne
	@JoinColumn(name = "bank_name", nullable = false)
	private Bank bank;

	public Account(long number, Customer customer) {
		this.number = number;
		this.customer = customer;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
