package unlar.edu.ar.model;

public class Libro {
    
    // Atributos definidos en la sección 2.1
    private String ISBN;
    private String titulo;
    private String autor;
    private int anio;
    private boolean disponible;

    // 1. Constructor por defecto
    public Libro() {
        // Un libro recién creado por defecto suele estar disponible
        this.disponible = true; 
    }

    // 1. Constructor parametrizado
    public Libro(String ISBN, String titulo, String autor, int anio, boolean disponible) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.disponible = disponible;
    }

    // 0. Todos los atributos privados con getters/setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // 2. Método toString() informativo
    @Override
    public String toString() {
        return "Libro {" +
                "ISBN='" + ISBN + '\'' +
                ", Titulo='" + titulo + '\'' +
                ", Autor='" + autor + '\'' +
                ", Año=" + anio +
                ", Disponible=" + (disponible ? "Sí" : "No") +
                '}';
    }
}