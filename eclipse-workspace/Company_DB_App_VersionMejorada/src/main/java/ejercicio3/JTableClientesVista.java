package ejercicio3;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import tablas.Customer;

/**
 * Ventana que muestra un JTable con todos los clientes.
 * 
 * Desde aquí se puede seleccionar un cliente y pulsar el botón para modificarlo.
 * El botón "Modificar cliente" se activa solo si se ha seleccionado una fila.
 * 
 * Esta ventana forma parte del ejercicio 3.
 * 
 * @author Antonio
 */
public class JTableClientesVista extends JFrame {

	private JButton btnModificarCliente;
	private JTable tablaCustomers;
	private List<Customer> customerList;
	private CustomerTableModel ctm;

	/**
	 * Constructor que crea la vista, recibe la lista de clientes a mostrar
	 * y configura la tabla y el botón.
	 */
	public JTableClientesVista(List<Customer> customerList) {
		this.customerList = customerList;
		ctm = new CustomerTableModel(customerList);

		setTitle("Modificar Cliente");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);

		// Tabla con los clientes
		tablaCustomers = new JTable(ctm);
		JScrollPane barraScroll = new JScrollPane(tablaCustomers);

		// Botón para modificar (empieza desactivado)
		btnModificarCliente = new JButton("Modificar cliente");
		btnModificarCliente.setEnabled(false);

		// Panel para colocar el botón
		JPanel panelBoton = new JPanel();
		panelBoton.add(btnModificarCliente);

		// Solo se puede seleccionar una fila a la vez
		tablaCustomers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Activar el botón solo si hay algo seleccionado
		tablaCustomers.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				btnModificarCliente.setEnabled(tablaCustomers.getSelectedRow() != -1);
			}
		});

		// Añadir la tabla y el botón a la ventana
		add(barraScroll, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);
	}

	// Getters para que el controlador pueda acceder al botón y a la tabla

	public JButton getBtnModificarCliente() {
		return btnModificarCliente;
	}

	public JTable getTablaCustomers() {
		return tablaCustomers;
	}
}
