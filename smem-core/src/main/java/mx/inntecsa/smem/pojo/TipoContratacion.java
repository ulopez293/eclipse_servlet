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

/**
* Clase (POJO) mapeada con la tabla de cat_subtipo_contrato. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "cat_tipo_contratacion")
public class TipoContratacion implements java.io.Serializable {

	private int idTipoContratacion;
	private String tipoContratacion;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<Contrato> contratos = new HashSet<Contrato>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_tipo_contratacion", unique = true, nullable = false)
	public int getIdTipoContratacion() {
		return this.idTipoContratacion;
	}

	public void setIdTipoContratacion(int idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}

	@Column(name = "tipo_contratacion", nullable = false, length = 250)
	public String getTipoContratacion() {
		return this.tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoContratacion")
	public Set<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

}
