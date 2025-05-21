package ejercicio3;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import tablas.Customer;

/**
 * Clase que sirve para mostrar los clientes en un JTable.
 * 
 * Implementa TableModel de forma manual (no con AbstractTableModel) y se usa
 * para mostrar solo algunos datos del cliente: nombre, dirección, web y crédito.
 * 
 * Este modelo lo usamos en la vista del ejercicio 3 para mostrar la lista
 * de clientes que se pueden modificar.
 * 
 * @author Antonio
 */
public class CustomerTableModel implements TableModel {

	private List<Customer> customerList;

	/**
	 * Constructor que recibe la lista de clientes que se van a mostrar.
	 */
	public CustomerTableModel(List<Customer> customerList) {
		this.customerList = customerList;
	}

	@Override
	public int getRowCount() {
		return customerList.size(); // tantas filas como clientes
	}

	@Override
	public int getColumnCount() {
		return 4; // solo mostramos 4 columnas
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0: return "Nombre";
			case 1: return "Dirección";
			case 2: return "Pagina Web";
			case 3: return "Limite de crédito";
			default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
			case 2: return String.class;
			case 3: return Double.class;
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; // no editable desde la tabla
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer c = customerList.get(rowIndex);

		switch (columnIndex) {
			case 0: return c.getName();
			case 1: return c.getAddres();
			case 2: return c.getWebsite();
			case 3: return c.getCreditLimit();
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// No lo usamos porque no es editable desde la tabla
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// No lo usamos
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// Tampoco lo usamos
	}
}
