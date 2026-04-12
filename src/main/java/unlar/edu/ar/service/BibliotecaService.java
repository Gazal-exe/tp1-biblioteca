package unlar.edu.ar.service;

import unlar.edu.ar.model.*;
import unlar.edu.ar.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BibliotecaService {

    private ArrayList<Libro> catalogo = new ArrayList<>();
    private HashMap<String, Estudiante> estudiantes = new HashMap<>();
    private HashSet<Prestamo> prestamosActivos = new HashSet<>();

    // Agregar libro
    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
    }

    // Agregar estudiante
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.put(estudiante.getLegajo(), estudiante);
    }

    // Registrar préstamo
    public void registrarPrestamo(String isbn, String legajo)
            throws LibroNoDisponibleException,
            EstudianteNoEncontradoException,
            LimitePrestamosExcedidoException {

        // Buscar libro
        Libro libro = catalogo.stream()
                .filter(l -> l.getISBN().equals(isbn))
                .findFirst()
                .orElse(null);

        if (libro == null || !libro.isDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible.");
        }

        // Buscar estudiante
        Estudiante estudiante = estudiantes.get(legajo);

        if (estudiante == null) {
            throw new EstudianteNoEncontradoException("El estudiante no existe.");
        }

        // Verificar límite de 3 préstamos
        long cantidad = prestamosActivos.stream()
                .filter(p -> p.getEstudiante().getLegajo().equals(legajo))
                .count();

        if (cantidad >= 3) {
            throw new LimitePrestamosExcedidoException("Máximo 3 préstamos permitidos.");
        }

        // Crear préstamo
        Prestamo prestamo = new Prestamo(
                libro,
                estudiante,
                LocalDate.now(),
                null);

        prestamosActivos.add(prestamo);
        libro.setDisponible(false);
    }

    // Registrar devolución
    public double registrarDevolucion(String isbn) {

        Prestamo prestamo = prestamosActivos.stream()
                .filter(p -> p.getLibro().getISBN().equals(isbn))
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            return 0;
        }

        prestamo.setFechaDevolucion(LocalDate.now());

        long dias = ChronoUnit.DAYS.between(
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion());

        prestamosActivos.remove(prestamo);
        prestamo.getLibro().setDisponible(true);

        if (dias > 0) {
            return calcularMulta((int) dias, 1000);
        }

        return 0;
    }

    // Método recursivo
    public double calcularMulta(int diasRetraso, double valorLibro) {

        if (diasRetraso <= 0) {
            return 0;
        }

        if (diasRetraso > 30) {
            diasRetraso = 30;
        }

        return (valorLibro * 0.01)
                + calcularMulta(diasRetraso - 1, valorLibro);
    }

    // Listar préstamos por estudiante
    public void listarPrestamosPorEstudiante(String legajo) {

        prestamosActivos.stream()
                .filter(p -> p.getEstudiante().getLegajo().equals(legajo))
                .forEach(System.out::println);
    }

    //lista completa de libros
    public List<Libro> getCatalogo() {
        return catalogo;
    }

    //todos los estudiantes registrados
    public Collection<Estudiante> getEstudiantes() {
        return estudiantes.values();
    }

    //todos los préstamos que existen en el sistema
    public Set<Prestamo> getPrestamosActivos() {
        return prestamosActivos;
    }
}