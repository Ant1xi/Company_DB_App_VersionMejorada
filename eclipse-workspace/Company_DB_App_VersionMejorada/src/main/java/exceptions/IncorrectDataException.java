package exceptions;

@SuppressWarnings("serial")
public class IncorrectDataException extends CompanyException {
	public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }
}
