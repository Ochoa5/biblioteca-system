package com.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Subclase que representa un libro físico.
 * Hereda de la clase Libro y añade atributos específicos.
 * Demuestra el concepto de HERENCIA.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class LibroFisico extends Libro {
    private String ubicacionEstante;
    private int numeroPaginas;
    private String editorial;

    /**
     * Constructor de LibroFisico
     * @param isbn Identificador único
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param fechaPublicacion Fecha de publicación
     * @param categoria Categoría del libro
     * @param ubicacionEstante Ubicación física en la biblioteca
     * @param numeroPaginas Número de páginas del libro
     * @param editorial Editorial que publicó el libro
     */
    public LibroFisico(String isbn, String titulo, String autor, LocalDate fechaPublicacion,
                       String categoria, String ubicacionEstante, int numeroPaginas, String editorial) {
        super(isbn, titulo, autor, fechaPublicacion, categoria);
        this.ubicacionEstante = ubicacionEstante;
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("📚 LIBRO FÍSICO");
        System.out.println("═══════════════════════════════════════");
        System.out.println("ISBN: " + isbn);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Categoría: " + categoria);
        System.out.println("Páginas: " + numeroPaginas);
        System.out.println("Editorial: " + editorial);
        System.out.println("Ubicación: " + ubicacionEstante);
        System.out.println("Estado: " + (disponible ? "Disponible ✓" : "Prestado ✗"));
        System.out.println("═══════════════════════════════════════");
    }

    @Override
    public int getDiasMaximoPrestamo() {
        return 14; // 14 días para libros físicos
    }

    // Getters específicos
    public String getUbicacionEstante() {
        return ubicacionEstante;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getEditorial() {
        return editorial;
    }
}