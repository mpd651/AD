package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the grupos database table.
 * 
 */
@Entity
@Table(name="grupos")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long codgrupo;

	private Integer annograb;

	private String compania;

	private Boolean esgrupo;

	private String estilo;

	@Temporal(TemporalType.DATE)
	private Date fechaestreno;

	private String localidad;

	private String nombre;

	//bi-directional many-to-one association to Cancion
	@OneToMany(mappedBy="grupoBean")
	private List<Cancion> canciones;

	//bi-directional many-to-one association to Componente
	@OneToMany(mappedBy="grupoBean")
	private List<Componente> componentes;

	public Grupo() {
	}

	public Long getCodgrupo() {
		return this.codgrupo;
	}

	public void setCodgrupo(Long codgrupo) {
		this.codgrupo = codgrupo;
	}

	public Integer getAnnograb() {
		return this.annograb;
	}

	public void setAnnograb(Integer annograb) {
		this.annograb = annograb;
	}

	public String getCompania() {
		return this.compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public Boolean getEsgrupo() {
		return this.esgrupo;
	}

	public void setEsgrupo(Boolean esgrupo) {
		this.esgrupo = esgrupo;
	}

	public String getEstilo() {
		return this.estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public Date getFechaestreno() {
		return this.fechaestreno;
	}

	public void setFechaestreno(Date fechaestreno) {
		this.fechaestreno = fechaestreno;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return this.canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	public Cancion addCancione(Cancion cancione) {
		getCanciones().add(cancione);
		cancione.setGrupoBean(this);

		return cancione;
	}

	public Cancion removeCancione(Cancion cancione) {
		getCanciones().remove(cancione);
		cancione.setGrupoBean(null);

		return cancione;
	}

	public List<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public Componente addComponente(Componente componente) {
		getComponentes().add(componente);
		componente.setGrupoBean(this);

		return componente;
	}

	public Componente removeComponente(Componente componente) {
		getComponentes().remove(componente);
		componente.setGrupoBean(null);

		return componente;
	}

}