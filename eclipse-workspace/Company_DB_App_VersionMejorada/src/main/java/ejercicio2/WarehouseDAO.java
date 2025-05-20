package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.CompanyException;
import exceptions.WarehouseDataException;
import tablas.Warehouse;

public class WarehouseDAO {

	public void insertarAlmacen(Connection conn, String nombre, int locationId) throws SQLException, CompanyException {
		String sql = "INSERT INTO warehouses (warehouse_name, location_id) VALUES (?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nombre);
			pstmt.setInt(2, locationId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompanyException("Error al insertar almacen");
		}
	}
}
