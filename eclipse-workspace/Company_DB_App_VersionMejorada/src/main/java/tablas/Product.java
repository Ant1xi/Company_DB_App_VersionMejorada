package tablas;

import exceptions.ProductDataException;

public class Product {
	private Integer productId;
	private String productName; //No puede ser nulo
	private String description;
	private Double standarCost;
	private Double listPrice;
	private Integer categoryId; //No puede ser nulo
	
	public Product(Integer productId, String productName, String description, Double standarCost, Double listPrice,
			Integer categoryId) throws ProductDataException {

		if (productName == null || categoryId == null) {
			throw new ProductDataException("Los campos 'productName' y 'categoryId' no pueden ser nulos.");
		}

		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.standarCost = standarCost;
		this.listPrice = listPrice;
		this.categoryId = categoryId;
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getStandarCost() {
		return standarCost;
	}

	public void setStandarCost(Double standarCost) {
		this.standarCost = standarCost;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", standarCost=" + standarCost + ", listPrice=" + listPrice + ", categoryId=" + categoryId + "]";
	}
	
	
	
}
