package es.marioperez.ejercicio2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws IOException
    {
        int opc=0;
        Scanner tecladoS=new Scanner(System.in);
        Scanner tecladoI=new Scanner(System.in);
        System.out.println("Introduce un nombre de fichero del ejercicio anterior");
        String nombreFichero=tecladoS.nextLine();
        Path ruta= Paths.get(System.getProperty("user.home"), "Escritorio", "DAM2", "AD", "TEMA1", "hoja_01_ficheros_06","ejercicio1",nombreFichero);
        
        if (Files.exists(ruta))
        {
            do
            {
                System.out.println("MENU"
                        + "\n\t 1-Listar futbolistas"
                        + "\n\t 2-Añadir futbolista"
                        + "\n\t 3-Listar futbolistas por puesto"
                        + "\n\t 4-Obtener el futbolista más alto"
                        + "\n\t 5-Modificar puesto de futbolista"
                        + "\n\t 6-Eliminar futbolista"
                        + "\n\t 0-Salir");
                System.out.println("Introduce una opcion:");
                opc=tecladoI.nextInt();
                
                switch (opc){
                    case 1:
                        listaJugadores(ruta).stream()
                                .forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Introduce id de jugador");
                        int id=tecladoI.nextInt();
                        System.out.println("Introduce nombre de jugador");
                        String nombre=tecladoS.nextLine();
                        System.out.println("Introduce apellidos de jugador");
                        String apellidos=tecladoS.nextLine();
                        System.out.println("Introduce alias de jugador");
                        String alias=tecladoS.nextLine();
                        System.out.println("Introduce puesto de jugador");
                        String puesto=tecladoS.nextLine();
                        System.out.println("Introduce altura de jugador");
                        int altura=tecladoI.nextInt();
                        System.out.println("Introduce fecha de nacimiento de jugador(aaaa-mm-dd)");
                        String fechaNacimiento=tecladoS.nextLine();
                        System.out.println("Introduce codigo de equipo de jugador");
                        int codigoEquipo=tecladoI.nextInt();
                        Jugador j=new Jugador(id, nombre, apellidos, alias, puesto, altura, LocalDate.parse(fechaNacimiento), codigoEquipo);
                        
                        BufferedWriter escritor=Files.newBufferedWriter(ruta, StandardOpenOption.APPEND);
                        escritor.write(j.toCSV());
                        escritor.newLine();
                        escritor.close();
                        break;

                    case 3:
                        System.out.println("Introduce puesto de futbolista:");
                        String puesto3=tecladoS.nextLine();
                        listaJugadores(ruta).stream()
                                .filter(j3->j3.getPuesto().equalsIgnoreCase(puesto3))
                                .map(j3 -> j3.toString())
                                .forEach(System.out::println);
                        break;
                    case 4:
                        listaJugadores(ruta).stream()
                                .sorted(Comparator.comparing(Jugador::getAltura))
                                .limit(1)
                                .forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("Introduce el identificador de un futbolista");
                        int identificador=tecladoI.nextInt();
                        listaJugadores(ruta).stream()
                                .filter(j5 -> j5.getId()==identificador);
                                
                        
                        break;
                    case 6:
                        break;                        
                }
            } while (opc!=0);
            
        }
        else
        {
            System.out.println("La ruta no existe");
        }
    }
    
    public static List<Jugador> listaJugadores(Path ruta) throws IOException
    {
        return Files.lines(ruta)
                .map(l -> l.split(","))
                .map(palabra -> new Jugador(
                Integer.parseInt(palabra[0]),
                palabra[1],
                palabra[2],
                palabra[3],
                palabra[4],
                Integer.parseInt(palabra[5]),
                LocalDate.parse(palabra[6]),
                Integer.parseInt(palabra[7]))
                ).toList();
    }

}
