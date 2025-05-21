package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.CompanyException;

/**
 * DAO (Data Access Object) para la entidad {@code Warehouse}.
 * 
 * Proporciona el método necesario para insertar un nuevo almacén
 * en la base de datos, asociado a una ubicación específica.
 * 
 * Utiliza una conexión externa proporcionada desde el controlador.
 * 
 * @author Antonio
 */
public class WarehouseDAO {

    /**
     * Inserta un nuevo almacén en la base de datos.
     *
     * @param conn       conexión activa a la base de datos.
     * @param nombre     nombre del almacén a crear.
     * @param locationId ID de la ubicación en la que se ubicará el almacén.
     * @throws SQLException si ocurre un error en la ejecución SQL.
     * @throws CompanyException si no se puede completar la operación de inserción.
     */
    public void insertarAlmacen(Connection conn, String nombre, int locationId) throws SQLException, CompanyException {
        String sql = "INSERT INTO warehouses (warehouse_name, location_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, locationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al insertar almacén");
        }
    }
}
