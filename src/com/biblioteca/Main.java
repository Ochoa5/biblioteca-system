package com.biblioteca;

import com.biblioteca.gestor.BibliotecaManager;
import com.biblioteca.modelo.*;
import com.biblioteca.factory.*;
import com.biblioteca.estrategia.*;
import com.biblioteca.excepciones.*;
import com.biblioteca.util.Validador;

import java.time.LocalDate;
import java.util.*;

/**
 * Clase principal del Sistema de Gestión de Biblioteca.
 *
 * DEMUESTRA:
 * 1. Clases y herencia (Libro, LibroFisico, LibroDigital)
 * 2. Relaciones (Asociación, Composición, Agregación)
 * 3. Patrón Singleton (BibliotecaManager)
 * 4. Patrón Factory Method (LibroFactory)
 * 5. Patrón Strategy (EstrategiaBusqueda)
 * 6. Programación Funcional (Streams, Lambdas)
 * 7. Manejo de Excepciones (try-catch-finally)
 * 8. Validaciones de entrada
 *
 * @author Sistema Biblioteca
 * @version 1.0
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static BibliotecaManager biblioteca;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE BIBLIOTECA - JAVA        ║");
        System.out.println("║   Proyecto Académico Completo                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // PATRÓN SINGLETON: Obtener instancia única del gestor
        biblioteca = BibliotecaManager.getInstance();

        // Cargar datos de ejemplo
        cargarDatosEjemplo();

        // Menú principal
        boolean salir = false;
        while (!salir) {
            try {
                mostrarMenuPrincipal();
                int opcion = leerOpcion(0, 10);

                switch (opcion) {
                    case 1 -> agregarLibro();
                    case 2 -> registrarUsuario();
                    case 3 -> realizarPrestamo();
                    case 4 -> devolverLibro();
                    case 5 -> buscarLibros();
                    case 6 -> mostrarLibrosDisponibles();
                    case 7 -> mostrarEstadisticas();
                    case 8 -> demonstrarProgramacionFuncional();
                    case 9 -> demonstrarPatrones();
                    case 10 -> mostrarDocumentacion();
                    case 0 -> {
                        System.out.println("\n✓ Gracias por usar el sistema. ¡Hasta pronto!");
                        salir = true;
                    }
                }
            } catch (Exception e) {
                System.err.println("\n⚠️  Error inesperado: " + e.getMessage());
                System.out.println("Presione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Muestra el menú principal del sistema
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                    MENÚ PRINCIPAL");
        System.out.println("═".repeat(60));
        System.out.println("1. 📚 Agregar nuevo libro");
        System.out.println("2. 👤 Registrar nuevo usuario");
        System.out.println("3. 📖 Realizar préstamo");
        System.out.println("4. 📥 Devolver libro");
        System.out.println("5. 🔍 Buscar libros (Strategy Pattern)");
        System.out.println("6. 📋 Ver libros disponibles");
        System.out.println("7. 📊 Ver estadísticas");
        System.out.println("8. 🚀 Demostración: Programación Funcional");
        System.out.println("9. 🎯 Demostración: Patrones de Diseño");
        System.out.println("10. 📖 Ver documentación del proyecto");
        System.out.println("0. ❌ Salir");
        System.out.println("═".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    /**
     * MANEJO DE ERRORES: Lee y valida la opción del menú
     */
    private static int leerOpcion(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int opcion = Validador.parsearEntero(input, "Opción");
                Validador.validarOpcionMenu(opcion, min, max);
                return opcion;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.out.print("Intente nuevamente: ");
            }
        }
    }

    /**
     * PATRÓN FACTORY METHOD: Agregar nuevo libro usando Factory
     */
    private static void agregarLibro() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("          AGREGAR NUEVO LIBRO (Factory Pattern)");
        System.out.println("═".repeat(60));

        try {
            System.out.println("Seleccione tipo de libro:");
            System.out.println("1. Libro Físico");
            System.out.println("2. Libro Digital");
            System.out.print("Opción: ");

            int tipoOpcion = leerOpcion(1, 2);
            TipoLibro tipo = (tipoOpcion == 1) ? TipoLibro.FISICO : TipoLibro.DIGITAL;

            // Validaciones con try-catch
            System.out.print("ISBN (formato: 978-X-XXX-XXXXX-X): ");
            String isbn = scanner.nextLine();
            Validador.validarISBN(isbn);

            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            Validador.validarNoVacio(titulo, "Título");

            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            Validador.validarNoVacio(autor, "Autor");

            System.out.print("Fecha de publicación (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            LocalDate fecha = Validador.validarYParsearFecha(fechaStr);

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();
            Validador.validarNoVacio(categoria, "Categoría");

            Libro libro;

            if (tipo == TipoLibro.FISICO) {
                System.out.print("Ubicación en estante: ");
                String ubicacion = scanner.nextLine();

                System.out.print("Número de páginas: ");
                int paginas = Validador.parsearEntero(scanner.nextLine(), "Páginas");
                Validador.validarPositivo(paginas, "Número de páginas");

                System.out.print("Editorial: ");
                String editorial = scanner.nextLine();

                // USO DEL FACTORY
                libro = LibroFactory.crearLibroFisico(isbn, titulo, autor, fecha,
                        categoria, ubicacion, paginas, editorial);
            } else {
                System.out.print("Formato (PDF/EPUB/MOBI): ");
                String formato = scanner.nextLine().toUpperCase();

                System.out.print("Tamaño en MB: ");
                double tamaño = Validador.parsearDouble(scanner.nextLine(), "Tamaño");
                Validador.validarPositivo(tamaño, "Tamaño");

                System.out.print("URL de descarga: ");
                String url = scanner.nextLine();

                System.out.print("¿Requiere DRM? (s/n): ");
                boolean drm = scanner.nextLine().toLowerCase().startsWith("s");

                // USO DEL FACTORY
                libro = LibroFactory.crearLibroDigital(isbn, titulo, autor, fecha,
                        categoria, formato, tamaño, url, drm);
            }

            biblioteca.agregarLibro(libro);
            libro.mostrarDetalles();

        } catch (IllegalArgumentException e) {
            System.err.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n❌ Error al agregar libro: " + e.getMessage());
        } finally {
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * Registrar nuevo usuario con validaciones
     */
    private static void registrarUsuario() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("              REGISTRAR NUEVO USUARIO");
        System.out.println("═".repeat(60));

        try {
            System.out.print("ID de usuario: ");
            String id = scanner.nextLine();
            Validador.validarNoVacio(id, "ID");

            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            Validador.validarNoVacio(nombre, "Nombre");

            System.out.print("Email: ");
            String email = scanner.nextLine();
            Validador.validarEmail(email);

            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            Validador.validarNoVacio(telefono, "Teléfono");

            Usuario usuario = new Usuario(id, nombre, email, telefono);
            biblioteca.registrarUsuario(usuario);
            usuario.mostrarInformacion();

        } catch (IllegalArgumentException e) {
            System.err.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n❌ Error al registrar usuario: " + e.getMessage());
        } finally {
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * MANEJO DE EXCEPCIONES PERSONALIZADAS: Realizar préstamo
     */
    private static void realizarPrestamo() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                 REALIZAR PRÉSTAMO");
        System.out.println("═".repeat(60));

        try {
            System.out.print("ID del usuario: ");
            String idUsuario = scanner.nextLine();

            System.out.print("ISBN del libro: ");
            String isbn = scanner.nextLine();

            // Esto puede lanzar excepciones personalizadas
            Prestamo prestamo = biblioteca.realizarPrestamo(idUsuario, isbn);
            prestamo.mostrarDetalles();

        } catch (UsuarioNoValidoException e) {
            System.err.println("\n❌ ERROR DE USUARIO: " + e.getMessage());
            System.out.println("💡 Sugerencia: Verifique que el ID sea correcto o registre al usuario.");
        } catch (LibroNoDisponibleException e) {
            System.err.println("\n❌ ERROR DE DISPONIBILIDAD: " + e.getMessage());
            System.out.println("💡 Sugerencia: Consulte cuándo estará disponible el libro.");
        } catch (Exception e) {
            System.err.println("\n❌ Error inesperado: " + e.getMessage());
        } finally {
            // FINALLY: Se ejecuta siempre
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * Registrar devolución de libro
     */
    private static void devolverLibro() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                DEVOLVER LIBRO");
        System.out.println("═".repeat(60));

        // Mostrar préstamos activos
        List<Prestamo> activos = biblioteca.obtenerPrestamosActivos();

        if (activos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nPréstamos activos:");
        activos.forEach(Prestamo::mostrarDetalles);

        try {
            System.out.print("\nID del préstamo a devolver: ");
            String idPrestamo = scanner.nextLine();

            biblioteca.registrarDevolucion(idPrestamo);

        } catch (Exception e) {
            System.err.println("\n❌ Error: " + e.getMessage());
        } finally {
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * PATRÓN STRATEGY: Buscar libros con diferentes estrategias
     */
    private static void buscarLibros() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("          BUSCAR LIBROS (Strategy Pattern)");
        System.out.println("═".repeat(60));

        try {
            System.out.println("Seleccione estrategia de búsqueda:");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Buscar por Autor");
            System.out.print("Opción: ");

            int opcion = leerOpcion(1, 2);

            // Seleccionar estrategia dinámicamente
            EstrategiaBusqueda estrategia;
            if (opcion == 1) {
                estrategia = new BusquedaPorTitulo();
            } else {
                estrategia = new BusquedaPorAutor();
            }

            System.out.print("Ingrese criterio de búsqueda: ");
            String criterio = scanner.nextLine();
            Validador.validarNoVacio(criterio, "Criterio");

            // Ejecutar búsqueda con la estrategia seleccionada
            List<Libro> resultados = estrategia.buscar(biblioteca.getLibros(), criterio);

            System.out.println("\n📋 Resultados encontrados: " + resultados.size());

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros con ese criterio.");
            } else {
                // PROGRAMACIÓN FUNCIONAL: forEach
                resultados.forEach(libro -> {
                    System.out.println("\n" + "-".repeat(50));
                    libro.mostrarDetalles();
                });
            }

        } catch (Exception e) {
            System.err.println("\n❌ Error en búsqueda: " + e.getMessage());
        } finally {
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * Mostrar libros disponibles
     */
    private static void mostrarLibrosDisponibles() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("              LIBROS DISPONIBLES");
        System.out.println("═".repeat(60));

        List<Libro> disponibles = biblioteca.obtenerLibrosDisponibles();

        if (disponibles.isEmpty()) {
            System.out.println("No hay libros disponibles actualmente.");
        } else {
            System.out.println("Total de libros disponibles: " + disponibles.size());
            disponibles.forEach(libro -> {
                System.out.println("\n" + libro);
            });
        }

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Mostrar estadísticas usando el Singleton
     */
    private static void mostrarEstadisticas() {
        biblioteca.mostrarEstadisticas();
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * DEMOSTRACIÓN: Programación Funcional con Streams y Lambdas
     */
    private static void demonstrarProgramacionFuncional() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("    DEMOSTRACIÓN: PROGRAMACIÓN FUNCIONAL");
        System.out.println("═".repeat(60));

        List<Libro> libros = biblioteca.getLibros();

        // 1. FILTER: Filtrar libros disponibles
        System.out.println("\n1️⃣  USO DE filter() - Libros disponibles:");
        long disponibles = libros.stream()
                .filter(Libro::isDisponible)
                .count();
        System.out.println("   Cantidad: " + disponibles);

        // 2. MAP: Obtener solo los títulos
        System.out.println("\n2️⃣  USO DE map() - Lista de títulos:");
        libros.stream()
                .map(Libro::getTitulo)
                .limit(5)
                .forEach(titulo -> System.out.println("   • " + titulo));

        // 3. FILTER + MAP: Autores de libros disponibles (sin duplicados)
        System.out.println("\n3️⃣  USO DE filter() + map() + distinct():");
        System.out.println("   Autores con libros disponibles:");
        libros.stream()
                .filter(Libro::isDisponible)
                .map(Libro::getAutor)
                .distinct()
                .forEach(autor -> System.out.println("   • " + autor));

        // 4. COLLECT: Agrupar por categoría
        System.out.println("\n4️⃣  USO DE collect() + groupingBy():");
        System.out.println("   Libros por categoría:");
        libros.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Libro::getCategoria,
                        java.util.stream.Collectors.counting()))
                .forEach((cat, count) ->
                        System.out.println("   • " + cat + ": " + count + " libro(s)"));

        // 5. SORTED: Ordenar por título
        System.out.println("\n5️⃣  USO DE sorted():");
        System.out.println("   Primeros 3 libros ordenados alfabéticamente:");
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .limit(3)
                .forEach(libro -> System.out.println("   • " + libro.getTitulo()));

        // 6. anyMatch, allMatch
        System.out.println("\n6️⃣  USO DE anyMatch() y allMatch():");
        boolean hayDigitales = libros.stream()
                .anyMatch(libro -> libro instanceof LibroDigital);
        System.out.println("   ¿Hay libros digitales? " + (hayDigitales ? "Sí ✓" : "No ✗"));

        // 7. Expresiones Lambda personalizadas
        System.out.println("\n7️⃣  EXPRESIONES LAMBDA personalizadas:");
        libros.stream()
                .filter(libro -> libro.getTitulo().length() > 10)
                .forEach(libro -> System.out.println(
                        "   • Título largo: " + libro.getTitulo() +
                                " (" + libro.getTitulo().length() + " caracteres)"));

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * DEMOSTRACIÓN: Patrones de Diseño implementados
     */
    private static void demonstrarPatrones() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("       DEMOSTRACIÓN: PATRONES DE DISEÑO");
        System.out.println("═".repeat(60));

        // 1. SINGLETON
        System.out.println("\n1️⃣  PATRÓN SINGLETON");
        System.out.println("   " + "-".repeat(50));
        BibliotecaManager instancia1 = BibliotecaManager.getInstance();
        BibliotecaManager instancia2 = BibliotecaManager.getInstance();
        System.out.println("   ¿Son la misma instancia? " + (instancia1 == instancia2 ? "Sí ✓" : "No ✗"));
        System.out.println("   Hash instancia 1: " + System.identityHashCode(instancia1));
        System.out.println("   Hash instancia 2: " + System.identityHashCode(instancia2));
        System.out.println("   📌 Garantiza una única instancia del gestor en toda la aplicación");

        // 2. FACTORY METHOD
        System.out.println("\n2️⃣  PATRÓN FACTORY METHOD");
        System.out.println("   " + "-".repeat(50));
        System.out.println("   Creando libro usando Factory...");
        Libro libroFactory = LibroFactory.crearLibroEjemplo(TipoLibro.FISICO);
        System.out.println("   ✓ Libro creado: " + libroFactory.getTitulo());
        System.out.println("   📌 Encapsula la lógica de creación de objetos");

        // 3. STRATEGY
        System.out.println("\n3️⃣  PATRÓN STRATEGY");
        System.out.println("   " + "-".repeat(50));
        System.out.println("   Probando diferentes estrategias de búsqueda:");

        EstrategiaBusqueda estrategiaTitulo = new BusquedaPorTitulo();
        List<Libro> resultado1 = estrategiaTitulo.buscar(biblioteca.getLibros(), "Code");
        System.out.println("   Estrategia por Título: " + resultado1.size() + " resultado(s)");

        EstrategiaBusqueda estrategiaAutor = new BusquedaPorAutor();
        List<Libro> resultado2 = estrategiaAutor.buscar(biblioteca.getLibros(), "Martin");
        System.out.println("   Estrategia por Autor: " + resultado2.size() + " resultado(s)");
        System.out.println("   📌 Permite cambiar el algoritmo en tiempo de ejecución");

        // 4. HERENCIA
        System.out.println("\n4️⃣  HERENCIA");
        System.out.println("   " + "-".repeat(50));
        System.out.println("   LibroFisico y LibroDigital heredan de Libro");
        System.out.println("   Cada uno implementa métodos abstractos de forma específica:");
        Libro fisico = biblioteca.getLibros().stream()
                .filter(l -> l instanceof LibroFisico)
                .findFirst()
                .orElse(null);
        if (fisico != null) {
            System.out.println("   Días máximo préstamo (Físico): " + fisico.getDiasMaximoPrestamo());
        }

        // 5. COMPOSICIÓN
        System.out.println("\n5️⃣  COMPOSICIÓN");
        System.out.println("   " + "-".repeat(50));
        System.out.println("   Usuario contiene (compone) una lista de Préstamos");
        biblioteca.getUsuarios().stream()
                .findFirst()
                .ifPresent(usuario -> {
                    System.out.println("   Usuario: " + usuario.getNombre());
                    System.out.println("   Préstamos en historial: " +
                            usuario.getHistorialPrestamos().size());
                });

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Mostrar documentación del proyecto
     */
    private static void mostrarDocumentacion() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           DOCUMENTACIÓN DEL PROYECTO");
        System.out.println("═".repeat(60));

        System.out.println("\n📚 ESTRUCTURA DEL PROYECTO:");
        System.out.println("   ✓ 3+ Clases principales: Libro, Usuario, Prestamo");
        System.out.println("   ✓ Herencia: LibroFisico y LibroDigital heredan de Libro");
        System.out.println("   ✓ Asociación: Prestamo asocia Usuario y Libro");
        System.out.println("   ✓ Composición: Usuario compone lista de Préstamos");

        System.out.println("\n🎯 PATRONES DE DISEÑO:");
        System.out.println("   1. Singleton: BibliotecaManager (gestor único)");
        System.out.println("   2. Factory Method: LibroFactory (creación flexible)");
        System.out.println("   3. Strategy: EstrategiaBusqueda (algoritmos intercambiables)");

        System.out.println("\n🚀 PROGRAMACIÓN FUNCIONAL:");
        System.out.println("   ✓ filter() - Filtrado de colecciones");
        System.out.println("   ✓ map() - Transformación de elementos");
        System.out.println("   ✓ forEach() - Iteración con lambdas");
        System.out.println("   ✓ collect() - Recolección de resultados");
        System.out.println("   ✓ sorted(), distinct(), anyMatch(), allMatch()");

        System.out.println("\n⚠️  GESTIÓN DE ERRORES:");
        System.out.println("   ✓ try-catch-finally en todas las operaciones críticas");
        System.out.println("   ✓ Excepciones personalizadas (LibroNoDisponibleException)");
        System.out.println("   ✓ Validaciones centralizadas (clase Validador)");
        System.out.println("   ✓ Mensajes de error descriptivos");

        System.out.println("\n📝 DOCUMENTACIÓN:");
        System.out.println("   ✓ JavaDoc en todas las clases y métodos");
        System.out.println("   ✓ Comentarios explicativos");
        System.out.println("   ✓ Código limpio y bien estructurado");

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Carga datos de ejemplo para demostración
     */
    private static void cargarDatosEjemplo() {
        System.out.println("\n⏳ Cargando datos de ejemplo...");

        try {
            // USAR FACTORY para crear libros
            Libro libro1 = LibroFactory.crearLibroFisico(
                    "978-0-132350-88-4", "Clean Code", "Robert C. Martin",
                    LocalDate.of(2008, 8, 1), "Programación",
                    "Estante A3", 464, "Prentice Hall"
            );

            Libro libro2 = LibroFactory.crearLibroDigital(
                    "978-0-201633-61-2", "Design Patterns", "Gang of Four",
                    LocalDate.of(1994, 10, 21), "Programación",
                    "PDF", 3.8, "https://biblioteca.com/patterns.pdf", true
            );

            Libro libro3 = LibroFactory.crearLibroFisico(
                    "978-0-596007-12-4", "Head First Design Patterns", "Eric Freeman",
                    LocalDate.of(2004, 10, 25), "Programación",
                    "Estante A4", 638, "O'Reilly"
            );

            Libro libro4 = LibroFactory.crearLibroDigital(
                    "978-0-134685-99-1", "Effective Java", "Joshua Bloch",
                    LocalDate.of(2017, 12, 27), "Programación",
                    "EPUB", 2.5, "https://biblioteca.com/effective.epub", false
            );

            biblioteca.agregarLibro(libro1);
            biblioteca.agregarLibro(libro2);
            biblioteca.agregarLibro(libro3);
            biblioteca.agregarLibro(libro4);

            // Crear usuarios
            Usuario usuario1 = new Usuario("USR001", "Juan Pérez",
                    "juan.perez@email.com", "+57-300-1234567");
            Usuario usuario2 = new Usuario("USR002", "María García",
                    "maria.garcia@email.com", "+57-310-7654321");

            biblioteca.registrarUsuario(usuario1);
            biblioteca.registrarUsuario(usuario2);

            // Realizar algunos préstamos
            biblioteca.realizarPrestamo("USR001", "978-0-132350-88-4");
            biblioteca.realizarPrestamo("USR002", "978-0-201633-61-2");

            System.out.println("✓ Datos de ejemplo cargados exitosamente");

        } catch (Exception e) {
            System.err.println("⚠️  Error al cargar datos: " + e.getMessage());
        }
    }
}