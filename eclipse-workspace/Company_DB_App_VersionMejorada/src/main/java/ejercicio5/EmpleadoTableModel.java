package ejercicio5;

import java.sql.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tablas.Employee;

/**
 * Clase que implementa el modelo de tabla para mostrar empleados en un JTable.
 * 
 * Se utiliza en la vista de eliminación de empleados (ejercicio 5). Cada fila representa un empleado
 * y cada columna muestra uno de sus campos (nombre, apellidos, email, etc).
 * 
 * No es editable desde la tabla.
 * 
 * @author Antonio
 */
public class EmpleadoTableModel implements TableModel {

	private List<Employee> listaEmpleados;

	/**
	 * Constructor que recibe la lista de empleados a mostrar.
	 * 
	 * @param listaEmpleados Lista con los empleados.
	 */
	public EmpleadoTableModel(List<Employee> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	/**
	 * Método para obtener el empleado de una fila concreta.
	 * 
	 * @param fila índice de la fila seleccionada
	 * @return objeto Employee correspondiente
	 */
	public Employee getEmpleadoAt(int fila) {
		return listaEmpleados.get(fila);
	}

	@Override
	public int getRowCount() {
		return listaEmpleados.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0: return "Nombre";
			case 1: return "Apellidos";
			case 2: return "Email";
			case 3: return "Teléfono";
			case 4: return "Fecha de contratación";
			case 5: return "Manager ID";
			case 6: return "Cargo";
			default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0: return String.class;
			case 1: return String.class;
			case 2: return String.class;
			case 3: return String.class;
			case 4: return Date.class;
			case 5: return Integer.class;
			case 6: return String.class;
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; // La tabla no se puede editar
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee e = listaEmpleados.get(rowIndex);

		switch (columnIndex) {
			case 0: return e.getFirstName();
			case 1: return e.getLastName();
			case 2: return e.getEmail();
			case 3: return e.getPhone();
			case 4: return e.getHireDate();
			case 5: return e.getManagerId();
			case 6: return e.getJobTitle();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// No se usa porque la tabla no es editable
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// No implementado porque no se necesita
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// No implementado porque no se necesita
	}
}
