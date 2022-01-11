package es.marioperez.ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        File ficheroPersonas = new File("personas.txt");

        System.out.println("MENU"
                + "\n   1-Añadir persona"
                + "\n   2-Buscar persona"
                + "\n   3-Buscar nombre"
                + "\n   4-Apellidos comienzan por"
                + "\n   5-Eliminar persona"
                + "\n   0-Salir");
        Scanner tecladoI = new Scanner(System.in);
        Scanner tecladoS = new Scanner(System.in);
        System.out.println("Introduce una opcion: ");
        int opc = tecladoI.nextInt();

        while (opc != 0)
        {
            switch (opc)
            {
                case 1:
                    System.out.println("Introduca nombre:");
                    String nombre1 = tecladoS.nextLine();
                    System.out.println("Introduzca apellidos:");
                    String apellido1 = tecladoS.nextLine();

                    String linea1 = apellido1.toUpperCase() + ", " + nombre1.toUpperCase();
                    {
                        try
                        {
                            BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroPersonas, true));
                            escritor.write(linea1);
                            escritor.close();
                        } catch (IOException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case 2:
                    System.out.println("Introduca nombre:");
                    String nombre2 = tecladoS.nextLine();
                    System.out.println("Introduzca apellidos:");
                    String apellido2 = tecladoS.nextLine();

                    String linea2 = apellido2.toUpperCase() + ", " + nombre2.toUpperCase();
                    {
                        try
                        {
                            BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonas));
                            String lineaLeida = "";
                            while (lineaLeida != null)
                            {
                                if (lineaLeida.equals(linea2))
                                {
                                    System.out.println("La persona buscada se encuentra en el fichero");
                                } else
                                {
                                    System.out.println("La persona buscada NO se encuentra en el fichero");
                                }
                            }

                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;

                case 3:
                    System.out.println("Introduzca nombre a buscar: ");
                    String nombrePersona = tecladoS.nextLine();
                    ArrayList lista3 = new ArrayList();
                    {
                        try
                        {
                            BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonas));
                            String linea = "";
                            while (linea != null)
                            {
                                linea = lector.readLine();
                                if (linea.contains(nombrePersona.toUpperCase()))
                                {
                                    lista3.add(linea);
                                }
                            }
                            lector.close();
                            for (int i = 0; i < lista3.size(); i++)
                            {
                                System.out.println(lista3.get(i));
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

                case 4:
                    System.out.println("Introduce los primeros caracteres de un apellido:");
                    String inicialesApellido = tecladoS.nextLine();
                    ArrayList lista4 = new ArrayList();
                    {
                        try
                        {
                            BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonas));
                            String linea = "";
                            while (linea != null)
                            {
                                linea = lector.readLine();
                                if (linea.startsWith(inicialesApellido.toUpperCase()))
                                {
                                    lista4.add(linea);
                                }
                            }
                            lector.close();
                            for (int i = 0; i < lista4.size(); i++)
                            {
                                System.out.println(lista4.get(i));
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

                case 5:
                    System.out.println("Introduce nombre de una persona:");
                    String nombrePersona5 = tecladoS.nextLine();
                    System.out.println("Introduce apellido de una persona:");
                    String apellidoPersona5 = tecladoS.nextLine();
                    File ficheroAuxiliar = new File("fAuxiliar.txt");
                    String nombreFinal = apellidoPersona5.toUpperCase() + ", " + nombrePersona5.toUpperCase();
                    {
                        try
                        {
                            BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonas));
                            BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroAuxiliar));

                            String linea = "";
                            while (linea != null)
                            {
                                linea = lector.readLine();
                                if (!linea.equals(nombreFinal))
                                {
                                    escritor.write(linea);
                                }
                            }
                            lector.close();
                            escritor.close();

                            ficheroPersonas.delete();
                            ficheroAuxiliar.renameTo(ficheroPersonas);

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
            System.out.println("MENU"
                    + "\n   1-Añadir persona"
                    + "\n   2-Buscar persona"
                    + "\n   3-Buscar nombre"
                    + "\n   4-Apellidos comienzan por"
                    + "\n   5-Eliminar persona"
                    + "\n   0-Salir");
            System.out.println("Introduce una opcion: ");
            opc = tecladoI.nextInt();
        }

    }
}
