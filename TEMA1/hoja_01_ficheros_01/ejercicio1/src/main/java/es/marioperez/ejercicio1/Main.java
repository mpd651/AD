package es.marioperez.ejercicio1;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    
    public static void main(String[] args)
    {
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce la ruta");
        String ruta= teclado.nextLine();
        File directorio  = new File (ruta);
        
        if (directorio.exists()){
            System.out.println("Ficheros del directorio: "+ ruta);
            
            File [] listaFicheros= directorio.listFiles();
            
            for (File fichero: listaFicheros){
                System.out.println(fichero.getName() + " Tamaño: "+fichero.length());
            }
        }
        else{
            System.err.println("Error. No existe el directorio: "+ruta);
        }
    }
}
