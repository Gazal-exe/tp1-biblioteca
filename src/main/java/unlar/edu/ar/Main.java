package unlar.edu.ar;
import java.time.LocalDate;
import unlar.edu.ar.exception.EstudianteNoEncontradoException;
import unlar.edu.ar.exception.LimitePrestamosExcedidoException;
import unlar.edu.ar.exception.LibroNoDisponibleException;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.ui.MenuUI;
import unlar.edu.ar.model.Estudiante;
import unlar.edu.ar.model.Libro;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciamos el cerebro del sistema
        BibliotecaService service = new BibliotecaService();

        System.out.println("Iniciando Sistema de Biblioteca UNLaR...");
        System.out.println("Cargando base de datos temporal...");

        // --- CARGA DE ESTUDIANTES ---
        service.agregarEstudiante(new Estudiante("111", "Yessica Esmeralda", "Sistemas", "yessicaesmeralda@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("222", "Walter Arias Molino", "Medicina", "walterarias@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("333", "Carlos Esteban", "Abogacia", "carlosesteban@unlar.edu.ar"));

        // --- CARGA DE LIBROS ---
        service.agregarLibro(new Libro("1001", "Calculo: Una variable", "George B. Thomas", 2021, true));
        service.agregarLibro(new Libro("1002", "Calculo de varias variables", "James Stewart", 2008, true));
        service.agregarLibro(new Libro("1003", "Fisica Universitaria", "Zemansky", 1994, true));
        service.agregarLibro(new Libro("1004", "Fisica 1 y 2", "Resnick", 2018, true));
        service.agregarLibro(new Libro("1005", "Física para la Ciencia y la Tecnología", "Paul Tipler & Gene Mosca", 1605, true));

        // Mostramos un encabezado para la sección de pruebas
        System.out.println("\n------------------------------------------");
        System.out.println("CASOS DE PRUEBA");
        System.out.println("-------------------------------------------");

        //CASO DE PRUEBA: PRESTAMO EXITOSO
        //se presta un libro disponible a un estudiante registrado.
        System.out.println("\n--- CASO DE PRUEBA: Préstamo exitoso ---");
        try {
            // Juan Perez (111) pide el libro de Java (1001)
            service.registrarPrestamo("1001", "111");
            System.out.println("✅ Préstamo registrado: ISBN 1001 → Legajo 111");
        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar el préstamo: " + e.getMessage());
        }

        // 4. CASO DE PRUEBA: LIBRO NO DISPONIBLE
        // Intentamos prestar nuevamente el mismo libro 1001, Esto debe lanzar LibroNoDisponibleException
        System.out.println("\n--- PRUEBA 2: LIBRO NO DISPONIBLE ---");
        try {
            service.registrarPrestamo("1001", "222");

            // Si llega aquí, significa que NO lanzó la excepción esperada
            System.out.println("ERROR: no se lanzó la excepción esperada.");
        } catch (LibroNoDisponibleException e) {
            System.out.println("OK: se capturó LibroNoDisponibleException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 5. CASO DE PRUEBA: ESTUDIANTE NO ENCONTRADO
        // Intentamos prestar un libro a un legajo inexistente, Esto debe lanzar EstudianteNoEncontradoException
        System.out.println("\n--- PRUEBA 3: ESTUDIANTE NO ENCONTRADO ---");
        try {
            service.registrarPrestamo("1002", "999");

            // Si no falla, entonces la prueba salió mal
            System.out.println("ERROR: no se lanzó la excepción esperada.");
        } catch (EstudianteNoEncontradoException e) {
            System.out.println("OK: se capturó EstudianteNoEncontradoException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 6. CASO DE PRUEBA: LIMITE DE PRESTAMOS EXCEDIDO
        // A un mismo estudiante le prestamos 3 libros distintos, debe fallar
        // con LimitePrestamosExcedidoException en el cuarto intento
        System.out.println("\n--- PRUEBA 4: LIMITE DE PRESTAMOS EXCEDIDO ---");
        try {
            service.registrarPrestamo("1002", "333");
            service.registrarPrestamo("1003", "333");
            service.registrarPrestamo("1004", "333");

            // Este cuarto prestamo debe disparar la excepción
            service.registrarPrestamo("1005", "333");

            // Si llega hasta aquí, la excepción no ocurrió
            System.out.println("ERROR: no se lanzó la excepción esperada.");
        } catch (LimitePrestamosExcedidoException e) {
            System.out.println("Se capturó LimitePrestamosExcedidoException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // 7. CASO DE PRUEBA: MULTA POR 15 DIAS DE RETRASO
        // Prestamos un libro y simulamos una devolucion 15 dias despues
        // para comprobar que el metodo recursivo calcula la multa
        System.out.println("\n--- PRUEBA 5: MULTA POR 15 DIAS ---");
        try {
            // Elegimos un libro que quede disponible para esta prueba
            service.registrarPrestamo("1005", "222");

            // Simulamos que se devuelve 15 dias despues
            LocalDate fechaSimulada = LocalDate.now().plusDays(15);

            // El metodo devuelve el valor de la multa calculada
            double multa = service.registrarDevolucionConFecha("1005", fechaSimulada);

            System.out.println("📅 Fecha de devolución simulada: " + fechaSimulada);
            System.out.printf("💰 Multa calculada: $%.2f%n", multa);
            System.out.println("Cálculo esperado: 15 dias x 1% x $1000");
        } catch (Exception e) {
            System.out.println("⚠️Error inesperado en cálculo de multa: " + e.getMessage());
        }

        // Cerramos la sección de pruebas
        System.out.println("\n========================================");
        System.out.println("FIN DE PRUEBAS");
        System.out.println("========================================");

        // Una vez ejecutadas las pruebas, se lanza el menu
        MenuUI menu = new MenuUI(service);
        menu.mostrarMenu();
    }
}