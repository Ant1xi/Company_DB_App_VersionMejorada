package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WarehouseDataException;

class WarehouseTest {

    private Warehouse almacen;

    @BeforeEach
    void setUp() throws WarehouseDataException {
        // Creamos un almacén con todos los datos
        almacen = new Warehouse(1, "AlmacénUno", 100);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que se han guardado bien los valores
        assertEquals(1, almacen.getWarehouseId());
        assertEquals("AlmacénUno", almacen.getWarehouseName());
        assertEquals(100, almacen.getLocationId());
    }

    @Test
    void testSetters() {
        // Cambiamos los valores y comprobamos que se actualicen
        almacen.setWarehouseId(2);
        almacen.setWarehouseName("AlmacénDos");
        almacen.setLocationId(101);

        assertEquals(2, almacen.getWarehouseId());
        assertEquals("AlmacénDos", almacen.getWarehouseName());
        assertEquals(101, almacen.getLocationId());
    }

    @Test
    void testConstructorSinId() throws WarehouseDataException {
        // También se puede crear sin ID si lo asigna la base de datos después
        Warehouse otroAlmacen = new Warehouse("Almacén Japonés", 102);
        assertNull(otroAlmacen.getWarehouseId());
        assertEquals("Almacén Japonés", otroAlmacen.getWarehouseName());
        assertEquals(102, otroAlmacen.getLocationId());
    }

    @Test
    void testConstructorCompletoConCamposNulos() {
        // No se puede crear si algún campo obligatorio es null

        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse(null, "Nombre", 100);
        });

        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse(1, null, 100);
        });

        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse(1, "Nombre", null);
        });
    }

    @Test
    void testConstructorParcialConCamposNulos() {
        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse(null, 100);
        });

        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse("Nombre", null);
        });
    }

    @Test
    void testToString() {
        // Comprobamos que toString devuelva el formato correcto
        String textoEsperado = "Warehouse [warehouseId=1, warehouseName=AlmacénUno, locationId=100]";
        assertEquals(textoEsperado, almacen.toString());
    }

    // Curiosidad:
    // Aunque hay dos constructores, ambos validan que ningún campo obligatorio venga a null.
    // Así evitas crear almacenes incompletos o con errores desde el principio.
}

