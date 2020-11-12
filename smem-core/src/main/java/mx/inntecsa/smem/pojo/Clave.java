package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cat_clave")
public class Clave implements java.io.Serializable {

	private int idClave;
	private String clave;
	private String nombreGenerico;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<GrupoClave> grupoClaves = new HashSet<GrupoClave>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_clave", unique = true, nullable = false)
	public int getIdClave() {
		return this.idClave;
	}

	public void setIdClave(int idClave) {
		this.idClave = idClave;
	}

	@Column(name = "clave", nullable = false)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "nombre_generico")
	public String getNombreGenerico() {
		return this.nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", length = 23)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion", length = 23)
	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_baja", length = 23)
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "baja")
	public Estatus getBaja() {
		return this.baja;
	}

	public void setBaja(Estatus baja) {
		this.baja = baja;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clave")
	public Set<GrupoClave> getGrupoClaves() {
		return this.grupoClaves;
	}

	public void setGrupoClaves(Set<GrupoClave> grupoClaves) {
		this.grupoClaves = grupoClaves;
	}

}
