package Clases;

public class Libro extends Material {
    private String isbn;
    private String editorial;
    private int numPaginas;

    public Libro(String titulo, String autor, double precio, int anio, String isbn, String editorial, int numPaginas) {
        super(titulo, autor, precio, anio);
        this.isbn = isbn;
        this.editorial = editorial;
        this.numPaginas = numPaginas;
    }

    @Override
    public String getDescripcion() {
        return "Libro: " + titulo + " - " + autor + " - ISBN: " + isbn;
    }
}
