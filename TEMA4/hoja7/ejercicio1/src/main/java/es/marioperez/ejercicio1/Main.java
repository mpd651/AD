package es.marioperez.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            String url = "jdbc:mariadb://localhost:3310";
            String usuario = "root"; 
            String contra = "root";
            Connection conexion = DriverManager.getConnection(url, usuario, contra);
            
            File fichero=new File("datos_coches.sql");
            List<String> lineas=leerLineasScript(fichero);
            
            for (String l:lineas){
                Statement st=conexion.createStatement();
                st.execute(l);
            }
            
            
            
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private static List<String> leerLineasScript(File fichero){
        BufferedReader lector=null;
        List<String>lineas=new ArrayList();
        try
        {
            String linea="";
            lector = new BufferedReader(new FileReader(fichero));
            lector.readLine();
            while ((linea=lector.readLine())!=null){
                lineas.add(linea);
            }
            
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                lector.close();
            } catch (IOException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lineas;
    }
    
    
    
    private static String leerScript(Path fichero)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(fichero));
        }
        catch (IOException ex)
        {
            System.err.println("Error al leer el fichero " + ex.getMessage());
        }
        return contenido;
    }
}
