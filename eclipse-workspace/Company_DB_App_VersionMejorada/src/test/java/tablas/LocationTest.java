package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.LocationDataException;

class LocationTest {

    private Location ubicacion;

    @BeforeEach
    void setUp() throws LocationDataException {
        // Creamos una ubicación válida con todos los datos
        ubicacion = new Location(301, "Calle Alcázar 12", "28045", "Madrid", "Madrid", "ES");
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que al crearla se guarden bien los datos
        assertEquals(301, ubicacion.getLocationId());
        assertEquals("Calle Alcázar 12", ubicacion.getAddress());
        assertEquals("28045", ubicacion.getPostalCode());
        assertEquals("Madrid", ubicacion.getCity());
        assertEquals("Madrid", ubicacion.getState());
        assertEquals("ES", ubicacion.getCountryId());
    }

    @Test
    void testSetters() {
        // Cambiamos los datos para comprobar que se actualizan bien
        ubicacion.setLocationId(302);
        ubicacion.setAddress("Av. del Sur 8");
        ubicacion.setPostalCode("41010");
        ubicacion.setCity("Sevilla");
        ubicacion.setState("Andalucía");
        ubicacion.setCountryId("ES");

        assertEquals(302, ubicacion.getLocationId());
        assertEquals("Av. del Sur 8", ubicacion.getAddress());
        assertEquals("41010", ubicacion.getPostalCode());
        assertEquals("Sevilla", ubicacion.getCity());
        assertEquals("Andalucía", ubicacion.getState());
        assertEquals("ES", ubicacion.getCountryId());
    }

    @Test
    void testCamposObligatoriosNulosLanzanExcepcion() {
        // Estos casos deberían lanzar excepción por faltar datos clave

        assertThrows(LocationDataException.class, () -> {
            new Location(303, null, "12345", "Barcelona", "Cataluña", "ES");
        });

        assertThrows(LocationDataException.class, () -> {
            new Location(304, "Calle Falsa 123", "12345", "Barcelona", "Cataluña", null);
        });
    }

    @Test
    void testToString() {
        // Comparamos el resultado de toString con el texto esperado
        String textoEsperado = "Location [locationId=301, address=Calle Alcázar 12, postalCode=28045, city=Madrid, state=Madrid, countryId=ES]";
        assertEquals(textoEsperado, ubicacion.toString());
    }

    
    // Validé que no se pueda crear una ubicación sin dirección ni país.
    // Me parece lógico porque esos dos datos son los más básicos para identificar un sitio.
}
