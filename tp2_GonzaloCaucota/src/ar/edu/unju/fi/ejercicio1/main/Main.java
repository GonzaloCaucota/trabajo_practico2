package ar.edu.unju.fi.ejercicio1.main;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.f.iejercicio1.model.Categoria;
import ar.edu.unju.f.iejercicio1.model.OrigenFabricacion;
import ar.edu.unju.f.iejercicio1.model.Producto;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1 - Crear Producto");
            System.out.println("2 - Mostrar productos");
            System.out.println("3 - Modificar producto");
            System.out.println("4 - Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        crearProducto(productos, scanner);
                        break;
                    case 2:
                        mostrarProductos(productos);
                        break;
                    case 3:
                        modificarProducto(productos, scanner);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
                opcion = 0;
            }
        } while (opcion != 4);
    }

    private static void crearProducto(ArrayList<Producto> productos, Scanner scanner) {
        System.out.println("\nCreación de Producto:");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio unitario: ");
        double precioUnitario = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("------ Origen de fabricación ------");
        mostrarOpciones(OrigenFabricacion.values());
        System.out.print("Elija una opción: ");
        int opcionOrigen = scanner.nextInt();
        OrigenFabricacion origenFabricacion = OrigenFabricacion.values()[opcionOrigen - 1];
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("------ Categoría ------");
        mostrarOpciones(Categoria.values());
        System.out.print("Elija una opción: ");
        int opcionCategoria = scanner.nextInt();
        Categoria categoria = Categoria.values()[opcionCategoria - 1];
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Producto nuevoProducto = new Producto(codigo, descripcion, precioUnitario, origenFabricacion, categoria);
        productos.add(nuevoProducto);
        System.out.println("Producto creado exitosamente.");
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("\nProductos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void modificarProducto(ArrayList<Producto> productos, Scanner scanner) {
        System.out.println("\nModificar Producto:");
        System.out.print("Ingrese el código del producto a modificar: ");
        String codigo = scanner.nextLine();

        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                encontrado = true;
                System.out.println("Seleccione el atributo a modificar:");
                System.out.println("1 - Descripción");
                System.out.println("2 - Precio unitario");
                System.out.println("3 - Origen de fabricación");
                System.out.println("4 - Categoría");
                System.out.print("Elija una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la nueva descripción: ");
                        String nuevaDescripcion = scanner.nextLine();
                        producto.setDescripcion(nuevaDescripcion);
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo precio unitario: ");
                        double nuevoPrecio = scanner.nextDouble();
                        producto.setPrecioUnitario(nuevoPrecio);
                        break;
                    case 3:
                        System.out.println("------ Nuevo origen de fabricación ------");
                        mostrarOpciones(OrigenFabricacion.values());
                        System.out.print("Elija una opción: ");
                        int opcionOrigen = scanner.nextInt();
                        OrigenFabricacion nuevoOrigen = OrigenFabricacion.values()[opcionOrigen - 1];
                        producto.setOrigenFabricacion(nuevoOrigen);
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        break;
                    case 4:
                        System.out.println("------ Nueva categoría ------");
                        mostrarOpciones(Categoria.values());
                        System.out.print("Elija una opción: ");
                        int opcionCategoria = scanner.nextInt();
                        Categoria nuevaCategoria = Categoria.values()[opcionCategoria - 1];
                        producto.setCategoria(nuevaCategoria);
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        break;
                    default:
                        System.out.println("Opción no válida. No se realizaron modificaciones.");
                }
                System.out.println("Producto modificado exitosamente.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún producto con el código proporcionado.");
        }
    }

    private static void mostrarOpciones(Enum<?>[] opciones) {
        int i = 1;
        for (Enum<?> opcion : opciones) {
            System.out.println(i + " - " + opcion);
            i++;
        }
    }
}