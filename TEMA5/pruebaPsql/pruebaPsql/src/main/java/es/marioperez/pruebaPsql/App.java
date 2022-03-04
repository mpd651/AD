package es.marioperez.pruebaPsql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManager em=Persistence.createEntityManagerFactory("pruebaPsql").createEntityManager();
    	TypedQuery<Usuario> query=em.createQuery("select u from Usuario u", Usuario.class);
    	List <Usuario> usuarios=query.getResultList();
    	for (Usuario u:usuarios) {
    		System.out.println(u.getNombre());
    	}
    }
}
