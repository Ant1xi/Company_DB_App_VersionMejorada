package ejercicio5;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tablas.Employee;

/**
 * Vista del ejercicio 5.
 * 
 * Esta ventana muestra una tabla con todos los empleados de la base de datos.
 * Permite seleccionar uno y eliminarlo con el botón correspondiente.
 * 
 * El botón "Eliminar empleado" solo se activa si hay una fila seleccionada.
 * 
 * @author Antonio
 */
public class EliminarEmpladoVista extends JFrame {

	private JButton btnEliminarEmpleado;
	private JTable tablaClientes;
	private List<Employee> listaClientes;
	private EmpleadoTableModel etm;

	/**
	 * Constructor de la vista. Recibe la lista de empleados y prepara la tabla.
	 * 
	 * @param listaClientes lista de empleados cargados desde la base de datos.
	 */
	public EliminarEmpladoVista(List<Employee> listaClientes) {
		this.listaClientes = listaClientes;
		etm = new EmpleadoTableModel(listaClientes);

		setTitle("Modificar Cliente"); // Se puede cambiar por "Eliminar Empleado"
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);

		// Crear tabla con scroll
		tablaClientes = new JTable(etm);
		JScrollPane barraScroll = new JScrollPane(tablaClientes);

		// Botón para eliminar
		btnEliminarEmpleado = new JButton("Eliminar empleado");
		btnEliminarEmpleado.setEnabled(false); // desactivado al inicio

		// Activamos el botón solo si se selecciona una fila
		tablaClientes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaClientes.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) {
				btnEliminarEmpleado.setEnabled(tablaClientes.getSelectedRow() != -1);
			}
		});

		// Añadir elementos al layout
		JPanel panelBoton = new JPanel();
		panelBoton.add(btnEliminarEmpleado);

		add(barraScroll, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);
	}

	// Getters necesarios para que el controlador acceda a los componentes

	public JButton getBtnEliminarEmpleado() {
		return btnEliminarEmpleado;
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}
}
