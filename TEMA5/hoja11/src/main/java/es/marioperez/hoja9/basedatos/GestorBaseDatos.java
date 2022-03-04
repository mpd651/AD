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
import es.marioperez.hoja9.modelo.Voto;

public class GestorBaseDatos {
	private EntityManager em;
	
	public GestorBaseDatos() {
		em=Persistence.createEntityManagerFactory("hoja11").createEntityManager();
	}
	
	public boolean cambiarComponenteGrupo(String nombreComponente, String apellidos, String nombreGrupo) {
		boolean actualizado=false;
		TypedQuery<Integer> query = em.createNamedQuery("Componente.actualizarGrupo", Integer.class);
		query.setParameter(1, nombreComponente);
		query.setParameter(2, apellidos);
		query.setParameter(3, nombreGrupo);
		int numeroActualizados=query.executeUpdate();
		if (numeroActualizados==1) {
			actualizado=true;
		}
		return actualizado;
		
	}
	
	public boolean borrarVotos(String claveUsuario, String nombreGrupo) {
		TypedQuery<Voto> query=em.createQuery("select v from votos v where v.cancion.grupo=?1 and v.usuario.user=?2", Voto.class);
		query.setParameter(2, claveUsuario);
		query.setParameter(1, nombreGrupo);
		List<Voto> votos=query.getResultList();
		for (Voto voto: votos) {
			em.createNativeQuery("delete from votos where usuario=?1 and cancion=(select)");
		}

	}
	
	

}
