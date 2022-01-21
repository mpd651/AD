package es.marioperez.ejercicio3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws SQLException
    {
        Connection con=Conexion.getInstance().getConnection();
        int jornadaMax=obtenerJornada(con);
        int lineasNuevas=llamarProcedimiento(con, jornadaMax);
        System.out.println("Se han añadido "+lineasNuevas+" lineas");
        String nuevosPartidos=obtenerDatosJornada(con, jornadaMax);
        System.out.println("nuevos partidos \n"+nuevosPartidos);
        
        
        
        
    }
    
    private static int llamarProcedimiento(Connection con, int jornada) throws SQLException{
        String sql="{call siguienteJornada(?)}";
        CallableStatement sentencia=con.prepareCall(sql);
        sentencia.setInt(1, jornada);
        int lineasNuevas=sentencia.executeUpdate();
        return lineasNuevas;
    }
    
    private static int obtenerJornada(Connection con) throws SQLException{
        int jornada=0;
        String sql="select numero_jornada from partidos order by numero_jornada desc limit 1";
        Statement st=con.createStatement();
        ResultSet result=st.executeQuery(sql);
        
        while (result.next()){
            jornada= result.getInt(1);
        }
        return jornada;
    }
    
    private static String obtenerDatosJornada(Connection con, int numJornada) throws SQLException{
        String datosJornada="";
         
        
        String sql="select equipo_local, equipo_visitante from partidos where numero_jornada=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1, numJornada);
        ResultSet result=st.executeQuery();
        
        while (result.next()){
            result.getString(1);
            datosJornada=datosJornada+result.getString(1)+"-"+result.getString(2)+"\n";
        }
        return datosJornada;
    }
}
