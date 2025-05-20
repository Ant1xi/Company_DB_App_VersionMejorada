package tablas;

import exceptions.InventoryDataException;

public class Inventory {
	private Integer productId; //Clave foranea
	private Integer warehouseId; //Clave forenea
	private Integer quantity; //No puede ser nulo

	public Inventory(Integer productId, Integer warehouseId, Integer quantity) throws InventoryDataException{
		
		if (productId == null || warehouseId == null || quantity == null) {
			throw new InventoryDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}
		this.productId = productId;
		this.warehouseId = warehouseId;
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [productId=" + productId + ", warehouseId=" + warehouseId + ", quantity=" + quantity + "]";
	}

}
