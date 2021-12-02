package homeworks.hw15.q2.api.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "deposit")
public class DepositTransaction extends Transaction {

	public DepositTransaction(Account srcAccount, double amount) {
		super(srcAccount, null, amount);
	}

}
