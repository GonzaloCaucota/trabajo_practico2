package ar.edu.unju.fi.ejercicio5.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Categoria;
import ar.edu.unju.fi.ejercicio1.model.OrigenFabricacion;
import ar.edu.unju.fi.ejercicio1.model.Producto;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = preCargarProductos();

        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1 - Mostrar productos");
            System.out.println("2 - Realizar compra");
            System.out.println("3 - Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        mostrarProductos(productos);
                        break;
                    case 2:
                        realizarCompra(productos, scanner);
                        break;
                    case 3:
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
        } while (opcion != 3);
    }

    private static ArrayList<Producto> preCargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        // Agregar algunos productos de ejemplo
        productos.add(new Producto("001", "Smartphone", 500.0, OrigenFabricacion.CHINA, Categoria.TELEFONIA));
        productos.add(new Producto("002", "Laptop", 1200.0, OrigenFabricacion.ARGENTINA, Categoria.INFORMATICA));
        productos.add(new Producto("003", "Licuadora", 50.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR));
        return productos;
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("\nProductos disponibles:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void realizarCompra(ArrayList<Producto> productos, Scanner scanner) {
        ArrayList<Producto> productosComprados = new ArrayList<>();
        double totalCompra = 0;

        System.out.println("\nSeleccione los productos que desea comprar:");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            System.out.println((i+1) + " - " + producto.getDescripcion() + " - $" + producto.getPrecioUnitario());
        }
        System.out.println("0 - Finalizar selección");

        int opcionProducto;
        do {
            System.out.print("Elija un producto: ");
            opcionProducto = Integer.parseInt(scanner.nextLine());

            if (opcionProducto >= 1 && opcionProducto <= productos.size()) {
                Producto productoSeleccionado = productos.get(opcionProducto - 1);
                productosComprados.add(productoSeleccionado);
                totalCompra += productoSeleccionado.getPrecioUnitario();
                System.out.println("Producto agregado: " + productoSeleccionado.getDescripcion());
            } else if (opcionProducto == 0) {
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (true);

        System.out.println("Total de la compra: $" + totalCompra);

        int opcionPago;
        do {
            System.out.println("\nSeleccione el método de pago:");
            System.out.println("1 - Pago en efectivo");
            System.out.println("2 - Pago con tarjeta");
            System.out.print("Elija una opción: ");
            opcionPago = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            if (opcionPago == 1) {
                System.out.println("Realizando pago en efectivo...");
                double montoTotalEfectivo = totalCompra * 0.90; // Aplicar descuento del 10%
                System.out.println("Monto total a pagar en efectivo: $" + montoTotalEfectivo);
                // Lógica para procesar el pago en efectivo
                System.out.println("¡Pago en efectivo realizado con éxito!");
            } else if (opcionPago == 2) {
                System.out.println("Ingrese el número de tarjeta:");
                String numeroTarjeta = scanner.nextLine();
                System.out.println("Realizando pago con tarjeta...");
                double montoTotalTarjeta = totalCompra * 1.15; // Aplicar recargo del 15%
                System.out.println("Monto total a pagar con tarjeta: $" + montoTotalTarjeta);
                // Lógica para procesar el pago con tarjeta
                System.out.println("¡Pago con tarjeta realizado con éxito!");
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionPago != 1 && opcionPago != 2);
    }
}