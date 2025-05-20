package exceptions;

@SuppressWarnings("serial")
public class InventoryDataException extends CompanyException{
	public InventoryDataException() {
    }

    public InventoryDataException(String message) {
        super(message);
    }
}