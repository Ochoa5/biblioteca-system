package com.biblioteca.gestor;

import com.biblioteca.modelo.*;
import com.biblioteca.excepciones.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Clase que gestiona toda la biblioteca.
 * Implementa el patrón SINGLETON para asegurar una única instancia
 * del gestor de la biblioteca en toda la aplicación.
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class BibliotecaManager {
    // SINGLETON: Instancia única y estática
    private static BibliotecaManager instance;

    // Colecciones para almacenar datos
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    // Contadores para generar IDs únicos
    private AtomicInteger contadorPrestamos;

    /**
     * Constructor privado para prevenir instanciación externa.
     * SINGLETON: El constructor debe ser privado.
     */
    private BibliotecaManager() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.contadorPrestamos = new AtomicInteger(1);
    }

    /**
     * Método estático para obtener la única instancia de BibliotecaManager.
     * SINGLETON: Método de acceso a la instancia única.
     * Thread-safe usando sincronización.
     *
     * @return La única instancia de BibliotecaManager
     */
    public static synchronized BibliotecaManager getInstance() {
        if (instance == null) {
            instance = new BibliotecaManager();
        }
        return instance;
    }

    // ==================== GESTIÓN DE LIBROS ====================

    /**
     * Agrega un libro al catálogo
     * @param libro Libro a agregar
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("✓ Libro agregado exitosamente: " + libro.getTitulo());
    }

    /**
     * Busca un libro por ISBN
     * @param isbn ISBN del libro
     * @return Libro encontrado
     * @throws LibroNoDisponibleException si no se encuentra el libro
     */
    public Libro buscarLibroPorIsbn(String isbn) throws LibroNoDisponibleException {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new LibroNoDisponibleException(
                        "No se encontró libro con ISBN: " + isbn));
    }

    /**
     * Filtra libros por categoría usando Streams
     * PROGRAMACIÓN FUNCIONAL: Uso de filter() y collect()
     *
     * @param categoria Categoría a buscar
     * @return Lista de libros de esa categoría
     */
    public List<Libro> filtrarPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los libros disponibles
     * PROGRAMACIÓN FUNCIONAL: Uso de filter()
     *
     * @return Lista de libros disponibles
     */
    public List<Libro> obtenerLibrosDisponibles() {
        return libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());
    }

    /**
     * Busca libros por título (búsqueda parcial)
     * PROGRAMACIÓN FUNCIONAL: Uso de filter()
     *
     * @param titulo Título o parte del título
     * @return Lista de libros encontrados
     */
    public List<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                .filter(libro -> libro.getTitulo().toLowerCase()
                        .contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Muestra todos los libros usando forEach
     * PROGRAMACIÓN FUNCIONAL: Uso de forEach()
     */
    public void mostrarTodosLosLibros() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📚 CATÁLOGO COMPLETO DE LIBROS");
        System.out.println("=".repeat(50));

        if (libros.isEmpty()) {
            System.out.println("No hay libros en el catálogo.");
            return;
        }

        libros.forEach(libro -> {
            System.out.println("\n" + libro);
            System.out.println("   Categoría: " + libro.getCategoria());
        });
    }

    // ==================== GESTIÓN DE USUARIOS ====================

    /**
     * Registra un nuevo usuario
     * @param usuario Usuario a registrar
     */
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("✓ Usuario registrado: " + usuario.getNombre());
    }

    /**
     * Busca un usuario por ID
     * @param id ID del usuario
     * @return Usuario encontrado
     * @throws UsuarioNoValidoException si no se encuentra
     */
    public Usuario buscarUsuarioPorId(String id) throws UsuarioNoValidoException {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UsuarioNoValidoException(
                        "No se encontró usuario con ID: " + id));
    }

    /**
     * Obtiene usuarios con préstamos vencidos
     * PROGRAMACIÓN FUNCIONAL: Uso de filter() y flatMap()
     *
     * @return Lista de usuarios con préstamos vencidos
     */
    public List<Usuario> obtenerUsuariosConPrestamosVencidos() {
        return usuarios.stream()
                .filter(usuario -> usuario.getPrestamosActivos().stream()
                        .anyMatch(Prestamo::estaVencido))
                .collect(Collectors.toList());
    }

    // ==================== GESTIÓN DE PRÉSTAMOS ====================

    /**
     * Realiza un préstamo de libro
     * @param idUsuario ID del usuario
     * @param isbnLibro ISBN del libro
     * @return El préstamo creado
     * @throws LibroNoDisponibleException si el libro no está disponible
     * @throws UsuarioNoValidoException si el usuario no existe
     */
    public Prestamo realizarPrestamo(String idUsuario, String isbnLibro)
            throws LibroNoDisponibleException, UsuarioNoValidoException {

        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Libro libro = buscarLibroPorIsbn(isbnLibro);

        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException(
                    "El libro '" + libro.getTitulo() + "' no está disponible");
        }

        String idPrestamo = "PREST-" + contadorPrestamos.getAndIncrement();
        Prestamo prestamo = new Prestamo(idPrestamo, usuario, libro);

        prestamos.add(prestamo);
        usuario.agregarPrestamo(prestamo);

        System.out.println("✓ Préstamo realizado exitosamente");
        System.out.println("  ID: " + idPrestamo);
        System.out.println("  Devolución esperada: " + prestamo.getFechaDevolucionEsperada());

        return prestamo;
    }

    /**
     * Registra la devolución de un libro
     * @param idPrestamo ID del préstamo
     * @throws Exception si no se encuentra el préstamo
     */
    public void registrarDevolucion(String idPrestamo) throws Exception {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getIdPrestamo().equals(idPrestamo))
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró préstamo con ID: " + idPrestamo));

        if (prestamo.isDevuelto()) {
            throw new Exception("Este préstamo ya fue devuelto anteriormente");
        }

        prestamo.registrarDevolucion();
        System.out.println("✓ Devolución registrada exitosamente");

        if (prestamo.getDiasRetraso() > 0) {
            System.out.println("⚠️  El libro fue devuelto con " +
                    prestamo.getDiasRetraso() + " días de retraso");
        }
    }

    /**
     * Obtiene préstamos activos
     * PROGRAMACIÓN FUNCIONAL: Uso de filter()
     *
     * @return Lista de préstamos activos
     */
    public List<Prestamo> obtenerPrestamosActivos() {
        return prestamos.stream()
                .filter(p -> !p.isDevuelto())
                .collect(Collectors.toList());
    }

    /**
     * Calcula el promedio de días de préstamo
     * PROGRAMACIÓN FUNCIONAL: Uso de mapToInt() y average()
     *
     * @return Promedio de días
     */
    public double calcularPromedioDiasPrestamo() {
        return prestamos.stream()
                .filter(Prestamo::isDevuelto)
                .mapToLong(p -> java.time.temporal.ChronoUnit.DAYS.between(
                        p.getFechaPrestamo(), p.getFechaDevolucionReal()))
                .average()
                .orElse(0.0);
    }

    // ==================== ESTADÍSTICAS ====================

    /**
     * Muestra estadísticas de la biblioteca
     * PROGRAMACIÓN FUNCIONAL: Uso de múltiples operaciones de Stream
     */
    public void mostrarEstadisticas() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 ESTADÍSTICAS DE LA BIBLIOTECA");
        System.out.println("=".repeat(50));

        System.out.println("Total de libros: " + libros.size());
        System.out.println("Libros disponibles: " +
                libros.stream().filter(Libro::isDisponible).count());

        System.out.println("\nTotal de usuarios: " + usuarios.size());
        System.out.println("Usuarios con préstamos activos: " +
                usuarios.stream().filter(u -> u.contarPrestamosActivos() > 0).count());

        System.out.println("\nTotal de préstamos: " + prestamos.size());
        System.out.println("Préstamos activos: " + obtenerPrestamosActivos().size());
        System.out.println("Préstamos vencidos: " +
                prestamos.stream().filter(Prestamo::estaVencido).count());

        System.out.println("\nPromedio días de préstamo: " +
                String.format("%.2f", calcularPromedioDiasPrestamo()));

        // Categoría más popular
        libros.stream()
                .collect(Collectors.groupingBy(Libro::getCategoria, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println("Categoría más popular: " +
                        entry.getKey() + " (" + entry.getValue() + " libros)"));
    }

    // Getters
    public List<Libro> getLibros() {
        return new ArrayList<>(libros);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Prestamo> getPrestamos() {
        return new ArrayList<>(prestamos);
    }
}