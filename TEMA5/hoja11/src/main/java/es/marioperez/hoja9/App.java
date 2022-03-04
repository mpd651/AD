package es.marioperez.hoja9;

import java.util.List;
import java.util.Scanner;

import es.marioperez.hoja9.basedatos.GestorBaseDatos;

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
    		System.out.println("1.- Cambiar componente de grupo\n"
    				+ "2.- Borrar votos de usuario para un grupo\n"
    				+ "3.- Insertar votos a grupo\n"
    				+ "0.- Salir");
    		System.out.println("Introduce una opcion del menu");
    		opc=teclado.nextInt();
    		
    		switch (opc) {
    		case 1:
    			break;
    		case 2:
    			break;
    		case 3:
    			break;
    		case 0:
    			break;
    		}
    		
    	}while (opc!=0);
    	
        
    }
}
