package es.marioperez.hoja6;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    private static GestorBaseDatos gestor;
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws IOException
    {
        gestor=new GestorBaseDatos();
        int opcion;
        do
        {
            System.out.println("1.- Obtener comunidades autónomas y capitales\n"
                    + "2.- Obtener comunidades autónomas con habitantes comprendidos entre valores\n"
                    + "3.- Obtener comunidades autónomas uniprovinciales.\n"
                    + "4.- Obtener capitales de comunidad autónoma con más habitantes que\n"
                    + "5.- Obtener comunidades autónomas sin fecha de estatuto\n"
                    + "6.- Obtener provincias de comunidad autónoma\n"
                    + "7.- Crear fichero JSON\n"
                    + "0.- Salir");
            System.out.println("Introduce una opcion");
            opcion = teclado.nextInt();
            switch (opcion)
            {
                case 1:
                    gestor.obtenerCCAAyCapitales();
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
                    gestor.guardarFichero();
            }
        } while (opcion!=0);
        
        
    }
}
