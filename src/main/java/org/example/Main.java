package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.impl.CursoDAOClass;
import org.example.dao.impl.EstudianteDAOClass;
import org.example.dao.impl.GenericInterfaceDAO;
import org.example.modelo.Curso;
import org.example.modelo.Estudiante;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    private static GenericInterfaceDAO<Curso, Integer> cursoDAO = new CursoDAOClass();
    private static GenericInterfaceDAO<Estudiante, Integer> estudianteDAO = new EstudianteDAOClass();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Elija una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> crearCurso(scanner);
                case "2" -> listarCursos();
                case "3" -> eliminarCurso(scanner);
                case "4" -> crearEstudiante(scanner);
                case "5" -> listarEstudiantes();
                case "6" -> eliminarEstudiantes(scanner);
                case "7" -> salir = true;

                default -> LOGGER.info("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }




    private static void mostrarMenu() {
        System.out.println("\n-- Menú --");
        System.out.println("1. Crear curso");
        System.out.println("2. Listar cursos");
        System.out.println("3. Eliminar curso");
        System.out.println("4. Crear estudiante");
        System.out.println("5. Listar estudiantes");
        System.out.println("6. Eliminar estudiante");

        System.out.println("7. Salir");
    }


    private static void eliminarEstudiantes(Scanner scanner) {
        System.out.print("Id del estudiante: ");
        String id = scanner.nextLine();
        if (id.isBlank()) {
            LOGGER.warn("Debe ingresar un ID.");
            return;
        }
        estudianteDAO.eliminar(Integer.parseInt(id));

    }

    private static void eliminarCurso(Scanner scanner) {
        System.out.print("Id del curso: ");
        String id = scanner.nextLine();
        if (id.isBlank()) {
            LOGGER.warn("Debe ingresar un ID.");
            return;
        }
        cursoDAO.eliminar(Integer.parseInt(id));
    }
    private static void crearCurso(Scanner scanner) {
        System.out.print("Nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción del curso: ");
        String descripcion = scanner.nextLine();

        if (nombre.isBlank() || descripcion.isBlank()) {
            LOGGER.warn("Nombre y descripción no pueden estar vacíos.");
            return;
        }

        Curso curso = new Curso(0, nombre, descripcion);
        cursoDAO.crear(curso);
        LOGGER.info("Curso creado exitosamente.");
    }

    private static void listarCursos() {
        List<Curso> cursos = cursoDAO.listarTodos();
        if (cursos.isEmpty()) {
            LOGGER.info("No hay cursos disponibles.");
        } else {
            cursos.forEach(c -> System.out.println(c));
        }
    }

    private static void crearEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del estudiante: ");
        String email = scanner.nextLine();

        System.out.println("Cursos disponibles:");
        listarCursos();

        System.out.print("ID del curso: ");
        String cursoIdStr = scanner.nextLine();

        if (nombre.isBlank() || email.isBlank() || cursoIdStr.isBlank()) {
            LOGGER.warn("Todos los campos son obligatorios.");
            return;
        }

        try {
            int cursoId = Integer.parseInt(cursoIdStr);
            Estudiante estudiante = new Estudiante(0, nombre, email, cursoId);
            estudianteDAO.crear(estudiante);
            LOGGER.info("Estudiante creado exitosamente.");
        } catch (NumberFormatException e) {
            LOGGER.warn("ID de curso inválido.");
        }
    }


    private static void listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteDAO.listarTodos();
        if (estudiantes.isEmpty()) {
            LOGGER.info("No hay estudiantes disponibles.");
        } else {
            estudiantes.forEach(e -> System.out.println(e));
        }
    }
}
