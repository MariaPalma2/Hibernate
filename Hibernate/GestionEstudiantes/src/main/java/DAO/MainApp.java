package DAO;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class MainApp {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static EstudianteDAO estudianteDAO;
    private static CursoDAO cursoDAO;
    private static NotaDAO notaDAO;

    public static void main(String[] args) {
        try {
            // Inicializar Hibernate
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();

            // Inicializar DAOs
            estudianteDAO = new EstudianteDAO(session);
            cursoDAO = new CursoDAO(session);
            notaDAO = new NotaDAO(session);

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n==== MENÚ PRINCIPAL ====");
                System.out.println("1. Gestionar Cursos");
                System.out.println("2. Gestionar Estudiantes");
                System.out.println("3. Gestionar Notas");
                System.out.println("4. Consultar Notas de un Estudiante en un Curso");
                System.out.println("5. Obtener Estudiantes y Notas de un Curso");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1 -> gestionarCursos(scanner);
                    case 2 -> gestionarEstudiantes(scanner);
                    case 3 -> gestionarNotas(scanner);
                    case 4 -> consultarNotasDeEstudiante(scanner);
                    case 5 -> obtenerEstudiantesYNotas(scanner);
                    case 6 -> {
                        System.out.println("Saliendo del programa...");
                        exit = true;
                    }
                    default -> System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    // === MÉTODOS PARA GESTIONAR CURSOS ===
    private static void gestionarCursos(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n==== GESTIÓN DE CURSOS ====");
            System.out.println("1. Crear Curso");
            System.out.println("2. Actualizar Curso");
            System.out.println("3. Leer Curso");
            System.out.println("4. Eliminar Curso");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> crearCurso(scanner);
                case 2 -> actualizarCurso(scanner);
                case 3 -> leerCurso(scanner);
                case 4 -> eliminarCurso(scanner);
                case 5 -> back = true;
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void crearCurso(Scanner scanner) {
        System.out.print("Introduce el nombre del curso: ");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine();
        System.out.print("Introduce la descripción del curso: ");
        String descripcion = scanner.nextLine();

        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        cursoDAO.save(curso);

        System.out.println("Curso creado con éxito: " + curso);
    }

    private static void actualizarCurso(Scanner scanner) {
        System.out.print("Introduce el ID del curso a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Introduce el nuevo nombre del curso: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Introduce la nueva descripción del curso: ");
        String nuevaDescripcion = scanner.nextLine();

        Curso curso = cursoDAO.findById(id);
        if (curso != null) {
            curso.setNombre(nuevoNombre);
            curso.setDescripcion(nuevaDescripcion);
            cursoDAO.update(curso);
            System.out.println("Curso actualizado con éxito: " + curso);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void leerCurso(Scanner scanner) {
        System.out.print("Introduce el ID del curso a leer: ");
        Long id = scanner.nextLong();

        Curso curso = cursoDAO.findById(id);
        if (curso != null) {
            System.out.println("Curso encontrado: " + curso);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void eliminarCurso(Scanner scanner) {
        System.out.print("Introduce el ID del curso a eliminar: ");
        Long id = scanner.nextLong();

        cursoDAO.delete(id);
        System.out.println("Curso eliminado con éxito.");
    }

    // === MÉTODOS PARA GESTIONAR ESTUDIANTES ===
    private static void gestionarEstudiantes(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n==== GESTIÓN DE ESTUDIANTES ====");
            System.out.println("1. Crear Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Leer Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> crearEstudiante(scanner);
                case 2 -> actualizarEstudiante(scanner);
                case 3 -> leerEstudiante(scanner);
                case 4 -> eliminarEstudiante(scanner);
                case 5 -> back = true;
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void crearEstudiante(Scanner scanner) {

        System.out.print("Introduce el nombre del estudiante: ");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine();
        System.out.print("Introduce el apellido del estudiante: ");
        String apellido = scanner.nextLine();
        System.out.print("Introduce el email del estudiante: ");
        String email = scanner.nextLine();

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        estudianteDAO.save(estudiante);

        System.out.println("Estudiante creado con éxito: " + estudiante);
    }

    private static void actualizarEstudiante(Scanner scanner) {
        System.out.print("Introduce el ID del estudiante a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Introduce el nuevo nombre del estudiante: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Introduce el nuevo apellido del estudiante: ");
        String nuevoApellido = scanner.nextLine();
        System.out.print("Introduce el nuevo email del estudiante: ");
        String nuevoEmail = scanner.nextLine();

        Estudiante estudiante = estudianteDAO.findById(id);
        if (estudiante != null) {
            estudiante.setNombre(nuevoNombre);
            estudiante.setApellido(nuevoApellido);
            estudiante.setEmail(nuevoEmail);
            estudianteDAO.update(estudiante);
            System.out.println("Estudiante actualizado con éxito: " + estudiante);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void leerEstudiante(Scanner scanner) {
        System.out.print("Introduce el ID del estudiante a leer: ");
        Long id = scanner.nextLong();

        Estudiante estudiante = estudianteDAO.findById(id);
        if (estudiante != null) {
            System.out.println("🔍 Estudiante encontrado: " + estudiante);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void eliminarEstudiante(Scanner scanner) {
        System.out.print("Introduce el ID del estudiante a eliminar: ");
        Long id = scanner.nextLong();

        estudianteDAO.delete(id);
        System.out.println("Estudiante eliminado con éxito.");
    }

    // === MÉTODOS PARA GESTIONAR NOTAS ===
    private static void gestionarNotas(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n==== GESTIÓN DE NOTAS ====");
            System.out.println("1. Crear Nota");
            System.out.println("2. Leer Nota");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> crearNota(scanner);
                case 2 -> leerNota(scanner);
                case 3 -> back = true;
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void crearNota(Scanner scanner) {
        System.out.print("Introduce el ID del estudiante: ");
        Long estudianteId = scanner.nextLong();
        System.out.print("Introduce el ID del curso: ");
        Long cursoId = scanner.nextLong();
        System.out.print("Introduce el valor de la nota: ");
        int valor = scanner.nextInt();

        Estudiante estudiante = estudianteDAO.findById(estudianteId);
        Curso curso = cursoDAO.findById(cursoId);
        if (estudiante != null && curso != null) {
            Nota nota = new Nota();
            nota.setEstudiante(estudiante);
            nota.setCurso(curso);
            nota.setValor((double) valor);
            nota.setFecha(new Date());
            notaDAO.save(nota);

            System.out.println("Nota creada con éxito: " + nota);
        } else {
            System.out.println("Estudiante o curso no encontrado.");
        }
    }

    private static void leerNota(Scanner scanner) {
        System.out.print("Introduce el ID de la nota a leer: ");
        Long id = scanner.nextLong();

        Nota nota = notaDAO.findById(id);
        if (nota != null) {
            System.out.println("🔍 Nota encontrada: " + nota);
        } else {
            System.out.println("Nota no encontrada.");
        }
    }

    private static void consultarNotasDeEstudiante(Scanner scanner) {
        System.out.print("Introduce el ID del estudiante: ");
        Long estudianteId = scanner.nextLong();
        System.out.print("Introduce el ID del curso: ");
        Long cursoId = scanner.nextLong();

        Estudiante estudiante = estudianteDAO.findById(estudianteId);
        Curso curso = cursoDAO.findById(cursoId);
        if (estudiante != null && curso != null) {
            System.out.println("\n=== Notas del estudiante en el curso ===");
            curso.getNotas().stream()
                    .filter(nota -> nota.getEstudiante().equals(estudiante))
                    .forEach(nota -> System.out.println("Nota: " + nota.getValor() + " - Fecha: " + nota.getFecha()));
        } else {
            System.out.println("Estudiante o curso no encontrado.");
        }
    }

    private static void obtenerEstudiantesYNotas(Scanner scanner) {
        System.out.print("Introduce el ID del curso: ");
        Long cursoId = scanner.nextLong();

        Curso curso = cursoDAO.findById(cursoId);
        if (curso != null) {
            System.out.println("\n=== Estudiantes y notas en el curso ===");
            curso.getNotas().forEach(nota -> {
                Estudiante estudiante = nota.getEstudiante();
                System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() +
                        " - Nota: " + nota.getValor());
            });
        } else {
            System.out.println("Curso no encontrado.");
        }
    }
}
