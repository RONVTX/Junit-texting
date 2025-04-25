package org.example;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class Depurar {

    // Metodo estático para validar la fecha de nacimiento
    public static boolean validarFechaNacimiento(String fechaNacimiento) {
        try {
            String[] fechaParts = fechaNacimiento.split("-");
            if (fechaParts.length != 3) {
                System.out.println("⚠ Error: El formato debe ser 'dd-MM-yyyy'.");
                return false;
            }
            // Se pide cada parte de la fecha de nacimiento
            int diaNacimiento = Integer.parseInt(fechaParts[0]);
            int mesNacimiento = Integer.parseInt(fechaParts[1]);
            int anoNacimiento = Integer.parseInt(fechaParts[2]);

            // Verificar si el año es negativo
            if (anoNacimiento < 0) {
                System.out.println("⚠ Error: El año no puede ser negativo.");
                return false;
            }

            // Verificar si el mes está en el rango correcto
            if (mesNacimiento < 1 || mesNacimiento > 12) {
                System.out.println("⚠ Error: El mes ingresado no es válido (debe estar entre 1 y 12).");
                return false;
            }

            /*
            Validar días del mes:
            Creamos un array de enteros para almacenar el numero de dias de cada mes (Empieza en 0)
            Year.isLeap es una expresión condicional, que significa:
            Si el año es bisiesto, entonces el valor de febrero (índice 2) será 29.
            Si el año NO es bisiesto, entonces febrero tendrá 28 días.
             */
            int[] diasPorMes = {0, 31, (Year.isLeap(anoNacimiento) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (diaNacimiento < 1 || diaNacimiento > diasPorMes[mesNacimiento]) {
                System.out.println("⚠ Error: El día ingresado no es válido para el mes seleccionado.");
                return false;
            }

            // Crear la fecha y se comprueba si es futura o no
            LocalDate fechaNacimientoDate = LocalDate.of(anoNacimiento, mesNacimiento, diaNacimiento);

            if (fechaNacimientoDate.isAfter(LocalDate.now())) {
                System.out.println("⚠ Error: La fecha no puede ser en el futuro.");
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            System.out.println("⚠ Error: La fecha debe contener solo números y estar en formato 'dd-MM-yyyy'.");
            return false;
        } catch (DateTimeException e) {
            System.out.println("⚠ Error: Fecha inválida. Verifica el día, mes y año.");
            return false;
        }
    }

    // Metodo para solicitar un número entero positivo
    public static int solicitarNumeroEntero(Scanner scanner, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero > 0) {
                    return numero;
                } else {
                    System.out.println("⚠ Error: Debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: Ingrese un número válido.");
            }
        }
    }

    // Metodo para solicitar un texto no vacío
    public static  String solicitarTexto(Scanner scanner, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("⚠ Error: No puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    // Metodo para obtener una cantidad válida evitando que el usuario introduzca letras
    public static double obtenerCantidadValida(Scanner scanner, String mensaje) {
        double cantidad;
        while (true) {
            System.out.print(mensaje);
            try {
                String input = scanner.nextLine().trim(); // Leer toda la línea
                cantidad = Double.parseDouble(input);     // Parsear manualmente
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa. Inténtalo de nuevo.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Introduce un número válido.");
            }
        }
        return cantidad;
    }


}
