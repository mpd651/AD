package es.marioperez.mongohoja5;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Accumulators.addToSet;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.bson.Document;
/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        MongoCollection<Document> ccaa=establecerConexion();
        Scanner tecladoi = new Scanner(System.in);
        Scanner teclados=new Scanner(System.in);
        int opcion;
        
        do
        {
            System.out.println(""
                    + "1.- Añadir Comunidad Autónoma sin provincias\n"
                    + "2.- Consultar Comunidad Autónoma\n"
                    + "3.- Asignar provincias a Comunidad Autónoma\n"
                    + "4.- Añadir provincia a Comunidad Autónoma\n"
                    + "5.- Modificar nombre de Comunidad Autónoma\n"
                    + "6.- Eliminar provincia en Comunidad Autónoma\n"
                    + "7.- Asignar capital a Comunidad Autónoma\n"
                    + "8.- Asignar fecha Estatuto Autonomía\n"
                    + "9.- Eliminar Comunidad Autónoma\n"
                    + "0. -Salir");
            System.out.println("Introduzca una opcion del menu");
            opcion = tecladoi.nextInt();
            
            switch (opcion){
                case 1:
                    ccaa.insertOne(CrearDocCCAASinProvincias());
                    break;
                case 2:
                    Document ca2=BuscarCCAA(ccaa);
                    if (ca2!=null){
                        System.out.println("Nombre: "+ca2.getString("nombre")+", habitantes: "+ca2.getInteger("habitantes")
                        +", extension: "+ca2.getInteger("extension")+", capital: "+ca2.getString("capital.nombre"));
                        System.out.println("\n FORMATO JASON:\n"
                                + ca2.toJson());
                    }else{
                        System.out.println("La ca autonoma con ese codigo no existe");
                    }
                    
                    break;
                case 3:
                    Document ca3=BuscarCCAA(ccaa);
                    if (ca3!=null){
                        String continuar="si";
                        int contador=0;
                        ArrayList provincias=new ArrayList();
                        do
                        {
                            System.out.println("Añade una provincia");
                            provincias.add(teclados.nextLine());
                            System.out.println("Desea añadir mas provincias? (si/no)");
                            continuar=teclados.nextLine();
                        } while (continuar.equalsIgnoreCase("si"));
                        ccaa.updateOne(eq("codigo",ca3.getString("codigo")), set("provincias", provincias));
                        
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;
                case 4:
                    Document ca4=BuscarCCAA(ccaa);
                    if (ca4!=null){
                        System.out.println("Introduzca nombre de la provincia a añadir");
                        String provincia=teclados.nextLine();
                        UpdateResult result=ccaa.updateOne(eq("codigo",ca4.getString("codigo")), Updates.addToSet("provincias",provincia));
                        if (result.getModifiedCount()>0){
                            System.out.println("Se ha añadido la provincia correctamente");
                        }else{
                            System.out.println("No se ha añadido la provincia correctamente");
                        }
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;                    
                case 5:
                    Document ca5=BuscarCCAA(ccaa);
                    if (ca5!=null){
                        System.out.println("Introduzca el nuevo nombre para la comunidad autonoma");
                        String nombre=teclados.nextLine();
                        ccaa.updateOne(eq("codigo", ca5.getString("codigo")), set("nombre", nombre));
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;    
                case 6:
                    Document ca6=BuscarCCAA(ccaa);
                    if (ca6!=null){
                        System.out.println("Introduce el nombre de la provincia a eliminar");
                        String provincia=teclados.nextLine();
                        DeleteResult result=ccaa.deleteOne(eq("provincias."+provincia, "provincias."+provincia));
                        if (result.getDeletedCount()>0){
                            System.out.println("Se ha borrado la provincia");
                        }else{
                            System.out.println("No se ha borrado");
                        }
//                        ArrayList<String> provincias=(ArrayList)ca6.get("provincias");
//                        Iterator itr = provincias.iterator();
//                        while (itr.hasNext()){
//                            String p=(String) itr.next();
//                            if (p.equalsIgnoreCase(provincia)){
//                                itr.remove();
//                                System.out.println("Se ha borrado la provincia");
//                            }
//                        }
//                        ccaa.updateOne(eq("codigo",ca6.getString("codigo")), set("provincias", provincias));
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;    
                case 7:
                    Document ca7=BuscarCCAA(ccaa);
                    if (ca7!=null){
                        Object obj=ca7.get("capital");
                        if (obj==null){
                            System.out.println("Introduce nombre de la capital:");
                            String nombreCap=teclados.nextLine();
                            System.out.println("Introduce habitantes de la capital:");
                            int habitantesCap=tecladoi.nextInt();
                            ccaa.updateOne(eq("codigo",ca7.getString("codigo")), set("capital", 
                                    new Document("nombre",nombreCap).append("habitantes", habitantesCap)));
                        }else{
                            System.out.println("La ca ya tiene capital");
                        }
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;
                case 8:
                    Document ca8=BuscarCCAA(ccaa);
                    if (ca8!=null){
                        System.out.println("Introduce la fecha de vigor del estatuto de autonomia (dd/MM/yyyy)");
                        
                    }else{
                        System.out.println("La ca con ese codigo no existe");
                    }
                    break;    
                case 9:
                    Document ca9=BuscarCCAA(ccaa);
                    if (ca9!=null){
                        System.out.println(ca9.toJson());
                        System.out.println("Se quiere eliminar esta comunidad?(S/N)");
                        String respuesta=teclados.nextLine();
                        if (respuesta.equals("S")){
                            DeleteResult result=ccaa.deleteOne(eq("codigo",ca9.getString("codigo")));
                            if (result.getDeletedCount()>0){
                                System.out.println("La ca se ha eliminado");
                            }else{
                                System.out.println("No se ha eliminado");
                            }
                        }
                    }
                    break;   
            }
        } while (opcion != 0);

    }
    
    
    public static MongoCollection<Document> establecerConexion(){
        MongoClient cliente = MongoClients.create("mongodb://root:root@localhost:27017");
        MongoCollection<Document> ccaa=null;
        if (cliente==null){
            System.out.println("No se ha establecido la conexion");
        }
        else
        {
            MongoDatabase db = cliente.getDatabase("geografia");
            ccaa = db.getCollection("ccaa");
            System.out.println("Conexion establecida");                       
        }
        return ccaa;
    }
    
    public static Document CrearDocCCAASinProvincias(){
        Scanner tecladoi = new Scanner(System.in);
        Scanner teclados=new Scanner(System.in);
        
        System.out.println("Introduce codigo de la ccaa");
        String codigo=teclados.nextLine();
        System.out.println("Introduce el nombre de la ccaa");
        String nombre=teclados.nextLine();
        System.out.println("Introduce la abreviatura de la ccaa");
        String abreviatura=teclados.nextLine();
        System.out.println("Introduce el nombre de la capital de la ccaa");
        String nombreCapital=teclados.nextLine();
        System.out.println("Introduce los habitantes de la capital de la ccaa");
        int habitantesCapital=tecladoi.nextInt();
        System.out.println("Introduce los habitantes de la ccaa");
        int habitantes=tecladoi.nextInt();
        System.out.println("Introduce la extension de la ccaa");
        int extension=tecladoi.nextInt();
        
        Document docCCAA=new Document("codigo",codigo)
                .append("nombre", nombre)
                .append("abreviatura", abreviatura)
                .append("capital",
                        new Document("nombre",nombreCapital)
                            .append("habitantes", habitantesCapital))
                .append("habitantes", habitantes)
                .append("extension", extension);
        return docCCAA;
    }   
    
    public static Document BuscarCCAA(MongoCollection<Document> ccaa){
        Scanner teclados = new Scanner(System.in);
        System.out.println("Introduce el codigo de la ccaa a buscar");
        String codigo=teclados.nextLine();
        Document doc=ccaa.find(eq("codigo",codigo)).first();
        return doc;
        
        
       
    }
}
