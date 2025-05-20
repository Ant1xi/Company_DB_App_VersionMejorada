package exceptions;

@SuppressWarnings("serial")
public class OrderDataException extends CompanyException{
	public OrderDataException() {
    }

    public OrderDataException(String message) {
        super(message);
    }
}