package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Location;

/**
 * DAO (Data Access Object) para la entidad {@link Location}.
 * 
 * Permite obtener todas las ubicaciones (locations) de un país específico,
 * necesarias para el alta de almacenes.
 * 
 * Utiliza una conexión externa proporcionada por el controlador.
 * 
 * @author Antonio
 */
public class LocationDAO {

    /**
     * Devuelve una lista de ubicaciones pertenecientes a un país concreto.
     * 
     * @param conn      conexión activa a la base de datos.
     * @param countryId ID del país (por ejemplo, "ES", "US", etc.).
     * @return lista de objetos {@link Location} correspondientes al país.
     * @throws CompanyException si ocurre un error al ejecutar la consulta.
     */
    public List<Location> getLocationsByCountry(Connection conn, String countryId) throws CompanyException {
        String sql = "SELECT location_id, address, postal_code, city, state, country_id FROM locations WHERE country_id = ?";
        List<Location> lista = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, countryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int locationId = rs.getInt("location_id");
                    String address = rs.getString("address");
                    String postalCode = rs.getString("postal_code");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String country = rs.getString("country_id");

                    lista.add(new Location(locationId, address, postalCode, city, state, country));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al obtener ubicaciones");
        }

        return lista;
    }
}
