package mx.inntecsa.smem.dto;


import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;

@SuppressWarnings("serial")
public class ActaEntregaRecepcionDto extends GenericDto implements java.io.Serializable {

	private int idActaEntregaRecepcion;
	private ProgramacionServicioDto programacionServicio;
	private String tecnicoEnviado;
	private Date fechaInicioServicio;
	private Date fechaFinalServicio;
	private Integer mantenimientoExitoso;
	private boolean cancelaActa;
	private String atribuible;
	private Float horasRealServicio;
	private String kitRefUtilizadas;
	private String recomendaciones;
	private String descripcionCompletaServicio;
	private String responsableEquipo;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	public int getIdActaEntregaRecepcion() {
		return idActaEntregaRecepcion;
	}

	public void setIdActaEntregaRecepcion(int idActaEntregaRecepcion) {
		this.idActaEntregaRecepcion = idActaEntregaRecepcion;
	}

	public ProgramacionServicioDto getProgramacionServicio() {
		return programacionServicio;
	}

	public void setProgramacionServicio(
			ProgramacionServicioDto programacionServicio) {
		this.programacionServicio = programacionServicio;
	}

	public String getTecnicoEnviado() {
		return tecnicoEnviado;
	}

	public void setTecnicoEnviado(String tecnicoEnviado) {
		this.tecnicoEnviado = tecnicoEnviado;
	}

	public Date getFechaInicioServicio() {
		return fechaInicioServicio;
	}

	public void setFechaInicioServicio(Date fechaInicioServicio) {
		this.fechaInicioServicio = fechaInicioServicio;
	}

	public Date getFechaFinalServicio() {
		return fechaFinalServicio;
	}

	public void setFechaFinalServicio(Date fechaFinalServicio) {
		this.fechaFinalServicio = fechaFinalServicio;
	}

	public Integer getMantenimientoExitoso() {
		return mantenimientoExitoso;
	}

	public void setMantenimientoExitoso(Integer mantenimientoExitoso) {
		this.mantenimientoExitoso = mantenimientoExitoso;
	}

	public boolean getCancelaActa() {
		return cancelaActa;
	}

	public void setCancelaActa(boolean cancelaActa) {
		this.cancelaActa = cancelaActa;
	}

	public String getAtribuible() {
		return atribuible;
	}

	public void setAtribuible(String atribuible) {
		this.atribuible = atribuible;
	}

	public Float getHorasRealServicio() {
		return horasRealServicio;
	}

	public void setHorasRealServicio(Float horasRealServicio) {
		this.horasRealServicio = horasRealServicio;
	}

	public String getKitRefUtilizadas() {
		return kitRefUtilizadas;
	}

	public void setKitRefUtilizadas(String kitRefUtilizadas) {
		this.kitRefUtilizadas = kitRefUtilizadas;
	}

	public String getRecomendaciones() {
		return recomendaciones;
	}

	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}

	public String getDescripcionCompletaServicio() {
		return descripcionCompletaServicio;
	}

	public void setDescripcionCompletaServicio(
			String descripcionCompletaServicio) {
		this.descripcionCompletaServicio = descripcionCompletaServicio;
	}

	public String getResponsableEquipo() {
		return responsableEquipo;
	}

	public void setResponsableEquipo(String responsableEquipo) {
		this.responsableEquipo = responsableEquipo;
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
		return "Acta Entrega Recepcion con id: " + this.getIdActaEntregaRecepcion();
	}

}
