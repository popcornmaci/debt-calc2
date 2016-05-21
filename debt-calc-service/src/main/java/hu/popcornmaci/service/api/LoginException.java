package hu.popcornmaci.service.api;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}
	
}
