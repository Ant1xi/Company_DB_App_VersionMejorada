package ejercicio2;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;

/**
 * Vista gráfica para la creación de nuevos almacenes.
 * 
 * Esta interfaz permite al usuario seleccionar una región, país y ubicación
 * para asociar el nuevo almacén, así como introducir su nombre.
 * 
 * Utiliza combos dependientes: al seleccionar una región, se cargan
 * los países correspondientes, y al seleccionar un país, se cargan
 * las ubicaciones asociadas.
 * 
 * Llama al {@link AlmacenControllerEjercicio2} para obtener datos y guardar el almacén.
 * 
 * @author Antonio
 */
public class AltaAlmacenVista extends JFrame {

    private JComboBox<RegionLocationDTO> cmbRegiones;
    private JComboBox<CountryDTO> cmbPaises;
    private JComboBox<RegionLocationDTO> cmbLocations;

    private JTextField txtNombreAlmacen;
    private JButton btnCrear;

    private AlmacenControllerEjercicio2 controller = new AlmacenControllerEjercicio2();

    /**
     * Constructor. Inicializa la vista y configura los eventos de los combos
     * y el botón de creación del almacén.
     */
    public AltaAlmacenVista() {
        setTitle("Alta de Almacén");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        cmbRegiones = new JComboBox<>();
        cmbPaises = new JComboBox<>();
        cmbLocations = new JComboBox<>();

        txtNombreAlmacen = new JTextField();
        btnCrear = new JButton("Crear nuevo almacén");

        add(new JLabel("Regiones:"));
        add(cmbRegiones);
        add(new JLabel("Países:"));
        add(cmbPaises);
        add(new JLabel("Ubicaciones:"));
        add(cmbLocations);
        add(new JLabel("Nombre del almacén:"));
        add(txtNombreAlmacen);
        add(new JLabel()); // Espaciador
        add(btnCrear);

        cargarRegiones();

        cmbRegiones.addActionListener(e -> cargarPaises());
        cmbPaises.addActionListener(e -> cargarUbicaciones());
        btnCrear.addActionListener(e -> crearAlmacen());

        setVisible(true);
    }

    /**
     * Llama al controlador para obtener las regiones y cargarlas en el combo correspondiente.
     * Añade control de errores mediante mensajes al usuario.
     */
    private void cargarRegiones() {
        try {
            List<RegionLocationDTO> regiones = controller.obtenerRegiones();
            for (RegionLocationDTO region : regiones) {
                cmbRegiones.addItem(region);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar regiones", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Se ejecuta al seleccionar una región.
     * Obtiene los países correspondientes a la región seleccionada
     * y los carga en el combo de países.
     */
    private void cargarPaises() {
        RegionLocationDTO regionSeleccionada = (RegionLocationDTO) cmbRegiones.getSelectedItem();
        if (regionSeleccionada != null) {
            try {
                List<CountryDTO> paises = controller.obtenerPaisesPorRegion(regionSeleccionada.getId());
                cmbPaises.removeAllItems();
                for (CountryDTO pais : paises) {
                    cmbPaises.addItem(pais);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar países", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Se ejecuta al seleccionar un país.
     * Obtiene las ubicaciones disponibles dentro del país
     * y las carga en el combo de ubicaciones.
     */
    private void cargarUbicaciones() {
        CountryDTO paisSeleccionado = (CountryDTO) cmbPaises.getSelectedItem();
        if (paisSeleccionado != null) {
            try {
                List<RegionLocationDTO> ubicaciones = controller.obtenerUbicacionesPorPais(paisSeleccionado.getId());
                cmbLocations.removeAllItems();
                for (RegionLocationDTO ubicacion : ubicaciones) {
                    cmbLocations.addItem(ubicacion);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar ubicaciones", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Lógica de validación y envío al pulsar el botón de "Crear nuevo almacén".
     * Verifica que los campos no estén vacíos, y si todo es correcto,
     * llama al controlador para insertar el nuevo almacén.
     */
    private void crearAlmacen() {
        String nombre = txtNombreAlmacen.getText().trim();
        RegionLocationDTO ubicacion = (RegionLocationDTO) cmbLocations.getSelectedItem();

        if (nombre.isEmpty() || ubicacion == null) {
            JOptionPane.showMessageDialog(this, "Debes completar todos los campos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            controller.insertarAlmacen(nombre, ubicacion.getId());
            JOptionPane.showMessageDialog(this, "Almacén creado correctamente.");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
