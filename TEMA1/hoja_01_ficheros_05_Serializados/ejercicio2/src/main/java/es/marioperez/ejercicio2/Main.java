package es.marioperez.ejercicio2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        File f=new File("equipos.dat");
        Equipo e=crearEquipo();
        introducirAlumnos(e);
        escribir(e,f);
        ArrayList<Equipo> equipos=leer(f);
        
        for (Equipo equipo:equipos){
            System.out.println(equipo.toString());
        }
        
        
    }
    
    public static void escribir(Equipo e, File fichero) throws IOException
    {

        if (fichero.exists() == false)
        {
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(fichero));
            escritor.writeObject(e);
            escritor.close();
        } else
        {
            ObjectOutputStreamSinCabeceras escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(fichero, true));
            escritor.writeObject(e);
            escritor.close();
        }
    }
    
    public static ArrayList leer(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList <Equipo>lista = new ArrayList();
        ObjectInputStream lector = new ObjectInputStream(new FileInputStream(fichero));
        try
        {
            while (true)
            {
                Equipo e = (Equipo) lector.readObject();
                lista.add(e);
            }
        } catch (EOFException e)
        {
            lector.close();
            return lista;
        }

    }

    public static Equipo crearEquipo()
    {
        Scanner tecladoI=new Scanner(System.in);
        Scanner tecladoS=new Scanner(System.in);
        
        System.out.println("Introduce nombre del equipo:");
        String nombre=tecladoS.nextLine();
        Equipo e=new Equipo (nombre);
        return e;
    }
    
    public static void introducirAlumnos(Equipo e){
        Scanner tecladoI=new Scanner(System.in);
        Scanner tecladoS=new Scanner(System.in);
        
        System.out.println("Cuantos alumnos quiere introducir?");
        int numAlumnos=tecladoI.nextInt();
        e.setNumAlumnos(numAlumnos);
        
        Alumno [] alumnos=new Alumno [numAlumnos];
        
        for (int i = 0; i < numAlumnos; i++)
        {
            System.out.println("Introduce datos del alumno "+i);
            System.out.println("Introduce nombre del alumno:");
            String nombre=tecladoS.nextLine();
            System.out.println("Introduce edad del alumno");
            int edad=tecladoI.nextInt();
            Alumno a=new Alumno(nombre, edad);
            alumnos[i]=a;
        }
        
        e.setAlumnos(alumnos);
    }
}
