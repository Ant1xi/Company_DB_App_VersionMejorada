package tablas;
import exceptions.WarehouseDataException;

public class Warehouse {
	private Integer warehouseId;
	private String warehouseName;
	private Integer locationId;

	public Warehouse(Integer warehouseId, String warehouseName, Integer locationId) throws WarehouseDataException {
		if (warehouseId == null || warehouseName == null || locationId == null) {
			throw new WarehouseDataException("Ninguno de los campos puede ser nulo.");
		}

		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.locationId = locationId;
	}

	public Warehouse(String warehouseName, Integer locationId) throws WarehouseDataException {
		if (warehouseName == null || locationId == null) {
			throw new WarehouseDataException("WarehouseName y locationId no pueden ser nulos.");
		}

		this.warehouseName = warehouseName;
		this.locationId = locationId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", locationId="
				+ locationId + "]";
	}

}
