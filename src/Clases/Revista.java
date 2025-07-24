package Clases;

public class Revista extends Material {
    private String issn;
    private int numero;
    private String periodicidad;

    public Revista(String titulo, String autor, double precio, int anio, String issn, int numero, String periodicidad) {
        super(titulo, autor, precio, anio);
        this.issn = issn;
        this.numero = numero;
        this.periodicidad = periodicidad;
    }

    @Override
    public String getDescripcion() {
        return "Revista: " + titulo + " - Nº: " + numero + " - Periodicidad: " + periodicidad;
    }
}

