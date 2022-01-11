package es.marioperez.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Error al cargar el driver");
        }
        
        try
        {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:concursomusica");
            Scanner teclado=new Scanner(System.in);
            System.out.println("Introduce nombre del grupo");
            String nombreGrupo= teclado.nextLine();
            buscarDuracion(conexion, nombreGrupo);
        } catch (SQLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexion OK con concursomusica.db"); 
        
        
        
    }
    
    private static void insertaGrupo(Connection conexion)
    {
        //Recoger datos (lo ideal sería haber recibido un objeto Grupo por parámetro)
        Scanner teclado = new Scanner(System.in);
        //Crear texto de consulta con parámetros sustituibles
        System.out.println("Nombre");
        String nombre = teclado.nextLine();
        System.out.println("Localidad");
        String localidad = teclado.nextLine();
        System.out.println("¿Es grupo? 0/1");
        int esGrupo = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Fecha");
        String fecha = teclado.nextLine();
        System.out.println("Año");
        int año = teclado.nextInt();

        //Crear texto de consulta con parámetros sustituibles
        String sql = "INSERT INTO grupos (nombregrupo,localidad,"
                + "esgrupo,fechaPrimerConcierto,annoPrimerDisco) "
                + "VALUES (?,?,?,?,?);";

        //Construir un PreparedStatement para sustituir valores en consulta parametrizada
        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
        {
            // Sustituye la ? primera por el contenido de nombre
            consultaPreparada.setString(1, nombre);
            // Sustituye la ? segunda por el contenido de localidad
            consultaPreparada.setString(2, localidad);
            consultaPreparada.setInt(3, esGrupo);
            consultaPreparada.setString(4, fecha);
            consultaPreparada.setInt(5, año);

            int numeroGruposInsertados = consultaPreparada.executeUpdate();
            System.out.println("Se han insertado " + numeroGruposInsertados + " grupos");
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }
    }
    
    private static void insertaGrupos(Connection conexion){
        Scanner teclado = new Scanner(System.in);
        String continuar="";
        do
        {
            insertaGrupo(conexion);
            System.out.println("Desea insertar mas grupos? (si/no)");
            continuar=teclado.nextLine();
        } while (continuar.equalsIgnoreCase("si"));
        
    }
    
    private static void buscarDuracion(Connection conexion, String nombreGrupo){
        String consulta="select titulo, duracion from canciones c inner join grupos g on c.numgrupo=g.numgrupo where nombregrupo='"+nombreGrupo+"'";
        
        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(consulta))
        {
            // Sustituye la ? primera por el contenido de nombre
            //consultaPreparada.setString(1, nombreGrupo);
            Statement st=conexion.createStatement();
            
            ResultSet result= st.executeQuery(consulta);
            //ResultSet result = consultaPreparada.executeQuery();
            while (result.next()){
                System.out.println("GRUPO: " + nombreGrupo + " TITULO: "+result.getString("titulo")+" DURACION: "+result.getInt("duracion"));
            }
            
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }
        
        
    }
    
    
    
     
}
