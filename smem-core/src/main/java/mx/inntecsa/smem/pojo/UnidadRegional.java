package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


/**
* Clase (POJO) mapeada con la tabla de cat_unidad_regional. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "cat_unidad_regional")
public class UnidadRegional implements java.io.Serializable {

	private int idUnidadRegional;
	private Supervisor supervisor;
	private Entidad entidad;
	private String unidadRegional;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<CentroTrabajo> centrosTrabajos = new HashSet<CentroTrabajo>(0);

	@Id
	@Column(name = "id_unidad_regional", unique = true, nullable = false)
	public int getIdUnidadRegional() {
		return this.idUnidadRegional;
	}

	public void setIdUnidadRegional(int idUnidadRegional) {
		this.idUnidadRegional = idUnidadRegional;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_supervisor")
	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entidad")
	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	@Column(name = "unidad_regional", length = 100)
	public String getUnidadRegional() {
		return this.unidadRegional;
	}

	public void setUnidadRegional(String unidadRegional) {
		this.unidadRegional = unidadRegional;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadRegional")
	public Set<CentroTrabajo> getCentrosTrabajos() {
		return centrosTrabajos;
	}

	public void setCentrosTrabajos(Set<CentroTrabajo> centrosTrabajos) {
		this.centrosTrabajos = centrosTrabajos;
	}

}
