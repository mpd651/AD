package es.marioperez.ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner tecladoI=new Scanner(System.in);
        Scanner tecladoS=new Scanner(System.in);
        
        System.out.println("MENU: \n"
                + "1 Listado de futbolistas\n"
                + "2 Listado de futbolistas de equipo\n"
                + "3 Búsqueda de futbolista\n"
                + "4 Creación de fichero de equipo\n"
                + "5 Listado de fichero de equipo\n"
                + "6 Mostrar todos los fichero de equipo\n"
                + "0 Salir");
        int opc = tecladoI.nextInt();
        Path ruta = Paths.get("fubolistas.csv");
        BufferedWriter escritor = Files.newBufferedWriter(ruta);
        escritor.write("1,mario,perez,mp,portero,190,1992-07-10,1");
        escritor.write("2,nom2,ape2,na2,delantero,185,1995-07-10,2");
        escritor.close();

        do
        {
            switch (opc)
            {
                case 1:
                    if (Files.exists(ruta))
                    {
                        
                        getFutbolistas(ruta)
                                .stream()
                                .forEach(System.out::println);
//                        String descripcionJugador=jugadores.stream()
//                                .map(j -> j.toString())
//                                .toString();
//                        System.out.println(descripcionJugador);
                    }
                    break;
                case 2:
                    System.out.println("Introduce codigo de equipo:");
                    int codigo=tecladoI.nextInt();
                    getFutbolistas(ruta)
                            .stream()
                            .filter(f -> f.getCodigoEquipo()==codigo)
                            .forEach(System.out::println);                    
                    break;
                case 3:
                    System.out.println("Introduce alias de futbolista");
                    String alias=tecladoS.nextLine();
                    getFutbolistas(ruta)
                            .stream()
                            .filter(f->f.getAlias().equalsIgnoreCase(alias))
                            .forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Introduce el codigo de un equipo");
                    int equipo=tecladoI.nextInt();
                    Path ruta4=Paths.get(String.valueOf(equipo)+".csv");
                    
                    List<String> jugadores= getFutbolistas(ruta)
                            .stream()
                            .filter(f -> f.getCodigoEquipo()==equipo)
                            .map(f -> f.toCSV())
                            .toList();
                    Files.write(ruta, jugadores);
                    
                    break;
                case 5:
                    System.out.println("Introduce el codigo de un equipo");
                    equipo=tecladoI.nextInt();
                    Path ruta5=Paths.get(String.valueOf(equipo)+".csv");
                    Files.lines(ruta5)
                            .forEach(System.out::println);
                    
                    break;
                case 6:
                    Files.list(ruta.toAbsolutePath().getParent())
                            .map(p -> p.getFileName())
                            .map(p -> p.toString())
                            .filter(s -> s.endsWith(".csv"))
                            .forEach(System.out::println);
                    break;
            }
            
            System.out.println("MENU: \n"
                + "1 Listado de futbolistas\n"
                + "2 Listado de futbolistas de equipo\n"
                + "3 Búsqueda de futbolista\n"
                + "4 Creación de fichero de equipo\n"
                + "5 Listado de fichero de equipo\n"
                + "6 Mostrar todos los fichero de equipo\n"
                + "0 Salir");
            System.out.println("Introduce una opcion del menu");
            opc=tecladoI.nextInt();
            
        } while (opc!=0);
        
    }


   public static List<Jugador> getFutbolistas(Path ruta) throws IOException{
       List <Jugador> jugadores = Files.lines(ruta)
                                .map(linea -> linea.split(","))
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
       
       
       return jugadores;
   }
    

}
