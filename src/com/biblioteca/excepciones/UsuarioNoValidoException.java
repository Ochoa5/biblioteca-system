package com.biblioteca.excepciones;

/**
 * Excepción personalizada que se lanza cuando un usuario no es válido
 * o no se encuentra en el sistema.
 *
 * Demuestra el manejo de EXCEPCIONES PERSONALIZADAS.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class UsuarioNoValidoException extends Exception {

    /**
     * Constructor con mensaje personalizado
     * @param mensaje Mensaje descriptivo del error
     */
    public UsuarioNoValidoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * @param mensaje Mensaje descriptivo del error
     * @param causa Excepción que causó este error
     */
    public UsuarioNoValidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}