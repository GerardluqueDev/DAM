package Main;


import MongoDBConnect.MongoDBConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        //Datos de conexión a la BBDD
        String uri = "mongodb://Gery:63ry@localhost:27017/mibasededatos";
        String dbNombre = "mibasededatos";

        //Crear la instancia de conexión a MongoDB
        MongoDBConnection dbConect = new MongoDBConnection(uri, dbNombre);

        //Obtener la BBDD
        MongoDatabase db = dbConect.getDatabase();
        System.out.println("Conectado a la BBDD: "+db.getName());
        //Los datos de una colección se pueden cargar en una lista utilizando el método find().into():
        try {
            MongoCollection<Document> coleccion = db.getCollection("empleados");
            List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
            System.out.println("Empleados:");
//            for (int i = 0; i < consulta.size(); i++) {
//                Document empleado = consulta.get(i);
//
//                System.out.println("\tNOMBRE: "+empleado.getString("nombre")+", DEPARTAMENTO: "+
//                        empleado.getInteger("dep")+", SALARIO: "+empleado.getInteger("salario")+
//                        ", FECHA DE ALTA: "+empleado.getString("fechaalta")+
//                        ", OFICIO: "+empleado.getString("oficio")+", COMISION: "+empleado.getInteger("comision"));
//            }
            //Insertar un empleado con insertOne()
            Document empleado = new Document();
            empleado.put("nombre", "Joselito");
            empleado.put("dep", 20);
            empleado.put("salario", 3600);
            empleado.put("fechaalta", "04/03/2002");
            empleado.put("oficio", "Analista");
            empleado.put("comision", 100);
            coleccion.insertOne(empleado);

            // Insertar un empleado utilizando append de Document.
            Document empleado2 = new Document("nombre","Martina")
                    .append("dep", 20)
                    .append("salario", 40000)
                    .append("fechaalta", "04/03/2002")
                    .append("oficio", "Programador")
                    .append("comision", 300);
            coleccion.insertOne(empleado2);

            //utilizar le método iterator() para recorrer el cursor.
            MongoCursor<Document> cursor = coleccion.find().iterator();
            while (cursor.hasNext()) {
                Document empleadoCursor = cursor.next();
                System.out.println(empleadoCursor.toJson());
            }
            cursor.close();

        } catch (Exception e) {
            System.out.println("ERROR durante la operación. "+e.getMessage());
        }finally{
            dbConect.close();
            System.out.println("Conexión cerrada");
        }

    }
}
