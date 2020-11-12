package mx.inntecsa.smem.dto;


import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;


public class EntregaProveedorDto extends GenericDto implements java.io.Serializable {

	private int idEntregaProvedor;
	private ProgramacionServicioDto programacionServicio;
	private Date fechaFin;
	private String comentarios;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	public int getIdEntregaProvedor() {
		return idEntregaProvedor;
	}

	public void setIdEntregaProvedor(int idEntregaProvedor) {
		this.idEntregaProvedor = idEntregaProvedor;
	}

	public ProgramacionServicioDto getProgramacionServicio() {
		return programacionServicio;
	}

	public void setProgramacionServicio(
			ProgramacionServicioDto programacionServicio) {
		this.programacionServicio = programacionServicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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
		return "Entrega Proveedor con id: " + this.getIdEntregaProvedor();
	}

}
