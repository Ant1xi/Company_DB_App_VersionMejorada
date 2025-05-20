package ejercicio2;

public class RegionLocationDTO {
	private int id;
    private String nombre;

    public RegionLocationDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;  // Esto es lo que se verá en el JComboBox
    }
}
