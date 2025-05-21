package ejercicio3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ContactDataException;
import tablas.Contact;

/**
 * DAO para la tabla CONTACTS.
 * 
 * Aquí tenemos los métodos que usamos para:
 * - Obtener el contacto de un cliente a partir de su ID.
 * - Actualizar los datos de un contacto en la base de datos.
 * 
 * Estos métodos se usan en el ejercicio donde se permite modificar
 * tanto los datos del cliente como los del contacto desde la misma vista.
 * 
 * @author Antonio
 */
public class ContactDAO {

    /**
     * Busca el contacto asociado a un cliente usando su ID.
     * 
     * @param conn conexión abierta a la base de datos.
     * @param customerId ID del cliente.
     * @return el contacto correspondiente, o null si no hay ninguno.
     * @throws SQLException si falla la consulta.
     * @throws ContactDataException si hay algún problema con los datos.
     */
    public Contact getByCustomerId(Connection conn, int customerId) throws SQLException, ContactDataException {
        String consultaSQL = "SELECT * FROM contacts WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Contact(
                    rs.getInt("contact_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    customerId
                );
            } else {
                return null;
            }
        }
    }

    /**
     * Actualiza los datos de un contacto en la base de datos.
     * 
     * @param conn conexión abierta a la base de datos.
     * @param contact el objeto Contact con los nuevos datos ya modificados.
     * @throws SQLException si falla la actualización.
     */
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
