package com.biblioteca.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un préstamo de libro.
 * Demuestra ASOCIACIÓN entre Usuario y Libro.
 * Un préstamo "usa" un Usuario y un Libro sin ser dueño de ellos.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class Prestamo {
    private String idPrestamo;
    private Usuario usuario; // ASOCIACIÓN con Usuario
    private Libro libro;     // ASOCIACIÓN con Libro
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private boolean devuelto;

    /**
     * Constructor de Préstamo
     * @param idPrestamo Identificador único del préstamo
     * @param usuario Usuario que realiza el préstamo
     * @param libro Libro que se presta
     */
    public Prestamo(String idPrestamo, Usuario usuario, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionEsperada = fechaPrestamo.plusDays(libro.getDiasMaximoPrestamo());
        this.devuelto = false;

        // Marcar el libro como no disponible
        libro.setDisponible(false);
    }

    /**
     * Registra la devolución del libro
     */
    public void registrarDevolucion() {
        this.fechaDevolucionReal = LocalDate.now();
        this.devuelto = true;
        this.libro.setDisponible(true);
    }

    /**
     * Calcula si el préstamo está vencido
     * @return true si está vencido, false en caso contrario
     */
    public boolean estaVencido() {
        if (devuelto) {
            return false;
        }
        return LocalDate.now().isAfter(fechaDevolucionEsperada);
    }

    /**
     * Calcula los días de retraso
     * @return Número de días de retraso
     */
    public long getDiasRetraso() {
        if (!estaVencido()) {
            return 0;
        }
        return ChronoUnit.DAYS.between(fechaDevolucionEsperada, LocalDate.now());
    }

    /**
     * Muestra los detalles del préstamo
     */
    public void mostrarDetalles() {
        System.out.println("\n📋 Préstamo ID: " + idPrestamo);
        System.out.println("   Usuario: " + usuario.getNombre());
        System.out.println("   Libro: " + libro.getTitulo());
        System.out.println("   Fecha préstamo: " + fechaPrestamo);
        System.out.println("   Fecha devolución esperada: " + fechaDevolucionEsperada);
        System.out.println("   Estado: " + (devuelto ? "Devuelto ✓" : "Activo"));

        if (estaVencido()) {
            System.out.println("   ⚠️  VENCIDO - Días de retraso: " + getDiasRetraso());
        }
    }

    // Getters
    public String getIdPrestamo() {
        return idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    @Override
    public String toString() {
        return String.format("Préstamo[ID=%s, Usuario=%s, Libro=%s, Estado=%s]",
                idPrestamo, usuario.getNombre(), libro.getTitulo(),
                devuelto ? "Devuelto" : "Activo");
    }
}