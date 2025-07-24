package Clases;

import java.io.*;
import java.util.List;

public class Persistencia {

    public static <T> void guardar(String nombreArchivo, List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> cargar(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}
