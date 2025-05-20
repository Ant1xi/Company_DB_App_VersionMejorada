package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Location;

public class LocationDAO {
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
