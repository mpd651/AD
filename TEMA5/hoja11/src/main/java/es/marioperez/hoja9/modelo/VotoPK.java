package es.marioperez.hoja9.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the votos database table.
 * 
 */
@Embeddable
public class VotoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String usuario;

	@Temporal(TemporalType.DATE)
	private java.util.Date fecha;

	public VotoPK() {
	}
	public String getUsuario() {
		return this.usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VotoPK)) {
			return false;
		}
		VotoPK castOther = (VotoPK)other;
		return 
			this.usuario.equals(castOther.usuario)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usuario.hashCode();
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}