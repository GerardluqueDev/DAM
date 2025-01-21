package jdbc;

import java.sql.*;

public class trabajandoJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/biblioteca";  // URL de la base de datos
        String usuario = "root";  // Usuario de la base de datos
        String pass = "";  // Contraseña de la base de datos

        String sql = "INSERT INTO `editorial` (`idEditorial`,`NombreEditorial`,`Direccion`,`Telefono`) VALUES (NULL,?,?,?)";
        String sqlStatement = "SELECT * FROM editorial";
        String prepareCall = "{Call insertar_Autor(?)}";
        try (Connection connection = DriverManager.getConnection(url,usuario,pass);
             Statement st = connection.createStatement();
             PreparedStatement ps = connection.prepareStatement(sql);
             CallableStatement cs = connection.prepareCall(prepareCall)) {

            System.out.println("Conexion con exito");
            String nombrEditorial = "WBC";
            String direccionEditorial = "C/ Las Barracas nº24";
            String telefonoEditorial = "697990737";
            if(verificareditorial(connection,nombrEditorial)){
                System.out.println("El editorial existe");
            }else{
                System.out.println("El editorial no existe, se creara un editorial:");
                insertarEditorial(nombrEditorial,direccionEditorial,telefonoEditorial,ps);
                if(verificareditorial(connection,nombrEditorial)){
                    System.out.println("Editorial insertado");
                }else{
                    System.out.println("Error al insertar el editorial");
                }
            }
            verEditoriales(st,sqlStatement);
            String nombre = "Alfonsito";
            verificarAutor(nombre,cs,st);
            mostrarAutores(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarAutores(Statement st) throws SQLException {
        String sql = "SELECT * FROM autor";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID = "+rs.getInt(1)+" NOMBRE = "+rs.getString(2));
        }
    }

    private static void verificarAutor(String nombre, CallableStatement cs,Statement st) throws SQLException {
        String sql = "SELECT * FROM autor WHERE NombreAutor='"+nombre+"'";
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            System.out.println("Autor existente");
        }else{
            System.out.println("Autor no existe, le damos de alta");
            cs.setString(1,nombre);
            cs.executeUpdate();
        }
    }

    private static void verEditoriales(Statement st,String query) throws SQLException {
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println("ID: "+rs.getString(1));
            System.out.println("NOMBRE: "+rs.getString(2));
            System.out.println("DIRECCION: "+rs.getString(3));
            System.out.println("TELEFONO: "+rs.getString(4));
            System.out.println("=========================================");
        }
    }

    private static void insertarEditorial(String nombrEditorial, String direccionEditorial, String telefonoEditorial, PreparedStatement ps) throws SQLException {
        ps.setString(1,nombrEditorial);
        ps.setString(2,direccionEditorial);
        ps.setString(3,telefonoEditorial);
        ps.executeUpdate();
    }

    private static boolean verificareditorial(Connection connection, String nombrEditorial) throws SQLException {
        String sql = "SELECT * FROM editorial WHERE NombreEditorial = '"+nombrEditorial+"'";
        ResultSet rs = connection.createStatement().executeQuery(sql);
        return rs.next();
    }


}
