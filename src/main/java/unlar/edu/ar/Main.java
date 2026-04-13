package unlar.edu.ar;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.ui.MenuUI;

import java.time.LocalDate;

import unlar.edu.ar.model.Estudiante;
import unlar.edu.ar.model.Libro;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciamos el cerebro del sistema
        BibliotecaService service = new BibliotecaService();

        System.out.println("Iniciando Sistema de Biblioteca UNLaR...");
        System.out.println("Cargando base de datos temporal...");

        // --- CARGA DE ESTUDIANTES ---
        service.agregarEstudiante(
                new Estudiante("111", "Yesica Esmeralda", "Sistemas", "yesicaesmeralda@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("222", "Walter Arias Molino", "Medicina", "walterarias@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("333", "Carlos Esteban", "Abogacia", "carlosesteban@unlar.edu.ar"));

        // --- CARGA DE LIBROS ---
        service.agregarLibro(new Libro("1001", "Calculo: Una variable", "George B. Thomas", 2021, true));
        service.agregarLibro(new Libro("1002", "Calculo de varias variables", "James Stewart", 2008, true));
        service.agregarLibro(new Libro("1003", "Fisica Universitaria", "Zemansky", 1994, true));
        service.agregarLibro(new Libro("1004", "Fisica 1 y 2", "Resnick", 2018, true));
        service.agregarLibro(
                new Libro("1005", "Física para la Ciencia y la Tecnología", "Paul Tipler & Gene Mosca", 1605, true));

        // --- CARGA DE ALGUNOS PRESTAMOS INICIALES ---
        // Para probar las opciones de listado y devolución de entrada
        try {
            // Juan Perez (111) pide el libro de Java (1001)
            service.registrarPrestamo("1001", "111");

            // Maria Gomez (222) pide Clean Code (1002)
            service.registrarPrestamo("1002", "222");

            System.out.println("✅ Datos de prueba cargados con exito.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar prestamos iniciales: " + e.getMessage());
        }

        System.out.println("------------------------------------------\n");
        // ── PUNTO 4.6: Caso de prueba con 15 días de retraso ──────────────────
        System.out.println("══════════════════════════════════════════");
        System.out.println("   CASO DE PRUEBA: Multa por 15 días      ");
        System.out.println("══════════════════════════════════════════");

        try {
            // Prestamos el libro 1003 al estudiante 333
            service.registrarPrestamo("1003", "333");
            System.out.println("✅ Préstamo registrado: ISBN 1003 → Legajo 333");

            // Simulamos que devuelve 15 días después
            LocalDate fechaSimulada = LocalDate.now().plusDays(15);
            double multa = service.registrarDevolucionConFecha("1003", fechaSimulada);

            System.out.println("📅 Fecha de devolución simulada: " + fechaSimulada);
            System.out.printf("💰 Multa calculada (15 días): $%.2f%n", multa);
            System.out.println("   → Cálculo: 15 días × 1% × $1000 = $" + multa);

        } catch (Exception e) {
            System.out.println("⚠️ Error en caso de prueba: " + e.getMessage());
        }

        System.out.println("══════════════════════════════════════════\n");
        // ── Fin caso de prueba ────────────────────────────────────────────────

        // 2. Lanzamos la interfaz de usuario
        MenuUI menu = new MenuUI(service);
        menu.mostrarMenu();
    }
}