package mx.inntecsa.smem.dto;


import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusRequiereServicio;
import mx.inntecsa.smem.enums.Funcionalidad;


public class UniversoDto extends GenericDto implements java.io.Serializable {

	private long idUniverso;
	private Integer idGrupoClaveInt;
	private CentroTrabajoDto centroTrabajo;
	private SectorAdqDto sectorAdq = new SectorAdqDto();
	private Funcionalidad funcionalidad;
	private EspecialidadDto especialidad = new EspecialidadDto();
	private GrupoClaveDto grupoClave = new GrupoClaveDto();
	private EquipoDto equipo = new EquipoDto();
	private NivelAtencionDto nivelAtencion = new NivelAtencionDto();
	private String inventario;
	private String marca;
	private String modelo;
	private String serie;
	private String cabm;
	private String obsubica;
	private Date fechaInstalacion;
	private Date fechaApertura;
	private Date fechaGarantia;
	private EstatusRequiereServicio requiereServicio;
	private String observaciones;
	private String numeroLicitacion;
	private String partida;
	private String noContratoAdq;
	private String actualizacionTecnologica;
	private String proveedorVentaEquipo;
	private String anioAdq;
	private String precioEquipoSinIva;
	private boolean esSibobim;
	private boolean estaActualizado;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private String numeroBitacora;
	/***SICOBIM**/
	private String claveUr;
	private String claveCt;
	private String clavePf;
	/***Datos SMEMV1***/
	private String numeroContrato;
	private Integer consecutivoContrato;
	private Date inicioPeriodo1;
	private Date finPeriodo1;
	private Date inicioPeriodo2;
	private Date finPeriodo2;
	private Date inicioPeriodo3;
	private Date finPeriodo3;
	private Date inicioPeriodo4;
	private Date finPeriodo4;
	
	
	public long getIdUniverso() {
		return idUniverso;
	}

	public void setIdUniverso(long idUniverso) {
		this.idUniverso = idUniverso;
	}

	public Integer getIdGrupoClaveInt() {
		return idGrupoClaveInt;
	}

	public void setIdGrupoClaveInt(Integer idGrupoClaveInt) {
		this.idGrupoClaveInt = idGrupoClaveInt;
	}

	public CentroTrabajoDto getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(CentroTrabajoDto centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public SectorAdqDto getSectorAdq() {
		return sectorAdq;
	}

	public void setSectorAdq(SectorAdqDto sectorAdq) {
		this.sectorAdq = sectorAdq;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public EspecialidadDto getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadDto especialidad) {
		this.especialidad = especialidad;
	}

	public GrupoClaveDto getGrupoClave() {
		return grupoClave;
	}

	public void setGrupoClave(GrupoClaveDto grupoClave) {
		this.grupoClave = grupoClave;
	}
	
	public EquipoDto getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoDto equipo) {
		this.equipo = equipo;
	}

	public NivelAtencionDto getNivelAtencion() {
		return nivelAtencion;
	}

	public void setNivelAtencion(NivelAtencionDto nivelAtencion) {
		this.nivelAtencion = nivelAtencion;
	}

	public String getInventario() {
		return inventario;
	}

	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCabm() {
		return cabm != null ? cabm : "";
	}

	public void setCabm(String cabm) {		
		this.cabm = cabm != null ? cabm : "";
	}

	public String getObsubica() {
		return obsubica;
	}

	public void setObsubica(String obsubica) {
		this.obsubica = obsubica;
	}

	public Date getFechaInstalacion() {
		return fechaInstalacion;
	}

	public void setFechaInstalacion(Date fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaGarantia() {
		return fechaGarantia;
	}

	public void setFechaGarantia(Date fechaGarantia) {
		this.fechaGarantia = fechaGarantia;
	}	
	
	public EstatusRequiereServicio getRequiereServicio() {
		return requiereServicio;
	}

	public void setRequiereServicio(EstatusRequiereServicio requiereServicio) {
		this.requiereServicio = requiereServicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNumeroLicitacion() {
		return numeroLicitacion;
	}

	public void setNumeroLicitacion(String numeroLicitacion) {
		this.numeroLicitacion = numeroLicitacion;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getNoContratoAdq() {
		return noContratoAdq;
	}

	public void setNoContratoAdq(String noContratoAdq) {
		this.noContratoAdq = noContratoAdq;
	}

	public String getActualizacionTecnologica() {
		return actualizacionTecnologica;
	}

	public void setActualizacionTecnologica(String actualizacionTecnologica) {
		this.actualizacionTecnologica = actualizacionTecnologica;
	}

	public String getProveedorVentaEquipo() {
		return proveedorVentaEquipo;
	}

	public void setProveedorVentaEquipo(String proveedorVentaEquipo) {
		this.proveedorVentaEquipo = proveedorVentaEquipo;
	}

	public String getAnioAdq() {
		return anioAdq;
	}

	public void setAnioAdq(String anioAdq) {
		this.anioAdq = anioAdq;
	}

	public String getPrecioEquipoSinIva() {
		return precioEquipoSinIva;
	}

	public void setPrecioEquipoSinIva(String precioEquipoSinIva) {
		this.precioEquipoSinIva = precioEquipoSinIva;
	}

	public boolean isEsSibobim() {
		return esSibobim;
	}

	public void setEsSibobim(boolean esSibobim) {
		this.esSibobim = esSibobim;
	}

	public boolean isEstaActualizado() {
		return estaActualizado;
	}

	public void setEstaActualizado(boolean estaActualizado) {
		this.estaActualizado = estaActualizado;
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
	
	public String getClaveUr() {
		return claveUr;
	}

	public void setClaveUr(String claveUr) {
		this.claveUr = claveUr;
	}

	public String getClaveCt() {
		return claveCt;
	}

	public void setClaveCt(String claveCt) {
		this.claveCt = claveCt;
	}

	public String getClavePf() {
		return clavePf;
	}

	public void setClavePf(String clavePf) {
		this.clavePf = clavePf;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Universo con id: " + this.getIdUniverso();
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Integer getConsecutivoContrato() {
		return consecutivoContrato;
	}

	public void setConsecutivoContrato(Integer consecutivoContrato) {
		this.consecutivoContrato = consecutivoContrato;
	}

	public Date getInicioPeriodo1() {
		return inicioPeriodo1;
	}

	public void setInicioPeriodo1(Date inicioPeriodo1) {
		this.inicioPeriodo1 = inicioPeriodo1;
	}

	public Date getFinPeriodo1() {
		return finPeriodo1;
	}

	public void setFinPeriodo1(Date finPeriodo1) {
		this.finPeriodo1 = finPeriodo1;
	}

	public Date getInicioPeriodo2() {
		return inicioPeriodo2;
	}

	public void setInicioPeriodo2(Date inicioPeriodo2) {
		this.inicioPeriodo2 = inicioPeriodo2;
	}

	public Date getFinPeriodo2() {
		return finPeriodo2;
	}

	public void setFinPeriodo2(Date finPeriodo2) {
		this.finPeriodo2 = finPeriodo2;
	}

	public Date getInicioPeriodo3() {
		return inicioPeriodo3;
	}

	public void setInicioPeriodo3(Date inicioPeriodo3) {
		this.inicioPeriodo3 = inicioPeriodo3;
	}

	public Date getFinPeriodo3() {
		return finPeriodo3;
	}

	public void setFinPeriodo3(Date finPeriodo3) {
		this.finPeriodo3 = finPeriodo3;
	}

	public Date getInicioPeriodo4() {
		return inicioPeriodo4;
	}

	public void setInicioPeriodo4(Date inicioPeriodo4) {
		this.inicioPeriodo4 = inicioPeriodo4;
	}

	public Date getFinPeriodo4() {
		return finPeriodo4;
	}

	public void setFinPeriodo4(Date finPeriodo4) {
		this.finPeriodo4 = finPeriodo4;
	}

	public String getNumeroBitacora() {
		return numeroBitacora;
	}

	public void setNumeroBitacora(String numeroBitacora) {
		this.numeroBitacora = numeroBitacora;
	}
	
}
