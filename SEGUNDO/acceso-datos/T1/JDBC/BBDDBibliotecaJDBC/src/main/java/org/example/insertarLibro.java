package org.example;

import java.sql.*;

/**
 * Insertar Libro
 *
 */
public class insertarLibro {
    public static void main( String[] args ) {
        //user-biblioteca'@localhost IDENTIFIED BY 'Gery1489
        String url = "jdbc:mysql://localhost:3307/biblioteca";
        String usuario = "root";
        String pass = "";
        try (Connection con = DriverManager.getConnection(url, usuario, pass);
             Statement sentencia = con.createStatement();){


            System.out.println("Todo correcto");

            //Visualizar libros
            visualizarLibro(sentencia);


            //Insertar libro

            /*    //tenemos que gestionar:
            String autor = "";
            String editorial = "";
            String tema = "";
            */

           /*     //Inserción FALSA.. en el mejor de los casos:
            String titulo = "Victoria-falso";
            String isbn = "45625565-falso";
            int numeroEjemplares = 50;
            int autor = 2;
            int editorial = 4;
            int tema = 4;
            String sentenciaInsert = "INSERT INTO libro VALUES(null,'"+isbn+"','"+titulo+
                    "',"+numeroEjemplares+",'"+autor+"','"+editorial+"','"+tema+"')";
            sentencia.executeUpdate(sentenciaInsert);
            System.out.println("Insercion falsa correcta");
            */
            String autor = "";
            String editorial = "";
            String tema = "";
            String titulo = "";
            String isbn = "1234567891";
            int numeroEjemplares = 0;
            //Si existe o no el isbn
            //Si no existe algo...lo creamos...y nos quedamos con su ID
            //Si existe algo...nos quedamos con su ID
            //Al final hacemos la inserción
            String selectLibro = "SELECT * FROM libro WHERE isbn='"+isbn+"'";
            ResultSet sentenciaSelect = sentencia.executeQuery(selectLibro);
            if (sentenciaSelect.next()){ //Si existe
                System.out.println("El libro ya existe");
            }else{ //Si no existe hay que darlo de alta
                //Empezamos con el autor
                if (!existeAutor(autor, con)){
                    altaAutor(autor);
                }
            }


        }catch (SQLException e){
            System.out.println("error en INSERT");
            System.out.println(e.getMessage());
        }
    }

    private static void visualizarLibro(Statement sentencia) throws SQLException {
        String sentenciaSelect = "SELECT * FROM libro NATURAL JOIN(autor,editorial,tema);";
        ResultSet cursor = sentencia.executeQuery(sentenciaSelect);
        while (cursor.next()){
            String isbn = cursor.getString("ISBN");
            String titulo = cursor.getString("Titulo");
            int numeroEjemplares = cursor.getInt("numeroEjemplares");
            System.out.println("Titulo: "+titulo+" ISBN: "+isbn+" Ejemplares: "+numeroEjemplares);
        }
    }

    private static boolean existeAutor(String autor, Connection con) throws SQLException {
        String selectAutor = "SELECT idAutor FROM autor WHERE nombreAutor = '"+autor+"'";
        ResultSet salida = con.createStatement().executeQuery(selectAutor);
        return salida.next();

    }
}
