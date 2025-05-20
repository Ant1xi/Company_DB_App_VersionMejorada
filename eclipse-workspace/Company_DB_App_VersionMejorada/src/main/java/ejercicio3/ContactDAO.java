package ejercicio3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ContactDataException;
import tablas.Contact;

public class ContactDAO {
	public Contact getByCustomerId(Connection conn, int customerId) throws SQLException, ContactDataException {
		String consultaSQL = "SELECT * FROM contacts WHERE customer_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Contact(rs.getInt("contact_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("phone"), customerId);
			} else {
				return null;
			}
		}
	}

	public void update(Connection conn, Contact contact) throws SQLException {
		String consultaSQL = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE contact_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, contact.getFirstName());
			pstmt.setString(2, contact.getLastName());
			pstmt.setString(3, contact.getEmail());
			pstmt.setString(4, contact.getPhone());
			pstmt.setInt(5, contact.getContactId());

			pstmt.executeUpdate();
		}
	}
}
