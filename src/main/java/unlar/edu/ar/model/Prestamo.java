package unlar.edu.ar.model;

import java.time.LocalDate;

public class Prestamo {

    // Atributos definidos en la sección 2.1
    private Libro libro;
    private Estudiante estudiante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // 1. Constructor por defecto
    public Prestamo() {
    }

    // 1. Constructor parametrizado
    public Prestamo(Libro libro, Estudiante estudiante, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // 0. Todos los atributos privados con getters/setters
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // 2. Método toString() informativo
    @Override
    public String toString() {
        return "Prestamo {" +
                "Libro=" + (libro != null ? libro.getTitulo() : "Ninguno") +
                ", Estudiante=" + (estudiante != null ? estudiante.getNombre() : "Ninguno") +
                ", Fecha de Préstamo=" + fechaPrestamo +
                ", Fecha de Devolución=" + (fechaDevolucion != null ? fechaDevolucion : "Aún no devuelto") +
                '}';
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Prestamo)) return false;
    Prestamo prestamo = (Prestamo) o;
    return libro.getISBN().equals(prestamo.libro.getISBN());
}

@Override
public int hashCode() {
    return libro.getISBN().hashCode();
}
}
