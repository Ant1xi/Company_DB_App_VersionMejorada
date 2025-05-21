package ejercicio3;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana donde se pueden modificar los datos de un cliente y su contacto.
 * 
 * Al abrirla, se rellenan los campos con la info actual del cliente (usando el DTO).
 * Después el usuario puede cambiar los datos y darle a "Grabar cambios".
 * 
 * Esta clase forma parte del ejercicio 3.
 * 
 * @author Antonio
 */
public class FormularioModificarClienteVista extends JFrame {

	// Campos de texto para los datos del cliente y su contacto
	private JTextField txtNombre, txtDireccion, txtWeb, txtCredito, txtNombreContacto, txtApellidoContacto, txtEmail,
			txtTelefono;
	private JButton btnGuardar;

	ModificarClienteControllerEjercicio3 controllerEjercicio3 = new ModificarClienteControllerEjercicio3();

	/**
	 * Constructor: recibe un DTO con los datos actuales y los pone en los campos.
	 * También prepara la ventana, el botón y su acción.
	 */
	public FormularioModificarClienteVista(ClienteModificarDTO dto) {
		setTitle("Modificar Cliente");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(9, 2, 10, 10));
		setVisible(true);

		// Campos del cliente
		add(new JLabel("Nombre de la empresa:"));
		txtNombre = new JTextField(dto.getCustomerName());
		add(txtNombre);

		add(new JLabel("Dirección:"));
		txtDireccion = new JTextField(dto.getPhone());
		add(txtDireccion);

		add(new JLabel("Página web:"));
		txtWeb = new JTextField(dto.getWebsite());
		add(txtWeb);

		add(new JLabel("Límite de crédito:"));
		txtCredito = new JTextField(dto.getCreditLimit());
		add(txtCredito);

		// Campos del contacto
		add(new JLabel("Nombre de contacto:"));
		txtNombreContacto = new JTextField(dto.getFirstName());
		add(txtNombreContacto);

		add(new JLabel("Apellido de contacto:"));
		txtApellidoContacto = new JTextField(dto.getLastName());
		add(txtApellidoContacto);

		add(new JLabel("Email de contacto:"));
		txtEmail = new JTextField(dto.getEmail());
		add(txtEmail);

		add(new JLabel("Teléfono de contacto:"));
		txtTelefono = new JTextField(dto.getPhone());
		add(txtTelefono);

		// Botón de guardar
		btnGuardar = new JButton("Grabar cambios");
		add(new JLabel()); // espacio vacío para alinear
		add(btnGuardar);

		// Acción del botón
		btnGuardar.addActionListener(e -> {
			controllerEjercicio3.guardarCambios(dto, this);
		});
	}

	// Getters para que el controlador pueda leer lo que puso el usuario

	public JTextField getTxtNombre() {
	    return txtNombre;
	}

	public JTextField getTxtDireccion() {
	    return txtDireccion;
	}

	public JTextField getTxtWeb() {
	    return txtWeb;
	}

	public JTextField getTxtCredito() {
	    return txtCredito;
	}

	public JTextField getTxtNombreContacto() {
	    return txtNombreContacto;
	}

	public JTextField getTxtApellidoContacto() {
	    return txtApellidoContacto;
	}

	public JTextField getTxtEmail() {
	    return txtEmail;
	}

	public JTextField getTxtTelefono() {
	    return txtTelefono;
	}

	public JButton getBtnGuardar() {
	    return btnGuardar;
	}

	/**
	 * Método de apoyo para saber si el usuario dejó algún campo vacío.
	 * 
	 * @return true si hay algún campo sin rellenar
	 */
	boolean camposVacios() {
		return txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtWeb.getText().isEmpty()
				|| txtCredito.getText().isEmpty() || txtNombreContacto.getText().isEmpty()
				|| txtApellidoContacto.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtTelefono.getText().isEmpty();
	}
}
