package es.marioperez.hoja5ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {
     
    
    public static void main(String[] args) throws SQLException
    {
        Scanner teclados=new Scanner(System.in);
        Connection conexion=null;
        try
        {
            conexion = Conexion.getInstance().getConnection();
        } catch (SQLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Scanner tecladoi=new Scanner(System.in);
        System.out.println("Introduce el id de un grupo para ver sus componentes");
        int id=tecladoi.nextInt();
        
        String sql="select * from componentes where grupo=?";
        PreparedStatement consultaPreparada=conexion.prepareStatement(sql);
        consultaPreparada.setInt(1, id);
        ResultSet result=consultaPreparada.executeQuery();
        
        while (result.next()){
            System.out.println("Componente "+result.getString("nombre")+", funcion: "+result.getString("funcion"));
            System.out.println("Quieres modificar la funcion del componente? (s/n)");
            String respuesta=teclados.nextLine();
            if (respuesta.equalsIgnoreCase("s")){
                String nuevaFuncion=teclados.nextLine();
                result.updateString("funcion", nuevaFuncion);
                result.updateRow();
            }
            
        }
        
        
    }
}
