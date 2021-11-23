package homeworks.hw15.q2.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CreditCardPassword implements Serializable {

	private static final long serialVersionUID = 83493253693378825L;
	@Id
	@OneToOne
	private CreditCard card;
	private short firstPassword;
	private Short secondPassword;

	public short getFirstPassword() {
		return firstPassword;
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
}
