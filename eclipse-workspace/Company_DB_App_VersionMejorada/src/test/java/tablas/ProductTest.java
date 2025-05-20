package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ProductDataException;

class ProductTest {

    private Product producto;

    @BeforeEach
    void setUp() throws ProductDataException {
        // Creamos un producto con todos los campos completos
        producto = new Product(1001, "Altavoz portátil", "Bluetooth, batería de larga duración", 22.50, 35.99, 4);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que los datos se hayan guardado bien
        assertEquals(1001, producto.getProductId());
        assertEquals("Altavoz portátil", producto.getProductName());
        assertEquals("Bluetooth, batería de larga duración", producto.getDescription());
        assertEquals(22.50, producto.getStandarCost());
        assertEquals(35.99, producto.getListPrice());
        assertEquals(4, producto.getCategoryId());
    }

    @Test
    void testSetters() {
        // Cambiamos todos los valores y revisamos que se actualicen bien
        producto.setProductId(1002);
        producto.setProductName("Auriculares inalámbricos");
        producto.setDescription("Cancelación de ruido, micrófono incorporado");
        producto.setStandarCost(30.0);
        producto.setListPrice(49.99);
        producto.setCategoryId(5);

        assertEquals(1002, producto.getProductId());
        assertEquals("Auriculares inalámbricos", producto.getProductName());
        assertEquals("Cancelación de ruido, micrófono incorporado", producto.getDescription());
        assertEquals(30.0, producto.getStandarCost());
        assertEquals(49.99, producto.getListPrice());
        assertEquals(5, producto.getCategoryId());
    }

    @Test
    void testCamposObligatoriosNulosLanzanExcepcion() {
        // Estos casos deberían lanzar excepción porque faltan campos clave

        assertThrows(ProductDataException.class, () -> {
            new Product(1003, null, "Descripción de prueba", 20.0, 30.0, 2);
        });

        assertThrows(ProductDataException.class, () -> {
            new Product(1004, "Teclado mecánico", "Con iluminación LED", 15.0, 29.99, null);
        });
    }

    @Test
    void testToString() {
        // Comprobamos que el toString devuelve el texto esperado
        String textoEsperado = "Product [productId=1001, productName=Altavoz portátil, description=Bluetooth, batería de larga duración, standarCost=22.5, listPrice=35.99, categoryId=4]";
        assertEquals(textoEsperado, producto.toString());
    }

 
    // En este caso, solo se validan el nombre del producto y la categoría,
    // porque los demás campos sí pueden quedar vacíos. Así tambien es un poquito más flexible
}
