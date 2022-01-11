package es.marioperez.ejercicio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        File f = new File("profesores.dat");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("MENU"
                + "\n\t 1-Añadir profesores"
                + "\n\t 2-Listado de profesores de una localidad"
                + "\n\t 3-Profesores del instituto mas antiguo"
                + "\n\t 4-Traslado de profesor"
                + "\n\t 5-Destruir instituto"
                + "\n\t 0-salir");
        Scanner tecladoI = new Scanner(System.in);
        Scanner tecladoS = new Scanner(System.in);
        System.out.println("Introduce una opcion:");
        int opc = tecladoI.nextInt();

        while (opc != 0)
        {
            switch (opc)
            {
                case 1:
                    System.out.println("Introduzca numero de registro:");
                    int num = tecladoI.nextInt();
                    System.out.println("Introduzca nombre");
                    String nombre = tecladoS.nextLine();
                    System.out.println("Introduzca fecha de ingreso");
                    LocalDate fecha = LocalDate.parse(tecladoS.nextLine(), formatter);
                    System.out.println("Introduzca nombre del instituto");
                    String instituto = tecladoS.nextLine();
                    System.out.println("Introduzca fecha de construccion del instituto");
                    LocalDate fechaConstruccion = LocalDate.parse(tecladoS.nextLine(), formatter);
                    System.out.println("Introduzca localidad del instituto");
                    String localidadInstituto = tecladoS.nextLine();
                    Instituto i = new Instituto(instituto, fechaConstruccion, localidadInstituto);
                    Profesor p = new Profesor(num, nombre, fecha, i);
                    escribirProfesor(p, f);
                    break;

                case 2:
                    System.out.println("Introduzca la localidad en la que buscar profesores");
                    listado(tecladoS.nextLine());
                    break;

                case 3:
                    ArrayList<Profesor> lista = leerProfesor(f);
                    LocalDate masAntiguo = LocalDate.MAX;

                    for (Profesor profe : lista)
                    {
                        if (masAntiguo.compareTo(profe.getInstituto().getFechaConstruccion()) < 0)
                        {
                            masAntiguo = profe.getInstituto().getFechaConstruccion();
                        }
                    }
                    for (Profesor profe2 : lista)
                    {
                        if (profe2.getInstituto().getFechaConstruccion().compareTo(masAntiguo) == 0)
                        {
                            System.out.println("Profesor: " + profe2.getNombre());
                        }
                    }
                    break;

                case 4:
                    File fAuxiliar = new File("profesoresAuxiliar.dat");
                    boolean encontrado = false;
                    System.out.println("Introduzca numero de registro del profesor:");
                    int registro = tecladoI.nextInt();
                    System.out.println("Introduzca nombre del nuevo instituto:");
                    String instituto4 = tecladoS.nextLine();

                    ArrayList<Profesor> lista5 = leerProfesor(f);
                    for (Profesor profe : lista5)
                    {
                        if (profe.getNumeroRegistro() == registro)
                        {
                            encontrado = true;
                            profe.getInstituto().setNombre(instituto4);
                        }
                        escribirProfesor(profe, fAuxiliar);
                    }

                    if (encontrado == true)
                    {
                        f.delete();
                        fAuxiliar.renameTo(f);
                    } else
                    {
                        System.out.println("Profesor no encontrado. Archivo no cambiado");
                        fAuxiliar.delete();
                    }
                    break;

                case 5:
                    System.out.println("Introduce el nombre del instituto a borrar");
                    ArrayList<Profesor> listaBorrados = destruirInstituto(tecladoS.nextLine());

                    System.out.println("Profesores borrados:");
                    for (Profesor profe : listaBorrados)
                    {
                        System.out.println(profe.toString());
                    }
                    break;
            }
            System.out.println("MENU"
                    + "\n\t 1-Añadir profesores"
                    + "\n\t 2-Listado de profesores de una localidad"
                    + "\n\t 3-Profesores del instituto mas antiguo"
                    + "\n\t 4-Traslado de profesor"
                    + "\n\t 5-Destruir instituto"
                    + "\n\t 0-salir");
            System.out.println("Introduce una opcion:");
            opc = tecladoI.nextInt();
        }
    }

    public static void escribirProfesor(Profesor p, File fichero) throws IOException
    {

        if (fichero.exists() == false)
        {
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(fichero));
            escritor.writeObject(p);
            escritor.close();
        } else
        {
            ObjectOutputStreamSinCabeceras escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(fichero, true));
            escritor.writeObject(p);
            escritor.close();
        }

    }

    public static ArrayList leerProfesor(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList lista = new ArrayList();
        ObjectInputStream lector = new ObjectInputStream(new FileInputStream(fichero));
        try
        {
            while (true)
            {
                Profesor p = (Profesor) lector.readObject();
                lista.add(p);
            }
        } catch (EOFException e)
        {
            lector.close();
            return lista;
        }

    }

    public static void listado(String localidad) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        File f = new File("profesores.dat");
        ArrayList<Profesor> lista = leerProfesor(f);
        boolean encontrado = false;
        for (Profesor profe : lista)
        {
            if (profe.getInstituto().getLocalidad().equalsIgnoreCase(localidad))
            {
                encontrado = true;
                System.out.println("Profesor :" + profe.getNombre() + ", instituto: " + profe.getInstituto().getNombre()
                        + ", meses de clase: " + Period.between(profe.getFechaIngreso(), LocalDate.now()).getMonths());
            }
        }
        if (encontrado == false)
        {
            System.out.println("No se han encontrado profesores en la localidad");
        }
    }

    public static ArrayList destruirInstituto(String instituto) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList listaBorrados = new ArrayList();
        File f = new File("profesores.dat");
        File fAuxiliar = new File("profesoresAuxiliar.dat");
        ArrayList<Profesor> lista = leerProfesor(f);

        for (Profesor profe : lista)
        {
            if (profe.getInstituto().getNombre().equalsIgnoreCase(instituto))
            {
                listaBorrados.add(profe);
            } else
            {
                escribirProfesor(profe, fAuxiliar);
            }
        }

        f.delete();
        fAuxiliar.renameTo(f);
        return listaBorrados;
    }
}
