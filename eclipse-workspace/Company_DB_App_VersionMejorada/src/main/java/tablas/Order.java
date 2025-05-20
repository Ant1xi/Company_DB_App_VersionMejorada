package tablas;


import java.util.Date;

import exceptions.OrderDataException;

public class Order {
	private Integer orderId; // Clave primaria
	private Integer customerId; // No puede ser nulo y clave foranea
	private String status; // No puede ser nulo
	private Integer salesmanId; // Clave foranea
	private Date orderDate; // No puede ser nulo

	public Order(Integer orderId, Integer customerId, String status, Integer salesmanId, Date orderDate) throws OrderDataException {
		if (customerId == null || status == null || orderDate == null) {
			throw new OrderDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}

		this.orderId = orderId;
		this.customerId = customerId;
		this.status = status;
		this.salesmanId = salesmanId;
		this.orderDate = orderDate;
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", status=" + status + ", salesmanId="
				+ salesmanId + ", orderDate=" + orderDate + "]";
	}

}
