package homeworks.hw15.q2.api.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author MoMi
 *
 */
@Entity
public class Account {
	@Id
	private long number;
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_customer_account"))
	private Customer customer;
	private double balance;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Branch branch;

	public Account(long number, Customer customer, Branch branch) {
		this.number = number;
		this.customer = customer;
		this.branch = branch;
	}

	public long getNumber() {
		return number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getBalance() {
		return balance;
	}

	public void increaseBalance(double balance) {
		this.balance += balance;
	}

	public void decreaseBalance(double balance) {
		this.balance -= balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (number ^ (number >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [number=" + number + ", customer=" + customer + ", balance=" + balance + ", branch=" + branch
				+ "]";
	}

}
