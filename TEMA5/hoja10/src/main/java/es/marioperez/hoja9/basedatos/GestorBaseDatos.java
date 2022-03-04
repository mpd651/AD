package es.marioperez.hoja9.basedatos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.marioperez.hoja9.modelo.Cancion;
import es.marioperez.hoja9.modelo.Componente;
import es.marioperez.hoja9.modelo.Grupo;
import es.marioperez.hoja9.modelo.Usuario;

public class GestorBaseDatos {
	private EntityManager em;
	
	public GestorBaseDatos() {
		em=Persistence.createEntityManagerFactory("hoja10").createEntityManager();
	}
	
	public List<Usuario> usuariosNacidosAnoMayor(int ano){
		TypedQuery<Usuario> query=em.createQuery("select u from Usuario u where year(u.fechanac)>=?1 order by u.fechanac ", Usuario.class);
		query.setParameter(1, ano);
		List <Usuario> usuarios=query.getResultList();
		return usuarios;
	}
	public List<Grupo> gruposConNumeroCancionesMayor(int canciones){
		TypedQuery<Grupo> query = em.createNamedQuery("Grupo.masCanciones", Grupo.class)
				.setParameter(1, canciones);
		List<Grupo> grupos = query.getResultList();
		return grupos;
	}
	public List<Cancion> getCancionesGrupos(List<String> grupos){
		List<Cancion> canciones=new ArrayList();
		for (String grupo:grupos) {
			Query query=em.createQuery("select g.canciones from Grupo g where g.nombre=?1");
			query.setParameter(1, grupo);
			List<Cancion> cancionesGrupo=query.getResultList();
			canciones.addAll(cancionesGrupo);
		}
		return canciones;
	}
	public List<Grupo> gruposLocalidadAnio(String texto, int numero){
		TypedQuery<Grupo> query = em.createNamedQuery("Grupo.localidadPrimerDisco", Grupo.class);
		query.setParameter(1, texto);
		query.setParameter(2, numero);
		List<Grupo> grupos = query.getResultList();
		return grupos;
	}

}
