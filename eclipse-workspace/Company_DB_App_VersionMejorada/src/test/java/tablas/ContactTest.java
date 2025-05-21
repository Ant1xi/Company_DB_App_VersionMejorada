package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ContactDataException;

class ContactTest {

    private Contact contacto;

    @BeforeEach
    void setUp() throws ContactDataException {
        // Creamos un contacto con todos los datos obligatorios para que no falle
        contacto = new Contact(10, "Marina", "Lopez", "marina.lopez@gmail.com", "678123456", 55);
    }

    @Test
    void testConstructorYGetters() {
        // Aquí simplemente comprobamos que los datos del constructor se guardan bien
        assertEquals(10, contacto.getContactId());
        assertEquals("Marina", contacto.getFirstName());
        assertEquals("Lopez", contacto.getLastName());
        assertEquals("marina.lopez@gmail.com", contacto.getEmail());
        assertEquals("678123456", contacto.getPhone());
        assertEquals(55, contacto.getCustomerId());
    }

    @Test
    void testSetters() {
        // Cambiamos todos los campos para asegurarnos de que los setters funcionan
        contacto.setContactId(11);
        contacto.setFirstName("Sergio");
        contacto.setLastName("Vargas");
        contacto.setEmail("sergio.vargas@gmail.com");
        contacto.setPhone("690987654");
        contacto.setCustomerId(77);

        assertEquals(11, contacto.getContactId());
        assertEquals("Sergio", contacto.getFirstName());
        assertEquals("Vargas", contacto.getLastName());
        assertEquals("sergio.vargas@gmail.com", contacto.getEmail());
        assertEquals("690987654", contacto.getPhone());
        assertEquals(77, contacto.getCustomerId());
    }

    @Test
    void testCamposObligatoriosNulosLanzanExcepcion() {
        // En todos estos casos debería lanzar la excepción porque falta algo obligatorio

        assertThrows(ContactDataException.class, () -> {
            new Contact(null, "Laura", "Santos", "laura.santos@gmail.com", "611223344", 30);
        });

        assertThrows(ContactDataException.class, () -> {
            new Contact(12, null, "Santos", "laura.santos@gmail.com", "611223344", 30);
        });

        assertThrows(ContactDataException.class, () -> {
            new Contact(12, "Laura", null, "laura.santos@gmail.com", "611223344", 30);
        });

        assertThrows(ContactDataException.class, () -> {
            new Contact(12, "Laura", "Santos", null, "611223344", 30);
        });

        assertThrows(ContactDataException.class, () -> {
            new Contact(12, "Laura", "Santos", "laura.santos@gmail.com", "611223344", null);
        });
    }

    @Test
    void testToString() {
        // Creamos el texto tal como lo devolvería el toString
        String textoEsperado = "Contact [contactId=10, firstName=Marina, lastName=Lopez, email=marina.lopez@gmail.com, phone=678123456, customerId=55]";
        assertEquals(textoEsperado, contacto.toString());
    }

    // Me pareció bien que si falta algún dato obligatorio al crear el contacto, lance una excepción.
    // Así no se crean contactos incompletos por error, y te das cuenta enseguida.
}
