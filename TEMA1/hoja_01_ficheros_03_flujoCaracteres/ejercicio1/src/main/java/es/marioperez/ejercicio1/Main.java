package es.marioperez.ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        Scanner teclado= new Scanner(System.in);
        System.out.println("Introduce ruta del fichero:");
        String ruta= teclado.nextLine();
        System.out.println("Introduce el texto que quieres añadir al fichero:");
        String contenido= teclado.nextLine();
        File f1=new File(ruta);
        
        try
        {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(f1, true));
            escritor.write(contenido);
            escritor.close();
            
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            BufferedReader lector= new BufferedReader(new FileReader(f1));
            String linea="";
            while (linea!= null){
                linea=linea+lector.readLine();
                
            }
            lector.close();
            String lineaAuxiliar="";
            
            for (int i = 0; i < linea.length(); i++)
            {
                if (Character.isUpperCase(linea.charAt(i))){
                    lineaAuxiliar=lineaAuxiliar+Character.toLowerCase(linea.charAt(i));
                }else if (Character.isLowerCase(linea.charAt(i))){
                    lineaAuxiliar=lineaAuxiliar+Character.toUpperCase(linea.charAt(i));
                }
            }
            
            System.out.println(lineaAuxiliar);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
