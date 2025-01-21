package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/coches";
        String user = "user-coches";
        String pass = "C0ch3s1234";
        String sqlInsertPrep = "INSERT INTO `coche` (`id`, `marca`, `modelo`) VALUES (?, ?, ?)";

        try (Scanner sc = new Scanner(System.in);
             Connection con = DriverManager.getConnection(url, user, pass);
             Statement sentencia = con.createStatement();
             PreparedStatement sentenciaPreparada = con.prepareStatement(sqlInsertPrep)) {

            System.out.println("Conexión correcta");

//Sentencia preparada PrepareStatement:
            System.out.println("Introduce id, marca y modelo");
            int id = sc.nextInt();
            String marca = sc.next();
            String modelo = sc.next();

            sentenciaPreparada.setInt(1, id);
            sentenciaPreparada.setString(2, marca);
            sentenciaPreparada.setString(3, modelo);
            sentenciaPreparada.executeUpdate();

           /*
           //Creación de una tabla
           String creacionTabla = "CREATE TABLE `coches`.`coche` " +
                    "(`id` INT NOT NULL , `marca` VARCHAR(255) NOT NULL , " +
                    "`modelo` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            if (sentencia.execute(creacionTabla)) {
                System.out.println("creación de la tabla coche correcta");
            } else {
                System.out.println("creación de la tabla coche incorrecta");
            }
            //Insertar valores a la tabla
            String instertarC1 = "INSERT INTO `coche` (`id`, `marca`, `modelo`) VALUES ('1', 'Audi', 'Q5')";
            String instertarC2 = "INSERT INTO `coche` (`id`, `marca`, `modelo`) VALUES ('2', 'Mercedes', 'Benz')";
            String insertC3 = "INSERT INTO `coche` (`id`, `marca`, `modelo`) VALUES ('3', 'Renault', 'Clio')";
            sentencia.executeUpdate(instertarC1);
            sentencia.executeUpdate(instertarC2);
            sentencia.executeUpdate(insertC3);

            //Modificar valores
            String modificarC2 = "UPDATE `coche` SET `marca` = 'BMW', `modelo` = 'M3' WHERE `coche`.`id` = 2";
            sentencia.executeUpdate(modificarC2);

            //Borrar un valor de la tabla
            String borrarC1 = "DELETE FROM coche WHERE `coche`.`id` = 1";
            sentencia.executeUpdate(borrarC1);*/

            //Hacer una query
            String sentenciaSelect ="SELECT * FROM `coche`";
            ResultSet rs = sentencia.executeQuery(sentenciaSelect);//Ahora rs contiene todas las filas
            System.out.println("Tabla coches");
            while (rs.next()){
                System.out.print(rs.getInt(1)+" ");
                System.out.print(rs.getString(2)+" ");
                System.out.println(rs.getString(3));
                System.out.println("------------------");
            }


        } catch (SQLException e) {
            System.out.println("Fallo en SQL");
        }
    }
}
