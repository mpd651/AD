package es.marioperez.hoja1;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Empleado;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hoja1");

        //Generamos un EntityManager
        EntityManager em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();

        // Construimos un objeto Alumno
        Empleado empleado1= new Empleado();
        empleado1.setFechaAlta(LocalDate.now());
        empleado1.setId(1L);
        empleado1.setNombre("mario");
        empleado1.setOficio("programador");
        empleado1.setSalario(1500);

        // Construimos otro objeto Alumno
        Empleado empleado2= new Empleado();
        empleado2.setFechaAlta(LocalDate.now());
        empleado2.setId(2L);
        empleado2.setNombre("pepe");
        empleado2.setOficio("panadero");
        empleado2.setSalario(5000);

        //Persistimos los objetos
        em.persist(empleado1);
        em.persist(empleado2);

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
}
