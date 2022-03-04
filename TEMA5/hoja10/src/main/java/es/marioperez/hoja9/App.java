package es.marioperez.hoja9;

import java.util.List;
import java.util.Scanner;

import es.marioperez.hoja9.basedatos.GestorBaseDatos;
import es.marioperez.hoja9.modelo.Usuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	GestorBaseDatos gestor=new GestorBaseDatos();
    	Scanner teclado=new Scanner(System.in);
    	int opc;
    	do {
    		System.out.println("1.- Listado de usuarios nacidos a partir de año …\n"
    				+ "2.- Grupos que tienen mas de N canciones\n"
    				+ "3.- Canciones de grupos\n"
    				+ "4.- Grupos de localidad con primer disco en año antes de año\n"
    				+ "0.- Salir");
    		System.out.println("Introduce una opcion del menu");
    		opc=teclado.nextInt();
    		
    		switch (opc) {
    		case 1:
    			List<Usuario> usuarios=gestor.usuariosNacidosAnoMayor(2000);
    			for (Usuario u:usuarios) {
    				System.out.println(u.getNombre());
    			}
    			break;
    		case 2:
    			break;
    		case 3:
    			break;
    		case 4:
    			break;
    		case 0:
    			break;
    		}
    		
    	}while (opc!=0);
    	
        
    }
}
