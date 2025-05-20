package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.RegionDataException;

class RegionTest {

    private Region region;

    @BeforeEach
    void setUp() throws RegionDataException {
        // Creamos una región válida con nombre
        region = new Region(1, "Europa");
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que los valores se guardan bien
        assertEquals(1, region.getRegionId());
        assertEquals("Europa", region.getRegionName());
    }

    @Test
    void testSetters() {
        // Cambiamos los datos para ver que se actualicen correctamente
        region.setRegionId(3);
        region.setRegionName("Asia");

        assertEquals(3, region.getRegionId());
        assertEquals("Asia", region.getRegionName());
    }

    @Test
    void testNombreNuloLanzaExcepcion() {
        // Si intentamos crear una región sin nombre, debería lanzar una excepción personalizada
        assertThrows(RegionDataException.class, () -> {
            new Region(3, null);
        });
    }

    @Test
    void testToString() {
        // Verificamos que el toString devuelve el texto esperado
        String textoEsperado = "Region [regionId=1, regionName=Europa]";
        assertEquals(textoEsperado, region.toString());
    }

    // El ID lo pone la base de datos, pero el nombre sí es obligatorio.
    // Así evitamos tener regiones sin identificar en el sistema.
}

