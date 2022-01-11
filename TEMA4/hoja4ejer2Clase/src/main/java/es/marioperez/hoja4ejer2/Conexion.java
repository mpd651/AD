package es.marioperez.hoja4ejer2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Conexion {
    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/concursomusicacompleto";;
    private String username = "postgres";;
    private String password = "PassWd!10";;

    private Conexion() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.postgresql.Driver");
        connection= DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection()
    {
        return connection;
    }
    
    
    public static Conexion getInstance() throws SQLException, ClassNotFoundException{
        if (instance==null){
            try
            {
                instance=new Conexion();
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex)
            {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (instance.getConnection().isClosed()){
            instance=new Conexion();
        }
        return instance;
    }
    
}
