package tablas;

import exceptions.ContactDataException;

public class Contact {
	private Integer contactId; // Clave primaria
	private String firstName; // No puede ser nulo
	private String lastName; // No pude ser nulo
	private String email; // No puede ser nulo
	private String phone;
	private Integer customerId; //Clave foranea

	public Contact(Integer contactId, String firstName, String lastName, String email, String phone,
			Integer customerId) throws ContactDataException {
		
		if (contactId == null || firstName == null || lastName == null || email == null || customerId == null) {
			throw new ContactDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}
		
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.customerId = customerId;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + ", customerId=" + customerId + "]";
	}

}
