package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    
	private static final String URL = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10791393";
	private static final String USER = "sql10791393";
	private static final String PASSWORD = "CSyXRTGlmh";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conn;
    }
}
