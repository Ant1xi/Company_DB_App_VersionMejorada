package ejercicio3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tablas.Customer;

import java.awt.BorderLayout;
import java.util.List;

public class JTableClientesVista extends JFrame {

	private JButton btnModificarCliente;
	private JTable tablaCustomers;
	private List<Customer> customerList;
	private CustomerTableModel ctm;

	public JTableClientesVista(List<Customer> customerList) {
		this.customerList = customerList;
		ctm = new CustomerTableModel(customerList);

		setTitle("Modificar Cliente");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);

		tablaCustomers = new JTable(ctm);
		JScrollPane barraScroll = new JScrollPane(tablaCustomers);

		btnModificarCliente = new JButton("Modificar cliente");
		btnModificarCliente.setEnabled(false); // Lo deshabilitamos al inicio

		JPanel panelBoton = new JPanel(); // Un panel para poner el botón centrado
		panelBoton.add(btnModificarCliente);

		tablaCustomers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tablaCustomers.getSelectionModel().addListSelectionListener(e -> {

			// Esta condición sirve para evitar que el evento se haga dos veces o
			// mientras se está "ajustando" la selección.
			// Lo encontré buscando cómo evitar que se ejecute innecesariamente cuando haces
			// clic varias veces.
			if (!e.getValueIsAdjusting()) {

				// Si hay una fila seleccionada, habilito el botón "Modificar cliente".
				// Si no hay ninguna fila seleccionada (por ejemplo si haces clic fuera de la
				// tabla), lo desactivo.
				btnModificarCliente.setEnabled(tablaCustomers.getSelectedRow() != -1);
			}
		});

		add(barraScroll, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);

	}

	public JButton getBtnModificarCliente() {
		return btnModificarCliente;
	}

	public JTable getTablaCustomers() {
		return tablaCustomers;
	}

}
