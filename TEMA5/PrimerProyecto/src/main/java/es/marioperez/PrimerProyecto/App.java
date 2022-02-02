package es.marioperez.PrimerProyecto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.marioperez.PrimerProyecto.modelo.Alumno;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimerProyecto");

        //Generamos un EntityManager
        EntityManager em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();

        // Construimos un objeto Alumno
        Alumno alumno1 = new Alumno();
        alumno1.setId(1L);
        alumno1.setNombre("Pepe");
        alumno1.setNota(5);

        // Construimos otro objeto Alumno
        Alumno alumno2 = new Alumno();
        alumno2.setId(2L);
        alumno2.setNombre("María");
        alumno2.setNota(9);

        //Persistimos los objetos
        em.persist(alumno1);
        em.persist(alumno2);

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
}
