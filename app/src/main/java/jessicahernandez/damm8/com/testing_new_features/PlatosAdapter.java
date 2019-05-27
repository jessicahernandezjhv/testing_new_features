package jessicahernandez.damm8.com.testing_new_features;

public class PlatosAdapter{
    String nombre;
    String ingredientes;
    String precio;

    public PlatosAdapter() {
    }

    public PlatosAdapter(String nombre, String ingredientes, String precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
