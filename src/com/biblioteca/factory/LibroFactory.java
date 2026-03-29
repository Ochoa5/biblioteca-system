package com.biblioteca.factory;

import com.biblioteca.modelo.*;
import java.time.LocalDate;

/**
 * Clase Factory que implementa el patrón FACTORY METHOD.
 * Proporciona un método centralizado para crear diferentes tipos de libros
 * sin exponer la lógica de creación al cliente.
 *
 * VENTAJAS:
 * - Encapsula la lógica de creación de objetos
 * - Facilita agregar nuevos tipos de libros en el futuro
 * - Reduce el acoplamiento entre código cliente y clases concretas
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class LibroFactory {

    /**
     * Crea un libro físico con los parámetros especificados.
     * FACTORY METHOD para LibroFisico
     *
     * @param isbn Identificador único
     * @param titulo Título del libro
     * @param autor Autor
     * @param fechaPublicacion Fecha de publicación
     * @param categoria Categoría
     * @param ubicacionEstante Ubicación en estantería
     * @param numeroPaginas Número de páginas
     * @param editorial Editorial
     * @return Instancia de LibroFisico
     */
    public static LibroFisico crearLibroFisico(String isbn, String titulo, String autor,
                                               LocalDate fechaPublicacion, String categoria,
                                               String ubicacionEstante, int numeroPaginas,
                                               String editorial) {
        System.out.println("🏭 Factory: Creando libro físico...");
        return new LibroFisico(isbn, titulo, autor, fechaPublicacion, categoria,
                ubicacionEstante, numeroPaginas, editorial);
    }

    /**
     * Crea un libro digital con los parámetros especificados.
     * FACTORY METHOD para LibroDigital
     *
     * @param isbn Identificador único
     * @param titulo Título del libro
     * @param autor Autor
     * @param fechaPublicacion Fecha de publicación
     * @param categoria Categoría
     * @param formatoArchivo Formato digital (PDF, EPUB, etc.)
     * @param tamañoMB Tamaño en megabytes
     * @param urlDescarga URL de descarga
     * @param requiereDRM Si requiere DRM
     * @return Instancia de LibroDigital
     */
    public static LibroDigital crearLibroDigital(String isbn, String titulo, String autor,
                                                 LocalDate fechaPublicacion, String categoria,
                                                 String formatoArchivo, double tamañoMB,
                                                 String urlDescarga, boolean requiereDRM) {
        System.out.println("🏭 Factory: Creando libro digital...");
        return new LibroDigital(isbn, titulo, autor, fechaPublicacion, categoria,
                formatoArchivo, tamañoMB, urlDescarga, requiereDRM);
    }

    /**
     * Método Factory genérico que decide qué tipo de libro crear
     * basándose en el tipo especificado.
     *
     * FACTORY METHOD PRINCIPAL: Decide dinámicamente qué objeto crear
     *
     * @param tipo Tipo de libro (FISICO o DIGITAL)
     * @param parametros Array de parámetros necesarios
     * @return Libro creado según el tipo
     * @throws IllegalArgumentException si el tipo no es válido
     */
    public static Libro crearLibro(TipoLibro tipo, Object... parametros) {
        System.out.println("🏭 Factory: Iniciando creación de libro tipo " + tipo);

        switch (tipo) {
            case FISICO:
                if (parametros.length < 8) {
                    throw new IllegalArgumentException(
                            "Parámetros insuficientes para crear libro físico");
                }
                return crearLibroFisico(
                        (String) parametros[0],     // isbn
                        (String) parametros[1],     // titulo
                        (String) parametros[2],     // autor
                        (LocalDate) parametros[3],  // fechaPublicacion
                        (String) parametros[4],     // categoria
                        (String) parametros[5],     // ubicacionEstante
                        (Integer) parametros[6],    // numeroPaginas
                        (String) parametros[7]      // editorial
                );

            case DIGITAL:
                if (parametros.length < 8) {
                    throw new IllegalArgumentException(
                            "Parámetros insuficientes para crear libro digital");
                }
                return crearLibroDigital(
                        (String) parametros[0],     // isbn
                        (String) parametros[1],     // titulo
                        (String) parametros[2],     // autor
                        (LocalDate) parametros[3],  // fechaPublicacion
                        (String) parametros[4],     // categoria
                        (String) parametros[5],     // formatoArchivo
                        (Double) parametros[6],     // tamañoMB
                        (String) parametros[7],     // urlDescarga
                        parametros.length > 8 ? (Boolean) parametros[8] : false // requiereDRM
                );

            default:
                throw new IllegalArgumentException("Tipo de libro no válido: " + tipo);
        }
    }

    /**
     * Crea un libro de ejemplo para pruebas rápidas
     * @param tipo Tipo de libro
     * @return Libro de ejemplo
     */
    public static Libro crearLibroEjemplo(TipoLibro tipo) {
        switch (tipo) {
            case FISICO:
                return crearLibroFisico(
                        "978-0-123456-78-9",
                        "Clean Code",
                        "Robert C. Martin",
                        LocalDate.of(2008, 8, 1),
                        "Programación",
                        "Estante A3",
                        464,
                        "Prentice Hall"
                );

            case DIGITAL:
                return crearLibroDigital(
                        "978-0-987654-32-1",
                        "The Pragmatic Programmer",
                        "Andrew Hunt",
                        LocalDate.of(1999, 10, 30),
                        "Programación",
                        "PDF",
                        5.2,
                        "https://biblioteca.example/pragmatic.pdf",
                        true
                );

            default:
                throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}