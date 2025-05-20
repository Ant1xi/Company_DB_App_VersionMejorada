package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Region;

public class RegionDAO {
	
	public List<Region> getAll(Connection conn) throws CompanyException {
		String sqlQuery = "SELECT * FROM regions";

		List<Region> regionList = new ArrayList<Region>();

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery); ResultSet rs = pstmt.executeQuery();) {
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
