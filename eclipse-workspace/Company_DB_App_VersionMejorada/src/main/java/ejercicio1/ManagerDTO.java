package ejercicio1;

/**
 * DTO (Data Transfer Object) que representa a un manager (jefe).
 * 
 * Se utiliza principalmente para poblar el combo desplegable de managers
 * en la vista de alta de empleados. Contiene solo la información necesaria:
 * el ID del manager y su nombre completo.
 * 
 * Al sobrescribir el método {@code toString()}, permite que el combo
 * muestre directamente el nombre del manager.
 * 
 * @author Antonio
 */
public class ManagerDTO {

    /** ID único del manager. */
    private int id;

    /** Nombre completo del manager. */
    private String nombre;

    /**
     * Constructor que inicializa el DTO con los datos del manager.
     *
     * @param id ID del manager.
     * @param nombre nombre completo del manager.
     */
    public ManagerDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Devuelve el ID del manager.
     * 
     * @return el identificador numérico.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nombre completo del manager.
     * 
     * @return nombre del manager.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre como representación textual del objeto.
     * Esto permite que el {@code JComboBox} muestre el nombre directamente.
     *
     * @return nombre del manager.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
