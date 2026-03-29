package com.biblioteca.excepciones;

/**
 * Excepción personalizada que se lanza cuando un libro no está disponible
 * o no se encuentra en el sistema.
 *
 * Demuestra el manejo de EXCEPCIONES PERSONALIZADAS.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class LibroNoDisponibleException extends Exception {

    /**
     * Constructor con mensaje personalizado
     * @param mensaje Mensaje descriptivo del error
     */
    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * @param mensaje Mensaje descriptivo del error
     * @param causa Excepción que causó este error
     */
    public LibroNoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}