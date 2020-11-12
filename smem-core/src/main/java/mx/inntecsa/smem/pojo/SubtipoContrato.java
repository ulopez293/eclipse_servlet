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
@Table(name = "cat_subtipo_contrato")
public class SubtipoContrato implements java.io.Serializable {

	private int idSubtipoContrato;
	private String subtipoContrato;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<Contrato> contratos = new HashSet<Contrato>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_subtipo_contrato", unique = true, nullable = false)
	public int getIdSubtipoContrato() {
		return this.idSubtipoContrato;
	}

	public void setIdSubtipoContrato(int idSubtipoContrato) {
		this.idSubtipoContrato = idSubtipoContrato;
	}

	@Column(name = "subtipo_contrato", nullable = false, length = 50)
	public String getSubtipoContrato() {
		return this.subtipoContrato;
	}

	public void setSubtipoContrato(String subtipoContrato) {
		this.subtipoContrato = subtipoContrato;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subtipoContrato")
	public Set<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

}
