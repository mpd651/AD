package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String user;

	private String apellidos;

	private String contraseña;

	@Temporal(TemporalType.DATE)
	private Date fechanac;

	private String nombre;

	private Long numvotos;

	//bi-directional many-to-one association to Voto
	@OneToMany(mappedBy="usuarioBean")
	private List<Voto> votos;

	public Usuario() {
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFechanac() {
		return this.fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumvotos() {
		return this.numvotos;
	}

	public void setNumvotos(Long numvotos) {
		this.numvotos = numvotos;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Voto addVoto(Voto voto) {
		getVotos().add(voto);
		voto.setUsuarioBean(this);

		return voto;
	}

	public Voto removeVoto(Voto voto) {
		getVotos().remove(voto);
		voto.setUsuarioBean(null);

		return voto;
	}

}