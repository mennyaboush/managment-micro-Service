package management.service;

public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = -5894960579374317955L;

	public DataNotFoundException() {
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


}
