package es.marioperez.hoja9.basedatos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.marioperez.hoja9.modelo.Componente;
import es.marioperez.hoja9.modelo.Grupo;
import es.marioperez.hoja9.modelo.Usuario;

public class GestorBaseDatos {
	private EntityManager em;
	
	public GestorBaseDatos() {
		em=Persistence.createEntityManagerFactory("hoja9").createEntityManager();
	}
	
	public List<Grupo> listadoGrupos(){
		TypedQuery<Grupo> query = em.createQuery(
				"SELECT g FROM Grupos g order by g.nombre asc", Grupo.class);
		List<Grupo> grupos= query.getResultList();
		return grupos;
	}
	
	public List<Usuario> usuariosSinVotos(){
		TypedQuery<Usuario> query=em.createQuery("select u from Usuario u where u.numvotos=0", Usuario.class);
		List<Usuario> usuarios=query.getResultList();
		return usuarios;
	}
	public List<Usuario> usuariosPosteriores1990(){
		TypedQuery<Usuario> query=em.createQuery("select u from Usuario u where year(u.fechanac)>=1990 order by u.fechanac ", Usuario.class);
		List <Usuario> usuarios=query.getResultList();
		return usuarios;
	}
	public List<Grupo> gruposSinComponentes(){
		TypedQuery<Grupo> query=em.createQuery("select g from Grupo g where g.componentes is empty", Grupo.class);
		List <Grupo> grupos=query.getResultList();
		return grupos;
	}
	public List<Grupo> gruposSinCompania(){
		TypedQuery<Grupo> query=em.createQuery("select g from Grupo g where g.compania is null", Grupo.class);
		List<Grupo> grupos=query.getResultList();
		return grupos;
	}
	public List<Grupo> gruposDeAnioEstreno(String ciudad, int anio){
		TypedQuery<Grupo> query=em.createQuery("select g from Grupo g where g.localidad=?1 and annograb>=?2", Grupo.class);
		query.setParameter(1, ciudad);
        query.setParameter(2, anio);
		List<Grupo> grupos=query.getResultList();
		return grupos;
	}
	public long numeroGruposDe(String ciudad) {
		TypedQuery<Long> query=em.createQuery("select count(*) from Grupo g group by g.localidad where g.localidad=?1", Long.class);
		query.setParameter(1, ciudad);
		Long numero=query.getSingleResult();
		return numero;
	}
	
	public List<Object[]> numCancionesGrupos(String ciudad){
		TypedQuery<Object[]> query=em.createQuery("select g.nombre, size(g.canciones) from Grupo g where g.localidad=?1 ", Object[].class);
		query.setParameter(1, ciudad);
		List<Object[]> grupos=query.getResultList();
		return grupos;
	}
	
	
	//public List<Object[]> duracionMediaCancionesGrupos(){
	//	
	//}
	

}
