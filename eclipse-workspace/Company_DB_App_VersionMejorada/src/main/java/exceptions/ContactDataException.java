package exceptions;

@SuppressWarnings("serial")
public class ContactDataException extends CompanyException{
	public ContactDataException() {
    }

    public ContactDataException(String message) {
        super(message);
    }
}