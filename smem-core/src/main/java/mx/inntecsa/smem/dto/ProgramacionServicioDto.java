package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;


public class ProgramacionServicioDto extends GenericDto implements java.io.Serializable {

	private int idProgramacionServicio;
	private SolicitudServicioDto solicitudServicio;
	private TipoServicio tipoServicio;
	private String folio;
	private EstatusServicio estatus;
	private Integer nprogramacion;
	private String tecnicoProv;
	private String telTecnico;
	private String observaciones;
	private String noControl;
	private Date fechaVisita;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ActaEntregaRecepcionDto> actasEntregaRecepciones = new HashSet<ActaEntregaRecepcionDto>(0);
	private Set<EntregaProveedorDto> entregasProveedores = new HashSet<EntregaProveedorDto>(0);

	private boolean mostrarVistaActa;
	private boolean mostrarVistaNotificacion;
	private boolean esCancelable; //solo se pueden cancelar las programaciones cuya solicitud no tiene mas de un dia de que se genero 
	
	public int getIdProgramacionServicio() {
		return idProgramacionServicio;
	}

	public void setIdProgramacionServicio(int idProgramacionServicio) {
		this.idProgramacionServicio = idProgramacionServicio;
	}

	public SolicitudServicioDto getSolicitudServicio() {
		return solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicioDto solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public EstatusServicio getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusServicio estatus) {
		this.estatus = estatus;
	}

	public Integer getNprogramacion() {
		return nprogramacion;
	}

	public void setNprogramacion(Integer nprogramacion) {
		this.nprogramacion = nprogramacion;
	}

	public String getTecnicoProv() {
		return tecnicoProv;
	}

	public void setTecnicoProv(String tecnicoProv) {
		this.tecnicoProv = tecnicoProv;
	}

	public String getTelTecnico() {
		return telTecnico;
	}

	public void setTelTecnico(String telTecnico) {
		this.telTecnico = telTecnico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNoControl() {
		return noControl;
	}

	public void setNoControl(String noControl) {
		this.noControl = noControl;
	}

	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
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

	public Set<ActaEntregaRecepcionDto> getActasEntregaRecepciones() {
		return actasEntregaRecepciones;
	}

	public void setActasEntregaRecepciones(
			Set<ActaEntregaRecepcionDto> actasEntregaRecepciones) {
		this.actasEntregaRecepciones = actasEntregaRecepciones;
	}

	public Set<EntregaProveedorDto> getEntregasProveedores() {
		return entregasProveedores;
	}

	public void setEntregasProveedores(
			Set<EntregaProveedorDto> entregasProveedores) {
		this.entregasProveedores = entregasProveedores;
	}

	public boolean getMostrarVistaActa() {
		return mostrarVistaActa;
	}

	public void setMostrarVistaActa(boolean mostrarVistaActa) {
		this.mostrarVistaActa = mostrarVistaActa;
	}

	public boolean getMostrarVistaNotificacion() {
		return mostrarVistaNotificacion;
	}

	public void setMostrarVistaNotificacion(boolean mostrarVistaNotificacion) {
		this.mostrarVistaNotificacion = mostrarVistaNotificacion;
	}

	public boolean getEsCancelable() {
		return esCancelable;
	}

	public void setEsCancelable(boolean esCancelable) {
		this.esCancelable = esCancelable;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Programacion de Servicio con id: " + this.getIdProgramacionServicio() + " folio: " + this.getFolio();
	}	
}
