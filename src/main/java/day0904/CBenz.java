package day0904;

import javax.persistence.Entity;

@Entity
public class CBenz extends Benz {
	private boolean automated;

	public boolean isAutomated() {
		return automated;
	}

	public void setAutomated(boolean automated) {
		this.automated = automated;
	}
}
