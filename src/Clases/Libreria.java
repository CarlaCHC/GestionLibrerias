package Clases;
import Clases.Stock;

import java.util.ArrayList;
import java.util.List;

public class Libreria {
    private String nombre;
    private Stock stock;
    private ArrayList<Registro> ventas;

    public Libreria(String nombre) {
        this.nombre = nombre;
        this.stock = new Stock();
        this.ventas = new ArrayList<>();

        List<Material> stockCargado = Persistencia.cargar(ARCHIVO_STOCK);
        if (stockCargado != null) stock.setMateriales(stockCargado);

        List<Registro> ventasCargadas = Persistencia.cargar(ARCHIVO_VENTAS);
        if (ventasCargadas != null) ventas.addAll(ventasCargadas);
    }


    private static final String ARCHIVO_STOCK = "stock.dat";
    private static final String ARCHIVO_VENTAS = "ventas.dat";

    
    public void agregarMaterialAlStock(Material m) {
        stock.agregarMaterial(m);
        Persistencia.guardar(ARCHIVO_STOCK, stock.getMateriales());
    }

    public void venderMaterial(String titulo, String comprador) {
        Material m = stock.buscarPorTitulo(titulo);
        if (m != null) {
            Registro r = new Registro(comprador);
            r.agregarMaterial(m);
            ventas.add(r);
            Persistencia.guardar(ARCHIVO_STOCK, stock.getMateriales());
            Persistencia.guardar(ARCHIVO_VENTAS, ventas);
            r.imprimirFactura();
        } else {
            System.out.println("Material no disponible.");
        }
    }


    public void generarReporteVentas() {
        double total = 0;
        for (Registro r : ventas) {
            total += r.calcularVentaTotal();
        }
        System.out.println("Ventas totales: Bs " + total);
    }

    public void mostrarStock() {
        stock.mostrarStock();
    }

    public void mostrarAgotados() {
        stock.alertarAgotados();
    }

    public String getStockList() {
        StringBuilder sb = new StringBuilder();
        for (Material m : stock.getMateriales()) {
            if (m.isDisponible()) {
                sb.append(m.getDescripcion()).append("\n");
            }
        }
        return sb.toString();
    }

   
    public String getVentasResumen() {
        StringBuilder sb = new StringBuilder();
        double totalGeneral = 0;
        for (Registro r : ventas) {
            sb.append("Venta ID: ").append(r.getId()).append(" | Comprador: ").append(r.getComprador()).append(" | Total: Bs ").append(r.calcularVentaTotal()).append("\n");
            totalGeneral += r.calcularVentaTotal();
        }
        sb.append("\nTotal general vendido: Bs ").append(totalGeneral);
        return sb.toString();
    }

    
    public String getMaterialesAgotados() {
        StringBuilder sb = new StringBuilder();
        for (Material m : stock.getMateriales()) {
            if (!m.isDisponible()) {
                sb.append(m.getDescripcion()).append(" - AGOTADO\n");
            }
        }
        return sb.toString();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    
}
