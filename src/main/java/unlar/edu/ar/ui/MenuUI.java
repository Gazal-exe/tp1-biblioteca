package unlar.edu.ar.ui;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.model.Libro;
import unlar.edu.ar.model.Estudiante;
import java.util.Scanner;

public class MenuUI {

    private BibliotecaService bibliotecaService;

    // Constructor: Para que el menú funcione, necesita que le pasemos el servicio
    public MenuUI(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA UNLaR (Consola) ---");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Agregar Libro al Catálogo");
            System.out.println("3. Realizar Préstamo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: "); String nom = sc.nextLine();
                        System.out.print("Legajo: "); String leg = sc.nextLine();
                        bibliotecaService.registrarEstudiante(new Estudiante(nom, leg));
                        System.out.println("Estudiante registrado con éxito.");
                        break;
                    case 2:
                        System.out.print("Título: "); String titulo = sc.nextLine();
                        System.out.print("ISBN: "); String isbn = sc.nextLine();
                        // Asumimos que los otros datos los podés completar vos
                        bibliotecaService.agregarLibro(new Libro(isbn, titulo, "Autor Desconocido", 2024, true));
                        System.out.println("Libro agregado al catálogo.");
                        break;
                    case 3:
                        System.out.print("Legajo del alumno: "); String l = sc.nextLine();
                        System.out.print("ISBN del libro: "); String i = sc.nextLine();
                        bibliotecaService.prestarLibro(l, i);
                        break;
                    case 0:
                        System.out.println("Cerrando sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingrese un número válido.");
            } catch (Exception e) {
                // Aquí caen tus excepciones: EstudianteNoEncontrado, etc.
                System.out.println("AVISO: " + e.getMessage());
            }
        }
        sc.close();
    }
}