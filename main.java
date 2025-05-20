
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static ArrayList<Producto> lista = new ArrayList<>();
    static int contadorProductos = 0;
    static Scanner sc = new Scanner(System.in);

    
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("6. Nombre del producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();        
            sc.nextLine();           

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductos();
                case 3 -> modificarProducto();
                case 4 -> eliminarProducto();
                case 5 -> System.out.println("Saliendo...");
                case 6 -> devolverNombreProducto();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5); 
    }

    // Método para crear un nuevo producto
    public static void crearProducto() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();     // Leer ID
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();            // Leer nombre
        System.out.print("Precio: ");
        double precio = sc.nextDouble();          // Leer precio

        // Crear un nuevo objeto Producto y agregarlo a la lista
        Producto nuevo = new Producto(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Se agregó el producto " + nombre);
    }

    public static void listarProductos() {
        if (lista.isEmpty()) {
            System.out.println("No hay productos en stock");
        } else {
            for (Producto a : lista) {
                 a.mostrar();  
            }
        }
    }

    // Método para modificar un producto existente
    public static void modificarProducto() {
        System.out.print("ID del producto a modificar: ");
        int id = sc.nextInt();
        for (Producto Producto : lista) {
            if (Producto.getId() == id) {
                sc.nextLine();
                System.out.print("Nuevo nombre: ");
                Producto.setNombre(sc.nextLine());      
                System.out.print("Nuevo precio: ");
                Producto.setPrecio(sc.nextDouble());  
                System.out.println("producto actualizado.");
                return;
            }
        }
        System.out.println("producto no encontrado.");
    }

    // Método para eliminar un producto por ID
    public static void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = sc.nextInt();
        lista.removeIf(a -> a.getId() == id);
        System.out.println("producto eliminado si existía.");
    }

    public static void devolverNombreProducto() {
        System.out.print("ID del producto para la consulta: ");
        int id = sc.nextInt();
        for (Producto Producto : lista) {
            if (Producto.getId() == id) {
                System.out.println("Nombre del producto: " + Producto.getNombre());
                return;
            }
        }
        System.out.println("producto no encontrado.");
    }
}
