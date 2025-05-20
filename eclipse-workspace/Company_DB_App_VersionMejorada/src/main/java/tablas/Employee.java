package tablas;

import java.util.Date;

import exceptions.EmployeeDataException;


public class Employee {
	// Wrapper para poder almacenar nulos
	private Integer employeeId; // Clave primaria
	private String firstName; // No puede ser nulo
	private String lastName; // No puede ser nulo
	private String email; // No puede ser nulo
	private String phone; // No puede ser nulo
	private Date hireDate; // No puede ser nulo
	private Integer managerId;
	private String jobTitle; // No puede ser nulo

	public Employee(Integer employeeId, String firstName, String lastName, String email, String phone, Date hireDate,
			Integer managerId, String jobTitle) throws EmployeeDataException{

// Validaciones para evitar valores nulos en campos "obligatorios"
		if (firstName == null || lastName == null || email == null || phone == null || jobTitle == null) {
			throw new EmployeeDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}

		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;

// Asigna hireDate con validación
		if (hireDate != null) {
			this.hireDate = hireDate;
		} else {
			this.hireDate = new Date(); // Si es null, usa la fecha actual
		}

		this.managerId = managerId;
		this.jobTitle = jobTitle;
	}
	
	public Employee(String firstName, String lastName, String email, String phone, Date hireDate,
			Integer managerId, String jobTitle) throws EmployeeDataException {

// Validaciones para evitar valores nulos en campos "obligatorios"
		if (firstName == null || lastName == null || email == null || phone == null || jobTitle == null) {
			throw new EmployeeDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;

		if (hireDate == null) {
		    throw new EmployeeDataException("La fecha de contratación no puede ser nula.");
		}
		this.hireDate = hireDate;

		this.managerId = managerId;
		this.jobTitle = jobTitle;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + ", hireDate=" + hireDate + ", managerId=" + managerId + ", jobTitle="
				+ jobTitle + "]";
	}

}
