package exceptions;

@SuppressWarnings("serial")
public class WarehouseDataException extends CompanyException{
	public WarehouseDataException() {
    }

    public WarehouseDataException(String message) {
        super(message);
    }
}