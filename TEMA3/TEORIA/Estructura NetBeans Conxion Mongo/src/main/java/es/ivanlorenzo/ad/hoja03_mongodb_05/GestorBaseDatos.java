package es.ivanlorenzo.ad.hoja03_mongodb_05;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author ivan
 */
public class GestorBaseDatos
{
    private final MongoCollection<Document> coleccion;
    
    
    public GestorBaseDatos()
    {
        coleccion = Conexion.getInstance().getColeccion();
    }
    
    
    public void insertaComAut()
    {
       

    }

    public void consultaComunidad()
    {
        

    }

    public void asignaProvinciasComunidad()
    {
        
    }

    public void addProvinciaComunidad()
    {
        
    }

    public void modificaNombre()
    {
        
    }

    public void eliminaProvinciaComunidad()
    {
        
    }

    public void asignaCapitalComunidad()
    {
        
    }

    public void asignaFechaEstatuto()
    {
       
    }

    public void eliminaComunidad()
    {
        
    }
}
