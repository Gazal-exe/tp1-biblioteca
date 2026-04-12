package unlar.edu.ar;

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
        service.agregarEstudiante(new Estudiante("111", "Juan Perez", "Sistemas", "juan@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("222", "Maria Gomez", "Medicina", "maria@unlar.edu.ar"));
        service.agregarEstudiante(new Estudiante("333", "Carlos Ruiz", "Abogacia", "carlos@unlar.edu.ar"));

        // --- CARGA DE LIBROS ---
        // (ISBN, Titulo, Autor, Año, Disponible)
        service.agregarLibro(new Libro("1001", "Java a fondo", "Pablo Sznajdleder", 2021, true));
        service.agregarLibro(new Libro("1002", "Clean Code", "Robert C. Martin", 2008, true));
        service.agregarLibro(new Libro("1003", "Design Patterns", "GoF", 1994, true));
        service.agregarLibro(new Libro("1004", "Effective Java", "Joshua Bloch", 2018, true));
        service.agregarLibro(new Libro("1005", "Don Quijote", "Miguel de Cervantes", 1605, true));

        // --- CARGA DE ALGUNOS PRÉSTAMOS INICIALES ---
        // Así probamos las opciones de listado y devolución de entrada
        try {
            // Juan Perez (111) pide el libro de Java (1001)
            service.registrarPrestamo("1001", "111");
            
            // Maria Gomez (222) pide Clean Code (1002)
            service.registrarPrestamo("1002", "222");
            
            System.out.println("✅ Datos de prueba cargados con éxito.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar préstamos iniciales: " + e.getMessage());
        }

        System.out.println("------------------------------------------\n");

        // 2. Lanzamos la interfaz de usuario
        MenuUI menu = new MenuUI(service);
        menu.mostrarMenu();
    }
}