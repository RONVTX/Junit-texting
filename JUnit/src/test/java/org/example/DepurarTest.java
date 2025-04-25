package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
class DepurarTest {

    @Test
    void validarFechaNacimiento() {

            // VALIDO
            assertTrue(Depurar.validarFechaNacimiento("15-08-1995"));
            assertTrue(Depurar.validarFechaNacimiento("29-02-2020")); // Año bisiesto

            //NO VALIDO
            assertFalse(Depurar.validarFechaNacimiento("31-04-2000")); // Abril tiene 30 días
            assertFalse(Depurar.validarFechaNacimiento("32-01-2000")); // Día inválido
            assertFalse(Depurar.validarFechaNacimiento("01-13-2000")); // Mes inválido
            assertFalse(Depurar.validarFechaNacimiento("01-01--2000")); // Año negativo
            assertFalse(Depurar.validarFechaNacimiento("01-01-3000")); // Fecha futura
            assertFalse(Depurar.validarFechaNacimiento("01/01/2000")); // Formato incorrecto
            assertFalse(Depurar.validarFechaNacimiento("texto-cualquiera")); // Texto sin formato

    }

    @Test
    void solicitarNumeroEntero() {
        String input = "abc\n-5\n0\n10\n"; // Prueba con entrada inválida, negativa, cero y válida
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        int resultado = Depurar.solicitarNumeroEntero(scanner, "Ingrese un número: ");
        assertEquals(10, resultado); // Debe devolver el primer número positivo válido
    }

    @Test
    void solicitarTexto() {
        String input = "\n   \nHola mundo\n"; // Vacío, espacios, y finalmente un texto válido
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        String resultado = Depurar.solicitarTexto(scanner, "Ingrese texto: ");
        assertEquals("Hola mundo", resultado); // Debe devolver el primer texto no vacío
    }

    @Test
    void obtenerCantidadValida() {
        String input = "texto\n-5.5\n0.0\n25.75\n"; // Inválido, negativo, cero válido, positivo
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        double resultado = Depurar.obtenerCantidadValida(scanner, "Ingrese cantidad: ");
        assertEquals(0.0, resultado); // Debe aceptar 0.0 como válido
    }
}
