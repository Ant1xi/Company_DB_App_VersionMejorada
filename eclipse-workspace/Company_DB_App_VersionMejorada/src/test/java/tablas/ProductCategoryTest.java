package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ProductCategoryDataException;

class ProductCategoryTest {

    private ProductCategory categoria;

    @BeforeEach
    void setUp() throws ProductCategoryDataException {
        // Creamos una categoría válida con un nombre cualquiera
        categoria = new ProductCategory(10, "Tecnología");
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que al crearla se guarden bien los datos
        assertEquals(10, categoria.getCategoryId());
        assertEquals("Tecnología", categoria.getCategoryName());
    }

    @Test
    void testSetters() {
        // Cambiamos los valores y revisamos que se actualicen bien
        categoria.setCategoryId(11);
        categoria.setCategoryName("Hogar");

        assertEquals(11, categoria.getCategoryId());
        assertEquals("Hogar", categoria.getCategoryName());
    }

    @Test
    void testNombreNuloLanzaExcepcion() {
        // Si se intenta crear una categoría sin nombre, debe lanzar la excepción personalizada
        assertThrows(ProductCategoryDataException.class, () -> {
            new ProductCategory(12, null);
        });
    }

    @Test
    void testToString() {
        // Comprobamos el texto que devuelve toString
        String textoEsperado = "ProductCategory [categoryId=10, categoryName=Tecnología]";
        assertEquals(textoEsperado, categoria.toString());
    }


    // Solo se valida el nombre porque el ID lo pone la base de datos.
    // Así evitamos tener categorías sin nombre, que no sirven de mucho.
}


