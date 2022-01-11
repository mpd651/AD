package es.ivanlorenzo.ad.hoja03_mongodb_05;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author ivan
 */
public class Conexion
{
    private static Conexion instance;
    private MongoDatabase db;
    private MongoCollection<Document> coleccion;

    private Conexion()
    {
        MongoClient cliente = MongoClients.create("mongodb://root:root@localhost:27017");
        if (cliente != null)
        {
            System.out.println("Conexión OK");
            db = cliente.getDatabase("geografia");
            coleccion = db.getCollection("ccaa");
        }
    }

    public MongoDatabase getBaseDatos()
    {
        return db;
    }

    public MongoCollection<Document> getColeccion()
    {
        return coleccion;
    }

    public static Conexion getInstance()
    {
        if (instance == null)
        {
            instance = new Conexion();
        }
        return instance;
    }
}
