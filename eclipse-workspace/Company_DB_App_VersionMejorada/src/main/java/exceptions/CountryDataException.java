package exceptions;

@SuppressWarnings("serial")
public class CountryDataException extends CompanyException{
	public CountryDataException() {
    }

    public CountryDataException(String message) {
        super(message);
    }
}
