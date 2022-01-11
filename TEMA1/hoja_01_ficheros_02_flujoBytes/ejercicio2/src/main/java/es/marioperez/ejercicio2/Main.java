package es.marioperez.ejercicio2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
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
        System.out.println("Introduce una ruta");
        String ruta=teclado.nextLine();
        System.out.println("Introduce un nombre del fichero");
        String nombre=teclado.nextLine();
        
        File f=new File(ruta+"/"+nombre);
        if (f.exists()){
            int c;
            String cadena="";
            try
            {
                FileInputStream fi=new FileInputStream(f);
                while (fi.available()>0){
                    c=fi.read();
                    cadena=cadena+(char) c;
                }
                StringTokenizer st=new StringTokenizer(cadena);
                while (st.hasMoreTokens()){
                    System.out.print(st.nextToken());
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
}
