package tablas;

import exceptions.IncorrectDataException;

public class Customer {
	private Integer customerId; // Clave primaria
	private String name; //No puede ser nulo
	private String addres;
	private String website;
	private Double creditLimit;

	public Customer(Integer customerId, String name, String addres, String website, Double creditLimit) throws IncorrectDataException {
		
		if (name == null) {
			throw new IncorrectDataException("'name' no puede ser nulo.");
		}
		
		this.customerId = customerId;
		this.name = name;
		this.addres = addres;
		this.website = website;
		this.creditLimit = creditLimit;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", addres=" + addres + ", website=" + website
				+ ", creditLimit=" + creditLimit + "]";
	}

}
