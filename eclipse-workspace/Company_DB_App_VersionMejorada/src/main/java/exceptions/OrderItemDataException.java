package exceptions;

@SuppressWarnings("serial")
public class OrderItemDataException extends CompanyException{
	public OrderItemDataException() {
    }

    public OrderItemDataException(String message) {
        super(message);
    }
}