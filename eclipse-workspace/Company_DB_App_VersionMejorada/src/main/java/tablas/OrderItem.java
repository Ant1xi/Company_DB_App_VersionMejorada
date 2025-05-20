package tablas;

import exceptions.OrderItemDataException;

public class OrderItem {
	private Integer orderId;
	private Integer itemId;
	private Integer productId; // No puede ser nulo
	private Double quantity; // No puede ser nulo
	private Double unitPrice; // No puede ser nulo

	public OrderItem(Integer orderId, Integer itemId, Integer productId, Double quantity, Double unitPrice) throws OrderItemDataException {
		if (orderId == null || itemId == null || productId == null || quantity == null || unitPrice == null) {
			throw new OrderItemDataException("Ninguno de los campos puede ser nulo.");
		}

		this.orderId = orderId;
		this.itemId = itemId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", itemId=" + itemId + ", productId=" + productId + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + "]";
	}

}
