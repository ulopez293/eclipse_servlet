package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cat_entidad")
public class Entidad implements java.io.Serializable {

	private int idEntidad;
	private String entidad;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<UnidadRegional> unidadesRegionales = new HashSet<UnidadRegional>(0);

	@Id
	@Column(name = "id_entidad", unique = true, nullable = false)
	public int getIdEntidad() {
		return this.idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	@Column(name = "entidad", nullable = false, length = 50)
	public String getEntidad() {
		return this.entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
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
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidad")
	public Set<UnidadRegional> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	public void setUnidadesRegionales(Set<UnidadRegional> unidadesRegionales) {
		this.unidadesRegionales = unidadesRegionales;
	}
	
	

}
