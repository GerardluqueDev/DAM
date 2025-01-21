package jdbc;

import java.sql.*;

public class trabajarJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/biblioteca";  // URL de la base de datos
        String usuario = "root";  // Usuario de la base de datos
        String pass = "";  // Contraseña de la base de datos

        String sql = "";
        try (Connection connection = DriverManager.getConnection(url,usuario,pass);
             Statement st = connection.createStatement();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            System.out.println("Conexion con exito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
