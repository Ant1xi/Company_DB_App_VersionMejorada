package ejercicio4;

import java.sql.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tablas.Order;

/**
 * Modelo de tabla para mostrar pedidos (orders) en un JTable.
 * 
 * Esta clase adapta una lista de objetos Order al formato que necesita la tabla.
 * No es editable, simplemente muestra los datos.
 * 
 * Se usa en el ejercicio 4.
 * 
 * @author Antonio
 */
public class OrderTableModel implements TableModel {

	private List<Order> orderList;

	/**
	 * Constructor que recibe la lista de pedidos a mostrar.
	 * 
	 * @param orderList lista de objetos Order.
	 */
	public OrderTableModel(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public int getRowCount() {
		return orderList.size();
	}

	@Override
	public int getColumnCount() {
		return 5; // orderId, customerId, status, salesmanId, orderDate
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0: return "orderId";
			case 1: return "customerId";
			case 2: return "status";
			case 3: return "salesmanId";
			case 4: return "orderDate";
			default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
			case 3: return Integer.class;
			case 2: return String.class;
			case 4: return Date.class;
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; // la tabla solo es para visualizar
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order odr = orderList.get(rowIndex);

		switch (columnIndex) {
			case 0: return odr.getOrderId();
			case 1: return odr.getCustomerId();
			case 2: return odr.getStatus();
			case 3: return odr.getSalesmanId();
			case 4: return odr.getOrderDate();
			default: return null;
		}
	}

	// Métodos no usados, pero necesarios por la interfaz
	@Override public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
	@Override public void addTableModelListener(TableModelListener l) {}
	@Override public void removeTableModelListener(TableModelListener l) {}
}
