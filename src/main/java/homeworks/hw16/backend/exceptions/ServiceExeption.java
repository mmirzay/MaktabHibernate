package homeworks.hw16.backend.exceptions;

public class ServiceExeption extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 3363814776681188977L;

	public ServiceExeption(String msg, Exception e) {
		super(msg, e);
	}

}
