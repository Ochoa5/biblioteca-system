package com.biblioteca.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Clase utilitaria para validaciones de entrada.
 * Centraliza la lógica de validación y usa excepciones para control de errores.
 *
 * Demuestra VALIDACIONES y MANEJO DE ERRORES.
 *
 * @author Sistema Biblioteca
 * @version 1.0
 */
public class Validador {

    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private static final Pattern PATRON_ISBN =
            Pattern.compile("^(978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}$");

    /**
     * Valida que una cadena no sea nula ni vacía
     * @param valor Cadena a validar
     * @param nombreCampo Nombre del campo para mensajes de error
     * @throws IllegalArgumentException si la validación falla
     */
    public static void validarNoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "❌ Error: " + nombreCampo + " no puede estar vacío");
        }
    }

    /**
     * Valida formato de email
     * @param email Email a validar
     * @throws IllegalArgumentException si el formato es inválido
     */
    public static void validarEmail(String email) {
        validarNoVacio(email, "Email");

        if (!PATRON_EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException(
                    "❌ Error: Formato de email inválido: " + email);
        }
    }

    /**
     * Valida formato de ISBN
     * @param isbn ISBN a validar
     * @throws IllegalArgumentException si el formato es inválido
     */
    public static void validarISBN(String isbn) {
        validarNoVacio(isbn, "ISBN");

        if (!PATRON_ISBN.matcher(isbn).matches()) {
            throw new IllegalArgumentException(
                    "❌ Error: Formato de ISBN inválido. Debe ser: 978-X-XXX-XXXXX-X");
        }
    }

    /**
     * Valida que un número sea positivo
     * @param numero Número a validar
     * @param nombreCampo Nombre del campo
     * @throws IllegalArgumentException si el número no es positivo
     */
    public static void validarPositivo(int numero, String nombreCampo) {
        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "❌ Error: " + nombreCampo + " debe ser un número positivo");
        }
    }

    /**
     * Valida que un número decimal sea positivo
     * @param numero Número a validar
     * @param nombreCampo Nombre del campo
     * @throws IllegalArgumentException si el número no es positivo
     */
    public static void validarPositivo(double numero, String nombreCampo) {
        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "❌ Error: " + nombreCampo + " debe ser un número positivo");
        }
    }

    /**
     * Valida y parsea una fecha en formato dd/MM/yyyy
     * @param fechaStr Fecha en formato texto
     * @return LocalDate parseado
     * @throws IllegalArgumentException si el formato es inválido
     */
    public static LocalDate validarYParsearFecha(String fechaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            // Validar que no sea fecha futura
            if (fecha.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "❌ Error: La fecha no puede ser futura");
            }

            return fecha;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "❌ Error: Formato de fecha inválido. Use dd/MM/yyyy (ej: 15/03/2020)");
        }
    }

    /**
     * Valida que un valor esté dentro de un rango
     * @param valor Valor a validar
     * @param min Valor mínimo
     * @param max Valor máximo
     * @param nombreCampo Nombre del campo
     * @throws IllegalArgumentException si está fuera de rango
     */
    public static void validarRango(int valor, int min, int max, String nombreCampo) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException(
                    String.format("❌ Error: %s debe estar entre %d y %d",
                            nombreCampo, min, max));
        }
    }

    /**
     * Valida una opción de menú
     * @param opcion Opción seleccionada
     * @param minimo Opción mínima válida
     * @param maximo Opción máxima válida
     * @throws IllegalArgumentException si la opción es inválida
     */
    public static void validarOpcionMenu(int opcion, int minimo, int maximo) {
        if (opcion < minimo || opcion > maximo) {
            throw new IllegalArgumentException(
                    String.format("❌ Error: Opción inválida. Debe estar entre %d y %d",
                            minimo, maximo));
        }
    }

    /**
     * Intenta parsear un entero desde un String
     * @param valor String a parsear
     * @param nombreCampo Nombre del campo
     * @return Entero parseado
     * @throws IllegalArgumentException si no se puede parsear
     */
    public static int parsearEntero(String valor, String nombreCampo) {
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "❌ Error: " + nombreCampo + " debe ser un número entero válido");
        }
    }

    /**
     * Intenta parsear un double desde un String
     * @param valor String a parsear
     * @param nombreCampo Nombre del campo
     * @return Double parseado
     * @throws IllegalArgumentException si no se puede parsear
     */
    public static double parsearDouble(String valor, String nombreCampo) {
        try {
            return Double.parseDouble(valor.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "❌ Error: " + nombreCampo + " debe ser un número decimal válido");
        }
    }
}