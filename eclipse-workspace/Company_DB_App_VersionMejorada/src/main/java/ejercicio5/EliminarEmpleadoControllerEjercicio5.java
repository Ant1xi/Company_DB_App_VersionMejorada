package ejercicio5;

import com.daw.ClaseConectoraBBDD;
import ejercicio1.EmployeeDAO;
import ejercicio4.OrderDAO;
import exceptions.EmployeeDataException;
import tablas.Employee;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador del ejercicio 5.
 * 
 * Se encarga de mostrar la vista con la tabla de empleados y permitir la eliminación
 * de uno de ellos. El proceso de eliminación se hace con una transacción.
 * 
 * Pasos:
 * - Si el empleado es jefe, se eliminan los manager_id de sus subordinados.
 * - Si tiene pedidos como vendedor, se le pone NULL en salesman_id.
 * - Finalmente se elimina al empleado.
 * 
 * Si algo falla, se hace rollback.
 * 
 * @author Antonio
 */
public class EliminarEmpleadoControllerEjercicio5 {

    /**
     * Método que carga la vista de eliminación de empleados y gestiona la acción
     * del botón de "Eliminar".
     * 
     * @throws EmployeeDataException si hay error al cargar los empleados desde la base de datos.
     */
    public void cargaEliminarEmpleadoVista() throws EmployeeDataException {
        try (Connection conn = ClaseConectoraBBDD.getConnection()) {

            // Cargamos todos los empleados para mostrarlos en la tabla
            List<Employee> empleados = new EmployeeDAO().getAll(conn);
            EliminarEmpladoVista vista = new EliminarEmpladoVista(empleados);

            // Acción al pulsar el botón de eliminar
            vista.getBtnEliminarEmpleado().addActionListener(e -> {
                int fila = vista.getTablaClientes().getSelectedRow();
                if (fila == -1) return;

                int confirm = JOptionPane.showConfirmDialog(
                        vista,
                        "Esta operación no se podrá deshacer, ¿estás seguro?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) return;

                EmpleadoTableModel model = (EmpleadoTableModel) vista.getTablaClientes().getModel();
                Employee empleado = model.getEmpleadoAt(fila);
                int empleadoId = empleado.getEmployeeId();

                Connection connTrans = null;

                try {
                    // Iniciamos la transacción manual
                    connTrans = ClaseConectoraBBDD.getConnection();
                    connTrans.setAutoCommit(false);

                    OrderDAO orderDAO = new OrderDAO();
                    EmployeeDAO employeeDAO = new EmployeeDAO();

                    // Paso 1: eliminar salesman_id en pedidos
                    orderDAO.anularSalesmanDeEmpleado(empleadoId, connTrans);

                    // Paso 2: eliminar manager_id en subordinados
                    employeeDAO.anularManagerDeSubordinados(empleadoId, connTrans);

                    // Paso 3: eliminar el empleado
                    employeeDAO.eliminarEmpleado(empleadoId, connTrans);

                    // Confirmar si todo fue bien
                    connTrans.commit();
                    JOptionPane.showMessageDialog(vista, "Empleado eliminado correctamente.");

                } catch (SQLException ex) {
                    // Si algo falla, hacemos rollback
                    if (connTrans != null) {
                        try {
                            connTrans.rollback();
                        } catch (SQLException rollbackEx) {
                            rollbackEx.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(vista, "Error al eliminar el empleado:\n" + ex.getMessage());

                } finally {
                    // Cerramos conexión y reactivamos autoCommit por si se reutiliza
                    if (connTrans != null) {
                        try {
                            connTrans.setAutoCommit(true);
                            connTrans.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar empleados:\n" + ex.getMessage());
        }
    }
}
