package es.marioperez.ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        Scanner teclado=new Scanner (System.in);
        File f1=new File("texto.txt");
        System.out.println("Introduce un caracter a buscar:");
        char caracter= teclado.nextLine().charAt(0);
        int contador=0;
        
        try
        {
            String linea = "";
            BufferedReader lector = new BufferedReader(new FileReader(f1));
            while (linea != null)
            {
                linea = linea + lector.readLine();
            }
            lector.close();

            for (int i = 0; i < linea.length(); i++)
            {
                if (linea.charAt(i) == Character.toUpperCase(caracter) || linea.charAt(i) == Character.toLowerCase(caracter))
                {
                    contador++;
                    System.out.println("El caracter " + caracter + " aparece en la posicion: " + i);
                }
            }
            if (contador == 0)
            {
                System.out.println("El caracter no aparece");
            } else
            {
                System.out.println("El caracter aparece " + contador + " veces");
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
