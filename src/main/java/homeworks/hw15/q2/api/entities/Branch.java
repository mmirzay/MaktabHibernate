package homeworks.hw15.q2.api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Branch {

	@Id
	private int code;
	@ManyToOne
	private Bank bank;
}
