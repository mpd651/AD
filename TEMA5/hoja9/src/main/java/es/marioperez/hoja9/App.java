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
    		System.out.println("1.- Listado de grupos\n"
        			+ "2.- Listado de usuarios que no han votado ninguna vez\n"
        			+ "3.- Listado de usuarios nacidos a partir de 1990\n"
        			+ "4.- Grupos sin componentes cargados\n"
        			+ "5.- Grupos sin compañía cargada\n"
        			+ "6.- Grupos de Barcelona con primer disco en año antes de 2010\n"
        			+ "7.- Número de grupos de Madrid\n"
        			+ "8.- Número de canciones de cada grupo de Madrid\n"
        			+ "9.- Duración media de las canciones de cada grupo\n"
        			+ "0.- Salir");
    		System.out.println("Introduce una opcion del menu");
    		opc=teclado.nextInt();
    		
    		switch (opc) {
    		case 1:
    			gestor.listadoGrupos();
    			break;
    		case 2:
    			break;
    		case 3:
    			break;
    		case 4:
    			break;
    		case 5:
    			break;
    		case 6:
    			break;
    		case 7:
    			break;
    		case 8:
    			List<Object[]> grupos=gestor.numCancionesGrupos("Madrid");
    			grupos.forEach(o ->System.out.println(o[0]+" : "+o[1]));
    			break;
    		case 9:
    			break;
    		case 0:
    			break;
    		}
    		
    	}while (opc!=0);
    	
        
    }
}
