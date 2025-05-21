package ejercicio5;

import java.sql.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tablas.Employee;

public class EmpleadoTableModel implements TableModel{

	private List<Employee> listaEmpleados;
	
	public EmpleadoTableModel(List<Employee> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	@Override
	public int getRowCount() {
		return listaEmpleados.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		
		switch (columnIndex) {
		
		case 0: { return "Nombre"; }
		case 1: { return "Apellidos"; }
		case 2: { return "Email"; }
		case 3: { return "Teléfono"; }
		case 4: { return "Fecha de contratación"; }
		case 5: { return "Manager ID"; }
		case 6: { return "Cargo"; }

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
			return String.class;
		}
		case 4: {
			return Date.class;
		}
		case 5: {
			return Integer.class;
		}
		case 6: {
			return String.class;
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
		Employee e = listaEmpleados.get(rowIndex);
		
		switch (columnIndex) {
		case 0: {return e.getFirstName();}
		case 1: {return e.getLastName();}
		case 2: {return e.getEmail();}
		case 3: {return e.getPhone();}
		case 4: {return e.getHireDate();}
		case 5: {return e.getManagerId();}
		case 6: {return e.getJobTitle();}

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
