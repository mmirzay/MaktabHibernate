package homeworks.hw15.q2.api.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "card")
public class CardToCardTransaction extends Transaction {

	public CardToCardTransaction(CreditCard source, CreditCard destination, double amount) {
		super(source.getAccount(), destination.getAccount(), amount);
	}

}
