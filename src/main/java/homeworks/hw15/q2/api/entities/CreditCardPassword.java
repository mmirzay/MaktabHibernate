package homeworks.hw15.q2.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author MoMi
 *
 */
@Entity
public class CreditCardPassword implements Serializable {

	private static final long serialVersionUID = 83493253693378825L;
	@Id
	@OneToOne
	private CreditCard card;
	private short firstPassword;
	private Short secondPassword;
	private short expirationYear;
	private short expirationMonth;
	private short cvv;
	private boolean active;

	public CreditCardPassword(CreditCard card, short firstPassword, short expirationYear, short expirationMonth,
			short cvv) {
		this.card = card;
		this.firstPassword = firstPassword;
		this.expirationYear = expirationYear;
		this.expirationMonth = expirationMonth;
		this.cvv = cvv;
		this.setActive(true);
	}

	public CreditCardPassword() {

	}

	public short getFirstPassword() {
		return firstPassword;
	}

	public CreditCard getCard() {
		return card;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public short getExpirationYear() {
		return expirationYear;
	}

	public short getExpirationMonth() {
		return expirationMonth;
	}

	public short getCvv() {
		return cvv;
	}

	public void setFirstPassword(short firstPassword) {
		this.firstPassword = firstPassword;
	}

	public Short getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(Short secondPassword) {
		this.secondPassword = secondPassword;
	}

	@Override
	public String toString() {
		return "CreditCardPassword [card=" + card + ", firstPassword=" + firstPassword + ", secondPassword="
				+ secondPassword + ", expirationYear=" + expirationYear + ", expirationMonth=" + expirationMonth
				+ ", cvv=" + cvv + ", active=" + active + "]";
	}

	public String getInfo() {
		return "Card Number: " + card.getNumber() + " CVV: " + cvv + " Year: " + expirationYear + " Month: "
				+ expirationMonth;
	}

}
