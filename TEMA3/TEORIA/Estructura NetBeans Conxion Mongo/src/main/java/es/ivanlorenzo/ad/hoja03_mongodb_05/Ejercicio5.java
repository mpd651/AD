package es.ivanlorenzo.ad.hoja03_mongodb_05;

import java.util.Scanner;

/**
 *
 * @author ivan
 */
public class Ejercicio5
{
    private static GestorBaseDatos gestor;
    private static final Scanner teclado = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        gestor = new GestorBaseDatos();
        int opcion;
        do
        {
            System.out.println("1.- Añadir Comunidad Autónoma sin provincias\n"
                    + "2.- Consultar Comunidad Autónoma\n"
                    + "3.- Asignar provincias a Comunidad Autónoma\n"
                    + "4.- Añadir provincia a Comunidad Autónoma\n"
                    + "5.- Modificar nombre de Comunidad Autónoma\n"
                    + "6.- Eliminar provincia en Comunidad Autónoma\n"
                    + "7.- Asignar capital a Comunidad Autónoma\n"
                    + "8.- Asignar fecha Estatuto Autonomía\n"
                    + "9.- Eliminar Comunidad Autónoma\n"
                    + "0.-         SALIR");
            opcion = teclado.nextInt();
            switch (opcion)
            {
                case 1 ->
                    gestor.insertaComAut();
                case 2 ->
                    gestor.consultaComunidad();
                case 3 ->
                    gestor.asignaProvinciasComunidad();
                case 4 ->
                    gestor.addProvinciaComunidad();
                case 5 ->
                    gestor.modificaNombre();
                case 6 ->
                    gestor.eliminaProvinciaComunidad();
                case 7 ->
                    gestor.asignaCapitalComunidad();
                case 8 ->
                    gestor.asignaFechaEstatuto();
                case 9 ->
                    gestor.eliminaComunidad();
            }
        }
        while (opcion != 0);
    }

}
