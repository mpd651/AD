package es.marioperez.hoja6;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.size;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author usuario
 */
public class GestorBaseDatos {
    private final MongoCollection<Document> coleccion;
    
    public GestorBaseDatos()
    {
        coleccion = Conexion.getInstance().getColeccion();
    }
    
    public void obtenerCCAAyCapitales(){
        MongoCursor<Document> cursor=coleccion.find().projection(include("nombre", "capital.nombre")).iterator();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            System.out.println(doc.toJson());
        }
    }
    
    public void obtenerCCAAconHabitantesEntre(){
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce un numero minimo de habitantes");
        int min=tecladoi.nextInt();
        System.out.println("Introduce un numero maximo de habitantes");
        int max=tecladoi.nextInt();
        
        MongoCursor<Document> cursor=coleccion.find(and(gt("habitantes",min), (lt("habitantes", max)))).sort(descending("habitantes")).iterator();
        
        while (cursor.hasNext()){
            Document doc=cursor.next();
            System.out.println(doc.getString("nombre")+ "---"+doc.get("habitantes", Number.class).longValue());
        }
    }
    
    public void obtenerCCAAconUnaProvincia(){
        MongoCursor<Document> cursor=coleccion
                .find(size("provincias", 1))
                .sort(descending("habitantes"))
                .sort(ascending("nombre"))
                .iterator();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            System.out.println("nombre: "+doc.getString("nombre"));
        }
    }
    
    public void obtenerCapitalesConMasHabitantes(){
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el numero de habitantes a partir del cual buscar capitales");
        int habitantesCapital=tecladoi.nextInt();
        
        MongoCursor<Document> cursor=coleccion.find(gt("capital.habitantes",habitantesCapital)).sort(ascending("capital.nombre")).iterator();
        
        while (cursor.hasNext()){
            Document doc=cursor.next();
            Document capital=(Document)doc.get("capital");
            System.out.println("Nombre: "+capital.getString("nombre"));
        }
    }
    
    public void obtenerCCAAsinFechaEstatuto(){
        MongoCursor<Document> cursor=coleccion.find
        (or
        (eq("fecha_estatuto",null), 
        (exists("fecha_estatuto",false))))
        .iterator();
        
        while (cursor.hasNext()){
            Document doc=cursor.next();
            System.out.println("nombre: "+doc.getString("nombre"));
        }
    }
    
    public void obternerCCAA(){
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el codigo de la ccaa");
        String codigo=teclado.nextLine();
        
        Document doc=coleccion.find(eq("codigo",codigo)).first();
        if (doc!=null){
            System.out.println("CCAA: "+doc.getString("nombre"));
            List<String> lista=(List<String>) doc.get("provincias");
            
            for (String l:lista){
                System.out.println(l);
            }
        }
    }
    
    public void guardarFichero() throws IOException{
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero");
        String ruta=teclado.nextLine();
        BufferedWriter escritor=new BufferedWriter(new FileWriter(new File(ruta)));
        
        
        MongoCursor<Document> cursor=coleccion.find().projection(fields(include("nombre","capital","provincias","habitantes","extension"), exclude("_id"))).iterator();
        
        while(cursor.hasNext()){
            Document doc=cursor.next();
            JsonWriterSettings configSalidaJson= JsonWriterSettings.builder().indent(true).build();
            escritor.write(doc.toJson(configSalidaJson)+"\n");
        }
        escritor.close();
        
    }
    
    
}
