package tablas;

import exceptions.ProductCategoryDataException;

public class ProductCategory {
	private Integer categoryId; // Clave primaria
	private String categoryName; // No puede ser nulo

	public ProductCategory(Integer categoryId, String categoryName) throws ProductCategoryDataException{
		if (categoryName == null) {
			throw new ProductCategoryDataException("El campo 'categoryName' no puede ser nulo.");
		}

		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
