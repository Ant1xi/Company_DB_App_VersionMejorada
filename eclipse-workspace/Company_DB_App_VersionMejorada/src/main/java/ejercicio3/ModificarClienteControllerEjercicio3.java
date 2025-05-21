package ejercicio3;

import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.daw.ClaseConectoraBBDD;
import tablas.Contact;
import tablas.Customer;

/**
 * Controlador del ejercicio 3: se encarga de cargar la lista de clientes,
 * mostrarla en una tabla, abrir el formulario cuando se selecciona uno
 * y guardar los cambios hechos por el usuario.
 * 
 * Aquí conectamos todo: la vista, el modelo y la lógica de modificación.
 * 
 * @author Antonio
 */
public class ModificarClienteControllerEjercicio3 {

    /**
     * Carga la ventana con el JTable de clientes.
     * Al pulsar en "Modificar cliente", se abre el formulario con los datos ya cargados.
     */
    public void cargarVistaModificarCustomers() {

        List<Customer> listaClientes;

        try (Connection conn = ClaseConectoraBBDD.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO();
            listaClientes = customerDAO.getAll(conn);

            JTableClientesVista tcv = new JTableClientesVista(listaClientes);

            // Acción cuando se pulsa "Modificar cliente"
            tcv.getBtnModificarCliente().addActionListener(e -> {
                try {
                    JTable tabla = tcv.getTablaCustomers();
                    int filaSeleccionada = tabla.getSelectedRow();

                    if (filaSeleccionada == -1) return;

                    Customer cliente = listaClientes.get(filaSeleccionada);
                    int customerId = cliente.getCustomerId();

                    try (Connection conn2 = ClaseConectoraBBDD.getConnection()) {
                        ContactDAO contactDAO = new ContactDAO();
                        Contact contacto = contactDAO.getByCustomerId(conn2, customerId);

                        // Creamos el DTO con los datos actuales
                        ClienteModificarDTO dto = new ClienteModificarDTO(
                            cliente.getName(),
                            cliente.getAddres(),
                            cliente.getWebsite(),
                            String.valueOf(cliente.getCreditLimit()),
                            contacto.getFirstName(),
                            contacto.getLastName(),
                            contacto.getEmail()
                        );

                        dto.setCustomerId(customerId);
                        dto.setContactId(contacto.getContactId());
                        dto.setPhone(contacto.getPhone());

                        // Mostramos la ventana con el formulario
                        FormularioModificarClienteVista vista = new FormularioModificarClienteVista(dto);

                        // Acción del botón "Grabar cambios"
                        vista.getBtnGuardar().addActionListener(e2 -> guardarCambios(dto, vista));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al abrir el formulario.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes.");
        }
    }

    /**
     * Guarda los cambios realizados en el cliente y su contacto.
     * Se valida que no haya campos vacíos y se actualizan ambos en la base de datos.
     *
     * @param dto DTO que contiene los IDs del cliente y contacto.
     * @param vista la ventana con los nuevos datos rellenados.
     */
    public void guardarCambios(ClienteModificarDTO dto, FormularioModificarClienteVista vista) {
        try {
            if (vista.camposVacios()) {
                JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.");
                return;
            }

            // Creamos los objetos con los nuevos datos
            Customer customer = new Customer(
                dto.getCustomerId(),
                vista.getTxtNombre().getText(),
                vista.getTxtDireccion().getText(),
                vista.getTxtWeb().getText(),
                Double.parseDouble(vista.getTxtCredito().getText())
            );

            Contact contact = new Contact(
                dto.getContactId(),
                vista.getTxtNombreContacto().getText(),
                vista.getTxtApellidoContacto().getText(),
                vista.getTxtEmail().getText(),
                vista.getTxtTelefono().getText(),
                dto.getCustomerId()
            );

            try (Connection conn = ClaseConectoraBBDD.getConnection()) {
                CustomerDAO customerDAO = new CustomerDAO();
                ContactDAO contactDAO = new ContactDAO();

                customerDAO.update(conn, customer);
                contactDAO.update(conn, contact);

                JOptionPane.showMessageDialog(vista, "Datos actualizados correctamente.");
                vista.dispose(); // cerrar ventana
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar los datos.");
        }
    }
}
