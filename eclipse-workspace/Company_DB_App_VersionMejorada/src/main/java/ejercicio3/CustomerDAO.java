package ejercicio3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Customer;

public class CustomerDAO {
	public List<Customer> getAll(Connection conn) throws CompanyException {
		List<Customer> customerList = new ArrayList<Customer>();

		String consultaSQL = "SELECT * FROM Customers";
		try {
			PreparedStatement pstmt = conn.prepareStatement(consultaSQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String addres = rs.getString("address");
				String website = rs.getString("website");
				Double creditLimit = rs.getDouble("credit_limit");

				customerList.add(new Customer(customerId, name, addres, website, creditLimit));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}

	public List<Customer> getByIdOrNombre(Connection conn, int id, String nombre) throws CompanyException {
		List<Customer> customerList = new ArrayList<Customer>();

		String consultaSQL = "SELECT * FROM Customers WHERE customer_id = ? OR name = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(consultaSQL);

			pstmt.setInt(1, id);
			pstmt.setString(2, nombre);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer customerId = rs.getInt("customer_id");
				String name = rs.getString("name");
				String addres = rs.getString("address");
				String website = rs.getString("website");
				Double creditLimit = rs.getDouble("credit_limit");

				customerList.add(new Customer(customerId, name, addres, website, creditLimit));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}

	public void update(Connection conn, Customer customer) throws SQLException {
		String consultaSQL = "UPDATE customers SET name = ?, address = ?, website = ?, credit_limit = ? WHERE customer_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddres());
			pstmt.setString(3, customer.getWebsite());
			pstmt.setDouble(4, customer.getCreditLimit());
			pstmt.setInt(5, customer.getCustomerId());

			pstmt.executeUpdate();
		}
	}
}
