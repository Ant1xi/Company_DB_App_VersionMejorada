package ejercicio1;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.ClaseConectoraBBDD;

import exceptions.EmployeeDataException;
import tablas.Employee;

public class EmployeeControllerEjercicio1 {

	public void cargaVistaAltaEmpleado() throws EmployeeDataException, SQLException {
		// Pasos para llenar el DTO
		List<ManagerDTO> listaManagers = new ArrayList<>();

		try (Connection conn = ClaseConectoraBBDD.getConnection();) {
			EmployeeDAO e = new EmployeeDAO();
			List<Employee> listaEmpleados = new ArrayList<>();
			listaEmpleados = e.getAll(conn);

			for (Employee emp : listaEmpleados) {
				listaManagers.add(new ManagerDTO(emp.getEmployeeId(), emp.getFirstName() + " " + emp.getLastName()));
			}

		} catch (EmployeeDataException e) {
			e.printStackTrace();
		}

		AltaEmpleadoVista aev = new AltaEmpleadoVista(listaManagers);
	}

	public void guardarEmpleado(String firstName, String lastName, String email, String phone, Date fecha,
			Integer managerId, String jobTitle) throws EmployeeDataException {

		Employee empleado = new Employee(firstName, lastName, email, phone, fecha, managerId, jobTitle);
		
		try (Connection conn = ClaseConectoraBBDD.getConnection();) {
			EmployeeDAO eDAO = new EmployeeDAO();
			eDAO.createEmployee(conn, empleado);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
