package MongoDBConnect;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase para gestionar la conexión a una base de datos MongoDB.
 */
public class MongoDBConnection {

    // Cliente para interactuar con MongoDB
    private MongoClient mongoClient;

    // Objeto que representa la base de datos seleccionada
    private MongoDatabase database;

    /**
     * Constructor que establece una conexión a MongoDB.
     * @param uri URI de conexión a MongoDB.
     * @param dbName Nombre de la base de datos a conectar.
     */
    public MongoDBConnection(String uri, String dbName) {
        try {
            // Crea un cliente MongoDB usando la URI proporcionada
            mongoClient = MongoClients.create(uri);

            // Obtiene la base de datos especificada por su nombre
            database = mongoClient.getDatabase(dbName);
        } catch (MongoException e) {
            // Manejo de errores: muestra un mensaje si ocurre una excepción al conectar
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener la base de datos conectada.
     * @return Un objeto MongoDatabase que representa la base de datos.
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Método para cerrar la conexión con MongoDB.
     * Libera los recursos del cliente si está abierto.
     */
    public void close() {
        if (mongoClient != null) { // Verifica si el cliente está inicializado
            mongoClient.close(); // Cierra la conexión
        }
    }
}
