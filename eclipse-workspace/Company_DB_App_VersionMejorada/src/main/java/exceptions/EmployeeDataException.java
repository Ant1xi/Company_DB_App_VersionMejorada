package exceptions;

@SuppressWarnings("serial")
public class EmployeeDataException extends CompanyException{
	public EmployeeDataException() {
    }

    public EmployeeDataException(String message) {
        super(message);
    }
}

