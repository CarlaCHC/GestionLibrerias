package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroDAO {

    public static void guardarRegistro(Registro r) {
        String sqlRegistro = "INSERT INTO Registro (fecha, comprador) VALUES (?, ?)";
        String sqlMaterial = "INSERT INTO Materiales (Titulo, Autor, Anio_Publicacion, Disponible, id_registro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmtRegistro = conn.prepareStatement(sqlRegistro, Statement.RETURN_GENERATED_KEYS)) {

            // Lic aqui quisimos implementar un registro sobre las ventas y un registro para el material, basicamente esto es para la BASE DE DATOS 
            stmtRegistro.setDate(1, java.sql.Date.valueOf(r.getFecha()));
            stmtRegistro.setString(2, r.getComprador());
            stmtRegistro.executeUpdate();

            // Aqui ID para el REGISTRO
            java.sql.ResultSet generatedKeys = stmtRegistro.getGeneratedKeys();
            int idRegistro = -1;

            if (generatedKeys.next()) {
                idRegistro = generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID del registro.");
            }

            for (Material m : r.getMateriales()) {
                try (PreparedStatement stmtMaterial = conn.prepareStatement(sqlMaterial)) {
                    stmtMaterial.setString(1, m.getTitulo());
                    stmtMaterial.setString(2, m.getAutor());
                    
                    stmtMaterial.setBoolean(4, m.isDisponible());
                    stmtMaterial.setInt(5, idRegistro);
                    stmtMaterial.executeUpdate();
                }
            }

            System.out.println("✅ Registro y materiales guardados en la base de datos.");

        } catch (SQLException e) {
            System.out.println("❌ Error al guardar el registro y sus materiales.");
            e.printStackTrace();
        }
    }
}
