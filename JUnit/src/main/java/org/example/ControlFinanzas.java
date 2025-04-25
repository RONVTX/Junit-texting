package org.example;

import java.util.Scanner;

public class ControlFinanzas {
    public static String usuario = null;
    public static double saldo = 0.0;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Finanzas ---");
            System.out.println("1. Introduce nombre de usuario");
            System.out.println("2. Introducir ingresos");
            System.out.println("3. Introducir gasto");
            System.out.println("4. Mostrar saldo");
            System.out.println("5. Salir");
            opcion = Depurar.solicitarNumeroEntero(sc, "Elige una opcion: ");
            switch (opcion) {
                case 1:
                    introducirUsuario();
                    break;
                case 2:
                    introducirIngreso();
                    break;
                case 3:
                    introducirGasto();
                    break;
                case 4:
                    mostrarSaldo();
                    break;
                case 5:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 5);
        sc.close();
    }

    public static void introducirUsuario() {
        usuario = Depurar.solicitarTexto(sc, "Introduce el nombre de usuario: ");
        System.out.println("Bienvenido/a, " + usuario + "!");
    }

    public static boolean verificarUsuario() {
        if (usuario == null) {
            System.out.println("Debe introducir primero el nombre de usuario (opción 1).");
            return false;
        }
        return true;
    }

    public static void introducirIngreso() {
        if (!verificarUsuario()) return;
        double ingreso = Depurar.obtenerCantidadValida(sc, usuario + ", introduzca su ingreso (Nómina): ");
        saldo += ingreso;
        System.out.println("Ingreso de " + ingreso + "€ añadido correctamente. Saldo actual: " + saldo + "€.");
    }

    public static void introducirGasto() {
        if (!verificarUsuario()) return;
        System.out.println(usuario + ", seleccione el tipo de gasto:");
        System.out.println("1. Vacaciones");
        System.out.println("2. Alquiler");
        System.out.println("3. Vicios variados");
        int tipoGasto = Depurar.solicitarNumeroEntero(sc, "Elija una opción (1-3): ");
        String concepto = null;

        switch (tipoGasto) {
            case 1:
                concepto = "Vacaciones";
                break;
            case 2:
                concepto = "Alquiler";
                break;
            case 3:
                concepto = "Vicios variados";
                break;
            default:
                System.out.println("Opción inválida de gasto.");
                break;
        }

        if (concepto != null) {
            double gasto = Depurar.obtenerCantidadValida(sc, "Introduce el monto del gasto (" + concepto + "): ");
            if (gasto > saldo) {
                System.out.println("No tiene saldo suficiente para realizar este gasto.");
            } else {
                saldo -= gasto;
                System.out.println("Su gasto ha sido de " + gasto + "€ en " + concepto + ". Saldo actual: " + saldo + "€.");
            }
        }
    }

    public static void mostrarSaldo() {
        if (!verificarUsuario()) return;
        System.out.println(usuario + ", su saldo actual es: " + saldo + "€.");
    }
}

