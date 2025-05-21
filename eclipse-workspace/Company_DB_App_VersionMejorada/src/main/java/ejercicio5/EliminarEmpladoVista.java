package ejercicio5;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tablas.Employee;

public class EliminarEmpladoVista extends JFrame {
	private JButton btnEliminarEmpleado;
	private JTable tablaClientes;
	private List<Employee> listaClientes;
	private EmpleadoTableModel etm;

	public EliminarEmpladoVista(List<Employee> listaClientes) throws HeadlessException {
		this.listaClientes = listaClientes;
		etm = new EmpleadoTableModel(listaClientes);

		setTitle("Modificar Cliente");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);

		tablaClientes = new JTable(etm);
		JScrollPane barraScroll = new JScrollPane(tablaClientes);

		btnEliminarEmpleado = new JButton("Eliminar empleado");
		btnEliminarEmpleado.setEnabled(false);

		JPanel panelBoton = new JPanel(); // Como uso Border LAYOUT, creo un panel para centrar el botón
		panelBoton.add(btnEliminarEmpleado);
		
		tablaClientes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaClientes.getSelectionModel().addListSelectionListener(e -> {
			if(e.getValueIsAdjusting()) {
				btnEliminarEmpleado.setEnabled(tablaClientes.getSelectedRow() != -1);
			}
		});
		
		add(barraScroll, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);
	}
	
	public JButton getBtnEliminarEmpleado() {
		return btnEliminarEmpleado;
	}
	
	public JTable getTablaClientes() {
		return tablaClientes;
	}

}
