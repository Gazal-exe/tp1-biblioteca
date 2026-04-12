package unlar.edu.ar.ui;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.model.Libro;
import unlar.edu.ar.model.Estudiante;

import java.util.List;
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
            System.out.println("6. Listar Catálogo Completo");
            System.out.println("7. Listar Estudiantes Registrados");
            System.out.println("8. Ver Todos los Préstamos Activos");
            System.out.println("9. Buscar Libros por Titulo");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- NUEVO ESTUDIANTE ---");
                        System.out.print("Legajo: ");
                        String leg = sc.nextLine();
                        System.out.print("Nombre: ");
                        String nom = sc.nextLine();
                        System.out.print("Carrera: ");
                        String carrera = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        bibliotecaService.agregarEstudiante(new Estudiante(leg, nom, carrera, email));
                        System.out.println("✅ Estudiante registrado.");
                        break;

                    case 2:
                        System.out.println("\n--- NUEVO LIBRO ---");
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Autor: ");
                        String autor = sc.nextLine();
                        System.out.print("Año: ");
                        int anio = Integer.parseInt(sc.nextLine());
                        bibliotecaService.agregarLibro(new Libro(isbn, titulo, autor, anio, true));
                        System.out.println("✅ Libro agregado.");
                        break;

                    case 3:
                        System.out.println("\n--- REGISTRAR PRESTAMO ---");
                        System.out.print("ISBN del libro: ");
                        String iPres = sc.nextLine();
                        System.out.print("Legajo del alumno: ");
                        String lPres = sc.nextLine();
                        bibliotecaService.registrarPrestamo(iPres, lPres);
                        System.out.println("✅ Prestamo realizado.");
                        break;

                    case 4:
                        System.out.println("\n--- DEVOLUCION ---");
                        System.out.print("ISBN del libro: ");
                        String iDev = sc.nextLine();
                        double multa = bibliotecaService.registrarDevolucion(iDev);
                        System.out.println("✅ Devolución procesada.");
                        if (multa > 0)
                            System.out.println("⚠️ Multa a cobrar: $" + multa);
                        break;

                    case 5:
                        System.out.print("\nLegajo del alumno: ");
                        String lBus = sc.nextLine();
                        bibliotecaService.listarPrestamosPorEstudiante(lBus);
                        break;

                    case 6:
                        System.out.println("\n--- CATALOGO ---");
                        bibliotecaService.getCatalogo().forEach(System.out::println);
                        break;

                    case 7:
                        System.out.println("\n--- ESTUDIANTES ---");
                        bibliotecaService.getEstudiantes().forEach(System.out::println);
                        break;

                    case 8:
                        System.out.println("\n--- PRESTAMOS GLOBALES ---");
                        bibliotecaService.getPrestamosActivos().forEach(System.out::println);
                        break;
                    case 9:
                        System.out.println("\n--- BUSQUEDA DE LIBROS ---");
                        System.out.print("Ingrese el título o parte de él: ");
                        String query = sc.nextLine();

                        List<Libro> resultados = bibliotecaService.buscarLibrosPorTitulo(query);

                        if (resultados.isEmpty()) {
                            System.out.println("❌ No se encontraron libros que coincidan con: " + query);
                        } else {
                            System.out.println("✅ Resultados encontrados:");
                            resultados.forEach(System.out::println);
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("❌ Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}