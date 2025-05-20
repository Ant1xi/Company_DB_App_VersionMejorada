package ejercicio3;

public class ClienteModificarDTO {

	// ID necesarios internamente (no se muestran al usuario)
	private int customerId;
	private int contactId;

	// Campos editables de CUSTOMERS
	private String customerName;
	private String phone;
	private String website;
	private String creditLimit;

	// Campos editables de CONTACTS
	private String firstName;
	private String lastName;
	private String email;

	// Constructor sin IDs
	public ClienteModificarDTO(String customerName, String phone, String fax, String website, String creditLimit,
			String firstName, String lastName, String email) {
		this.customerName = customerName;
		this.phone = phone;
		this.website = website;
		this.creditLimit = creditLimit;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Getters y setters para los campos visibles

	// Getters y setters para los IDs
	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
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

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
}
