package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


public class SolicitudServicioDto extends GenericDto implements java.io.Serializable {

	private int idSolicitudServicio;
	private ContratoDetalleDto contratoDetalle;
	private Date fechaInicio;
	private Date fechaFin;
	private String motivoSolicitud;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private String colorSemaforo;
	private Estatus baja;

	private Set<ProgramacionServicioDto> programacionServicios = new HashSet<ProgramacionServicioDto>(0);
	
	public int getIdSolicitudServicio() {
		return idSolicitudServicio;
	}

	public void setIdSolicitudServicio(int idSolicitudServicio) {
		this.idSolicitudServicio = idSolicitudServicio;
	}

	public ContratoDetalleDto getContratoDetalle() {
		return contratoDetalle;
	}

	public void setContratoDetalle(ContratoDetalleDto contratoDetalle) {
		this.contratoDetalle = contratoDetalle;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Estatus getBaja() {
		return baja;
	}

	public void setBaja(Estatus baja) {
		this.baja = baja;
	}

	public String getMotivoSolicitud() {
		return motivoSolicitud;
	}

	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}

	public Set<ProgramacionServicioDto> getProgramacionServicios() {
		return this.programacionServicios;
	}

	public void setProgramacionServicios(Set<ProgramacionServicioDto> programacionServicios) {
		this.programacionServicios = programacionServicios;
	}

	public String getColorSemaforo() {
		return colorSemaforo;
	}

	public void setColorSemaforo(String colorSemaforo) {
		this.colorSemaforo = colorSemaforo;
	}
	
	@Override
	public String getDescripcionBitacora() {
		return "Solicitud Servicio con id: " + this.getIdSolicitudServicio();
	}
}
