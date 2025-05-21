package ejercicio4;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import tablas.Order;
import java.awt.event.KeyListener;

/**
 * Vista gráfica del ejercicio 4.
 * 
 * Esta ventana permite buscar pedidos por cliente usando su ID o nombre.
 * Muestra una tabla con los pedidos encontrados y permite ver detalles.
 * 
 * El botón de buscar solo se activa si se escribe algo, y el de "Ver detalles"
 * solo se activa si se selecciona un pedido en la tabla.
 * 
 * @author Antonio
 */
public class BuscarPedidosClienteVista extends JFrame {

	private JButton btnBuscarCliente;
	private JButton btnDetallePedido;
	private JTextField txtCustomerId, txtCustomerName;
	private JTable tablaOrders;

	/**
	 * Constructor que crea la vista e inicializa los componentes.
	 * 
	 * @param orderList lista de pedidos que se muestran en la tabla (puede estar
	 *                  vacía al principio).
	 */
	public BuscarPedidosClienteVista(List<Order> orderList) {
		setTitle("Buscar pedidos de cliente");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(6, 2, 10, 10));
		setVisible(true);

		// Campos para buscar
		JLabel lblCustomerId = new JLabel("Customer ID:");
		txtCustomerId = new JTextField();

		JLabel lblCustomerName = new JLabel("Nombre:");
		txtCustomerName = new JTextField();

		btnBuscarCliente = new JButton("Buscar cliente");
		btnBuscarCliente.setEnabled(false); // desactivado hasta que se escriba algo

		btnDetallePedido = new JButton("Ver detalles del pedido");
		btnDetallePedido.setEnabled(false); // desactivado hasta que se seleccione algo

		// Tabla de pedidos
		tablaOrders = new JTable(new OrderTableModel(orderList));
		tablaOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tablaOrders);

		// Habilita el botón de detalles cuando se selecciona un pedido
		tablaOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					btnDetallePedido.setEnabled(tablaOrders.getSelectedRow() != -1);
				}
			}
		});

		// Layout de los elementos
		add(lblCustomerId);
		add(txtCustomerId);
		add(lblCustomerName);
		add(txtCustomerName);
		add(new JLabel());
		add(btnBuscarCliente);
		add(new JLabel("Pedidos:"));
		add(new JLabel());
		add(scrollPane);
		add(new JLabel());
		add(new JLabel());
		add(btnDetallePedido);

		// Listeners para habilitar botón de buscar si hay texto
		txtCustomerId.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				btnBuscarCliente.setEnabled(
						!txtCustomerId.getText().trim().isEmpty() || !txtCustomerName.getText().trim().isEmpty());
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		txtCustomerName.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				btnBuscarCliente.setEnabled(
						!txtCustomerId.getText().trim().isEmpty() || !txtCustomerName.getText().trim().isEmpty());
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	// Getters para que el controlador acceda a los campos
	public JTextField getTxtCustomerId() {
		return txtCustomerId;
	}

	public JTextField getTxtCustomerName() {
		return txtCustomerName;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public JButton getBtnDetallePedido() {
		return btnDetallePedido;
	}

	public JTable getTablaOrders() {
		return tablaOrders;
	}
}
