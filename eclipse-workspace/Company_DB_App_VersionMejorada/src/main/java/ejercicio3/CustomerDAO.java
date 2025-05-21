package ejercicio3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompanyException;
import tablas.Customer;

/**
 * DAO que usamos para acceder a los datos de la tabla CUSTOMERS.
 * 
 * Tiene métodos para:
 * - Obtener todos los clientes.
 * - Buscar clientes por ID o nombre.
 * - Actualizar un cliente concreto.
 * 
 * Esta clase se usa sobre todo en el ejercicio 3, para modificar clientes
 * desde la interfaz gráfica.
 * 
 * @author Antonio
 */
public class CustomerDAO {

    /**
     * Devuelve todos los clientes que hay en la tabla.
     * 
     * @param conn conexión abierta a la base de datos.
     * @return lista con todos los clientes.
     * @throws CompanyException si hay error al leer los datos.
     */
    public List<Customer> getAll(Connection conn) throws CompanyException {
        List<Customer> customerList = new ArrayList<>();
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
            e.printStackTrace(); // por si falla algo
        }

        return customerList;
    }

    /**
     * Devuelve una lista de clientes filtrando por ID o por nombre (lo que coincida).
     * 
     * @param conn conexión activa.
     * @param id ID del cliente (si lo hay).
     * @param nombre nombre del cliente (si lo hay).
     * @return lista con los resultados que coincidan.
     * @throws CompanyException si hay fallo en la base de datos.
     */
    public List<Customer> getByIdOrNombre(Connection conn, int id, String nombre) throws CompanyException {
        List<Customer> customerList = new ArrayList<>();
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
            e.printStackTrace();
        }

        return customerList;
    }

    /**
     * Actualiza un cliente con los nuevos datos que se le pasen.
     * 
     * @param conn conexión activa.
     * @param customer el cliente ya con los datos editados.
     * @throws SQLException si falla la actualización.
     */
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
