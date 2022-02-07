package es.marioperez.hoja1;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Departamento;
import modelo.Empleado;
import modelo.Sueldo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hoja4");

        //Generamos un EntityManager
        EntityManager em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();
        
        // Construimos objetos empleado
        Empleado empleado1= new Empleado();
        empleado1.setFechaAlta(LocalDate.now());
        empleado1.setNombre("mario");
        empleado1.setOficio("programador");
        Sueldo s1=new Sueldo();
        s1.setSalario(1500);
        empleado1.setSueldo(s1);

        Empleado empleado2= new Empleado();
        empleado2.setFechaAlta(LocalDate.now());
        empleado2.setNombre("pepe");
        empleado2.setOficio("panadero");
        Sueldo s2=new Sueldo();
        s2.setSalario(5000);
        empleado2.setSueldo(s2);
        
        Empleado empleado3= new Empleado();
        empleado3.setFechaAlta(LocalDate.now());
        empleado3.setNombre("juan");
        empleado3.setOficio("cocinero");
        Sueldo s3=new Sueldo();
        s3.setSalario(1000);
        empleado3.setSueldo(s3);
        
        Empleado empleado4= new Empleado();
        empleado4.setFechaAlta(LocalDate.now());
        empleado4.setNombre("lucia");
        empleado4.setOficio("jardinera");
        Sueldo s4=new Sueldo();
        s4.setSalario(10000);
        empleado4.setSueldo(s4);
        
        
        //Construimos objetos departamento y añadimos empleados
        Departamento d1=new Departamento();
        d1.setLocalidad("Torrelavega");
        d1.setNombre("d1");
        d1.addEmpleado(empleado1);
        d1.addEmpleado(empleado2);
        
        Departamento d2=new Departamento();
        d2.setLocalidad("Santander");
        d2.setNombre("d2");
        d2.addEmpleado(empleado3);
        d2.addEmpleado(empleado4);

        //Persistimos los objetos
        em.persist(d1);
        em.persist(d2);

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
}
