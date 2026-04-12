package unlar.edu.ar.ui;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.model.Libro;
import unlar.edu.ar.model.Estudiante;
import java.util.Scanner;

public class MenuUI {

    private BibliotecaService bibliotecaService;

    public MenuUI(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE BIBLIOTECA UNLaR   ");
            System.out.println("========================================");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Agregar Libro al Catálogo");
            System.out.println("3. Registrar Préstamo");
            System.out.println("4. Registrar Devolución (y ver multa)");
            System.out.println("5. Listar Préstamos de un Estudiante");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- NUEVO ESTUDIANTE ---");
                        System.out.print("Legajo: "); String leg = sc.nextLine();
                        System.out.print("Nombre: "); String nom = sc.nextLine();
                        System.out.print("Carrera: "); String carrera = sc.nextLine();
                        System.out.print("Email: "); String email = sc.nextLine();
                        
                        bibliotecaService.agregarEstudiante(new Estudiante(leg, nom, carrera, email));
                        System.out.println("✅ Estudiante registrado con éxito.");
                        break;

                    case 2:
                        System.out.println("\n--- NUEVO LIBRO ---");
                        System.out.print("ISBN: "); String isbn = sc.nextLine();
                        System.out.print("Título: "); String titulo = sc.nextLine();
                        System.out.print("Autor: "); String autor = sc.nextLine();
                        System.out.print("Año de publicación: "); 
                        int anio = Integer.parseInt(sc.nextLine());
                        
                        // Pasamos true por defecto porque un libro nuevo entra disponible
                        bibliotecaService.agregarLibro(new Libro(isbn, titulo, autor, anio, true));
                        System.out.println("✅ Libro agregado al catálogo.");
                        break;

                    case 3:
                        System.out.println("\n--- NUEVO PRÉSTAMO ---");
                        System.out.print("ISBN del libro: "); String isbnPrestamo = sc.nextLine();
                        System.out.print("Legajo del alumno: "); String legajoPrestamo = sc.nextLine();
                        
                        // Ojo acá: el service de Walter recibe primero ISBN y después Legajo
                        bibliotecaService.registrarPrestamo(isbnPrestamo, legajoPrestamo);
                        System.out.println("✅ Préstamo registrado correctamente.");
                        break;

                    case 4:
                        System.out.println("\n--- DEVOLUCIÓN DE LIBRO ---");
                        System.out.print("Ingrese el ISBN del libro a devolver: "); 
                        String isbnDev = sc.nextLine();
                        
                        double multa = bibliotecaService.registrarDevolucion(isbnDev);
                        System.out.println("✅ Libro devuelto.");
                        if (multa > 0) {
                            System.out.println("⚠️ Atención: El alumno tiene una multa de $" + multa);
                        } else {
                            System.out.println("No hay multas pendientes para esta devolución.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- CONSULTA DE PRÉSTAMOS ---");
                        System.out.print("Ingrese el Legajo del alumno: "); 
                        String legBusqueda = sc.nextLine();
                        
                        System.out.println("Préstamos activos:");
                        bibliotecaService.listarPrestamosPorEstudiante(legBusqueda);
                        break;

                    case 0:
                        System.out.println("Cerrando sistema... ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("❌ Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Por favor, ingrese un número válido.");
            } catch (Exception e) {
                // Atrapa tus excepciones personalizadas (EstudianteNoEncontrado, etc.)
                System.out.println("⚠️ AVISO: " + e.getMessage());
            }
        }
        sc.close();
    }
}