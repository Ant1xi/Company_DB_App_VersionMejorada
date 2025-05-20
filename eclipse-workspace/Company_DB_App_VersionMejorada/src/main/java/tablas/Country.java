package tablas;

import exceptions.CountryDataException;

public class Country {
	private String countryId; //Clave primaria
	private String countryName; //No puede ser nulo
	private Integer regionId; //Clave foranea de Region
	
	public Country(String countryId, String countryName, Integer regionId) throws CountryDataException{
		if (countryId == null || countryName == null || regionId == null) {
			throw new CountryDataException("Ninguno de los campos obligatorios puede ser nulo.");
		}
		this.countryId = countryId;
		this.countryName = countryName;
		this.regionId = regionId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", regionId=" + regionId + "]";
	}
	
	
	
}
