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

/**
 * Controlador del ejercicio 2: Gestión de alta de almacenes.
 * 
 * Se encarga de:
 * <ul>
 *   <li>Cargar la vista de alta de almacenes.</li>
 *   <li>Obtener regiones, países y ubicaciones para alimentar combos dependientes.</li>
 *   <li>Insertar un nuevo almacén en la base de datos.</li>
 * </ul>
 * 
 * Este controlador actúa como intermediario entre la vista y los DAOs
 * de {@link Region}, {@link Country}, {@link Location} y almacenes.
 * 
 * @author Antonio
 */
public class AlmacenControllerEjercicio2 {

    /**
     * Carga la interfaz gráfica para dar de alta un nuevo almacén.
     */
    public void cargaVistaAltaAlmacen() {
        new AltaAlmacenVista();
    }

    /**
     * Devuelve una lista de regiones disponibles en la base de datos,
     * representadas como objetos {@link RegionLocationDTO}.
     * 
     * Se añade una opción vacía al principio para evitar que el combo tenga
     * una región seleccionada por defecto.
     * 
     * @return lista de regiones en formato DTO.
     * @throws CompanyException si ocurre un error en la carga de datos.
     */
    public List<RegionLocationDTO> obtenerRegiones() throws CompanyException {
        List<RegionLocationDTO> listaRegionesDTO = new ArrayList<>();

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            RegionDAO regionDAO = new RegionDAO();
            List<Region> listaRegiones = regionDAO.getAll(conn);

            listaRegionesDTO.add(new RegionLocationDTO(0, ""));

            for (Region region : listaRegiones) {
                listaRegionesDTO.add(new RegionLocationDTO(region.getRegionId(), region.getRegionName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaRegionesDTO;
    }

    /**
     * Devuelve los países asociados a una región concreta.
     * Los países se representan mediante objetos {@link CountryDTO}.
     * 
     * Este método considera que los IDs de los países son cadenas (por ejemplo, "ES").
     * 
     * @param regionId ID de la región seleccionada.
     * @return lista de países en formato DTO.
     * @throws SQLException si falla la consulta.
     * @throws CompanyException si hay error en el acceso a los datos.
     */
    public List<CountryDTO> obtenerPaisesPorRegion(int regionId) throws SQLException, CompanyException {
        List<CountryDTO> listaPaisesDTO = new ArrayList<>();

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            CountryDAO countryDAO = new CountryDAO();
            List<Country> listaPaises = countryDAO.getCountriesByRegion(conn, regionId);

            listaPaisesDTO.add(new CountryDTO("", ""));

            for (Country country : listaPaises) {
                listaPaisesDTO.add(new CountryDTO(country.getCountryId(), country.getCountryName()));
            }
        }

        return listaPaisesDTO;
    }

    /**
     * Devuelve las ubicaciones asociadas a un país concreto,
     * representadas como objetos {@link RegionLocationDTO}.
     * 
     * Se utiliza para alimentar el combo de ubicaciones en la vista.
     * 
     * @param countryId ID del país (por ejemplo, "ES").
     * @return lista de ubicaciones (ciudades).
     * @throws SQLException si hay fallo en la consulta SQL.
     * @throws CompanyException si ocurre error al obtener los datos.
     */
    public List<RegionLocationDTO> obtenerUbicacionesPorPais(String countryId) throws SQLException, CompanyException {
        List<RegionLocationDTO> listaDTO = new ArrayList<>();

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            LocationDAO locationDAO = new LocationDAO();
            List<Location> listaLocations = locationDAO.getLocationsByCountry(conn, countryId);

            listaDTO.add(new RegionLocationDTO(0, ""));

            for (Location location : listaLocations) {
                listaDTO.add(new RegionLocationDTO(location.getLocationId(), location.getCity()));
            }
        }

        return listaDTO;
    }

    /**
     * Inserta un nuevo almacén en la base de datos con el nombre y ubicación especificados.
     * 
     * @param nombre nombre del almacén.
     * @param locationId ID de la ubicación donde se sitúa el almacén.
     * @throws CompanyException si ocurre un error al insertar el registro.
     */
    public void insertarAlmacen(String nombre, int locationId) throws CompanyException {
        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            WarehouseDAO warehouseDAO = new WarehouseDAO();
            warehouseDAO.insertarAlmacen(conn, nombre, locationId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al insertar Almacén");
        }
    }
}
