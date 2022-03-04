package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the votos database table.
 * 
 */
@Entity
@Table(name="votos")
@NamedQuery(name="Voto.findAll", query="SELECT v FROM Voto v")
public class Voto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VotoPK id;

	//bi-directional many-to-one association to Cancion
	@ManyToOne
	@JoinColumn(name="cancion")
	private Cancion cancione;

	//bi-directional many-to-one association to Usuario
	@MapsId("usuario")
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuarioBean;

	public Voto() {
	}

	public VotoPK getId() {
		return this.id;
	}

	public void setId(VotoPK id) {
		this.id = id;
	}

	public Cancion getCancione() {
		return this.cancione;
	}

	public void setCancione(Cancion cancione) {
		this.cancione = cancione;
	}

	public Usuario getUsuarioBean() {
		return this.usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

}