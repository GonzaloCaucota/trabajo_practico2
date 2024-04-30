package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
    public static void main(String[] args) {
        ArrayList<Efemeride> efemerides = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú de acciones:");
            System.out.println("1 - Crear efeméride");
            System.out.println("2 - Mostrar efemérides");
            System.out.println("3 - Eliminar efeméride");
            System.out.println("4 - Modificar efeméride");
            System.out.println("5 - Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        crearEfemeride(efemerides, scanner);
                        break;
                    case 2:
                        mostrarEfemerides(efemerides);
                        break;
                    case 3:
                        eliminarEfemeride(efemerides, scanner);
                        break;
                    case 4:
                        modificarEfemeride(efemerides, scanner);
                        break;
                    case 5:
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
        } while (opcion != 5);
    }

    private static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        System.out.println("\nCreación de Efeméride:");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el número del mes (1-12): ");
        int numeroMes = scanner.nextInt();
        while (numeroMes < 1 || numeroMes > 12) {
            System.out.print("Ingrese un número válido para el mes (1-12): ");
            numeroMes = scanner.nextInt();
        }
        Mes mes = Mes.values()[numeroMes - 1];
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Ingrese el día: ");
        int dia = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Ingrese el detalle: ");
        String detalle = scanner.nextLine();

        Efemeride nuevaEfemeride = new Efemeride(codigo, mes, dia, detalle);
        efemerides.add(nuevaEfemeride);
        System.out.println("Efeméride creada exitosamente.");
    }

    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        System.out.println("\nEfemérides:");
        for (Efemeride efemeride : efemerides) {
            System.out.println(efemeride);
        }
    }

    private static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        System.out.println("\nEliminar Efeméride:");
        System.out.print("Ingrese el código de la efeméride a eliminar: ");
        String codigo = scanner.nextLine();

        boolean encontrado = false;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                efemerides.remove(efemeride);
                encontrado = true;
                System.out.println("Efeméride eliminada exitosamente.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ninguna efeméride con el código proporcionado.");
        }
    }

    private static void modificarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        System.out.println("\nModificar Efeméride:");
        System.out.print("Ingrese el código de la efeméride a modificar: ");
        String codigo = scanner.nextLine();

        boolean encontrado = false;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                System.out.println("Seleccione el atributo a modificar:");
                System.out.println("1 - Mes");
                System.out.println("2 - Día");
                System.out.println("3 - Detalle");
                System.out.print("Elija una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nuevo número del mes (1-12): ");
                        int numeroMes = scanner.nextInt();
                        while (numeroMes < 1 || numeroMes > 12) {
                            System.out.print("Ingrese un número válido para el mes (1-12): ");
                            numeroMes = scanner.nextInt();
                        }
                        Mes nuevoMes = Mes.values()[numeroMes - 1];
                        efemeride.setMes(nuevoMes);
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo día: ");
                        int nuevoDia = scanner.nextInt();
                        efemeride.setDia(nuevoDia);
                        scanner.nextLine(); // Consumir el salto de línea pendiente
                        break;
                    case 3:
                        System.out.print("Ingrese el nuevo detalle: ");
                        String nuevoDetalle = scanner.nextLine();
                        efemeride.setDetalle(nuevoDetalle);
                        break;
                    default:
                        System.out.println("Opción no válida. No se realizaron modificaciones.");
                }
                System.out.println("Efeméride modificada exitosamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ninguna efeméride con el código proporcionado.");
        }
    }
}