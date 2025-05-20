package tablas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.OrderDataException;

class OrderTest {

    private Order pedido;
    private Date fechaPedido;

    @BeforeEach
    void setUp() throws OrderDataException {
        // Creamos un pedido con todos los campos obligatorios
        fechaPedido = new Date();
        pedido = new Order(5001, 200, "Pendiente", 101, fechaPedido);
    }

    @Test
    void testConstructorYGetters() {
        // Comprobamos que se hayan guardado bien todos los datos
        assertEquals(5001, pedido.getOrderId());
        assertEquals(200, pedido.getCustomerId());
        assertEquals("Pendiente", pedido.getStatus());
        assertEquals(101, pedido.getSalesmanId());
        assertEquals(fechaPedido, pedido.getOrderDate());
    }

    @Test
    void testSetters() {
        // Modificamos todos los valores para ver si los setters funcionan correctamente
    	
    	// Como puse antes new Date(0) representa el 1 de enero de 1970, que es lo que se llama el "epoch".
    	// Es como el punto de partida del tiempo para muchos lenguajes de programación.
    	// Asi no tengo que poner fechas especificas o largas, y facilita el trabajo

        Date nuevaFecha = new Date(0);
        pedido.setOrderId(5002);
        pedido.setCustomerId(201);
        pedido.setStatus("Enviado");
        pedido.setSalesmanId(102);
        pedido.setOrderDate(nuevaFecha);

        assertEquals(5002, pedido.getOrderId());
        assertEquals(201, pedido.getCustomerId());
        assertEquals("Enviado", pedido.getStatus());
        assertEquals(102, pedido.getSalesmanId());
        assertEquals(nuevaFecha, pedido.getOrderDate());
    }

    @Test
    void testCamposObligatoriosNulosLanzanExcepcion() {
        // Estos casos deberían fallar porque falta algún dato obligatorio

        assertThrows(OrderDataException.class, () -> {
            new Order(5003, null, "Pendiente", 103, new Date());
        });

        assertThrows(OrderDataException.class, () -> {
            new Order(5004, 202, null, 103, new Date());
        });

        assertThrows(OrderDataException.class, () -> {
            new Order(5005, 203, "Pendiente", 103, null);
        });
    }

    @Test
    void testToString() {
        // Verificamos que el toString devuelva el formato esperado
        String esperado = "Order [orderId=5001, customerId=200, status=Pendiente, salesmanId=101, orderDate=" + fechaPedido + "]";
        assertEquals(esperado, pedido.toString());
    }

 
    // Aquí no hace falta validar el orderId porque he visto que eso lo genera la base de datos.
    // Lo importante es que el cliente, el estado del pedido y la fecha no falten, si no el pedido no tendría sentido.
}

