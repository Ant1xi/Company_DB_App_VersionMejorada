package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryDataException;

class InventoryTest {

    private Inventory inventario;

    @BeforeEach
    void setUp() throws InventoryDataException {
        // Creamos un inventario normal con todos los datos
        inventario = new Inventory(1001, 5, 200);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que al crearlo, los datos quedan bien guardados
        assertEquals(1001, inventario.getProductId());
        assertEquals(5, inventario.getWarehouseId());
        assertEquals(200, inventario.getQuantity());
    }

    @Test
    void testSetters() {
        // Cambiamos todos los valores y vemos si se actualizan bien
        inventario.setProductId(1002);
        inventario.setWarehouseId(7);
        inventario.setQuantity(350);

        assertEquals(1002, inventario.getProductId());
        assertEquals(7, inventario.getWarehouseId());
        assertEquals(350, inventario.getQuantity());
    }

    @Test
    void testCamposNulosLanzanExcepcion() {
        // En estos casos debería lanzar la excepción porque faltan datos obligatorios

        assertThrows(InventoryDataException.class, () -> {
            new Inventory(null, 3, 100);
        });

        assertThrows(InventoryDataException.class, () -> {
            new Inventory(1003, null, 100);
        });

        assertThrows(InventoryDataException.class, () -> {
            new Inventory(1003, 3, null);
        });
    }

    @Test
    void testToString() {
        // Comparamos lo que devuelve el toString con el texto que esperamos
        String textoEsperado = "Inventory [productId=1001, warehouseId=5, quantity=200]";
        assertEquals(textoEsperado, inventario.toString());
    }

    // Si falta algún dato al crear el inventario, no te deja y lanza una excepción.
    // Esto viene bien para asegurarte de que no se crean registros con datos incompletos.
}
