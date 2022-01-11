package es.marioperez.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        System.out.println("MENU:"
                + "\n     1-Crear fichero numeros.dat"
                + "\n     2-Añadir numero a numeros.dat"
                + "\n     3-Listar numeros de numeros.dat"
                + "\n     4-Listar numeros de numeros.dat en hexadecimal"
                + "\n     5-Buscar numero en numeros.dat"
                + "\n     0-Salir");
        Scanner tecladoI=new Scanner(System.in);
        Scanner tecladoS = new Scanner(System.in);
        System.out.println("Introduzca una opcion del menu:");
        int opc = tecladoI.nextInt();
        File f1=new File("mifichero.txt");
        File fichero=new File("nombre.dat");
        DataOutputStream escritor;
        DataInputStream lector;
        while (opc != 0)
        {
            switch (opc)
            {
                case 1:
                    escritor=new DataOutputStream(new FileOutputStream(fichero));
                    for (int i = 0; i < 10; i++) {
                        int entero=(int)Math.random()*255;
                        escritor.writeInt(entero);
                    }
                    escritor.close();
                    break;
                case 2:
                    escritor=new DataOutputStream(new FileOutputStream(fichero));
                    System.out.println("Introduce el numero entre 0 y 255 que quieres añadir:");
                    int entero=tecladoI.nextInt();
                    escritor.writeInt(entero);
                    escritor.close();
                    break;
                case 3:
                    lector=new DataInputStream(new FileInputStream(fichero));
                    try{
                        if (fichero.exists()==true){
                        while (true){
                            System.out.println(lector.readInt());
                        }
                    }
                    }catch (EOFException e){
                        
                    }finally{
                        lector.close();
                    }
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Introduce un numero");
                    int numero5 =tecladoI.nextInt();
                    boolean encontrado=false;
                {
                    try
                    {
                        FileInputStream lector5=new FileInputStream(f1);
                        int contador=0;
                        while (lector5.available()>0){
                            if (numero5==lector5.read()){
                                encontrado=true;
                                contador++;
                                System.out.println("El numero se ha encontrado en el archivo. La posicion es: "+contador);
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
                    
                    break;

            }
        }
    }
}
