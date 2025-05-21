package ejercicio4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.ClaseConectoraBBDD;
import exceptions.CompanyException;
import tablas.Order;

/**
 * DAO de pedidos (orders).
 * 
 * Aquí tenemos los métodos para consultar los pedidos de un cliente
 * y para dejar a NULL el vendedor (salesman_id) cuando se elimina un empleado.
 * 
 * Esta clase se usa en el ejercicio 4 (y también en el 5 para eliminar empleados).
 * 
 * @author Antonio
 */
public class OrderDAO {

    /**
     * Devuelve todos los pedidos que tiene un cliente a partir de su ID.
     * 
     * @param conn conexión activa a la base de datos.
     * @param customerId ID del cliente del que se quieren ver los pedidos.
     * @return lista de pedidos encontrados.
     * @throws CompanyException si hay error con la consulta.
     */
    public List<Order> getByCustomerId(Connection conn, int customerId) throws CompanyException {
        List<Order> orderList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM orders WHERE customer_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int custId = rs.getInt("customer_id");
                String status = rs.getString("status");
                int salesmanId = rs.getInt("salesman_id");
                java.util.Date orderDate = rs.getDate("order_date");

                orderList.add(new Order(orderId, custId, status, salesmanId, orderDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyException("Error al obtener pedidos");
        }

        return orderList;
    }

    /**
     * Este método se usa en el ejercicio 5.
     * 
     * Quita el salesman_id de todos los pedidos donde trabajaba un empleado
     * que se quiere eliminar (para evitar errores por clave foránea).
     * 
     * @param employeeId ID del empleado a desvincular.
     * @param conn conexión activa.
     * @throws SQLException si falla la actualización.
     */
    public void anularSalesmanDeEmpleado(int employeeId, Connection conn) throws SQLException {
        String sql = "UPDATE orders SET salesman_id = NULL WHERE salesman_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }
}
