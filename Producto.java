
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private int id;
    

    // Constructor para inicializar el objeto Producto
    public Producto(String nombre, double precio, int stock, int id) {
        this.id = id;       
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getter para el id

    public int getId() {
        return id; }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String piNombre) {
        this.nombre = piNombre;
    }

        public double getPrecio() {
        return precio;
    }

    public void setPrecio(double piPrecio) {
        this.precio = piPrecio;
    }
public void mostrar() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio " | Stock: " + stock);
    }
