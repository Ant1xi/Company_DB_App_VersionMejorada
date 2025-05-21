package ejercicio3;

/**
 * DTO que usamos para modificar los datos de un cliente y su contacto.
 * 
 * Aquí guardamos tanto los datos de la tabla CUSTOMERS como los de CONTACTS,
 * para mostrarlos y editarlos en la interfaz gráfica.
 * 
 * También se guardan los IDs internos que necesitamos para luego actualizar en la base de datos,
 * aunque al usuario no se los mostramos.
 * 
 * Básicamente, con este objeto agrupamos todo lo que se puede cambiar de un cliente.
 * 
 * @author Antonio
 */
public class ClienteModificarDTO {

	// IDs necesarios para saber qué cliente y contacto estamos modificando
	private int customerId;
	private int contactId;

	// Datos del cliente que el usuario puede editar
	private String customerName;
	private String phone;
	private String website;
	private String creditLimit;

	// Datos del contacto asociados al cliente
	private String firstName;
	private String lastName;
	private String email;

	/**
	 * Constructor que se usa cuando cargamos los datos para mostrar o editar.
	 * Los IDs normalmente se rellenan aparte.
	 */
	public ClienteModificarDTO(String customerName, String phone, String website, String creditLimit,
			String firstName, String lastName, String email) {
		this.customerName = customerName;
		this.phone = phone;
		this.website = website;
		this.creditLimit = creditLimit;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Getters y setters (normales, sin misterio) para usar desde la vista/controlador

	public int getCustomerId() {
		return customerId;
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
}
