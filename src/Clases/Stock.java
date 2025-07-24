package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable {
	
    private List<Material> materiales;

    public Stock() {
        materiales = new ArrayList<>();
    }

    public void agregarMaterial(Material m) {
        materiales.add(m);
    }

    public void mostrarStock() {
        for (Material m : materiales) {
            if (m.isDisponible()) {
                System.out.println(m.getDescripcion());
            }
        }
    }

    public Material buscarPorTitulo(String titulo) {
        for (Material m : materiales) {
            if (m.getTitulo().equalsIgnoreCase(titulo) && m.isDisponible()) {
                return m;
            }
        }
        return null;
    }

    public void alertarAgotados() {
        for (Material m : materiales) {
            if (!m.isDisponible()) {
                System.out.println("AGOTADO: " + m.getDescripcion());
            }
        }
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }
}
