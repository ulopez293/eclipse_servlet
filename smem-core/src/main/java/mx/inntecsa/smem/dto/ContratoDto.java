package mx.inntecsa.smem.dto;


import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.TipoServicio;


public class ContratoDto extends GenericDto implements java.io.Serializable {

	private Integer idContrato;
	private ProveedorDto proveedor = new ProveedorDto();
	private SubtipoContratoDto subtipoContrato = new SubtipoContratoDto();
	private TipoContratacionDto tipoContratacion =  new TipoContratacionDto();
	private TipoServicio tipoServicio;
	private String numeroContrato;
	private String ejercicio;
	private Date fechaSuscripcion;
	private String oficioSufPresupuestal;
	private String partida;
	private Float montoMinimo;
	private Float montoMaximo;
	private Date vigenciaInicioContrato;
	private Date vigenciaFinContrato;
	private EstatusContrato estatus;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public ProveedorDto getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDto proveedor) {
		this.proveedor = proveedor;
	}

	public SubtipoContratoDto getSubtipoContrato() {
		return subtipoContrato;
	}

	public void setSubtipoContrato(SubtipoContratoDto subtipoContrato) {
		this.subtipoContrato = subtipoContrato;
	}

	public TipoContratacionDto getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(TipoContratacionDto tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Date getFechaSuscripcion() {
		return fechaSuscripcion;
	}

	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}

	public String getOficioSufPresupuestal() {
		return oficioSufPresupuestal;
	}

	public void setOficioSufPresupuestal(String oficioSufPresupuestal) {
		this.oficioSufPresupuestal = oficioSufPresupuestal;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public Float getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(Float montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	public Float getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(Float montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public Date getVigenciaInicioContrato() {
		return vigenciaInicioContrato;
	}

	public void setVigenciaInicioContrato(Date vigenciaInicioContrato) {
		this.vigenciaInicioContrato = vigenciaInicioContrato;
	}

	public Date getVigenciaFinContrato() {
		return vigenciaFinContrato;
	}

	public void setVigenciaFinContrato(Date vigenciaFinContrato) {
		this.vigenciaFinContrato = vigenciaFinContrato;
	}

	public EstatusContrato getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusContrato estatus) {
		this.estatus = estatus;
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
		return "Contrato con id: " + this.getIdContrato() + " numero: " + this.getNumeroContrato();
	}

}
