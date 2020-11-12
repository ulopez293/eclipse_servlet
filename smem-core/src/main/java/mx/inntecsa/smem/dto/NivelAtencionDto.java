package mx.inntecsa.smem.dto;


import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;


public class NivelAtencionDto extends GenericDto implements java.io.Serializable {

	private int idNivelAtencion;
	private String descripcion;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	public int getIdNivelAtencion() {
		return idNivelAtencion;
	}

	public void setIdNivelAtencion(int idNivelAtencion) {
		this.idNivelAtencion = idNivelAtencion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@Override
	public String getDescripcionBitacora() {
		return "Nivel Atencion con id: " + this.getIdNivelAtencion();
	}

}
