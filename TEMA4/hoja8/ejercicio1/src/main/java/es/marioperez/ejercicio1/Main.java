package es.marioperez.ejercicio1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner tecladoi = new Scanner(System.in);
        GestorBd gestor = new GestorBd();

        int opcion = 0;

        do
        {
            System.out.println("1.- Tutor de un curso\n"
                    + "2.- Cursos sin tutor\n"
                    + "3.- Cursos sin alumnos\n"
                    + "4.- Profesores no tutores\n"
                    + "5.- Número de alumnos en cada curso\n"
                    + "6.- Cursos con menos alumnos que\n"
                    + "7.- Alumnos del mismo curso que\n"
                    + "8.- Curso que tiene más alumnos\n"
                    + "9.- Alumnos con mayor nota media");
            System.out.println("Introduce una opcion:");
            opcion = tecladoi.nextInt();

            switch (opcion)
            {
                case 1:
                    System.out.println("Introduce el id de un curso del que saber el profesor");
                    int idCurso=tecladoi.nextInt();
                    String nombreProfe=gestor.obtenerProfesorDeCurso(idCurso);
                    if (!nombreProfe.equalsIgnoreCase("")){
                        System.out.println("El nombre del profesor es "+nombreProfe);
                    }else{
                        System.out.println("No se ha encontrado el nombre del profesor");
                    }
                    break;
                    
                case 2:
                    List<Curso> cursosSinTutor=new ArrayList();
                    System.out.println("Cursos sin tutor:");
                    for(Curso c:cursosSinTutor){
                        System.out.println(c.toString());
                    }
                    
                    break;
                    
                case 3:
                    List<Curso> cursos=gestor.obtenerCursos();
                    for (Curso c: cursos){
                        if (c.getAlumnos().size()==0){
                            System.out.println(c.toString());
                        }
                    }
                    break;
                    
                    
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    break;
            }       
            
        } while (opcion!=0);
        
        
        
    }
}
