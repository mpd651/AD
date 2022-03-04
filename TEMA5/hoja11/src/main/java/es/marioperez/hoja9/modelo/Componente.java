package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the componentes database table.
 * 
 */
@Entity
@Table(name="componentes")
@NamedQuery(name="Componente.findAll", query="SELECT c FROM Componente c")
@NamedNativeQuery(name = "Componente.actualizarGrupo", query="update componentes where nombre=?1 and apellidos=?2 set grupo=(select id from grupos where nombre=?3)", 
resultClass = Integer.class)
public class Componente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idcomp;

	private String alias;

	private String apellido;

	private String funcion;

	private String nombre;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="grupo")
	private Grupo grupoBean;

	public Componente() {
	}

	public Long getIdcomp() {
		return this.idcomp;
	}

	public void setIdcomp(Long idcomp) {
		this.idcomp = idcomp;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Grupo getGrupoBean() {
		return this.grupoBean;
	}

	public void setGrupoBean(Grupo grupoBean) {
		this.grupoBean = grupoBean;
	}

}