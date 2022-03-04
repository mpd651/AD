package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the canciones database table.
 * 
 */
@Entity
@Table(name="canciones")
@NamedQuery(name="Cancion.findAll", query="SELECT c FROM Cancion c")
public class Cancion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long numcancion;

	private Time duracion;

	private String titulo;

	@Column(name="total_votos")
	private Long totalVotos;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="grupo")
	private Grupo grupoBean;

	//bi-directional many-to-one association to Voto
	@OneToMany(mappedBy="cancione")
	private List<Voto> votos;

	public Cancion() {
	}

	public Long getNumcancion() {
		return this.numcancion;
	}

	public void setNumcancion(Long numcancion) {
		this.numcancion = numcancion;
	}

	public Time getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getTotalVotos() {
		return this.totalVotos;
	}

	public void setTotalVotos(Long totalVotos) {
		this.totalVotos = totalVotos;
	}

	public Grupo getGrupoBean() {
		return this.grupoBean;
	}

	public void setGrupoBean(Grupo grupoBean) {
		this.grupoBean = grupoBean;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Voto addVoto(Voto voto) {
		getVotos().add(voto);
		voto.setCancione(this);

		return voto;
	}

	public Voto removeVoto(Voto voto) {
		getVotos().remove(voto);
		voto.setCancione(null);

		return voto;
	}

}