package exceptions;

@SuppressWarnings("serial")
public class CompanyException extends Exception {
	public CompanyException() {
	}

	public CompanyException(String message) {
		super(message);
	}
}
