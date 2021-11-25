package day0904;

import javax.persistence.Entity;

@Entity
public class SBenz extends Benz {
	private boolean sunRoof;

	public boolean isSunRoof() {
		return sunRoof;
	}

	public void setSunRoof(boolean sunRoof) {
		this.sunRoof = sunRoof;
	}
}
