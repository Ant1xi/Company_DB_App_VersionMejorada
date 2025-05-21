package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Region;

/**
 * DAO (Data Access Object) para la entidad {@link Region}.
 * 
 * Proporciona métodos para acceder a los datos de las regiones almacenadas
 * en la base de datos. Es utilizada principalmente para cargar el combo
 * de regiones en la vista de alta de almacenes.
 * 
 * Requiere una conexión externa proporcionada por el controlador.
 * 
 * @author Antonio
 */
public class RegionDAO {

    /**
     * Recupera todas las regiones almacenadas en la base de datos.
     *
     * @param conn conexión activa a la base de datos.
     * @return lista de objetos {@link Region} con los datos obtenidos.
     * @throws CompanyException si ocurre un error en la consulta.
     */
    public List<Region> getAll(Connection conn) throws CompanyException {
        String sqlQuery = "SELECT * FROM regions";
        List<Region> regionList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Integer regionId = rs.getInt("region_id");
                String regionName = rs.getString("region_name");

                regionList.add(new Region(regionId, regionName));
            }

            return regionList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al obtener las regiones");
        }
    }
}
