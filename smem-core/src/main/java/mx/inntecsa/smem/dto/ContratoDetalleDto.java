package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;

@SuppressWarnings("serial")
public class ContratoDetalleDto extends GenericDto implements java.io.Serializable {

	private int idContratoDetalle;
	private ContratoDto contrato = new ContratoDto();
	private UniversoDto universo = new UniversoDto();
	private Integer periodo;
	private Integer consecutivoContrato;
	private Date inicioPeriodo;
	private Date finPeriodo;
	private Float enero;
	private Float febrero;
	private Float marzo;
	private Float abril;
	private Float mayo;
	private Float junio;
	private Float julio;
	private Float agosto;
	private Float septiembre;
	private Float octubre;
	private Float noviembre;
	private Float diciembre;
	private Date inicioVigencia;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Boolean agregarExcel = true;
	private Integer fila;
	
	private Set<SolicitudServicioDto> solicitudServicios = new HashSet<SolicitudServicioDto>(0);

	public int getIdContratoDetalle() {
		return idContratoDetalle;
	}

	public void setIdContratoDetalle(int idContratoDetalle) {
		this.idContratoDetalle = idContratoDetalle;
	}

	public ContratoDto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDto contrato) {
		this.contrato = contrato;
	}

	public UniversoDto getUniverso() {
		return universo;
	}

	public void setUniverso(UniversoDto universo) {
		this.universo = universo;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getConsecutivoContrato() {
		return consecutivoContrato;
	}

	public void setConsecutivoContrato(Integer consecutivoContrato) {
		this.consecutivoContrato = consecutivoContrato;
	}

	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public Date getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(Date finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public Float getEnero() {
		return enero;
	}

	public void setEnero(Float enero) {
		this.enero = enero;
	}

	public Float getFebrero() {
		return febrero;
	}

	public void setFebrero(Float febrero) {
		this.febrero = febrero;
	}

	public Float getMarzo() {
		return marzo;
	}

	public void setMarzo(Float marzo) {
		this.marzo = marzo;
	}

	public Float getAbril() {
		return abril;
	}

	public void setAbril(Float abril) {
		this.abril = abril;
	}

	public Float getMayo() {
		return mayo;
	}

	public void setMayo(Float mayo) {
		this.mayo = mayo;
	}

	public Float getJunio() {
		return junio;
	}

	public void setJunio(Float junio) {
		this.junio = junio;
	}

	public Float getJulio() {
		return julio;
	}

	public void setJulio(Float julio) {
		this.julio = julio;
	}

	public Float getAgosto() {
		return agosto;
	}

	public void setAgosto(Float agosto) {
		this.agosto = agosto;
	}

	public Float getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(Float septiembre) {
		this.septiembre = septiembre;
	}

	public Float getOctubre() {
		return octubre;
	}

	public void setOctubre(Float octubre) {
		this.octubre = octubre;
	}

	public Float getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(Float noviembre) {
		this.noviembre = noviembre;
	}

	public Float getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(Float diciembre) {
		this.diciembre = diciembre;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
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

	public Set<SolicitudServicioDto> getSolicitudServicios() {
		return solicitudServicios;
	}

	public void setSolicitudServicios(
			Set<SolicitudServicioDto> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}		

	public Boolean getAgregarExcel() {
		return agregarExcel;
	}

	public void setAgregarExcel(Boolean agregarExcel) {
		this.agregarExcel = agregarExcel;
	}	

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Contrato Detalle con id:" + this.getIdContratoDetalle();
	}

	@Override
	public String toString() {
		return "ContratoDetalleDto [idContratoDetalle=" + idContratoDetalle
				+ ", contrato=" + contrato.getIdContrato() + ", universo=" 
				+ universo.getIdUniverso()
				+ ", periodo=" + periodo + ", consecutivoContrato="
				+ consecutivoContrato + ", inicioPeriodo=" + inicioPeriodo
				+ ", finPeriodo=" + finPeriodo + ", enero=" + enero
				+ ", febrero=" + febrero + ", marzo=" + marzo + ", abril="
				+ abril + ", mayo=" + mayo + ", junio=" + junio + ", julio="
				+ julio + ", agosto=" + agosto + ", septiembre=" + septiembre
				+ ", octubre=" + octubre + ", noviembre=" + noviembre
				+ ", diciembre=" + diciembre + ", inicioVigencia="
				+ inicioVigencia + ", fechaRegistro=" + fechaRegistro
				+ ", fechaModificacion=" + fechaModificacion + ", fechaBaja="
				+ fechaBaja + ", baja=" + baja + ", solicitudServicios="
				+ solicitudServicios + "]";
	}
	
	

}
