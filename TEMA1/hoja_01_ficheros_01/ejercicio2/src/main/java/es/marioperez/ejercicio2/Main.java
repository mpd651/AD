package es.marioperez.ejercicio2;

import java.io.File;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        File fichero = new File(".");
        File [] listaFicheros= fichero.listFiles();
        
        for (File ficheroPrueba: listaFicheros){
            System.out.println("Nombre: "+ficheroPrueba.getName()+", ruta: "+ficheroPrueba.getPath()+", ruta absoluta: "+ficheroPrueba.getAbsolutePath()
            +", escribir: "+ficheroPrueba.canWrite()+ ", leer: "+ficheroPrueba.canRead());
        }
    }
}
