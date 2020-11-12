package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.reporte.ActaEntregaReporte;
import mx.inntecsa.smem.service.ActaEntregaRecepcionService;
import mx.inntecsa.smem.service.CentroTrabajoService;
import mx.inntecsa.smem.service.EntidadService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class ImpresionActaBean implements Serializable{

	private static final long serialVersionUID = -527594723313436195L;

	private Logger log = Logger.getLogger(ImpresionActaBean.class);
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ActaEntregaRecepcionService actaEntregaRecepcionService;
	
	@Autowired
	private EntidadService entidadService; // Servicio de entidades
	
	@Autowired
	private CentroTrabajoService centroTrabajoService; // Servicio de Centros de trabajo
	
	@Autowired
	private UnidadRegionalService unidadRegionalService; // Servicio de unidades regionales

	@Autowired
	private LoginBean loginBean;
	
	@Autowired
	private ActaEntregaReporte actaEntregaReporte;

	private int pagina; // Paginador para la tabla
	private List<ActaEntregaRecepcionDto> actas;
	
	private EstatusServicio[] estatusServicio = {EstatusServicio.CERRADO_NO_EXITOSO, EstatusServicio.CERRADO_EXITOSO,EstatusServicio.CANCELADO};
	
	private TipoServicio[] tiposServicio = TipoServicio.values();
	private List<EntidadDto> entidades; // Lista de tipos de Entidades
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales
	private List<CentroTrabajoDto> centrosTrabajo; // Lista de tipos de centros trabajo
	private ActaEntregaRecepcionDto acta;
	private int entidad;
	private int unidadRegional;
	private int centroTrabajo;
	private TipoServicio tipoServicio;
	private EstatusServicio estatus;
	private Date fechaInicio;
	private Date fechaFin;
	private String folio;
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de programaciones por usuario:" + loginBean.getUsername());
		String urct="";
		
		if(loginBean.esUsuarioAdministrador()){
			entidades = entidadService.getEntidadesPorEstatus(Estatus.ACTIVO);
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesPorIdEntidad(entidades.get(0).getIdEntidad());
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
			urct = centrosTrabajo.get(0).getUrct();
			
		}else{
			CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajoPorURCT(loginBean.getUsername());
			entidades = new ArrayList<EntidadDto>();
			entidades.add(centroTrabajo.getUnidadRegional().getEntidad());
			unidadesRegionales = new ArrayList<UnidadRegionalDto>();
			unidadesRegionales.add(centroTrabajo.getUnidadRegional());
			centrosTrabajo = new ArrayList<CentroTrabajoDto>();
			centrosTrabajo.add(centroTrabajo);
			urct=loginBean.getUsername();
		}

		actas=actaEntregaRecepcionService.getActasEntregaRecepcionPorUrctYEstatus(urct, estatusServicio);
		log.info("Numero de actas: "+actas.size());
		this.pagina = 1;
		
		return VistasEnum.IMPRESIONACTAS.getIdVista();
	}
	
	public void buscarUnidadesRegionales() {

		log.info(">>>Buscando Unidades por entidad: " + entidad);
		
		if(entidad != 0){
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesPorIdEntidad(entidad);
			
			if(unidadesRegionales != null && unidadesRegionales.size() > 0){
				centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
			}			
		}
		
	}
	
	public void buscarCentrosTrabajo() {

		log.info(">>>Buscando CT por unidad regional: " +unidadRegional);
		
		if(unidadRegional != 0){
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadRegional);
		}

	}
	
	public void descargarActa(){
		actaEntregaReporte.imprimeActa(acta, 2);
	}
	
	public void buscarRegistros(){
		log.info("Buscando programaciones por los siguientes parametros." +" " +centroTrabajo + " : " + fechaInicio + " : " + fechaFin + " : " + folio);
		actas=actaEntregaRecepcionService.getActasEntregaRecepcionPorParametros(centroTrabajo, tipoServicio, estatus, fechaInicio, fechaFin, folio);
		log.info("Registros encontrados de actas: "+actas.size());
		this.pagina = 1;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public EstatusServicio[] getEstatusServicio() {
		return estatusServicio;
	}

	public List<EntidadDto> getEntidades() {
		return entidades;
	}

	public List<UnidadRegionalDto> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	public List<CentroTrabajoDto> getCentrosTrabajo() {
		return centrosTrabajo;
	}

	public int getEntidad() {
		return entidad;
	}

	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}

	public int getUnidadRegional() {
		return unidadRegional;
	}

	public void setUnidadRegional(int unidadRegional) {
		this.unidadRegional = unidadRegional;
	}

	public int getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(int centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public TipoServicio[] getTiposServicio() {
		return tiposServicio;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public EstatusServicio getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusServicio estatus) {
		this.estatus = estatus;
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public List<ActaEntregaRecepcionDto> getActas() {
		return actas;
	}

	public void setActas(List<ActaEntregaRecepcionDto> actas) {
		this.actas = actas;
	}

	public ActaEntregaRecepcionDto getActa() {
		return acta;
	}

	public void setActa(ActaEntregaRecepcionDto acta) {
		this.acta = acta;
	}

}
