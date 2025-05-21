package ejercicio2;

/**
 * DTO (Data Transfer Object) genérico utilizado para representar tanto regiones como ubicaciones.
 * 
 * Contiene un identificador numérico y un nombre, y se emplea principalmente
 * en los {@link javax.swing.JComboBox} de la interfaz gráfica del ejercicio 2.
 * 
 * Gracias al método {@code toString()}, el combo mostrará directamente el nombre.
 * 
 * Este DTO se reutiliza para:
 * <ul>
 *   <li>Regiones (por su {@code region_id} y {@code region_name})</li>
 *   <li>Ubicaciones (por su {@code location_id} y {@code city})</li>
 * </ul>
 * 
 * @author Antonio
 */
public class RegionLocationDTO {

    /** Identificador numérico (puede ser ID de región o de ubicación). */
    private int id;

    /** Nombre legible (nombre de región o ciudad). */
    private String nombre;

    /**
     * Constructor que inicializa el DTO con los datos proporcionados.
     *
     * @param id identificador numérico.
     * @param nombre texto que se mostrará en pantalla.
     */
    public RegionLocationDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Devuelve el ID del DTO (usado como clave para búsquedas o inserciones).
     * 
     * @return identificador numérico.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nombre del DTO (mostrado al usuario).
     * 
     * @return nombre como texto legible.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre como representación textual del objeto.
     * Esto permite que el {@code JComboBox} muestre directamente el valor.
     *
     * @return nombre legible.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
