package com.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Clase base que representa un libro en la biblioteca.
 * Implementa características comunes a todos los tipos de libros.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public abstract class Libro {
    protected String isbn;
    protected String titulo;
    protected String autor;
    protected LocalDate fechaPublicacion;
    protected boolean disponible;
    protected String categoria;

    /**
     * Constructor de la clase Libro
     * @param isbn Identificador único del libro
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param fechaPublicacion Fecha de publicación
     * @param categoria Categoría o género del libro
     */
    public Libro(String isbn, String titulo, String autor, LocalDate fechaPublicacion, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.disponible = true;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases
     * para mostrar información específica del tipo de libro
     */
    public abstract void mostrarDetalles();

    /**
     * Método abstracto para calcular el tiempo máximo de préstamo
     * según el tipo de libro
     * @return días máximos de préstamo
     */
    public abstract int getDiasMaximoPrestamo();

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Disponible: %s",
                isbn, titulo, autor, disponible ? "Sí" : "No");
    }
}