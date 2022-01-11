package es.marioperez.hoja5ejercicio2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Connection conexion = Conexion.getInstance().getConnection();
        
        String sql="SELECT usuario,fecha,cancion FROM votos ORDER BY fecha DESC LIMIT 5;";
        Statement consulta=conexion.createStatement();
        ResultSet result=consulta.executeQuery(sql);
        
        while (result.next()){
            System.out.println("");
        }
    }
}
