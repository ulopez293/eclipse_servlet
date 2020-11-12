package mx.inntecsa.smem.bean;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.ActaEntregaRecepcionService;
import mx.inntecsa.smem.service.CentroTrabajoService;
import mx.inntecsa.smem.service.EntidadService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.service.UniversoService;
import mx.inntecsa.smem.reporte.ActaEntregaReporte;
import mx.inntecsa.smem.util.DownloadFile;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class ProgramacionServicioBean implements Serializable{

	private static final long serialVersionUID = -527594723313436195L;

	private Logger log = Logger.getLogger(ProgramacionServicioBean.class);
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;

	@Autowired
	private ActaEntregaRecepcionService actaEntregaRecepcionService;
	
	@Autowired
	private EntidadService entidadService; // Servicio de entidades
	
	@Autowired
	private CentroTrabajoService centroTrabajoService; // Servicio de Centros de trabajo
	
	@Autowired
	private UnidadRegionalService unidadRegionalService; // Servicio de unidades regionales

	@Autowired
	private UniversoService universoService;
	
	@Autowired
	private LoginBean loginBean;
	
	@Autowired
	private ActaEntregaReporte actaEntregaReporte;

	private int pagina; // Paginador para la tabla
	private List<ProgramacionServicioDto> programaciones; // lista de programaciones
	private EstatusServicio[] estatusServicio;
	private String urct;
	private TipoServicio[] tiposServicio = TipoServicio.values();
	private List<EntidadDto> entidades; // Lista de tipos de Entidades
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales
	private List<CentroTrabajoDto> centrosTrabajo; // Lista de tipos de centros trabajo
	private ProgramacionServicioDto programacion;
	private int entidad;
	private int unidadRegional;
	private int centroTrabajo;
	private TipoServicio tipoServicio;
	private EstatusServicio estatus;
	
	//variables para cerrar acta
	private ActaEntregaRecepcionDto actaEntrega;
	private String condicion;
	private boolean deshabilitaAtribuible;
	private boolean deshabilitaCampos;
	private boolean mostrarBoton=false;
	private ActaEntregaRecepcionDto actaEntregaGuardada=new ActaEntregaRecepcionDto();
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de programaciones por usuario:" + loginBean.getUsername());
		actaEntregaGuardada=null;
		mostrarBoton=false;
		
		if(loginBean.esUsuarioAdministrador()){
			
			estatusServicio  = EstatusServicio.values();
			entidades = entidadService.getEntidadesPorEstatus(Estatus.ACTIVO);
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesPorIdEntidad(entidades.get(0).getIdEntidad());
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
			urct = centrosTrabajo.get(0).getUrct();
			programaciones = programacionServicioService.getProgramacionServiciosPorEstatus(urct, estatusServicio);
		
		}else{
			
			EstatusServicio[] estatusServicioUnidad  = {EstatusServicio.INICIADO, EstatusServicio.ENPROGRAMACION, 
					EstatusServicio.ENPROCESO, EstatusServicio.CERRADO_NO_EXITOSO};
			
			estatusServicio = estatusServicioUnidad;
			
			CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajoPorURCT(loginBean.getUsername());
			entidades = new ArrayList<EntidadDto>();
			entidades.add(centroTrabajo.getUnidadRegional().getEntidad());
			unidadesRegionales = new ArrayList<UnidadRegionalDto>();
			unidadesRegionales.add(centroTrabajo.getUnidadRegional());
			centrosTrabajo = new ArrayList<CentroTrabajoDto>();
			centrosTrabajo.add(centroTrabajo);
			urct=loginBean.getUsername();

			programaciones = programacionServicioService.getProgramacionServiciosPorEstatus(urct, estatusServicio);
		}
		
		this.pagina = 1;
		
		return VistasEnum.PROGRAMACIONSERVICIOS.getIdVista();
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
	
	
	/**
	 * Actualiza el estatus de la programacion, cuando se revisa la notificacion de programacion
	 */
	public void actualizarEstatusProgramacion(){  
		log.info("Se actualiza el estatus de la programacion: " + programacion.getIdProgramacionServicio() + " solicitud " + 
				programacion.getSolicitudServicio().getIdSolicitudServicio());
		
		programacion.setEstatus(EstatusServicio.ENPROCESO);
		programacion.setFechaModificacion(new Date());
		
		boolean resultado = programacionServicioService.actualizarProgramacionServicio(programacion);
		
		if(resultado){
			alertaBean.setMensaje("El estatus ha sido actualizado correctamente.");
			alertaBean.setImagen(Icono.SUCCESS);
			
			//actualiza la lista de programaciones
			programaciones = programacionServicioService.getProgramacionServiciosPorEstatus(urct, estatusServicio);
			
			programacion = null;					
		}else{
			alertaBean.setMensaje("Ocurrio un error al actualizar el estatus de la programacion.");
			alertaBean.setImagen(Icono.ERROR);
		}
	}
	
	public void completarDatosActa(){
		mostrarBoton=false;
		actaEntregaGuardada=null;
		Date fechaActual = new Date();
		actaEntrega = new ActaEntregaRecepcionDto();
		actaEntrega.setFechaInicioServicio(fechaActual);
		actaEntrega.setFechaFinalServicio(fechaActual);	
		
		UniversoDto universoDto = programacion.getSolicitudServicio().getContratoDetalle().getUniverso();
		
		log.info("Buscando datos del universo "+ universoDto.getIdUniverso()+" para la generacion del acta");
		universoDto = universoService.getUniversoPorIdUniverso(universoDto.getIdUniverso());
		programacion.getSolicitudServicio().getContratoDetalle().setUniverso(universoDto);

		condicion="FUNCIONANDO";
		deshabilitaAtribuible = true;
		deshabilitaCampos = false;
	}
	
	public void deshabilitaCampos(){
		if(actaEntrega.getCancelaActa()){
			deshabilitaCampos = true;
		}else{
			deshabilitaCampos = false;
		}
	}
	
	public void cambiarCondicion(){
		if(actaEntrega.getMantenimientoExitoso()==1){
			condicion="FUNCIONANDO";
			deshabilitaAtribuible = true;
		}else{
			condicion="NO FUNCIONANDO";
			deshabilitaAtribuible = false;
		}
	}
	
	public void guardarActa(){
		log.info("Generando el acta para la programacion: " + programacion.getIdProgramacionServicio());
		actaEntrega.setProgramacionServicio(programacion);
		actaEntrega.setFechaRegistro(new Date());
		actaEntrega.setBaja(Estatus.ACTIVO);
		try{
			actaEntregaGuardada=actaEntregaRecepcionService.guardarActaEntrega(actaEntrega);
			//actualiza la lista de programaciones
			programaciones = programacionServicioService.getProgramacionServiciosPorEstatus(urct, estatusServicio);
			alertaBean.setMensaje("El acta se guardo exitosamente.");
			alertaBean.setImagen(Icono.SUCCESS);
			mostrarBoton=true;
		}catch(Exception e){
			log.error("",e);
			alertaBean.setImagen(Icono.ERROR);
			alertaBean.setMensaje("Ocurrio un error al guardar el acta. Intentelo nuevamente.");
		}
		
	}
	
	public void descargarActa(){
		actaEntregaReporte.imprimeActa(actaEntregaGuardada, 2);
	}
	
	public void buscarRegistros(){
		log.info("Buscando programaciones por los siguientes parametros." +" " +centroTrabajo);
		programaciones = programacionServicioService.getProgramacionServiciosPorParametros(centroTrabajo, tipoServicio,
				estatus, null, null, null);
		
		log.info("Registros encontrados" +" " +programaciones.size());
	}
	
	public void muestraNotificacion(){
		log.info("En el metodo antes de mostrar informacion de notificacion");
	}
	
	public List<ProgramacionServicioDto> getProgramaciones() {
		return programaciones;
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

	public ProgramacionServicioDto getProgramacion() {
		return programacion;
	}

	public void setProgramacion(ProgramacionServicioDto programacion) {
		this.programacion = programacion;
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

	public ActaEntregaRecepcionDto getActaEntrega() {
		return actaEntrega;
	}

	public void setActaEntrega(ActaEntregaRecepcionDto actaEntrega) {
		this.actaEntrega = actaEntrega;
	}

	public String getCondicion() {
		return condicion;
	}

	public boolean getDeshabilitaAtribuible() {
		return deshabilitaAtribuible;
	}

	public boolean getDeshabilitaCampos() {
		return deshabilitaCampos;
	}

	public boolean isMostrarBoton() {
		return mostrarBoton;
	}

	public void setMostrarBoton(boolean mostrarBoton) {
		this.mostrarBoton = mostrarBoton;
	}
	
	public boolean getMostrarBoton() {
		return mostrarBoton;
	}

	public ActaEntregaRecepcionDto getActaEntregaGuardada() {
		return actaEntregaGuardada;
	}

	public void setActaEntregaGuardada(ActaEntregaRecepcionDto actaEntregaGuardada) {
		this.actaEntregaGuardada = actaEntregaGuardada;
	}
	
	private List<List<String>> getDetalleParaExcel(List<ProgramacionServicioDto> programaciones) {
		List<List<String>> datos = new ArrayList<List<String>>();
		
		for (ProgramacionServicioDto programacion : programaciones) {
			List<String> listaDetalle = new ArrayList<String>();

			listaDetalle.add(programacion.getFolio());
			listaDetalle.add(String.valueOf(programacion.getSolicitudServicio().getContratoDetalle().getUniverso().getIdUniverso()));
			listaDetalle.add(programacion.getEstatus().getNombre());
			listaDetalle.add(validaDate(programacion.getSolicitudServicio().getFechaInicio()));
			listaDetalle.add(validaDate(programacion.getSolicitudServicio().getFechaFin()));			
			
			
			//agregando lista
			datos.add(listaDetalle);
		}		
		
		return datos;
	}	
	
	private String validaDate(Date dtfecha){
		String strFecha = "";
		if(dtfecha!=null){
			strFecha = dtfecha.toString();
		}
		return strFecha;		
	}
	
	public void descargarExcel(){
		
		log.info(">>>Descargando archivo Programaciones.xls");	
		List<String> encabezado = new ArrayList<String>(Arrays.asList("NO. DE CASO", "ID", "ESTATUS", "FECHA INICIO", "FECHA VENCIMIENTO"));		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			DownloadFile downloadFile = new DownloadFile("programaciones.xls",encabezado);
			downloadFile.write(this.getDetalleParaExcel(programaciones));
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=\"" + "programaciones.xls" + "\"");
			OutputStream os = response.getOutputStream();
			os.write(downloadFile.getByteFile());
			os.flush();
			os.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			log.error(">>>Error : " , e);
		}		
				
	}	
	
}
