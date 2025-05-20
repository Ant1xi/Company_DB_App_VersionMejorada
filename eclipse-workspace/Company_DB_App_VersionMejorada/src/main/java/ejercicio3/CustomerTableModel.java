package ejercicio3;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tablas.Customer;

public class CustomerTableModel implements TableModel {

	private List<Customer> customerList;

	public CustomerTableModel(List<Customer> customerList) {
		super();
		this.customerList = customerList;
	}

	@Override
	public int getRowCount() {
		return customerList.size();

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {

		switch (columnIndex) {
		case 0: {
			return "Nombre";
		}
		case 1: {
			return "Dirección";
		}
		case 2: {
			return "Pagina Web";
		}
		case 3: {
			return "Limete de crédito";
		}
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		switch (columnIndex) {
		case 0: {
			return String.class;
		}
		case 1: {
			return String.class;
		}
		case 2: {
			return String.class;
		}
		case 3: {
			return Double.class;
		}
		default:
			return null;
		}
	}
	

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub 
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Customer c = customerList.get(rowIndex);
		
		switch (columnIndex) {
		case 0: {
			return c.getName();
		}
		case 1: {
			return c.getAddres();
		}
		case 2: {
			return c.getWebsite();
		}
		case 3: {
			return c.getCreditLimit();
		}
		default:
			return null;
		}
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
