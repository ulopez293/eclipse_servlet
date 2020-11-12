package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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

@Entity
@Table(name = "cat_grupo_clave")
public class GrupoClave implements java.io.Serializable {

	private Integer idGrupoClave;
	private Equipo equipo;
	private Clave clave;
	private Grupo grupo;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<Universo> universos = new HashSet<Universo>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_grupo_clave", unique = true, nullable = false)
	public Integer getIdGrupoClave() {
		return this.idGrupoClave;
	}

	public void setIdGrupoClave(Integer idGrupoClave) {
		this.idGrupoClave = idGrupoClave;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipo", nullable = false)
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clave", nullable = false)
	public Clave getClave() {
		return clave;
	}

	public void setClave(Clave clave) {
		this.clave = clave;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo", nullable = false)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupoClave")
	public Set<Universo> getUniversos() {
		return this.universos;
	}

	public void setUniversos(Set<Universo> universos) {
		this.universos = universos;
	}

}
