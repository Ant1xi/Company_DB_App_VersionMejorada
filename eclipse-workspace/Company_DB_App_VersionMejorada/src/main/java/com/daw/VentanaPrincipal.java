package com.daw;

import javax.swing.*;

import ejercicio1.EmployeeControllerEjercicio1;
import ejercicio2.AlmacenControllerEjercicio2;
import ejercicio3.ModificarClienteControllerEjercicio3;
import ejercicio4.BuscarClienteYDetallesPedidoControllerEjercicio4;
import ejercicio5.EliminarEmpleadoControllerEjercicio5;
import exceptions.EmployeeDataException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Ventana principal del sistema, que actúa como menú para acceder
 * a las distintas funcionalidades implementadas en los ejercicios.
 * 
 * Carga y muestra una serie de botones para abrir vistas relacionadas
 * con empleados, almacenes, clientes y pedidos. Cada botón está vinculado
 * a su controlador correspondiente.
 * 
 * Esta clase extiende {@link JFrame} y representa la entrada principal
 * del programa.
 * 
 * @author Antonio
 */
public class VentanaPrincipal extends JFrame {

    /** Controlador del ejercicio 1: Alta de empleados. */
    EmployeeControllerEjercicio1 controllerEjercicio1 = new EmployeeControllerEjercicio1();

    /** Controlador del ejercicio 2: Alta de almacenes. */
    AlmacenControllerEjercicio2 controllerEjercicio2 = new AlmacenControllerEjercicio2();

    /** Controlador del ejercicio 3: Modificación de clientes. */
    ModificarClienteControllerEjercicio3 controllerEjercicio3 = new ModificarClienteControllerEjercicio3();

    /** Controlador del ejercicio 4: Búsqueda de pedidos por cliente. */
    BuscarClienteYDetallesPedidoControllerEjercicio4 controllerEjercicio4 = new BuscarClienteYDetallesPedidoControllerEjercicio4();

    /** Controlador del ejercicio 5: Eliminación de empleados. */
    EliminarEmpleadoControllerEjercicio5 controllerEjercicio5 = new EliminarEmpleadoControllerEjercicio5();

    /**
     * Constructor de la ventana principal. Configura los botones del menú
     * y sus acciones asociadas.
     */
    public VentanaPrincipal() {
        setTitle("Menú Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // 5 botones, 2 columnas, con separación

        // Botón para abrir la ventana de Alta de Empleado
        JButton btnAltaEmpleado = new JButton("Alta de Empleado");
        btnAltaEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controllerEjercicio1.cargaVistaAltaEmpleado();
                } catch (EmployeeDataException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Botón para abrir la ventana de Alta de Almacén
        JButton btnAltaAlmacen = new JButton("Alta de Almacén");
        btnAltaAlmacen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerEjercicio2.cargaVistaAltaAlmacen();
            }
        });

        // Botón para abrir la ventana de Modificar Cliente
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        btnModificarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerEjercicio3.cargarVistaModificarCustomers();
            }
        });

        // Botón para abrir la ventana de Buscar pedidos de un Cliente y ver detalles
        JButton btnBuscarPedidosCliente = new JButton("Buscar pedidos Cliente y ver detalles");
        btnBuscarPedidosCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerEjercicio4.cargarBuscarPedidosClienteVista();
            }
        });

        // Botón para abrir la ventana de Eliminar Empleado
        JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
        btnEliminarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controllerEjercicio5.cargaEliminarEmpleadoVista();
                } catch (EmployeeDataException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Agregar los botones al menú principal
        add(btnAltaEmpleado);
        add(btnAltaAlmacen);
        add(btnModificarCliente);
        add(btnBuscarPedidosCliente);
        add(btnEliminarEmpleado);

        // Mostrar la ventana
        setVisible(true);
    }

    /**
     * Método principal que lanza la aplicación mostrando el menú.
     *
     * @param args argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
