package tablas;

import exceptions.EmployeeDataException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void crearEmpleadoValidoConId() throws EmployeeDataException {
        Date fecha = new Date();
        Employee emp = new Employee(1, "Juan", "Pérez", "juan@example.com", "123456789", fecha, 10, "Developer");

        assertEquals(1, emp.getEmployeeId());
        assertEquals("Juan", emp.getFirstName());
        assertEquals("Pérez", emp.getLastName());
        assertEquals("juan@example.com", emp.getEmail());
        assertEquals("123456789", emp.getPhone());
        assertEquals(fecha, emp.getHireDate());
        assertEquals(10, emp.getManagerId());
        assertEquals("Developer", emp.getJobTitle());
    }

    @Test
    void crearEmpleadoValidoSinId() throws EmployeeDataException {
        Date fecha = new Date();
        Employee emp = new Employee("Laura", "López", "laura@example.com", "987654321", fecha, null, "Analyst");

        assertNull(emp.getEmployeeId());
        assertEquals("Laura", emp.getFirstName());
        assertEquals("López", emp.getLastName());
        assertEquals("laura@example.com", emp.getEmail());
        assertEquals("987654321", emp.getPhone());
        assertEquals(fecha, emp.getHireDate());
        assertNull(emp.getManagerId());
        assertEquals("Analyst", emp.getJobTitle());
    }

    @Test
    void crearEmpleadoConHireDateNullUsaFechaActual() throws EmployeeDataException {
        Employee emp = new Employee(2, "Ana", "García", "ana@example.com", "111222333", null, null, "Tester");

        assertNotNull(emp.getHireDate());
    }

    @Test
    void crearEmpleadoConCampoObligatorioNullLanzaExcepcion() {
        assertThrows(EmployeeDataException.class, () -> {
            new Employee(null, null, "López", "correo@example.com", "999999", new Date(), null, "QA");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee("Mario", null, "mario@example.com", "123123", new Date(), null, "QA");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee("Mario", "Lopez", null, "123123", new Date(), null, "QA");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee("Mario", "Lopez", "mario@example.com", null, new Date(), null, "QA");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee("Mario", "Lopez", "mario@example.com", "123123", new Date(), null, null);
        });
    }

    @Test
    void crearEmpleadoConHireDateNullEnSegundoConstructorLanzaExcepcion() {
        assertThrows(EmployeeDataException.class, () -> {
            new Employee("Luis", "Santos", "luis@example.com", "123456", null, null, "Manager");
        });
    }
}
