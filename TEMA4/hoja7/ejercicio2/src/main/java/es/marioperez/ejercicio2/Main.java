package es.marioperez.ejercicio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        
        Path fichero = Paths.get("datos_coches.sql");
        ejecutarScript(fichero);
    }
    
    
    
    public static void ejecutarScript(Path fichero)
    {
        String script = leerScript(fichero);
        Connection conexion = Conexion.getInstance().getConnection();
        try
        {
            conexion.setAutoCommit(false);
            
            //Ejecutar el script
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(script);

            //confirmar la transancción
            conexion.commit();
            System.out.println("Se ha realizado terminado la ejecución del script");
            sentencia.close();
        }
        catch (SQLException ex)
        {
            try
            {
                // Algo ha fallado en la ejecución del script, se anula la transacción
                conexion.rollback();
            }
            catch (SQLException ex1)
            {
                System.err.println("Error al hacer el rollback");
            }
            System.err.println("Error al ejecutar el script " + ex.getMessage());
        }
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
