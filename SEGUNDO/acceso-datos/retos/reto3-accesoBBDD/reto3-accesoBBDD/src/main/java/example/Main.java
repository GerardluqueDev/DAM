package example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase permite gestionar el registro de libros en una base de datos de una biblioteca,
 * incluyendo la inserción de libros, autores, editoriales y temas, comprobando si ya existen
 * en la base de datos antes de insertarlos.
 */
public class Main {
    public static void main(String[] args) {
        // Variables de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3307/biblioteca";  // URL de la base de datos
        String usuario = "root";  // Usuario de la base de datos
        String pass = "";  // Contraseña de la base de datos

        // Sentencia SQL para insertar un libro nuevo
        String sqlInsertP = "INSERT INTO `libro` " +
                "(`idLibro`, `ISBN`, `Titulo`, `NumeroEjemplares`, `idAutor`, `idEditorial`, `idTema`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        // Sentencia para llamar el procedimiento almacenado de alta de una editorial
        String prepareCall = "{CALL AltaEditorial(?, ?, ?)}";

        // Creación del objeto Scanner para leer entradas por consola
        Scanner sc = new Scanner(System.in);

        // Uso de try-with-resources para gestionar los recursos (conexión, sentencia, etc.)
        try (Connection con = DriverManager.getConnection(url, usuario, pass);  // Conexión a la base de datos
             Statement sentencia = con.createStatement();  // Sentencia para ejecutar consultas simples
             PreparedStatement sentenciaPreparada = con.prepareStatement(sqlInsertP);  // Sentencia preparada para insertar un libro
             CallableStatement cs = con.prepareCall(prepareCall);  // Sentencia para llamar al procedimiento almacenado de alta de editorial
        ) {
            System.out.println("Conectado con éxito a la Base de Datos");

            // Verificar si el libro que queremos registrar ya existe en la base de datos
            if (verificarLibro(con, sc)) {
                System.out.println("El libro existe, gracias por la consulta");
            } else {
                // Si el libro no existe, solicitar la información del libro para su registro
                System.out.println("Vaya! no disponemos de este libro en la biblioteca!");
                System.out.println("Repitame el isbn del libro para su registro porfavor");
                String isbn = sc.next();  // Leer el ISBN del libro
                System.out.println("Escriba el título del libro nuevo por favor");
                String titulo = sc.next();  // Leer el título del libro

                // Leer el número de ejemplares con validación para asegurar que se ingresa un número entero
                int ejemplares;
                while (true) {
                    try {
                        System.out.println("Introduzca cantidad de ejemplares");
                        ejemplares = sc.nextInt();  // Intentar leer un número entero
                        sc.nextLine();  // Limpiar el buffer del Scanner
                        break;  // Salir del bucle si la entrada es válida
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no válida. Por favor, ingrese un número entero:");
                        sc.nextLine();  // Limpiar el buffer del Scanner
                    }
                }

                // Solicitar el autor del libro
                System.out.println("Dígame quien es el autor del libro por favor");
                String autor = sc.nextLine();  // Leer el nombre del autor
                int autorId = existeAutor(autor, con, sc, sentencia);  // Verificar si el autor ya existe en la base de datos
                System.out.println("Dígame el editorial del libro por favor");
                String editorial = sc.nextLine();  // Leer el nombre de la editorial
                int editorialId = existeEditorial(editorial, con, sc, cs);  // Verificar si la editorial ya existe en la base de datos
                System.out.println("Dígame el tema del libro por favor");
                String tema = sc.nextLine();  // Leer el tema del libro
                int temaId = existeTema(tema, con, sc, sentencia);  // Verificar si el tema ya existe en la base de datos

                // Preparar la sentencia para insertar el libro en la base de datos
                sentenciaPreparada.setString(1, isbn);  // Establecer el valor del ISBN
                sentenciaPreparada.setString(2, titulo);  // Establecer el valor del título
                sentenciaPreparada.setInt(3, ejemplares);  // Establecer el número de ejemplares
                sentenciaPreparada.setInt(4, autorId);  // Establecer el ID del autor
                sentenciaPreparada.setInt(5, editorialId);  // Establecer el ID de la editorial
                sentenciaPreparada.setInt(6, temaId);  // Establecer el ID del tema

                // Ejecutar la sentencia para insertar el libro
                sentenciaPreparada.executeUpdate();  // Insertar el libro en la base de datos
            }

        } catch (SQLException e) {
            // En caso de error al conectar con la base de datos o ejecutar las sentencias SQL
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Verifica si el libro con el ISBN proporcionado ya existe en la base de datos.
     * @param con La conexión a la base de datos.
     * @param sc El objeto Scanner para leer la entrada del usuario.
     * @return true si el libro existe, false si no.
     * @throws SQLException Si hay un error en la consulta SQL.
     */
    public static boolean verificarLibro(Connection con, Scanner sc) throws SQLException {
        System.out.println("Introduzca el isbn del libro a consultar");
        String isbn = sc.next();  // Leer el ISBN desde la consola
        String verificarLibro = "SELECT * FROM libro WHERE isbn = ?";  // Sentencia SQL para verificar si existe un libro con ese ISBN

        // Usamos PreparedStatement para prevenir SQL Injection
        try (PreparedStatement ps = con.prepareStatement(verificarLibro)) {
            ps.setString(1, isbn);  // Establecer el valor del ISBN en la consulta
            ResultSet resultado = ps.executeQuery();  // Ejecutar la consulta
            return resultado.next();  // Si hay un resultado, el libro existe
        }
    }

    /**
     * Verifica si un autor existe en la base de datos. Si no, lo inserta.
     * @param autor El nombre del autor.
     * @param con La conexión a la base de datos.
     * @param sc El objeto Scanner para leer la entrada del usuario.
     * @param sentencia El Statement para ejecutar sentencias.
     * @return El ID del autor.
     * @throws SQLException Si hay un error en la consulta SQL.
     */
    private static int existeAutor(String autor, Connection con, Scanner sc, Statement sentencia) throws SQLException {
        String selectAutor = "SELECT idAutor FROM autor WHERE nombreAutor = ?";  // Sentencia SQL para verificar si el autor existe
        try (PreparedStatement ps = con.prepareStatement(selectAutor)) {
            ps.setString(1, autor);  // Establecer el nombre del autor en la consulta
            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("idAutor");  // Si el autor existe, devolver el ID
                } else {
                    System.out.println("Vaya! no tenemos registrado este autor, repita el nombre para su registro");
                    String nombreNuevoAutor = sc.nextLine();  // Leer el nombre del nuevo autor
                    String sentenciaAuto = "INSERT INTO autor VALUES (NULL, '" + nombreNuevoAutor + "')";  // Sentencia SQL para insertar un nuevo autor
                    sentencia.executeUpdate(sentenciaAuto);  // Ejecutar la sentencia para insertar el autor

                    return existeAutor(nombreNuevoAutor, con, sc, sentencia);  // Llamada recursiva para verificar el autor recién insertado
                }
            }
        }
    }

    /**
     * Verifica si una editorial existe en la base de datos. Si no, la inserta.
     * @param editorial El nombre de la editorial.
     * @param connection La conexión a la base de datos.
     * @param sc El objeto Scanner para leer la entrada del usuario.
     * @param call El CallableStatement para ejecutar procedimientos almacenados.
     * @return El ID de la editorial.
     * @throws SQLException Si hay un error en la consulta SQL.
     */
    private static int existeEditorial(String editorial, Connection connection, Scanner sc, CallableStatement call) throws SQLException {
        String selectEditorial = "SELECT idEditorial FROM editorial WHERE NombreEditorial = '" + editorial + "'";  // Sentencia SQL para verificar la editorial
        ResultSet salida = connection.createStatement().executeQuery(selectEditorial);  // Ejecutar la consulta SQL

        if (salida.next()) {
            return salida.getInt("idEditorial");  // Si la editorial existe, devolver su ID
        } else {
            // Si no existe, pedimos los datos para insertarla
            System.out.println("No tenemos registrada esta editorial, repitala para su registro");
            String nuevoEditorial = sc.nextLine();  // Leer el nombre de la nueva editorial
            System.out.println("Escriba la dirección");
            String nuevaDireccion = sc.nextLine();  // Leer la dirección de la nueva editorial
            System.out.println("Escriba el teléfono");
            String nuevaTelefono = sc.nextLine();  // Leer el teléfono de la nueva editorial
            call.setString(1, nuevoEditorial);  // Establecer los parámetros para el procedimiento almacenado
            call.setString(2, nuevaDireccion);
            call.setString(3, nuevaTelefono);
            call.executeUpdate();  // Ejecutar el procedimiento almacenado para insertar la editorial

            return existeEditorial(nuevoEditorial, connection, sc, call);  // Llamada recursiva para verificar la editorial recién insertada
        }
    }

    /**
     * Verifica si un tema existe en la base de datos. Si no, lo inserta.
     * @param tema El nombre del tema.
     * @param connection La conexión a la base de datos.
     * @param sc El objeto Scanner para leer la entrada del usuario.
     * @param sentencia El Statement para ejecutar las consultas.
     * @return El ID del tema.
     * @throws SQLException Si hay un error en la consulta SQL.
     */
    private static int existeTema(String tema, Connection connection, Scanner sc, Statement sentencia) throws SQLException {
        String selectTema = "SELECT idTema FROM tema WHERE NombreTema = ?";  // Sentencia SQL para verificar si el tema existe
        try (PreparedStatement ps = connection.prepareStatement(selectTema)) {
            ps.setString(1, tema);  // Establecer el nombre del tema
            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("idTema");  // Si el tema existe, devolver su ID
                } else {
                    System.out.println("Este tema no existe, ¿desea registrarlo?");
                    String nuevoTema = sc.nextLine();  // Leer el nuevo tema a insertar
                    String sentenciaInsert = "INSERT INTO tema VALUES (NULL, '" + nuevoTema + "')";  // Sentencia SQL para insertar el tema
                    sentencia.executeUpdate(sentenciaInsert);  // Ejecutar la sentencia de inserción

                    return existeTema(nuevoTema, connection, sc, sentencia);  // Llamada recursiva para verificar el tema recién insertado
                }
            }
        }
    }
}


/*
package example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/biblioteca";
        String usuario = "root";
        String pass = "";
        String sqlInsertP = "INSERT INTO `libro` " +
                "(`idLibro`, `ISBN`, `Titulo`, `NumeroEjemplares`, `idAutor`, `idEditorial`, `idTema`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        String prepareCall = "{CALL AltaEditorial(?, ?, ?)}";

        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(url, usuario, pass);
             Statement sentencia = con.createStatement();
             PreparedStatement sentenciaPreparada = con.prepareStatement(sqlInsertP);
             CallableStatement cs = con.prepareCall(prepareCall);
        ) {
            System.out.println("Conectado con éxito a la Base de Datos");

            //Verificamos si el libro que queremos insertar existe ya en la BBDD comprobando el isbn
            if (verificarLibro(con,sc)){
                System.out.println("El libro existe, gracias por la consulta");
            }else {
                System.out.println("Vaya! no disponemos de este libro en la biblioteca!");
                System.out.println("Repitame el isbn del libro para su registro porfavor");
                String isbn = sc.next();
                System.out.println("Escriba el título del libro nuevo por favor");
                String titulo = sc.next();
                System.out.println("Dígame el número de ejemplares por favor");
                int ejemplares;
                while (true) {
                    try {
                        ejemplares = sc.nextInt(); // Intentar leer un entero
                        sc.nextLine(); // Consumir el salto de línea
                        break; // Salir del bucle si la entrada es válida
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no válida. Por favor, ingrese un número entero:");
                        sc.nextLine(); // Limpiar el flujo de entrada para evitar un bucle infinito
                    }
                }
                System.out.println("Dígame quien es el autor del libro por favor");
                String autor = sc.nextLine();
                int autorId = existeAutor(autor,con,sc,sentencia);
                System.out.println("Dígame el editorial del libro por favor");
                String editorial = sc.nextLine();
                int editorialId = existeEditorial(editorial,con,sc,cs);
                System.out.println("Dígame el tema del libro por favor");
                String tema = sc.nextLine();
                int temaId = existeTema(tema,con,sc,sentencia);

                //Sentencia preparada
                sentenciaPreparada.setString(1,isbn);
                sentenciaPreparada.setString(2,titulo);
                sentenciaPreparada.setInt(3,ejemplares);
                sentenciaPreparada.setInt(4,autorId);
                sentenciaPreparada.setInt(5,editorialId);
                sentenciaPreparada.setInt(6,temaId);
                sentenciaPreparada.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
    }
    public static boolean verificarLibro(Connection con, Scanner sc) throws SQLException {
        System.out.println("Introduzca el isbn del libro a consultar");
        String isbn = sc.next();
        String verificarLibro = "SELECT * FROM libro WHERE isbn = ?";
        try (PreparedStatement ps = con.prepareStatement(verificarLibro)) {
            ps.setString(1, isbn);
            ResultSet resultado = ps.executeQuery();
            return resultado.next();
        }
        //String verificarLibro = "SELECT * FROM libro WHERE isbn = '" + isbn + "'";
        //ResultSet resultado = con.createStatement().executeQuery(verificarLibro);
        //return resultado.next();
    }
    private static int existeAutor(String autor, Connection con,Scanner sc, Statement sentencia) throws SQLException {
        String selectAutor = "SELECT idAutor FROM autor WHERE nombreAutor = ?";
        try (PreparedStatement ps = con.prepareStatement(selectAutor)) {
            ps.setString(1, autor);
            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("idAutor");
                }else {
                    System.out.println("Vaya! no tenemos registrado este autor, repita el nombre para su registro");
                    String nombreNuevoAutor = sc.nextLine();
                    String sentenciaAuto = "INSERT INTO autor VALUES (NULL, '" + nombreNuevoAutor + "')";
                    sentencia.executeUpdate(sentenciaAuto);

                    return existeAutor(nombreNuevoAutor,con,sc,sentencia);
                }
            }
        }
        /*String selectAutor = "SELECT idAutor FROM autor WHERE nombreAutor = '" + autor + "'";
        ResultSet salida = con.createStatement().executeQuery(selectAutor);
        if (salida.next()) {
            return salida.getInt("idAutor");
        }

    }
    private static int existeEditorial(String editorial, Connection connection,Scanner sc, CallableStatement call) throws SQLException {
        String selectEditorial = "SELECT idEditorial FROM editorial WHERE NombreEditorial = '" + editorial + "'";
        ResultSet salida = connection.createStatement().executeQuery(selectEditorial);
        if (salida.next()) {
            return salida.getInt("idEditorial");
        }else{
            System.out.println("No tenemos registrada esta editorial, repitala para su registro");
            String nuevoEditorial = sc.nextLine();
            System.out.println("Escriba la dirección");
            String nuevaDireccion = sc.nextLine();
            System.out.println("Escriba el teléfono");
            String nuevaTelefono = sc.nextLine();
            call.setString(1,nuevoEditorial);
            call.setString(2,nuevaDireccion);
            call.setString(3,nuevaTelefono);
            call.executeUpdate();

            return existeEditorial(nuevoEditorial,connection,sc,call);
        }

    }
    private static int existeTema(String tema, Connection connection, Scanner sc, Statement sentencia) throws SQLException {
        String selectTema = "SELECT idTema FROM tema WHERE NombreTema = '" + tema + "'";
        ResultSet salida = connection.createStatement().executeQuery(selectTema);

        if(salida.next()){
            return salida.getInt("idTema");
        }else{
            System.out.println("No tenemos este tema registrado, repitame el nombre para su registro");
            String nombreTema= sc.nextLine();
            String sentenciaInsert = "INSERT INTO tema VALUES(null,'" + nombreTema + "')";
            sentencia.executeUpdate(sentenciaInsert);

            return existeTema(nombreTema,connection,sc,sentencia);

        }
    }
}
*/