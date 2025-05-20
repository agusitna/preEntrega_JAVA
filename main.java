
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
            System.out.println("5. Consultar nombre por ID");
            System.out.println("6. Crear pedido");
            System.out.println("7. Mostrar pedidos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();        
            sc.nextLine();           

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductos();
                case 3 -> modificarProducto();
                case 4 -> eliminarProducto();
                case 5 -> devolverNombreProducto();
                case 6 -> crearPedido();
                case 7 -> mostrarPedidos();
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 8); 
    }

    // Método para crear un nuevo producto
    public static void crearProducto() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();     // Leer ID
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();            // Leer nombre
        System.out.print("Precio: ");
        double precio = sc.nextDouble();          // Leer precio

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

    public static void crearPedido() {
        Pedido pedido = new Pedido();

        while (true) {
            listarProductos();
            System.out.print("ID del producto a agregar (0 para finalizar): ");
            int id = sc.nextInt();
            if (id == 0) break;

            Producto prod = null;
            for (Producto p : lista) {
                if (p.getId() == id) {
                    prod = p;
                    break;
                }
            }

            if (prod == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad deseada: ");
            int cantidad = sc.nextInt();

            if (!prod.hayStock(cantidad)) {
                System.out.println("Stock insuficiente.");
                continue;
            }

            pedido.agregarLinea(new LineaPedido(prod, cantidad));
            prod.reducirStock(cantidad);
            System.out.println("Producto agregado al pedido.");
        }

        if (pedido.calcularTotal() > 0) {
            pedidos.add(pedido);
            System.out.println("Pedido creado con éxito. Total: $" + pedido.calcularTotal());
        } else {
            System.out.println("Pedido vacío, no se creó.");
        }
    }

    public static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
            return;
        }

        int i = 1;
        for (Pedido p : pedidos) {
            System.out.println("\nPedido #" + i++);
            p.mostrarPedido();
        }
    }

    class ProductosAsociados {
    private Producto producto;
    private int cantidad;

    public ProductosAsociados(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = $" + getSubtotal();
    }
}

// Clase Pedido que agrupa varios productos
class Pedido {
    private ArrayList<ProductosAsociados> lineas = new ArrayList<>();

    public void agregarLinea(ProductosAsociados lp) {
        lineas.add(lp);
    }

    public double calcularTotal() {
        double total = 0;
        for (ProductosAsociados lp : lineas) {
            total += lp.getSubtotal();
        }
        return total;
    }

    public void mostrarPedido() {
        for (ProductosAsociados lp : lineas) {
            System.out.println(lp);
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }
}
}
