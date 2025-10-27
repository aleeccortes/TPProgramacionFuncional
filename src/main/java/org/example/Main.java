package org.example;

import org.example.Entidades.Alumno;
import org.example.Entidades.Empleado;
import org.example.Entidades.Libro;
import org.example.Entidades.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("CASO 1");
        List<Alumno> alumnos = List.of(
                new Alumno("Ana", 8, "3A"),
                new Alumno("Luis", 9.5, "3A"),
                new Alumno("Marta", 6, "3B"),
                new Alumno("Juan", 7, "3B"),
                new Alumno("Pedro", 5, "3A"),
                new Alumno("Lucia", 10, "3C")
        );

//1. Obtener los nombres de los alumnos aprobados (nota ≥ 7) en mayúsculas y
//ordenados.


        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .toList();

        System.out.println(aprobados);


        //2. Calcular el promedio general de notas.

        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0);

        System.out.println(promedio);

        //3. Agrupar alumnos por curso usando Collectors.groupingBy().

        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(groupingBy(Alumno::getCurso));

        System.out.println(alumnosPorCurso);

        //4. Obtener los 3 mejores promedios

        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .toList();

        System.out.println(top3);

        System.out.println("-----------CASO 2----------------------");


        List<Producto> productos = List.of(
                new Producto("Teclado", "Periféricos", 120.0, 15),
                new Producto("Mouse", "Periféricos", 80.0, 40),
                new Producto("Monitor", "Pantallas", 250.0, 8),
                new Producto("HD 1TB", "Almacenamiento", 95.0, 20),
                new Producto("SSD 1TB", "Almacenamiento", 180.0, 12),
                new Producto("Notebook", "Computadoras", 950.0, 5)
        );

        // 1) Productos con precio > 100, ordenados por precio descendente
        List<Producto> carosDesc = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .toList();

        System.out.println("1) >$100 desc:");
        carosDesc.forEach(System.out::println);

        // 2) Agrupar por categoría y sumar stock
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(groupingBy(Producto::getCategoria, summingInt(Producto::getStock)));

        System.out.println("\n2) Stock total por categoría:");
        stockPorCategoria.forEach((cat, stk) -> System.out.println(cat + " -> " + stk));

        // 3) String con "nombre: precio" separado por ";"
        String listadoNombrePrecio = productos.stream()
                .map(p -> String.format("%s: %.2f", p.getNombre(), p.getPrecio()))
                .collect(joining("; "));

        System.out.println("\n3) Nombre y precio unidos:");
        System.out.println(listadoNombrePrecio);

        // 4) Precio promedio general
        double promedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);

        System.out.println("\n4a) Precio promedio general: " + promedioGeneral);

        // 4) Precio promedio por categoría
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(groupingBy(Producto::getCategoria, averagingDouble(Producto::getPrecio)));

        System.out.println("4b) Precio promedio por categoría:");
        promedioPorCategoria.forEach((cat, prom) -> System.out.println(cat + " -> " + prom));

        System.out.println("------------CASO 3------------");

        List<Libro> libros = List.of(
                new Libro("El Quijote", "Cervantes", 1100, 2500),
                new Libro("Crimen y Castigo", "Dostoievski", 550, 1800),
                new Libro("El Principito", "Saint-Exupéry", 95, 1200),
                new Libro("Rayuela", "Cortázar", 600, 2100),
                new Libro("1984", "Orwell", 330, 1700),
                new Libro("Animal Farm", "Orwell", 112, 900));


        // 1) Libros con más de 300 páginas, devolver solo títulos, y ordenados alfabéticamente
        List<String> titulos300 = libros.stream()
                .filter(l -> l.getPaginas() > 300)      // Filtrar libros con más de 300 páginas
                .map(Libro::getTitulo)                 // Transformar cada libro a solo su título
                .sorted()                              // Ordenar alfabéticamente
                .toList();                             // Convertir el Stream en una lista

        System.out.println("1) Libros > 300 páginas:");
        System.out.println(titulos300);

        // 2) Promedio de páginas de todos los libros
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)           // Extraer cantidad de páginas como int
                .average()                             // Calcular el promedio
                .orElse(0);                            // Valor por defecto si la lista estuviera vacía

        System.out.println("\n2) Promedio de páginas: " + promedioPaginas);

        // 3) Agrupar libros por autor y contar cuántos libros tiene cada uno
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(groupingBy(Libro::getAutor, counting())); // Agrupa por autor y cuenta por grupo

        System.out.println("\n3) Cantidad de libros por autor:");
        librosPorAutor.forEach((autor, cantidad) ->
                System.out.println(autor + " -> " + cantidad)
        );

        // 4) Obtener el libro más caro
        Libro masCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio)) // Selecciona el máximo según precio
                .orElse(null);                                     // Si la lista está vacía, null

        System.out.println("\n4) Libro más caro:");
        System.out.println(masCaro);

        System.out.println("---------------CASO 4---------------");


        List<Empleado> empleados = List.of(
                new Empleado("Juan", "Ventas", 2500, 28),
                new Empleado("María", "Marketing", 1800, 30),
                new Empleado("Pedro", "Ventas", 3200, 26),
                new Empleado("Ana", "Sistemas", 4000, 24),
                new Empleado("Luisa", "Sistemas", 2100, 29),
                new Empleado("Andrés", "Marketing", 1500, 35)
        );

        // 1) Empleados con salario > 2000, ordenados desc por salario
        List<Empleado> salarioAlto = empleados.stream()
                .filter(e -> e.getSalario() > 2000)                   // Filtrar salarios mayores a 2000
                .sorted(Comparator.comparingDouble(Empleado::getSalario)
                        .reversed())                                  // Orden descendente por salario
                .toList();                                            // Convertir a lista

        System.out.println("1) Empleados con salario > 2000 (desc):");
        salarioAlto.forEach(System.out::println);

        // 2) Salario promedio general
        double promedioSalario = empleados.stream()
                .mapToDouble(Empleado::getSalario)                    // Extraer los salarios como double
                .average()                                            // Calcular promedio
                .orElse(0);                                           // Si la lista está vacía

        System.out.println("\n2) Salario promedio general: $" + promedioSalario);

        // 3) Agrupar empleados por departamento y sumar salarios por cada grupo
        Map<String, Double> salarioPorDepto = empleados.stream()
                .collect(groupingBy(
                        Empleado::getDepartamento,                        // Clave: departamento
                        summingDouble(Empleado::getSalario)               // Suma salarios por grupo
                ));

        System.out.println("\n3) Suma de salarios por departamento:");
        salarioPorDepto.forEach((depto, suma) ->
                System.out.println(depto + " -> $" + suma)
        );

        // 4) Nombres de los 2 empleados más jóvenes
        List<String> dosMasJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))   // Ordenar por edad ascendente
                .limit(2)                                             // Tomar los primeros 2
                .map(Empleado::getNombre)                             // Quedarse solo con los nombres
                .toList();                                            // Convertir a lista

        System.out.println("\n4) Los dos empleados más jóvenes:");
        System.out.println(dosMasJovenes);
    }

    }






















