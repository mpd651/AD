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
        Scanner teclados=new Scanner(System.in);
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
                    String idCurso=teclados.nextLine();
                    String nombreProfe=gestor.obtenerProfesorDeCurso(idCurso);
                    if (!nombreProfe.equalsIgnoreCase("")){
                        System.out.println("El nombre del profesor es "+nombreProfe);
                    }else{
                        System.out.println("No se ha encontrado el nombre del profesor");
                    }
                    break;
                    
                case 2:
                    List<Curso> cursosSinTutor=gestor.obtenerCursosSinTutor();
                    System.out.println("Cursos sin tutor:");
                    for(Curso c:cursosSinTutor){
                        System.out.println(c.toString());
                    }
                    break;
                    
                case 3:
                    List<Curso> cursos=gestor.obtenerCursosSinAlumnos();
                    for (Curso c: cursos){
                        System.out.println(c.toString());
                    }
                    break;
                    
                    
                case 4:
                    List<Profesor> profesoresSinTutor=gestor.obtenerProfesoresNoTutores();
                    for (Profesor p: profesoresSinTutor){
                        System.out.println(p.toString());
                    }
                    break;
                    
                case 5:
                    String cursosYalumnos=gestor.obtenerCursosYAlumnos();
                    System.out.println("Lista de cursos");
                    System.out.println(cursosYalumnos);
                    break;
                    
                case 6:
                    System.out.println("Introduce un numero de alumnos limite:");
                    int numAlumnos=tecladoi.nextInt();
                    String cursosConMenosAlumnos=gestor.obtenerCursosConMenosAlumnosQue(numAlumnos);
                    System.out.println(cursosConMenosAlumnos);
                    break;
                    
                case 7:
                    System.out.println("Introduce un id de alumno");
                    int idAlumno=tecladoi.nextInt();
                    System.out.println("Alumnos del mismo curso");
                    Curso curso=gestor.obtenerCursoDeAlumno(idAlumno);
                    for (Alumno a: curso.getAlumnos()){
                        System.out.println(a.getNombre());
                    }
                    break;
                    
                case 8:
                    Curso curso8=gestor.obtenerCursoConMasAlumnos();
                    System.out.println("Curso con mas alumnos: "+curso8.getNombre());
                    break;
                    
                case 9:
                    System.out.println("Indica numero de alumnos a mostrar:");
                    int numAlumnos9=tecladoi.nextInt();
                    List <Alumno> alumnos=gestor.obtenerAlumnosConMasNota(numAlumnos9);
                    for (Alumno a: alumnos){
                        System.out.println(a.toString());
                    }
                    break;
                    
                case 0:
                    break;
            }       
            
        } while (opcion!=0);
        
        
        
    }
}
