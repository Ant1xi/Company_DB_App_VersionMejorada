package ejercicio2;

/**
 * DTO (Data Transfer Object) que representa un país para su uso en la interfaz.
 * 
 * Este objeto se utiliza principalmente para poblar el combo de países
 * en la vista de alta de almacenes, mostrando únicamente la información necesaria
 * (ID del país y su nombre).
 * 
 * El método {@code toString()} está sobrescrito para que el {@link javax.swing.JComboBox}
 * muestre automáticamente el nombre del país.
 * 
 * @author Antonio
 */
public class CountryDTO {

    /** ID del país (por ejemplo, "ES", "US"). */
    private String id;

    /** Nombre completo del país. */
    private String nombre;

    /**
     * Constructor que inicializa el DTO con los datos del país.
     *
     * @param id     ID del país.
     * @param nombre nombre del país.
     */
    public CountryDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Devuelve el ID del país.
     * 
     * @return el código del país.
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre del país.
     * 
     * @return nombre completo del país.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre como representación textual del objeto.
     * Esto permite que el {@code JComboBox} muestre el país directamente.
     *
     * @return nombre del país.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
