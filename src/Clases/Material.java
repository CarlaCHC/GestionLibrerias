package Clases;

import java.io.Serializable;

public abstract class Material implements Serializable {
   

    protected String titulo;
    protected String autor;
    protected double precio;
    protected int anioPublicacion;
    protected boolean disponible;

    public Material(String titulo, String autor, double precio, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
    }
    


    public abstract String getDescripcion();

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    
}
