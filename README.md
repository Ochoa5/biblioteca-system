# 📚 Sistema de Gestión de Biblioteca

<div align="center">

![Java](https://img.shields.io/badge/Java-11+-orange?style=for-the-badge&logo=java)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-3-purple?style=for-the-badge)

**Sistema completo de gestión de biblioteca implementando patrones de diseño, programación funcional y POO avanzada en Java**

[Características](#-características-principales) •
[Instalación](#-instalación) •
[Uso](#-uso) •
[Arquitectura](#-arquitectura) •
[Documentación](#-documentación)

</div>

---

## 📋 Tabla de Contenidos

- [Sobre el Proyecto](#-sobre-el-proyecto)
- [Características Principales](#-características-principales)
- [Tecnologías y Conceptos](#-tecnologías-y-conceptos)
- [Arquitectura](#-arquitectura)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Patrones de Diseño](#-patrones-de-diseño)
- [Programación Funcional](#-programación-funcional)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Roadmap](#-roadmap)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)
- [Contacto](#-contacto)

---

## 🎯 Sobre el Proyecto

Sistema de gestión de biblioteca desarrollado en Java que permite administrar libros (físicos y digitales), usuarios y préstamos. El proyecto fue diseñado con énfasis en:

- **Arquitectura limpia** y separación de responsabilidades
- **Patrones de diseño** (Singleton, Factory Method, Strategy)
- **Programación funcional** con Streams y Lambdas de Java 8+
- **Manejo robusto de errores** con excepciones personalizadas
- **Código documentado** con JavaDoc

### 🎓 Contexto Académico

Este proyecto fue desarrollado como actividad académica para demostrar conocimientos avanzados en:
- Programación Orientada a Objetos (POO)
- Patrones de diseño de software
- Programación funcional en Java
- Buenas prácticas de desarrollo

---

## ✨ Características Principales

### Gestión de Libros
- ✅ Agregar libros físicos y digitales
- ✅ Búsqueda flexible (por título, autor, categoría)
- ✅ Diferentes políticas de préstamo según tipo
- ✅ Control de disponibilidad en tiempo real

### Gestión de Usuarios
- ✅ Registro de usuarios con validaciones
- ✅ Historial completo de préstamos
- ✅ Seguimiento de préstamos activos
- ✅ Identificación de usuarios con retrasos

### Sistema de Préstamos
- ✅ Realización de préstamos con validaciones
- ✅ Cálculo automático de fechas de devolución
- ✅ Detección de préstamos vencidos
- ✅ Registro de devoluciones con cálculo de retrasos

### Características Técnicas
- ✅ Interfaz de consola interactiva
- ✅ Validaciones de entrada robustas
- ✅ Mensajes de error descriptivos
- ✅ Datos de ejemplo precargados
- ✅ Estadísticas en tiempo real

---

## 🛠️ Tecnologías y Conceptos

### Lenguaje y Herramientas
```
Java 11+
IntelliJ IDEA (recomendado)
Git & GitHub
```

### Paradigmas y Patrones

<table>
<tr>
<td>

**Programación Orientada a Objetos**
- Clases y Objetos
- Herencia
- Polimorfismo
- Encapsulamiento
- Abstracción

</td>
<td>

**Patrones de Diseño**
- Singleton
- Factory Method
- Strategy
- Composición
- Asociación

</td>
<td>

**Programación Funcional**
- Streams API
- Expresiones Lambda
- Method References
- Operaciones de filtrado
- Transformaciones de datos

</td>
</tr>
</table>

---

## 🏗️ Arquitectura

### Diagrama de Clases Principal

```
                    <<abstract>>
                       Libro
                         △
                         │
            ┌────────────┴────────────┐
            │                         │
      LibroFisico               LibroDigital
      
      
      Usuario ◆────────> List<Prestamo>
                         (Composición)
      
      
      Prestamo ◇────────> Usuario
               ◇────────> Libro
               (Asociación)
```

### Capas de la Aplicación

```
┌─────────────────────────────────────┐
│         Main (Presentación)         │  ← Interfaz de usuario
├─────────────────────────────────────┤
│   BibliotecaManager (Lógica)        │  ← Singleton, lógica de negocio
├─────────────────────────────────────┤
│   Factory & Strategy (Patrones)     │  ← Creación y comportamiento
├─────────────────────────────────────┤
│   Modelo (Dominio)                  │  ← Entidades del negocio
├─────────────────────────────────────┤
│   Util & Excepciones (Soporte)      │  ← Validaciones y errores
└─────────────────────────────────────┘
```

---

## 📁 Estructura del Proyecto

```
BibliotecaSystem/
│
├── src/com/biblioteca/
│   │
│   ├── Main.java                           # Punto de entrada
│   │
│   ├── modelo/                             # Capa de Dominio
│   │   ├── Libro.java                      # Clase base abstracta
│   │   ├── LibroFisico.java                # Herencia: libro físico
│   │   ├── LibroDigital.java               # Herencia: libro digital
│   │   ├── Usuario.java                    # Usuario con composición
│   │   └── Prestamo.java                   # Relación de asociación
│   │
│   ├── gestor/                             # Capa de Lógica
│   │   └── BibliotecaManager.java          # Patrón Singleton
│   │
│   ├── factory/                            # Patrón Factory Method
│   │   ├── LibroFactory.java               # Creador de libros
│   │   └── TipoLibro.java                  # Enum de tipos
│   │
│   ├── estrategia/                         # Patrón Strategy
│   │   ├── EstrategiaBusqueda.java         # Interfaz estrategia
│   │   ├── BusquedaPorTitulo.java          # Implementación 1
│   │   └── BusquedaPorAutor.java           # Implementación 2
│   │
│   ├── excepciones/                        # Excepciones personalizadas
│   │   ├── LibroNoDisponibleException.java
│   │   └── UsuarioNoValidoException.java
│   │
│   └── util/                               # Utilidades
│       └── Validador.java                  # Validaciones centralizadas
│
├── .gitignore
├── LICENSE
└── README.md
```

---

## 🚀 Instalación

### Prerrequisitos

- Java JDK 11 o superior
- IntelliJ IDEA Community Edition (recomendado) o cualquier IDE Java
- Git (para clonar el repositorio)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Ochoa5/biblioteca-system.git
   cd biblioteca-system
   ```

2. **Abrir en IntelliJ IDEA**
   ```
   File → Open → Seleccionar carpeta del proyecto
   ```

3. **Configurar JDK**
   ```
   File → Project Structure → Project SDK → Seleccionar JDK 11+
   ```

4. **Compilar el proyecto**
   ```
   Build → Build Project
   ```

5. **Ejecutar**
   ```
   Click derecho en Main.java → Run 'Main.main()'
   ```

### Instalación Alternativa (Línea de Comandos)

```bash
# Compilar
javac -d out -sourcepath src src/com/biblioteca/Main.java

# Ejecutar
java -cp out com.biblioteca.Main
```

---

## 💻 Uso

### Menú Principal

Al ejecutar el programa, verás un menú interactivo:

```
═══════════════════════════════════════════════════════════
                    MENÚ PRINCIPAL
═══════════════════════════════════════════════════════════
1. 📚 Agregar nuevo libro
2. 👤 Registrar nuevo usuario
3. 📖 Realizar préstamo
4. 📥 Devolver libro
5. 🔍 Buscar libros (Strategy Pattern)
6. 📋 Ver libros disponibles
7. 📊 Ver estadísticas
8. 🚀 Demostración: Programación Funcional
9. 🎯 Demostración: Patrones de Diseño
10. 📖 Ver documentación del proyecto
0. ❌ Salir
═══════════════════════════════════════════════════════════
```

### Ejemplos de Uso

#### Agregar un Libro Físico

```java
Opción: 1
Tipo: 1 (Físico)
ISBN: 978-0-134685-99-1
Título: Effective Java
Autor: Joshua Bloch
Fecha: 27/12/2017
Categoría: Programación
Ubicación: Estante A5
Páginas: 412
Editorial: Addison-Wesley

✓ Libro agregado exitosamente
```

#### Realizar un Préstamo

```java
Opción: 3
ID Usuario: USR001
ISBN: 978-0-132350-88-4

✓ Préstamo realizado exitosamente
ID: PREST-1
Devolución esperada: 12/04/2024
```

#### Buscar Libros (Strategy Pattern)

```java
Opción: 5
Estrategia: 1 (Por título)
Criterio: Java

🔍 Estrategia: Buscando por TÍTULO...

📋 Resultados encontrados: 2
- Effective Java
- Head First Java
```

---

## 🎯 Patrones de Diseño

### 1. Singleton Pattern

**Problema:** Solo debe existir una instancia del gestor de biblioteca.

**Solución:**
```java
public class BibliotecaManager {
    private static BibliotecaManager instance;
    
    private BibliotecaManager() {}  // Constructor privado
    
    public static synchronized BibliotecaManager getInstance() {
        if (instance == null) {
            instance = new BibliotecaManager();
        }
        return instance;
    }
}
```

**Beneficios:**
- ✅ Control centralizado de datos
- ✅ Acceso global controlado
- ✅ Thread-safe con sincronización

---

### 2. Factory Method Pattern

**Problema:** Creación de diferentes tipos de libros sin exponer la lógica de creación.

**Solución:**
```java
public class LibroFactory {
    public static Libro crearLibro(TipoLibro tipo, Object... params) {
        switch (tipo) {
            case FISICO:
                return crearLibroFisico(...);
            case DIGITAL:
                return crearLibroDigital(...);
        }
    }
}
```

**Beneficios:**
- ✅ Encapsulación de creación
- ✅ Fácil extensibilidad
- ✅ Reduce acoplamiento

---

### 3. Strategy Pattern

**Problema:** Necesidad de diferentes algoritmos de búsqueda intercambiables.

**Solución:**
```java
public interface EstrategiaBusqueda {
    List<Libro> buscar(List<Libro> libros, String criterio);
}

public class BusquedaPorTitulo implements EstrategiaBusqueda {
    @Override
    public List<Libro> buscar(List<Libro> libros, String criterio) {
        return libros.stream()
            .filter(l -> l.getTitulo().contains(criterio))
            .collect(Collectors.toList());
    }
}
```

**Beneficios:**
- ✅ Algoritmos intercambiables en runtime
- ✅ Evita condicionales complejos
- ✅ Principio Open/Closed

---

## 🌊 Programación Funcional

### Ejemplos de Streams y Lambdas

#### Filtrar Libros Disponibles
```java
public List<Libro> obtenerLibrosDisponibles() {
    return libros.stream()
            .filter(Libro::isDisponible)
            .collect(Collectors.toList());
}
```

#### Agrupar por Categoría
```java
Map<String, Long> librosPorCategoria = libros.stream()
    .collect(Collectors.groupingBy(
        Libro::getCategoria,
        Collectors.counting()
    ));
```

#### Usuarios con Préstamos Vencidos
```java
List<Usuario> usuariosConRetrasos = usuarios.stream()
    .filter(u -> u.getPrestamosActivos().stream()
            .anyMatch(Prestamo::estaVencido))
    .collect(Collectors.toList());
```

#### Transformación y Ordenamiento
```java
List<String> titulosOrdenados = libros.stream()
    .filter(Libro::isDisponible)
    .map(Libro::getTitulo)
    .sorted()
    .distinct()
    .collect(Collectors.toList());
```

### Operaciones Utilizadas

| Operación | Uso en el Proyecto | Descripción |
|-----------|-------------------|-------------|
| `filter()` | Libros disponibles, búsquedas | Filtra elementos por condición |
| `map()` | Extracción de títulos, autores | Transforma elementos |
| `collect()` | Recolección de resultados | Convierte Stream a colección |
| `forEach()` | Mostrar resultados | Itera sobre elementos |
| `anyMatch()` | Verificar préstamos vencidos | Verifica si alguno cumple condición |
| `groupingBy()` | Estadísticas por categoría | Agrupa elementos |
| `sorted()` | Ordenar resultados | Ordena elementos |
| `distinct()` | Eliminar duplicados | Valores únicos |
| `count()` | Contar elementos | Cantidad de elementos |

---

## 📸 Capturas de Pantalla

### Menú Principal
```
╔══════════════════════════════════════════════════╗
║   SISTEMA DE GESTIÓN DE BIBLIOTECA - JAVA      ║
║   Proyecto Académico Completo                   ║
╚══════════════════════════════════════════════════╝

⏳ Cargando datos de ejemplo...
✓ Datos de ejemplo cargados exitosamente
```

### Demostración de Patrones
```
═══════════════════════════════════════════════════
       DEMOSTRACIÓN: PATRONES DE DISEÑO
═══════════════════════════════════════════════════

1️⃣  PATRÓN SINGLETON
   --------------------------------------------------
   ¿Son la misma instancia? Sí ✓
   Hash instancia 1: 1234567890
   Hash instancia 2: 1234567890
   📌 Garantiza una única instancia del gestor
```

### Programación Funcional en Acción
```
═══════════════════════════════════════════════════
    DEMOSTRACIÓN: PROGRAMACIÓN FUNCIONAL
═══════════════════════════════════════════════════

1️⃣  USO DE filter() - Libros disponibles:
   Cantidad: 3

2️⃣  USO DE map() - Lista de títulos:
   • Clean Code
   • Design Patterns
   • Effective Java
```

---

## 🗺️ Roadmap

### Completado ✅
- [x] Sistema base de gestión
- [x] Tres patrones de diseño
- [x] Programación funcional
- [x] Manejo robusto de errores
- [x] Documentación completa
- [x] Interfaz de consola interactiva

### Futuras Mejoras 🚀
- [ ] Persistencia con base de datos (H2 o MySQL)
- [ ] Interfaz gráfica (JavaFX o Swing)
- [ ] Sistema de multas por retrasos
- [ ] Reservas de libros
- [ ] API REST con Spring Boot
- [ ] Autenticación de usuarios
- [ ] Reportes en PDF
- [ ] Testing unitario con JUnit
- [ ] Internacionalización (i18n)
- [ ] Logs con Log4j

---

## 🤝 Contribuir

Las contribuciones son bienvenidas. Si deseas mejorar este proyecto:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Guías de Contribución

- Sigue las convenciones de código Java
- Documenta con JavaDoc
- Incluye pruebas si es posible
- Actualiza el README si es necesario

---

## 📄 Licencia

Distribuido bajo la Licencia MIT. Ver `LICENSE` para más información.

---

## 📞 Contacto

**Link del Proyecto:** [https://github.com/Ochoa5/biblioteca-system](https://github.com/Ochoa5/biblioteca-system)

---

## 🙏 Agradecimientos

* [Java Documentation](https://docs.oracle.com/en/java/)
* [Design Patterns - Refactoring Guru](https://refactoring.guru/design-patterns)
* [Stream API Guide](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
* [Shields.io](https://shields.io) - Badges para README

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella ⭐**

Desarrollado con ❤️ y ☕ como proyecto académico

</div>
