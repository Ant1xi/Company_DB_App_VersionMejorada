package exceptions;

@SuppressWarnings("serial")
public class ProductCategoryDataException extends CompanyException{
	public ProductCategoryDataException() {
    }

    public ProductCategoryDataException(String message) {
        super(message);
    }
}