package com.daw;

import javax.swing.*;

import ejercicio1.EmployeeControllerEjercicio1;
import ejercicio2.AlmacenControllerEjercicio2;
import exceptions.EmployeeDataException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame {
	EmployeeControllerEjercicio1 controlerEjercicio1 = new EmployeeControllerEjercicio1();
	AlmacenControllerEjercicio2 controlerEjercicio2 = new AlmacenControllerEjercicio2();

	public VentanaPrincipal() {
		setTitle("Menú Principal");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas, separación

		// Botón para abrir la ventana de Alta de Empleado
		JButton btnAltaEmpleado = new JButton("Alta de Empleado");
		btnAltaEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controlerEjercicio1.cargaVistaAltaEmpleado();
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
				controlerEjercicio2.cargaVistaAltaAlmacen();

			}
		});

		// Agregar los botones al menú principal
		add(btnAltaEmpleado);
		add(btnAltaAlmacen);

		// Mostrar la ventana
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaPrincipal();
	}
}
