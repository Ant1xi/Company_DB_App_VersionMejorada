package ejercicio2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.ClaseConectoraBBDD;

import exceptions.CompanyException;
import tablas.Country;
import tablas.Location;
import tablas.Region;

public class AlmacenControllerEjercicio2 {
	public void cargaVistaAltaAlmacen() {
		new AltaAlmacenVista();
	}

	public List<RegionLocationDTO> obtenerRegiones() throws CompanyException {
		List<RegionLocationDTO> listaRegionesDTO = new ArrayList<>();

		try (Connection conn = ClaseConectoraBBDD.getConnection()) {
			RegionDAO regionDAO = new RegionDAO();
			List<Region> listaRegiones = regionDAO.getAll(conn);

			// Importante para que el JcomboBox no salga precargado con alguna Region
			listaRegionesDTO.add(new RegionLocationDTO(0, ""));

			for (Region region : listaRegiones) {
				listaRegionesDTO.add(new RegionLocationDTO(region.getRegionId(), region.getRegionName()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaRegionesDTO;
	}

	// Este método tiene un DTO especifico que recoge id de tipo String y nombre de
	// tipo String tambien ya que por algún motivo la base de datos guarda las id de
	// los paises como String por ejemplo id de España = "ES"

	public List<CountryDTO> obtenerPaisesPorRegion(int regionId) throws SQLException, CompanyException {
		List<CountryDTO> listaPaisesDTO = new ArrayList<>();

		try (Connection conn = ClaseConectoraBBDD.getConnection()) {
			CountryDAO countryDAO = new CountryDAO();
			List<Country> listaPaises = countryDAO.getCountriesByRegion(conn, regionId);

			// Importante para que el JcomboBox no salga precargado con algun País
			listaPaisesDTO.add(new CountryDTO("", ""));

			for (Country country : listaPaises) {
				listaPaisesDTO.add(new CountryDTO(country.getCountryId(), country.getCountryName()));
			}
		}

		return listaPaisesDTO;
	}
	
	public List<RegionLocationDTO> obtenerUbicacionesPorPais(String countryId) throws SQLException, CompanyException {
		List<RegionLocationDTO> listaDTO = new ArrayList<>();

		try (Connection conn = ClaseConectoraBBDD.getConnection()) {
			LocationDAO locationDAO = new LocationDAO();
			List<Location> listaLocations = locationDAO.getLocationsByCountry(conn, countryId);
			
			// Importante para que el JcomboBox no salga precargado con alguna Ubicación
			listaDTO.add(new RegionLocationDTO(0,""));

			for (Location location : listaLocations) {
				
				listaDTO.add(new RegionLocationDTO(location.getLocationId(), location.getCity()));
			}
		}

		return listaDTO;
	}
	
	public void insertarAlmacen(String nombre, int locationId) throws CompanyException {
		try (Connection conn = ClaseConectoraBBDD.getConnection()) {
			WarehouseDAO warehouseDAO = new WarehouseDAO();
			warehouseDAO.insertarAlmacen(conn, nombre, locationId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompanyException("Error al insertar Almacen");
		}
	}
}
