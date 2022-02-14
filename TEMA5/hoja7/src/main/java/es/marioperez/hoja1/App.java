package es.marioperez.hoja1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.PropertyAccessException;

import modelo.Alumno;
import modelo.Curso;
import modelo.Profesor;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner tecladoi=new Scanner(System.in);
	static Scanner teclados=new Scanner(System.in);
	public static EntityManager em;
    public static void main( String[] args )
    {
    	//Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejer1");

        //Generamos un EntityManager
        em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();
        
        // Llamamos al menu
        Menu();

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
    
    private static void Menu() {
    	int opcion;
    	do {
    		System.out.println("MENU:\n"
        			+ "1.- Datos de alumno\n"
        			+ "2.- Datos de curso\n"
        			+ "3.- Listado de cursos\n"
        			+ "4.- Listado de alumnos de curso\n"
        			+ "5.- Modificar título de curso\n"
        			+ "6.- Añadir curso\n"
        			+ "7.- Añadir o modificar curso\n"
        			+ "8.- Modificar tutor de curso\n"
        			+ "9.- Eliminar alumno\n"
        			+ "0.- Salir");
        	System.out.println("Introduce una opcion");
        	opcion=tecladoi.nextInt();
        	
        	switch (opcion) {
        	case 1:
        		Alumno alumno=datosAlumno();
        		if (alumno!=null) {
        			System.out.println("Nombre alumno: "+alumno.getNombre()+", id curso: "+alumno.getCursoBean().getId());
        		}
        		break;
        	case 2:
        		Curso curso=datosCurso();
        		if (curso!=null) {
        		System.out.println("Nombre del curso: "+curso.getNombre()+", Nombre del profesor "+curso.getProfesor().getNombre());
        		}
        		break;
        	case 3:
        		List<Curso> cursos=listadoCursos();
        		for (Curso c: cursos) {
        			System.out.println(c.toString());
        		}
        		break;
        	case 4:
        		System.out.println("Introduce el id de un curso a buscar:");
        		String id=teclados.nextLine();
        		
        		List<Alumno> alumnos=listadoAlumnosCurso(id);
        		if (alumnos.size()>0) {
        		for (Alumno a: alumnos) {
        			System.out.println(alumnos.toString());
        		}
        		}
        		break;
        	case 5:
        		System.out.println("Introduce el id de un curso:");
        		String id5=teclados.nextLine();
        		modificarTituloCurso(id5);
        		break;
        	case 6:
        		System.out.println("Introduce un id del curso");
        		String id6=teclados.nextLine();
        		System.out.println("Introduce un nombre del curso");
        		String nombre=teclados.nextLine();
        		addCurso(id6, nombre);
        		break;
        	case 7:
        		System.out.println("Introduce un id del curso");
        		String id7=teclados.nextLine();
        		System.out.println("Introduce un nombre del curso");
        		String nombre7=teclados.nextLine();
        		addCurso(id7, nombre7);
        		break;
        	case 8:
        		System.out.println("Introduce el id del curso a modificar:");
        		String id8=teclados.nextLine();
        		modificarTutorCurso(id8);
        		break;
        	case 9:
        		System.out.println("Introduce el id del alumno a borrar");
        		int id9=tecladoi.nextInt();
        		eliminarAlumno(id9);
        		break;
        	}
        	
        		
        			
    	}while (opcion!=0);
    }
    
    
    private static void eliminarAlumno(int id) {
    	Query borrado = em.createQuery("DELETE Alumno a WHERE a.id = ?1");
		int borrados=borrado.executeUpdate();
		if (borrados>0) {
			System.out.println("Se ha borrado el alumno");
		}
    }
    
    private static void modificarTutorCurso(String id) {
    	try {
    		TypedQuery<Curso> query = em.createQuery(
    				"SELECT c FROM Curso c where c.id= ?1 ",
    				Curso.class);
        	query.setParameter(1, id);
            Curso curso = query.getSingleResult();
            System.out.println("Nombre del curso: "+curso.getNombre()+", nombre del tutor: "+curso.getProfesor().getNombre());
            
            System.out.println("Introduce el id de un nuevo profesor:");
            int idProfesor=tecladoi.nextInt();
            TypedQuery<Profesor> query2 = em.createQuery(
    				"SELECT p FROM Profesor p where p.id= ?1 ",
    				Profesor.class);
        	query2.setParameter(1, idProfesor);
            Profesor profesor = query2.getSingleResult();
            
            if (profesor.getCursos().size()==0) {
            	curso.setProfesor(profesor);
            	System.out.println("Profesor cambiado");
            }else {
            	System.out.println("El profesor ya tiene cursos");
            }
            
    	}catch (NoResultException e) {
    		System.out.println("id incorrecto");
    	}
    }
    
    private static void addModificarCurso (String id, String nombre) {
    	try {
    		Curso curso=new Curso();
    		curso.setId(id);
    		curso.setNombre(nombre);
    		em.persist(curso);
    		System.out.println("Curso añadido");
    	}catch (PersistenceException e) {
    		Query update=em.createQuery("UPDATE Curso c SET c.nombre = ?1 where c.id=?2");
            update.setParameter(1, nombre);
            update.setParameter(2, id);
            int actualizado=update.executeUpdate();
            if (actualizado>0) {
            	System.out.println("Curso actualizado");
            }
    	}
    }
    
    private static void addCurso(String id, String nombre) {
    	try {
    		Curso curso=new Curso();
    		curso.setId(id);
    		curso.setNombre(nombre);
    		em.persist(curso);
    	}catch (PersistenceException e) {
    		System.out.println(e.toString());
    	}
    }
    
    private static void modificarTituloCurso(String id) {
    	Curso curso=null;
    	
    	try {
    		TypedQuery<Curso> query = em.createQuery(
    				"SELECT c FROM Curso c where c.id= ?1 ",
    				Curso.class);
        	query.setParameter(1, id);
            curso = query.getSingleResult();
            
            System.out.println("Nombre del curso: "+curso.getNombre());
            System.out.println("Introduce un nuevo nombre del curso");
            String nuevoNombre=teclados.nextLine();
            Query update=em.createQuery("UPDATE Curso c SET c.nombre = ?1 where c.id=?2");
            update.setParameter(1, nuevoNombre);
            update.setParameter(2, id);
            int actualizado=update.executeUpdate();
            if (actualizado>0) {
            	System.out.println("Curso actualizado");
            }
            
            
            
    	}catch (NoResultException e) {
    		System.out.println("Id incorrecto");
    	}catch (PropertyAccessException e) {
    		
    	}	
    }
    
    
    private static Alumno datosAlumno() {
    	System.out.println("Introduce el id del alumno a buscar:");
    	int id=tecladoi.nextInt();
    	Alumno alumno=null;
    	
    	try {
    		TypedQuery<Alumno> query = em.createQuery(
    				"SELECT a FROM Alumno a where a.id = ?1",
    				Alumno.class);
        	query.setParameter(1, id);
            alumno = query.getSingleResult();            
    	}catch (NoResultException e){
    		System.out.println("Id incorrecto");
    	}catch (PropertyAccessException e) {
    		
    	}

    	return alumno;
    }
    
    private static Curso datosCurso() {
    	System.out.println("Introduce el id del curso a buscar:");
    	String id=teclados.nextLine();
    	Curso curso=null;
    	
    	try {
    		TypedQuery<Curso> query = em.createQuery(
    				"SELECT c FROM Curso c where c.id= ?1 ",
    				Curso.class);
        	query.setParameter(1, id);
            curso = query.getSingleResult();
            return curso;
    	}catch (NoResultException e) {
    		System.out.println("Id incorrecto");
    	}catch (PropertyAccessException e) {
    		
    	}	
    	return curso;
    }
    
    private static List<Curso> listadoCursos(){
    	TypedQuery<Curso> query = em.createQuery(
				"SELECT c FROM Curso c",
				Curso.class);
    	List<Curso> cursos = query.getResultList();
    	return cursos;
    }
    
    private static List<Alumno> listadoAlumnosCurso(String id){
    	List<Alumno> alumnos=new ArrayList();
    	try {
        	TypedQuery<Curso> query = em.createQuery(
    				"SELECT c FROM Curso c where c.id= ?1 ",
    				Curso.class);
        	query.setParameter(1, id);
            Curso curso = query.getSingleResult();
            
            alumnos= curso.getAlumnos();
    	} catch (NoResultException e){
    		System.out.println("Id incorrecto");
    	}catch (PropertyAccessException e) {
    		
    	}
    	return alumnos;
    }
}
