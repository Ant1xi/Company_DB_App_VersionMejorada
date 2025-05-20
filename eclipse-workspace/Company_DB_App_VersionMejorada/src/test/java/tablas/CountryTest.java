package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.CountryDataException;

class CountryTest {

    private Country pais;

    @BeforeEach
    void setUp() throws CountryDataException {
        // Creamos un país con datos normales para poder hacer las pruebas
        pais = new Country("ES", "España", 1);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que al crear el objeto se guardan bien los datos
        assertEquals("ES", pais.getCountryId());
        assertEquals("España", pais.getCountryName());
        assertEquals(1, pais.getRegionId());
    }

    @Test
    void testSetters() {
        // Cambiamos los valores y vemos si los setters funcionan como deberían
        pais.setCountryId("FR");
        pais.setCountryName("Francia");
        pais.setRegionId(2);

        assertEquals("FR", pais.getCountryId());
        assertEquals("Francia", pais.getCountryName());
        assertEquals(2, pais.getRegionId());
    }

    @Test
    void testCamposNulosLanzanExcepcion() {
        // En estos casos no debería dejar crear el objeto porque faltan datos obligatorios

        assertThrows(CountryDataException.class, () -> {
            new Country(null, "Italia", 3);
        });

        assertThrows(CountryDataException.class, () -> {
            new Country("IT", null, 3);
        });

        assertThrows(CountryDataException.class, () -> {
            new Country("IT", "Italia", null);
        });
    }

    @Test
    void testToString() {
        // Creamos el texto igual al que debería devolver toString y lo comparamos
        String textoEsperado = "Country [countryId=ES, countryName=España, regionId=1]";
        assertEquals(textoEsperado, pais.toString());
    }

    
    // Le puse validaciones para que no se pueda crear un país sin datos clave como el ID o la región.
    // Así evitas errores más adelante por objetos mal construidos, que luego son más difíciles de detectar.
}
