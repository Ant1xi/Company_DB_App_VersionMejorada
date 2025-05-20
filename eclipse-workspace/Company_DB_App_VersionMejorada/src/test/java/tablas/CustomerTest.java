package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.IncorrectDataException;

class CustomerTest {

    private Customer cliente;

    @BeforeEach
    void setUp() throws IncorrectDataException {
        // Creamos un cliente con datos normales pero creíbles
        cliente = new Customer(234, "Carlos Romero", "Calle Los Olivos 15", "www.romeroweb.com", 7500.0);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que al crear el cliente, los datos quedan bien guardados
        assertEquals(234, cliente.getCustomerId());
        assertEquals("Carlos Romero", cliente.getName());
        assertEquals("Calle Los Olivos 15", cliente.getAddres());
        assertEquals("www.romeroweb.com", cliente.getWebsite());
        assertEquals(7500.0, cliente.getCreditLimit());
    }

    @Test
    void testSetters() {
        // Modificamos todos los campos y comprobamos que se actualicen correctamente
        cliente.setCustomerId(235);
        cliente.setName("Laura Navarro");
        cliente.setAddres("Avenida del Prado 22");
        cliente.setWebsite("www.navarroasesores.com");
        cliente.setCreditLimit(9800.0);

        assertEquals(235, cliente.getCustomerId());
        assertEquals("Laura Navarro", cliente.getName());
        assertEquals("Avenida del Prado 22", cliente.getAddres());
        assertEquals("www.navarroasesores.com", cliente.getWebsite());
        assertEquals(9800.0, cliente.getCreditLimit());
    }

    @Test
    void testNombreNuloLanzaExcepcion() {
        // Si intentamos crear un cliente sin nombre, debe lanzar una excepción
        assertThrows(IncorrectDataException.class, () -> {
            new Customer(300, null, "Calle Falsa 123", "www.clientedeprueba.com", 5000.0);
        });
    }

    @Test
    void testToString() {
        // Comparamos lo que devuelve el toString con lo que debería devolver exactamente
        String textoEsperado = "Customer [customerId=234, name=Carlos Romero, addres=Calle Los Olivos 15, website=www.romeroweb.com, creditLimit=7500.0]";
        assertEquals(textoEsperado, cliente.toString());
    }

    // Curiosidad que descubrí:
    // Si en el constructor no le pasas nombre al cliente, te lanza una excepción.
    // Así se aseguran de que al menos tenga lo básico, como el nombre. Me parece lógico.
}
