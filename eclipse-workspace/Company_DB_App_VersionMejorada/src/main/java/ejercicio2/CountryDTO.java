package ejercicio2;

public class CountryDTO {
	private String id;
    private String nombre;

    public CountryDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
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
