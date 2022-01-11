package es.marioperez.hoja07;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class GestorBaseDatos {
    private final MongoCollection<Document> coleccionAlumnos;
    private final MongoCollection<Document> coleccionCursos;
    
    public GestorBaseDatos()
    {
        coleccionAlumnos = Conexion.getInstance().getColeccionAlumnos();
        coleccionCursos= Conexion.getInstance().getColeccionCursos();
    }
    
    public void AnadirNotas(){
        Scanner teclado=new Scanner(System.in);
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el id del curso");
        String id=teclado.nextLine();
        Document doc=coleccionCursos.find(eq("id",id)).first();
        
        if (doc==null){
            System.out.println("id no existe");
        }else{
            MongoCursor<Document> cursor=coleccionAlumnos.find(eq("curso", id)).iterator();
            
            while (cursor.hasNext()){
                Document docAlumnos=cursor.next();
                System.out.println("nombre: "+docAlumnos.getString("nombre")+", Apellidos: "+docAlumnos.getString("apellidos"));
                System.out.println("Introduce la nota :");
                int nota=tecladoi.nextInt();
                String idAlumno=docAlumnos.getString("_id");
                
                coleccionAlumnos.updateOne((eq("_id", idAlumno)), push("notas", nota));
            }
        }
    }
    
    public void AnadirTema(){
        Scanner teclado=new Scanner(System.in);
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el id del curso");
        String id=teclado.nextLine();
        Document doc=coleccionCursos.find(eq("id",id)).first();
        
        if (doc==null){
            System.out.println("id no existe");
        }else{
            System.out.println("Introduce el nombre del tema");
            String titulo=teclado.nextLine();
            System.out.println("Introduce el numero de horas");
            int horas=teclado.nextInt();
            coleccionCursos.updateOne(eq("id", id), addToSet("temas",new Document("titulo", titulo).append("horas", horas)));
        }
    }
    
    public void ObtenerAlumnosCurso(){
        Scanner teclado=new Scanner(System.in);
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el id del curso");
        String id=teclado.nextLine();
        Document doc=coleccionCursos.find(eq("id",id)).first();
        
        if (doc==null){
            System.out.println("id no existe");
        }else{
            System.out.println("Titulo curso: "+doc.getString("titulo")+", horas: "+doc.getInteger("horas"));
            MongoCursor<Document> cursor=coleccionAlumnos.find(
            and(eq("curso", id), gte("nota_media",5))).sort(ascending("apellidos", "nombre")).iterator();
            
            while (cursor.hasNext()){
                Document docAlumno=cursor.next();
                //String.format(id, args)
                System.out.printf("%-30s-16s%4.2f\n",
                        docAlumno.getString("apllidos"),
                        docAlumno.getString("nombre"),
                        doc.getDouble("nota_media"));
            }
        }
    }
    
    public void NumeroAlumnosCurso(){
        MongoCursor cursor=coleccionCursos.find().iterator();
        
        while (cursor.hasNext()){
            Document doc=(Document) cursor.next();
            int alumnos=(int) coleccionAlumnos.countDocuments(eq("curso", doc.getString("id") ));
            System.out.println("Curso: "+doc.getString("nombre")+", alumnos: "+alumnos);
        }
    }
    
    public void NumeroAlumnosCursoAgregate(){
        MongoCursor cursor = coleccionAlumnos.aggregate(
                Arrays.asList(
                        Aggregates.group("$curso", Accumulators.sum("num", 1))
                )).iterator();
        
        while (cursor.hasNext()){
            Document doc=(Document) cursor.next();
            String idCurso=doc.getString("_id");
            Document curso=coleccionCursos.find(eq("_id",idCurso)).first();
            if (curso!=null){
                String titCurso=curso.getString("titulo");
                System.out.println(titCurso +"---"+doc.getInteger("num"));
            }
        }

    }
    
    public void ModificarNotaMedia(){
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce un id");
        String idAlumno=teclado.nextLine();
        double media=0.0;
        
        Document doc=coleccionAlumnos.find(eq("_id",idAlumno)).first();
        if (doc==null){
            System.out.println("No existe el id");
        }else{
            List<Double> notas=(List<Double>) doc.get("notas");
            Double suma=0.0;
            for (Double n:notas){
                suma=suma+n;
            }
            if (notas.size()>0){
                media=suma/notas.size();
            }
            DecimalFormat formato=new DecimalFormat("##.00");
            String cad=formato.format(media);
            coleccionAlumnos.updateOne(eq("_id", idAlumno), set("nota_media",cad));
        }
    }
    
    public void modificarHorasCurso(){
        Scanner teclado=new Scanner(System.in);
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el id del curso");
        String id=teclado.nextLine();
        Document doc=coleccionCursos.find(eq("id",id)).first();
        
        if (doc==null){
            System.out.println("id no existe");
        }else{
            int horas=0;
            if (doc.containsKey("temas")){
               List<Document> lista=(List<Document>) doc.get("temas");
               
               for (Document tema:lista){
                   horas=horas+tema.getInteger("horas");
               }
               coleccionCursos.updateOne(eq("_id", doc.getString("_id")), set("horas", horas));
               
            }else{
                coleccionCursos.updateOne(eq("_id", doc.getString("_id")), set("horas", 0));
            }
            
        }
    }
    
    public void DatosAlumno(){
        Scanner teclados=new Scanner(System.in);
        System.out.println("Introduce el id de un alumno");
        String id=teclados.nextLine();
        Document doc=coleccionAlumnos.find(eq("_id",id)).first();
        if (doc==null){
            System.out.println("id no existe");
        }else{
            String curso=coleccionCursos.find(eq("_id", doc.getString("curso"))).first().getString("titulo");
            System.out.println("Alumno: "+doc.getString("nombre")+", apellidos: "+doc.getString("apellidos")+", curso: "+curso);
        }
    }
    
    public void NotaMedia(){
        Scanner teclados=new Scanner(System.in);
        System.out.println("Introduce el id de un curso");
        String id=teclados.nextLine();
        Document doc=coleccionCursos.find(eq("_id",id)).first();
        if (doc==null){
            System.out.println("id no existe");
        }else{
            MongoCursor <Document> cursor=coleccionAlumnos.find(eq("curso",doc.getString("_id"))).iterator();
            double contador=0;
            double suma=0;
            while (cursor.hasNext()){
                Document docAlumno=cursor.next();
                Double nota=(Double) docAlumno.get("nota_media");
                suma=suma+nota;
                contador++;
            }
            double media=suma/contador;
            coleccionCursos.updateOne(eq("_id",doc.getString("id")), set("nota media", media));
            
        }
    }
    
    
}
