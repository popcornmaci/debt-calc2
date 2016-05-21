package hu.popcornmaci.service.api;

public class RegException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegException(String message) {
		super(message);
	}

	public RegException(Throwable cause) {
		super(cause);
	}
	
}
