package com.biblioteca.estrategia;

import com.biblioteca.modelo.Libro;
import java.util.List;

/**
 * Interfaz que define la estrategia de búsqueda de libros.
 * Implementa el patrón STRATEGY que permite definir una familia
 * de algoritmos (diferentes formas de buscar) y hacerlos intercambiables.
 *
 * VENTAJAS DEL PATRÓN STRATEGY:
 * - Permite cambiar el algoritmo en tiempo de ejecución
 * - Evita condicionales complejos (if/switch)
 * - Facilita agregar nuevas estrategias de búsqueda
 * - Cumple con el principio Open/Closed (abierto para extensión, cerrado para modificación)
 *
 * @author Luis Ochoa
 * @version 1.0
 */
@FunctionalInterface
public interface EstrategiaBusqueda {

    /**
     * Ejecuta la búsqueda según la estrategia implementada
     *
     * @param libros Lista de libros donde buscar
     * @param criterio Criterio de búsqueda (puede ser título, autor, etc.)
     * @return Lista de libros que cumplen el criterio
     */
    List<Libro> buscar(List<Libro> libros, String criterio);
}