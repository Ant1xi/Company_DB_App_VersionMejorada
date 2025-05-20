package ejercicio1;

import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exceptions.EmployeeDataException;

public class AltaEmpleadoVista extends JFrame {

	private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtJobTitle, txtHireDate;

	private JComboBox<ManagerDTO> cmbManager;

	private JButton btnGuardar;

	private List<ManagerDTO> managerList;

	public AltaEmpleadoVista(List<ManagerDTO> managerList) {

		this.managerList = managerList;

		setTitle("Alta de Empleado");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridLayout(8, 2, 10, 10));

		txtFirstName = new JTextField();
		txtLastName = new JTextField();
		txtEmail = new JTextField();
		txtPhone = new JTextField();
		txtHireDate = new JTextField(LocalDate.now().toString()); // Comando para recargar la fecha actual
		txtJobTitle = new JTextField();

		cmbManager = new JComboBox<>();
		btnGuardar = new JButton("Guardar");

		JLabel lblFirstName = new JLabel("Nombre: ");
		JLabel lblLastName = new JLabel("Apellido:");
		JLabel lblEmail = new JLabel("Correo electrónico:");
		JLabel lblPhone = new JLabel("Teléfono:");
		JLabel lblHireDate = new JLabel("Fecha de contratación (YYYY-MM-DD):");
		JLabel lblJobTitle = new JLabel("Cargo:");
		JLabel lblManager = new JLabel("Jefe:");

		add(lblFirstName);
		add(txtFirstName);
		add(lblLastName);
		add(txtLastName);
		add(lblEmail);
		add(txtEmail);
		add(lblPhone);
		add(txtPhone);
		add(lblHireDate);
		add(txtHireDate);
		add(lblJobTitle);
		add(txtJobTitle);
		add(lblManager);
		add(cmbManager);
		

		add(new JLabel());
		add(btnGuardar);

		

		cargarComboBoxManagers();
		
		btnGuardar.addActionListener(e -> guardarEmpleado());

	}

	private void cargarComboBoxManagers() {
		for (ManagerDTO opc : managerList) {
			cmbManager.addItem(opc);
		}
	}

	private void guardarEmpleado() {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtPhone.getText().isEmpty() || txtJobTitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos incompletos",
					JOptionPane.WARNING_MESSAGE);
		}
		
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String email = txtEmail.getText().trim();
		String phone = txtPhone.getText().trim();
		Date fecha = null;
		
		try {
		    fecha = java.sql.Date.valueOf(txtHireDate.getText().trim());
		} catch (IllegalArgumentException ex) {
		    JOptionPane.showMessageDialog(this, "Fecha inválida. Usa formato YYYY-MM-DD.", "Error de formato", JOptionPane.ERROR_MESSAGE);
		    return;
		}
		
		String jobTitle = txtJobTitle.getText().trim();

		ManagerDTO managerElegido = (ManagerDTO) cmbManager.getSelectedItem();
		Integer managerId = null;

		if (managerElegido != null) {
			managerId = managerElegido.getId();
		}

		try {
			EmployeeControllerEjercicio1 eController = new EmployeeControllerEjercicio1();
			eController.guardarEmpleado(firstName, lastName, email, phone, fecha, managerId, jobTitle);
			JOptionPane.showMessageDialog(this, "Empleado guardado correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			limpiarCampos();
		} catch (EmployeeDataException e) {
			JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Datos inválidos",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void limpiarCampos() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtJobTitle.setText("");
	}

}
