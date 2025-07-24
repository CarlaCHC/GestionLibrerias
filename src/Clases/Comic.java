package Clases;

public class Comic extends Material {
    private String ilustrador;
    private int volumen;
    private String serie;

    public Comic(String titulo, String autor, double precio, int anio, String ilustrador, int volumen, String serie) {
        super(titulo, autor, precio, anio);
        this.ilustrador = ilustrador;
        this.volumen = volumen;
        this.serie = serie;
    }

    @Override
    public String getDescripcion() {
        return "Comic: " + titulo + " - Vol: " + volumen + " - Serie: " + serie;
    }
}
