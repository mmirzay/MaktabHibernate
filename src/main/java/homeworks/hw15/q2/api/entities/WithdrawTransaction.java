package homeworks.hw15.q2.api.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "withdraw")
public class WithdrawTransaction extends Transaction {

	public WithdrawTransaction(Account srcAccount, double amount) {
		super(srcAccount, null, amount);
	}

}
