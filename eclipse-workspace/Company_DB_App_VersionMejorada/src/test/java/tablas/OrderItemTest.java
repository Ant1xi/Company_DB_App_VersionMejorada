package tablas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.OrderItemDataException;

class OrderItemTest {

    private OrderItem item;

    @BeforeEach
    void setUp() throws OrderItemDataException {
        // Creamos un item válido con todos los campos completos
        item = new OrderItem(101, 1, 501, 3.0, 19.99);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que los datos se guardan correctamente
        assertEquals(101, item.getOrderId());
        assertEquals(1, item.getItemId());
        assertEquals(501, item.getProductId());
        assertEquals(3.0, item.getQuantity());
        assertEquals(19.99, item.getUnitPrice());
    }

    @Test
    void testSetters() {
        // Modificamos todos los valores y comprobamos que se actualicen bien
        item.setOrderId(102);
        item.setItemId(2);
        item.setProductId(502);
        item.setQuantity(5.5);
        item.setUnitPrice(24.50);

        assertEquals(102, item.getOrderId());
        assertEquals(2, item.getItemId());
        assertEquals(502, item.getProductId());
        assertEquals(5.5, item.getQuantity());
        assertEquals(24.50, item.getUnitPrice());
    }

    @Test
    void testCamposObligatoriosNulosLanzanExcepcion() {
        // Todos estos deberían fallar porque falta algún dato obligatorio

        assertThrows(OrderItemDataException.class, () -> {
            new OrderItem(null, 1, 501, 3.0, 19.99);
        });

        assertThrows(OrderItemDataException.class, () -> {
            new OrderItem(101, null, 501, 3.0, 19.99);
        });

        assertThrows(OrderItemDataException.class, () -> {
            new OrderItem(101, 1, null, 3.0, 19.99);
        });

        assertThrows(OrderItemDataException.class, () -> {
            new OrderItem(101, 1, 501, null, 19.99);
        });

        assertThrows(OrderItemDataException.class, () -> {
            new OrderItem(101, 1, 501, 3.0, null);
        });
    }

    @Test
    void testToString() {
        // Comprobamos que el toString devuelve el formato esperado
        String textoEsperado = "OrderItem [orderId=101, itemId=1, productId=501, quantity=3.0, unitPrice=19.99]";
        assertEquals(textoEsperado, item.toString());
    }

 
    // En este caso todos los campos son obligatorios, porque la tabla los necesita sí o sí para funcionar.
    // Así que si falta algo, se lanza la excepción al momento de crear el objeto y te avisa antes de que vaya a la base de datos.
}
