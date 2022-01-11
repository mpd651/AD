package es.marioperez.ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        File fichero =new File ("entrada.txt");
        File ficheroSalida= new File ("salida.txt");
        
        try
        {
            FileReader lector = new FileReader(fichero);
            FileWriter escritor= new FileWriter(ficheroSalida);
            int c;
            while((c=lector.read())!=-1){
                escritor.write(c);
            }
            lector.close();
            escritor.close();
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
