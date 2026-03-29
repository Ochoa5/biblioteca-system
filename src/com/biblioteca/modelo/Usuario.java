package com.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario de la biblioteca.
 * Demuestra COMPOSICIÓN al contener una lista de Préstamos.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private List<Prestamo> historialPrestamos; // COMPOSICIÓN: Usuario "tiene" préstamos

    /**
     * Constructor de Usuario
     * @param id Identificador único del usuario
     * @param nombre Nombre completo
     * @param email Correo electrónico
     * @param telefono Número de teléfono
     */
    public Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.historialPrestamos = new ArrayList<>();
    }

    /**
     * Agrega un préstamo al historial del usuario
     * @param prestamo Préstamo a agregar
     */
    public void agregarPrestamo(Prestamo prestamo) {
        this.historialPrestamos.add(prestamo);
    }

    /**
     * Obtiene los préstamos activos del usuario
     * @return Lista de préstamos activos
     */
    public List<Prestamo> getPrestamosActivos() {
        return historialPrestamos.stream()
                .filter(p -> !p.isDevuelto())
                .toList(); // Uso de Stream y lambda
    }

    /**
     * Cuenta el número de préstamos activos
     * @return Cantidad de préstamos sin devolver
     */
    public long contarPrestamosActivos() {
        return historialPrestamos.stream()
                .filter(p -> !p.isDevuelto())
                .count(); // Uso de Stream y lambda
    }

    /**
     * Muestra la información del usuario
     */
    public void mostrarInformacion() {
        System.out.println("\n👤 Usuario: " + nombre);
        System.out.println("   ID: " + id);
        System.out.println("   Email: " + email);
        System.out.println("   Teléfono: " + telefono);
        System.out.println("   Préstamos activos: " + contarPrestamosActivos());
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Prestamo> getHistorialPrestamos() {
        return new ArrayList<>(historialPrestamos); // Retorna copia defensiva
    }

    @Override
    public String toString() {
        return String.format("Usuario[ID=%s, Nombre=%s, Email=%s]", id, nombre, email);
    }
}