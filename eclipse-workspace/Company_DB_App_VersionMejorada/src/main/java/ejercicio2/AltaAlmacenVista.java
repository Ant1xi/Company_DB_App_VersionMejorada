package ejercicio2;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AltaAlmacenVista extends JFrame {
	private JComboBox<RegionLocationDTO> cmbRegiones;
	private JComboBox<CountryDTO> cmbPaises;
	private JComboBox<RegionLocationDTO> cmbLocations;

	private JTextField txtNombreAlmacen;

	private JButton btnCrear;

	private AlmacenControllerEjercicio2 controller = new AlmacenControllerEjercicio2();

	public AltaAlmacenVista() {
		setTitle("Alta de Almacen");
		setSize(400, 300);
		setLocationRelativeTo(null); // para que aparezca centrada
		setVisible(true);
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
		add(new JLabel()); // celda vacía para que el boton de crear este alineado con los comboBox
		add(btnCrear);
		
		cargarRegiones(); //Nada mas abrimos las ventanas cargamos las regiones
		
		cmbRegiones.addActionListener(e -> cargarPaises());
		cmbPaises.addActionListener(e -> cargarUbicaciones());
		btnCrear.addActionListener(e -> crearAlmacen());
		
		setVisible(true);

	}

	// Este método llama al controlador para cargar las regiones disponibles
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

	// Al seleccionar una región, se cargan los países que pertenecen a esa región
	private void cargarPaises() {
		RegionLocationDTO regionSeleccionada = (RegionLocationDTO) cmbRegiones.getSelectedItem();
		if (regionSeleccionada != null) {
			try {
				List<CountryDTO> paises = controller.obtenerPaisesPorRegion(regionSeleccionada.getId());
				for (CountryDTO pais : paises) {
					cmbPaises.addItem(pais);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al cargar países", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Al seleccionar un país, se cargan las ubicaciones disponibles en ese país
	private void cargarUbicaciones() {
		CountryDTO paisSeleccionado = (CountryDTO) cmbPaises.getSelectedItem();
		if (paisSeleccionado != null) {
			try {
				List<RegionLocationDTO> ubicaciones = controller.obtenerUbicacionesPorPais(paisSeleccionado.getId());
				for (RegionLocationDTO ubicacion : ubicaciones) {
					cmbLocations.addItem(ubicacion);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al cargar ubicaciones", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Este método recoge los datos del formulario y crea el almacén si todo está
	// bien
	private void crearAlmacen() {
		String nombre = txtNombreAlmacen.getText().trim();
		RegionLocationDTO ubicacion = (RegionLocationDTO) cmbLocations.getSelectedItem();

		// Validamos que el usuario haya rellenado los datos
		if (nombre.isEmpty() || ubicacion == null) {
			JOptionPane.showMessageDialog(this, "Debes completar todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			controller.insertarAlmacen(nombre, ubicacion.getId());
			JOptionPane.showMessageDialog(this, "Almacén creado correctamente.");
			dispose(); // Cierra la ventana al finalizar
		} catch (Exception e) {
			e.printStackTrace(); // Lo dejo para depurar si falla
			JOptionPane.showMessageDialog(this, "Error al insertar el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
