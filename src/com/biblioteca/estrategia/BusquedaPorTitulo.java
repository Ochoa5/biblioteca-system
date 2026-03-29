package com.biblioteca.estrategia;

import com.biblioteca.modelo.Libro;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación concreta de EstrategiaBusqueda.
 * Busca libros por título usando coincidencia parcial.
 *
 * PATRÓN STRATEGY: Algoritmo concreto de búsqueda
 *
 * @author Luis Ochoa
 * @version 1.0
 */
public class BusquedaPorTitulo implements EstrategiaBusqueda {

    /**
     * Busca libros cuyo título contenga el criterio especificado
     * Usa PROGRAMACIÓN FUNCIONAL con filter()
     *
     * @param libros Lista de libros donde buscar
     * @param criterio Título o parte del título a buscar
     * @return Lista de libros que coinciden
     */
    @Override
    public List<Libro> buscar(List<Libro> libros, String criterio) {
        System.out.println("🔍 Estrategia: Buscando por TÍTULO...");

        return libros.stream()
                .filter(libro -> libro.getTitulo()
                        .toLowerCase()
                        .contains(criterio.toLowerCase()))
                .collect(Collectors.toList());
    }
}