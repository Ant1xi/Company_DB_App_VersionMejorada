package ejercicio1;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.ClaseConectoraBBDD;

import exceptions.EmployeeDataException;
import tablas.Employee;

/**
 * Controlador correspondiente al ejercicio 1.
 * 
 * Se encarga de coordinar la lógica relacionada con la creación de empleados:
 * - Preparar la vista de alta de empleado con la lista de posibles managers.
 * - Validar y enviar los datos introducidos por el usuario a la base de datos.
 * 
 * Este controlador actúa como intermediario entre la vista {@link AltaEmpleadoVista}
 * y el DAO {@link EmployeeDAO}.
 * 
 * @author Antonio
 */
public class EmployeeControllerEjercicio1 {

    /**
     * Carga la vista de alta de empleados y obtiene de la base de datos
     * la lista de empleados existentes para poblar el combo de managers.
     *
     * @throws EmployeeDataException si ocurre un error en la validación de datos.
     * @throws SQLException si falla la conexión o la lectura desde la base de datos.
     */
    public void cargaVistaAltaEmpleado() throws EmployeeDataException, SQLException {
        List<ManagerDTO> listaManagers = new ArrayList<>();

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            EmployeeDAO e = new EmployeeDAO();
            List<Employee> listaEmpleados = e.getAll(conn);

            for (Employee emp : listaEmpleados) {
                listaManagers.add(new ManagerDTO(emp.getEmployeeId(), emp.getFirstName() + " " + emp.getLastName()));
            }

        } catch (EmployeeDataException e) {
            e.printStackTrace(); // Esto solo lo captura si getAll lanza la excepción personalizada
        }

        AltaEmpleadoVista aev = new AltaEmpleadoVista(listaManagers);
    }

    /**
     * Valida y guarda un nuevo empleado utilizando el DAO correspondiente.
     * 
     * @param firstName  nombre del empleado.
     * @param lastName   apellidos del empleado.
     * @param email      correo electrónico.
     * @param phone      número de teléfono.
     * @param fecha      fecha de contratación.
     * @param managerId  ID del manager (puede ser null).
     * @param jobTitle   cargo del empleado.
     * @throws EmployeeDataException si los datos no cumplen con las reglas de negocio.
     */
    public void guardarEmpleado(String firstName, String lastName, String email, String phone, Date fecha,
                                 Integer managerId, String jobTitle) throws EmployeeDataException {

        Employee empleado = new Employee(firstName, lastName, email, phone, fecha, managerId, jobTitle);

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            EmployeeDAO eDAO = new EmployeeDAO();
            eDAO.createEmployee(conn, empleado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
