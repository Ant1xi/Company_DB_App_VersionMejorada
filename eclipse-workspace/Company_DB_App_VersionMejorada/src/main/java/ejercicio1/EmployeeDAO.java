package ejercicio1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.EmployeeDataException;
import tablas.Employee;

/**
 * DAO (Data Access Object) para la entidad {@link Employee}.
 * 
 * Gestiona todas las operaciones de acceso a datos relacionadas con los empleados,
 * incluyendo creación, obtención de todos los registros y operaciones específicas
 * del ejercicio 5 como la anulación de managers y la eliminación de empleados.
 * 
 * Todas las operaciones utilizan una conexión externa proporcionada por el controlador.
 * 
 * @author Antonio
 */
public class EmployeeDAO {

    /**
     * Obtiene todos los empleados de la tabla employees.
     * 
     * @param conn conexión activa a la base de datos.
     * @return lista de objetos {@link Employee}.
     * @throws EmployeeDataException si ocurre un error al realizar la consulta.
     */
    public List<Employee> getAll(Connection conn) throws EmployeeDataException {
        List<Employee> listaEmpleados = new ArrayList<>();
        String consultaSQL = "SELECT * FROM employees";

        try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
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

    /**
     * Inserta un nuevo empleado en la base de datos.
     * 
     * @param conn conexión activa a la base de datos.
     * @param e objeto {@link Employee} con los datos a insertar.
     * @throws EmployeeDataException si ocurre un error al insertar el registro.
     */
    public void createEmployee(Connection conn, Employee e) throws EmployeeDataException {
        String consultaSQL = "INSERT INTO employees (first_name, last_name, email, phone, hire_date, manager_id, job_title) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EmployeeDataException();
        }
    }

    // Métodos del ejercicio 5

    /**
     * Establece a NULL el campo manager_id de todos los empleados que dependían del manager indicado.
     *
     * @param managerId ID del manager que se va a eliminar.
     * @param conn conexión activa a la base de datos.
     * @throws SQLException si ocurre un error en la actualización.
     */
    public void anularManagerDeSubordinados(int managerId, Connection conn) throws SQLException {
        String sql = "UPDATE employees SET manager_id = NULL WHERE manager_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina de la base de datos al empleado con el ID proporcionado.
     * 
     * @param employeeId ID del empleado a eliminar.
     * @param conn conexión activa a la base de datos.
     * @throws SQLException si el empleado no existe o si ocurre un error en la eliminación.
     */
    public void eliminarEmpleado(int employeeId, Connection conn) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Empleado no encontrado.");
            }
        }
    }
}
