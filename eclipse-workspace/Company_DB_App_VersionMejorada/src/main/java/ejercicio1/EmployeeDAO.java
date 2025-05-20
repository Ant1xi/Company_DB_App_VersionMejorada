package ejercicio1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import exceptions.EmployeeDataException;
import tablas.Employee;

public class EmployeeDAO {
	public List<Employee> getAll(Connection conn) throws EmployeeDataException {
		List<Employee> listaEmpleados = new ArrayList<Employee>();
		String consultaSQL = "SELECT * FROM Employees";
		
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				
				int employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Date hireDate = rs.getDate("hire_date");
				Integer managerId = rs.getInt("manager_id");
				String jobTitle = rs.getString("job_title");
				
				listaEmpleados.add(new Employee(employeeId, firstName, lastName, email, phone, hireDate, managerId, jobTitle));
			}
			return listaEmpleados;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeDataException();
		}
	}
	
	public void createEmployee(Connection conn, Employee e) throws EmployeeDataException {
		String consultaSQL = "INSERT into employees (first_name, last_name, email, phone, hire_date, manager_id, job_title) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, e.getFirstName());
			pstmt.setString(2, e.getLastName());
			pstmt.setString(3, e.getEmail());
			pstmt.setString(4, e.getPhone());
			
			if (e.getHireDate() != null) {
			    pstmt.setDate(5, new java.sql.Date(e.getHireDate().getTime()));
			} else {
			    pstmt.setNull(5, java.sql.Types.DATE);
			}
			
			pstmt.setInt(6, e.getManagerId());
			pstmt.setString(7, e.getJobTitle());

			pstmt.executeUpdate(); // Ejecutamos la consulta para insertar nuestro nuevo empleado

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new EmployeeDataException(); // Lanzamos una excepción personalizada si hay error
		}
	}
}
