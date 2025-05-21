package ejercicio4;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.daw.ClaseConectoraBBDD;

import ejercicio3.CustomerDAO;
import tablas.Customer;
import tablas.Order;

/**
 * Controlador del ejercicio 4.
 * 
 * Esta clase se encarga de abrir la vista donde se puede buscar un cliente 
 * por ID o por nombre, y si existe, se cargan sus pedidos en una tabla.
 * 
 * Todavía no está del todo terminada, pero ya permite consultar pedidos
 * y mostrar resultados en pantalla.
 * 
 * @author Antonio
 */
public class BuscarClienteYDetallesPedidoControllerEjercicio4 {

    /**
     * Método principal que abre la vista y conecta los botones con la lógica.
     * 
     * Al pulsar "Buscar cliente", se validan los datos, se busca al cliente,
     * y si existe, se muestran sus pedidos en el JTable.
     */
    public void cargarBuscarPedidosClienteVista() {

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {

            // Creamos la ventana con la tabla vacía al principio
            List<Order> pedidosIniciales = new ArrayList<>();
            BuscarPedidosClienteVista vista = new BuscarPedidosClienteVista(pedidosIniciales);
            vista.setVisible(true);

            // Acción cuando se pulsa "Buscar cliente"
            vista.getBtnBuscarCliente().addActionListener((ActionEvent e) -> {

                String idRecogida = vista.getTxtCustomerId().getText().trim();
                String nombre = vista.getTxtCustomerName().getText().trim();

                int id = -1; // valor por defecto

                try {
                    if (!idRecogida.isEmpty()) {
                        id = Integer.parseInt(idRecogida); // convertir texto a número
                    }
                } catch (NumberFormatException excepcionDeNumeros) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
                    return;
                }

                // Segunda conexión solo para las consultas de DAOs
                try (Connection conexionExtra = ClaseConectoraBBDD.getConnection()) {

                    CustomerDAO customerDAO = new CustomerDAO();
                    List<Customer> listaClientes = customerDAO.getByIdOrNombre(conexionExtra, id, nombre);

                    if (listaClientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        return;
                    }

                    int customerId = listaClientes.get(0).getCustomerId();
                    System.out.println("ID del cliente encontrado: " + customerId);

                    OrderDAO orderDAO = new OrderDAO();
                    List<Order> pedidos = orderDAO.getByCustomerId(conexionExtra, customerId);

                    // Cargar los pedidos en la tabla
                    vista.getTablaOrders().setModel(new OrderTableModel(pedidos));

                    if (pedidos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Este cliente no tiene pedidos.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar pedidos.");
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la vista.");
        }
    }
}
