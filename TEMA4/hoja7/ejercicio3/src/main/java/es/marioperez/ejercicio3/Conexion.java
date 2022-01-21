package es.marioperez.ejercicio3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Conexion {
    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:mariadb://localhost:3310/liga2122";
    private String usuario = "root";
    private String contra = "root";

    private Conexion()
    {
        try
        {
            Class.forName("org.mariadb.jdbc.Driver"); //Driver
            connection = DriverManager.getConnection(url, usuario, contra);
            System.out.println("Conexion OK");
        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.toString());
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public static Conexion getInstance()
    {
        try
        {
            if (instance == null)
            {
                instance = new Conexion();
            } else if (instance.getConnection().isClosed())
            {
                instance = new Conexion();
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }

        return instance;
    }
    
}
