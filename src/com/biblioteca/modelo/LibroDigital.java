package com.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Subclase que representa un libro digital o eBook.
 * Hereda de la clase Libro y añade atributos específicos para formato digital.
 * Demuestra el concepto de HERENCIA.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class LibroDigital extends Libro {
    private String formatoArchivo; // PDF, EPUB, MOBI
    private double tamañoMB;
    private String urlDescarga;
    private boolean requiereDRM;

    /**
     * Constructor de LibroDigital
     * @param isbn Identificador único
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param fechaPublicacion Fecha de publicación
     * @param categoria Categoría del libro
     * @param formatoArchivo Formato digital (PDF, EPUB, etc.)
     * @param tamañoMB Tamaño del archivo en megabytes
     * @param urlDescarga URL para descargar el libro
     * @param requiereDRM Si requiere gestión de derechos digitales
     */
    public LibroDigital(String isbn, String titulo, String autor, LocalDate fechaPublicacion,
                        String categoria, String formatoArchivo, double tamañoMB,
                        String urlDescarga, boolean requiereDRM) {
        super(isbn, titulo, autor, fechaPublicacion, categoria);
        this.formatoArchivo = formatoArchivo;
        this.tamañoMB = tamañoMB;
        this.urlDescarga = urlDescarga;
        this.requiereDRM = requiereDRM;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("💻 LIBRO DIGITAL");
        System.out.println("═══════════════════════════════════════");
        System.out.println("ISBN: " + isbn);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Categoría: " + categoria);
        System.out.println("Formato: " + formatoArchivo);
        System.out.println("Tamaño: " + tamañoMB + " MB");
        System.out.println("DRM: " + (requiereDRM ? "Sí" : "No"));
        System.out.println("Estado: " + (disponible ? "Disponible ✓" : "Prestado ✗"));
        System.out.println("═══════════════════════════════════════");
    }

    @Override
    public int getDiasMaximoPrestamo() {
        return 7; // 7 días para libros digitales
    }

    // Getters específicos
    public String getFormatoArchivo() {
        return formatoArchivo;
    }

    public double getTamañoMB() {
        return tamañoMB;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

    public boolean isRequiereDRM() {
        return requiereDRM;
    }
}