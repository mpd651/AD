package es.marioperez.hoja5ejercicio2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Scanner tecladoi=new Scanner(System.in);
        Scanner teclados=new Scanner(System.in);
        Connection conexion = Conexion.getInstance().getConnection();
        
        String sql="SELECT usuario,fecha,cancion FROM votos ORDER BY fecha DESC LIMIT 5;";
        Statement consulta=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet result=consulta.executeQuery(sql);
        
        while (result.next()){
            System.out.println("Usuario: "+result.getString("usuario")+", fecha: "+result.getDate("fecha").toLocalDate()+", cancion: "+result.getInt("cancion"));
            System.out.println("Introduce una opcion");
            System.out.println("1.Modificar usuario\n"
                    + "2.Eliminar");
            int opcion=tecladoi.nextInt();
            
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nuevo usuario: ");
                    result.updateString("usuario", teclados.nextLine());
                    result.updateRow();
                    break;
                case 2:
                    result.deleteRow();
                    break;
            }
        }
    }
}
