package es.marioperez.hoja1;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Alumno;
import modelo.Curso;
import modelo.Profesor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejer1");

        //Generamos un EntityManager
        EntityManager em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();
        
        // Construimos objetos
//        Curso curso = em.find(Curso.class, "DAM2");
//        Alumno a1=new Alumno();
//        a1.setNombre("Mario");
//        a1.setNotaMedia(8);
//        a1.setCursoBean(curso);
//        
//        
//        //Persistimos los objetos
//        em.persist(a1);
        
        
        //Hacemos una query de los cursos que hay
        TypedQuery<Curso> query = em.createQuery("SELECT c from Curso c", Curso.class);
        List<Curso>cursos = query.getResultList();
        cursos.forEach(c->System.out.println(c.getNombre()));
        

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
}
