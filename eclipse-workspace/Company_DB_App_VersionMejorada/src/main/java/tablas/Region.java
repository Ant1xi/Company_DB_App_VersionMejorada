package tablas;

import exceptions.RegionDataException;

public class Region {
	private Integer regionId;
	private String regionName;

	public Region(Integer regionId, String regionName) throws RegionDataException{
		if (regionName == null) {
			throw new RegionDataException("El campo 'regionName' no puede ser nulo.");
		}

		this.regionId = regionId;
		this.regionName = regionName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + "]";
	}

}
