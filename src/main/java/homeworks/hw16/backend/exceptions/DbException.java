package homeworks.hw16.backend.exceptions;

public class DbException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -4685240399406206591L;

	public DbException(String msg, Exception e) {
		super(msg, e);
	}

}
