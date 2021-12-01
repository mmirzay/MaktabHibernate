package homeworks.hw15.q2.api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author MoMi
 *
 */
@Entity
public class Branch {

	@Id
	private int code;
	@ManyToOne
	private Bank bank;

	public Branch(Bank bank, int code) {
		this.code = code;
		this.bank = bank;
	}

	public int getCode() {
		return code;
	}

	public Bank getBank() {
		return bank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + code;
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
		Branch other = (Branch) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Branch [code=" + code + ", bank=" + bank + "]";
	}

}
