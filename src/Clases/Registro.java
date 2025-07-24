package Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Registro implements Serializable {

    private static int contador = 1;
    private int id;
    private LocalDate fecha;
    private String comprador;
    private ArrayList<Material> materiales;

    public Registro(String comprador) {
        this.id = contador++;
        this.fecha = LocalDate.now();
        this.comprador = comprador;
        this.materiales = new ArrayList<>();
    }

    public void agregarMaterial(Material m) {
        if (m.isDisponible()) {
            materiales.add(m);
            m.setDisponible(false);
        }
    }
    public int getId() {
        return id;
    }

    public String getComprador() {
        return comprador;
    }
    
   

    public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Registro.contador = contador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public double calcularVentaTotal() {
        double total = 0;
        for (Material m : materiales) {
            total += m.getPrecio();
        }
        return total;
    }

    public void imprimirFactura() {
        System.out.println("Factura #" + id + " - " + fecha);
        for (Material m : materiales) {
            System.out.println(m.getDescripcion() + " - Bs " + m.getPrecio());
        }
        System.out.println("TOTAL: Bs " + calcularVentaTotal());
    }
}
