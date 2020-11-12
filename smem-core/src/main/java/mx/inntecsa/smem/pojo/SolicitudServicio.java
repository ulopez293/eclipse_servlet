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


/**
* Clase (POJO) mapeada con la tabla de contrato. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "solicitud_servicio")
public class SolicitudServicio implements java.io.Serializable {

	private int idSolicitudServicio;
	private ContratoDetalle contratoDetalle;
	private Date fechaInicio;
	private Date fechaFin;
	private String motivoSolicitud;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ProgramacionServicio> programacionServicios = new HashSet<ProgramacionServicio>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_solicitud_servicio", unique = true, nullable = false)
	public int getIdSolicitudServicio() {
		return this.idSolicitudServicio;
	}

	public void setIdSolicitudServicio(int idSolicitudServicio) {
		this.idSolicitudServicio = idSolicitudServicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contrato_detalle", nullable = false, insertable = true)
	public ContratoDetalle getContratoDetalle() {
		return this.contratoDetalle;
	}

	public void setContratoDetalle(ContratoDetalle contratoDetalle) {
		this.contratoDetalle = contratoDetalle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", length = 23)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 23)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "motivo_solicitud", length = 500)
	public String getMotivoSolicitud() {
		return this.motivoSolicitud;
	}

	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
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

	@OneToMany(fetch = FetchType.LAZY,  mappedBy="solicitudServicio")
	public Set<ProgramacionServicio> getProgramacionServicios() {
		return this.programacionServicios;
	}

	public void setProgramacionServicios(
		Set<ProgramacionServicio> programacionServicios) {
		this.programacionServicios = programacionServicios;
	}

}
