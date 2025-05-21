package ejercicio1;

import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;

import exceptions.EmployeeDataException;

/**
 * Vista de alta de empleados. Permite al usuario introducir los datos
 * necesarios para crear un nuevo empleado y asociarlo, si se desea,
 * a un jefe (manager) existente.
 * 
 * Esta clase se encarga de construir la interfaz gráfica, validar los datos
 * introducidos y comunicarse con el controlador correspondiente para realizar
 * la operación de guardado.
 * 
 * @author Antonio
 */
public class AltaEmpleadoVista extends JFrame {

    private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtJobTitle, txtHireDate;
    private JComboBox<ManagerDTO> cmbManager;
    private JButton btnGuardar;
    private List<ManagerDTO> managerList;

    /**
     * Constructor que crea la vista de alta de empleado con la lista
     * de managers disponibles para seleccionar como jefe.
     *
     * @param managerList lista de posibles managers a mostrar en el combo.
     */
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
        txtHireDate = new JTextField(LocalDate.now().toString()); // Fecha por defecto: hoy
        txtJobTitle = new JTextField();

        cmbManager = new JComboBox<>();
        btnGuardar = new JButton("Guardar");

        // Etiquetas
        JLabel lblFirstName = new JLabel("Nombre: ");
        JLabel lblLastName = new JLabel("Apellido:");
        JLabel lblEmail = new JLabel("Correo electrónico:");
        JLabel lblPhone = new JLabel("Teléfono:");
        JLabel lblHireDate = new JLabel("Fecha de contratación (YYYY-MM-DD):");
        JLabel lblJobTitle = new JLabel("Cargo:");
        JLabel lblManager = new JLabel("Jefe:");

        // Añadir componentes a la vista
        add(lblFirstName); add(txtFirstName);
        add(lblLastName); add(txtLastName);
        add(lblEmail); add(txtEmail);
        add(lblPhone); add(txtPhone);
        add(lblHireDate); add(txtHireDate);
        add(lblJobTitle); add(txtJobTitle);
        add(lblManager); add(cmbManager);
        add(new JLabel()); add(btnGuardar);

        cargarComboBoxManagers();

        btnGuardar.addActionListener(e -> guardarEmpleado());
    }

    /**
     * Carga en el combo los managers disponibles recibidos en el constructor.
     */
    private void cargarComboBoxManagers() {
        for (ManagerDTO opc : managerList) {
            cmbManager.addItem(opc);
        }
    }

    /**
     * Valida los datos del formulario y, si son correctos, intenta guardar
     * el nuevo empleado utilizando el controlador correspondiente.
     * Muestra mensajes de error si hay datos inválidos.
     */
    private void guardarEmpleado() {
        if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtPhone.getText().isEmpty() || txtJobTitle.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        Date fecha = null;

        try {
            fecha = java.sql.Date.valueOf(txtHireDate.getText().trim());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Usa formato YYYY-MM-DD.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String jobTitle = txtJobTitle.getText().trim();
        ManagerDTO managerElegido = (ManagerDTO) cmbManager.getSelectedItem();
        Integer managerId = (managerElegido != null) ? managerElegido.getId() : null;

        try {
            EmployeeControllerEjercicio1 eController = new EmployeeControllerEjercicio1();
            eController.guardarEmpleado(firstName, lastName, email, phone, fecha, managerId, jobTitle);

            JOptionPane.showMessageDialog(this, "Empleado guardado correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (EmployeeDataException e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(),
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Limpia todos los campos del formulario tras guardar un empleado.
     */
    private void limpiarCampos() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtJobTitle.setText("");
    }
}
