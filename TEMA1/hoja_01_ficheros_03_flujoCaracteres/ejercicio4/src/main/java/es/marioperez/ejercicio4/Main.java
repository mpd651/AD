package es.marioperez.ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        File f=new File ("alumnos.txt");
        try
        {
            BufferedReader lector=new BufferedReader(new FileReader(f));
            String linea="";
            while (linea!=null){
                linea=lector.readLine();
                String nombre=linea.substring(0,linea.indexOf('-'));
                String edadString=linea.substring(linea.indexOf('-')+1);
                
                int edad=Integer.parseInt(edadString);
                
                if (edad>20){
                    System.out.println(nombre+" tiene "+edad+" años");
                }
                
            }
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
