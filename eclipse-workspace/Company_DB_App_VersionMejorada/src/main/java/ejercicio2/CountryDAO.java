package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Country;

/**
 * DAO (Data Access Object) para la entidad {@link Country}.
 * 
 * Esta clase permite acceder a la información de países almacenada
 * en la base de datos, especialmente filtrando por región.
 * 
 * Utiliza una conexión externa proporcionada por el controlador.
 * 
 * @author Antonio
 */
public class CountryDAO {

    /**
     * Obtiene todos los países que pertenecen a una región concreta.
     * 
     * @param conn conexión activa a la base de datos.
     * @param regionId ID de la región de la que se quieren obtener los países.
     * @return lista de países pertenecientes a la región especificada.
     * @throws CompanyException si ocurre un error al ejecutar la consulta.
     */
    public List<Country> getCountriesByRegion(Connection conn, int regionId) throws CompanyException {
        String sql = "SELECT country_id, country_name, region_id FROM countries WHERE region_id = ?";
        List<Country> lista = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, regionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("country_id");
                    String name = rs.getString("country_name");
                    int region = rs.getInt("region_id");

                    lista.add(new Country(id, name, region));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al obtener países");
        }

        return lista;
    }
}
