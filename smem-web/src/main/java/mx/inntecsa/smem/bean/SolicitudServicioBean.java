package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

//import org.richfaces.component.UIExtendedDataTable;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.ActaEntregaRecepcionService;
import mx.inntecsa.smem.service.ConfiguracionService;
import mx.inntecsa.smem.service.ContratoDetalleService;
import mx.inntecsa.smem.service.EquipoService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.service.UniversoService;
import mx.inntecsa.smem.utils.Fecha;
import mx.inntecsa.smem.reporte.SolicitudServicioReporte;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class SolicitudServicioBean implements Serializable{

	private static final long serialVersionUID = -1497896687905506856L;
	private Logger log = Logger.getLogger(SolicitudServicioBean.class);
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private UniversoService universoService;

	@Autowired
	private ContratoDetalleService contratoDetalleService;
	
	@Autowired
	private ConfiguracionService configuracionService;	
	
	@Autowired
	private LoginBean loginBean;	
	
	@Autowired
	private SolicitudServicioReporte solicitudServicioReporte;
	
	@Autowired
	private ActaEntregaRecepcionService actaEntregaRecepcionService;
	
	private int pagina; // Paginador para la tabla
	private ProgramacionServicioDto programacionServicio;
	private List<UniversoDto> universos;
	private List<EquipoDto> equipos;
	private Integer idEquipo;
	private String idUniverso;
	private String serie;
	private String inventario;
	private Collection<Object> selection; // seleccion utilizada para la eleccion de algun universo
	
	private UniversoDto universoSeleccionado;
	private ContratoDetalleDto contratoDetalleDto;
	private ActaEntregaRecepcionDto actaEntregaRecepcionDto;
	
	private String descripcionFalla;
	private boolean mostrarBoton=false;
	ProgramacionServicioDto programacionServicioGuardada = new ProgramacionServicioDto(); 
	
	public String inicializar(){
		
		log.info("Inicializando datos para nuevo servicio correctivo.");
		
		equipos = equipoService.getEquiposActivos(); 
		universos = new ArrayList<UniversoDto>();
		idUniverso=null;
		idEquipo = null;
		serie="";
		inventario="";
		
		descripcionFalla= null;
		universoSeleccionado= null;
		contratoDetalleDto = null;
		actaEntregaRecepcionDto = null;
		programacionServicioGuardada=null;
		mostrarBoton=false;
		
		this.pagina = 1;
		
		if(selection!=null && selection.size()>0){
			selection.clear();
		}
		
		return VistasEnum.SOLICITUDSERVICIOS.getIdVista();
	}
	
	public void buscarUniversos(){
		
		log.info("Buscando universos por equipo " + idEquipo + " idU: " + idUniverso + 
				" inv: " + inventario + " serie: " + serie);
		
		String urct = loginBean.getUsername();
		Long idUniversoL = null;
		
		if(!idUniverso.trim().equals("")){ 
			idUniversoL = new Long(idUniverso);
		}
		
		universos = universoService.getUniversosEnContratoSinServicios(urct,idEquipo,idUniversoL,serie,inventario);
		
		if(selection!=null && selection.size()>0){
			selection.clear();
		}
		
		universoSeleccionado=null;
		contratoDetalleDto = null;
		actaEntregaRecepcionDto = null;
		descripcionFalla= null;
		programacionServicioGuardada=null;
		mostrarBoton=false;
	}
	
//	public void selectionListener(AjaxBehaviorEvent event) {
//		UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
//
//        for (Object selectionKey : selection) {
//            dataTable.setRowKey(selectionKey);
//            if (dataTable.isRowAvailable()) {
//            	mostrarBoton=false;
//            	descripcionFalla=null;
//            	programacionServicioGuardada=null;
//                universoSeleccionado = (UniversoDto) dataTable.getRowData();
//                contratoDetalleDto = contratoDetalleService.getContratoDetallePorIdUniverso(universoSeleccionado.getIdUniverso());
//                actaEntregaRecepcionDto = actaEntregaRecepcionService.getUltimaActaEntregaPorIdUniverso(universoSeleccionado.getIdUniverso());
//                
//                log.info(">>>selection " + universoSeleccionado.getIdUniverso() + "ultimoMantenimiento: " + actaEntregaRecepcionDto);
//                
//                break;
//            }
//        }
//    }	
	
	public void guardarSolicitud(){
		if(universoSeleccionado!=null){
			log.info("Guardando una solicitud de servicio correctivo para el universo " + universoSeleccionado.getIdUniverso());
			boolean existeCorrectivo = programacionServicioService.getExisteCorrectivoEnProcesoPorUniverso(universoSeleccionado.getIdUniverso());
			log.info("existe correctivo " + existeCorrectivo);
			if(existeCorrectivo){
				log.info("Ya existe correctivo para el universo... " + universoSeleccionado.getIdUniverso());
				alertaBean.setMensaje("Ya existe un servicio correctivo en proceso.");
				alertaBean.setImagen(Icono.WARNING);
			}
			else{
				ConfiguracionDto configuracion = configuracionService.getConfiguracion();
				
				Date fechaActual = new Date();
				Date fechaFinal = Fecha.agregarDiasAFecha(fechaActual, configuracion.getDiasCorrectivos());
				Date fechaInicial = Fecha.agregarDiasAFecha(fechaActual, 1);
				int anioActual=GregorianCalendar.getInstance().get(Calendar.YEAR);
				String ultimoConsecutivo=programacionServicioService.getUltimoConsecutivoFolio(anioActual);
				log.info("***Ultimo Consecutivo: "+ultimoConsecutivo+" del año: "+anioActual);
				Integer consecutivo=Integer.parseInt(ultimoConsecutivo)+1;
				String folio = new String();
				//Estructura nueva del Folio: COR-000000(Consecutivo 6 digitos)/0000(Año 4 digitos)
				//String folio = "COR" + "-"+ loginBean.getUsername() + "-"+ universoSeleccionado.getIdUniverso();
				if(consecutivo<10){
					folio="COR"+"-"+"00000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<100){
					folio="COR"+"-"+"0000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<1000){
					folio="COR"+"-"+"000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<10000){
					folio="COR"+"-"+"00"+consecutivo+"/"+anioActual;
				}else if(consecutivo<100000){
					folio="COR"+"-"+"0"+consecutivo+"/"+anioActual;
				}else if(consecutivo<1000000){
					folio="COR"+"-"+consecutivo+"/"+anioActual;
				}else{
					folio=null;
				}
				if(folio!=null && !folio.equals("")){
					ProgramacionServicioDto programacionServicioDto = new ProgramacionServicioDto(); 
					programacionServicioDto.setTipoServicio(TipoServicio.SERVICIO_CORRECTIVO);
					programacionServicioDto.setFolio(folio);
					programacionServicioDto.setEstatus(EstatusServicio.INICIADO);
					programacionServicioDto.setFechaRegistro(new Date());
					programacionServicioDto.setBaja(Estatus.ACTIVO);
					programacionServicioDto.setNprogramacion(0);
					
					SolicitudServicioDto solicitudServicioDto = new SolicitudServicioDto();
					solicitudServicioDto.setContratoDetalle(contratoDetalleDto);
					solicitudServicioDto.setFechaInicio(fechaInicial);
					solicitudServicioDto.setFechaFin(fechaFinal);
					solicitudServicioDto.setMotivoSolicitud(descripcionFalla);
					solicitudServicioDto.setFechaRegistro(new Date());
					solicitudServicioDto.setBaja(Estatus.ACTIVO);
		
					programacionServicioDto.setSolicitudServicio(solicitudServicioDto);
					log.info("Guardando el correctivo con folio: " + folio);
					try{
						programacionServicioGuardada=programacionServicioService.guardarProgramacionServicioCorrectivo(programacionServicioDto, universoSeleccionado);
						alertaBean.setImagen(Icono.SUCCESS);
						alertaBean.setMensaje("Se genero servicio correctivo con folio " + folio);
						universos.remove(universoSeleccionado);
						mostrarBoton=true;
						universoSeleccionado= null;
						contratoDetalleDto = null;
						actaEntregaRecepcionDto = null;
						descripcionFalla=" ";
						if(selection!=null && selection.size()>0){
							selection.clear();
						}
					}catch(Exception e){
						log.error("",e);
						alertaBean.setImagen(Icono.ERROR);
						alertaBean.setMensaje("Ocurrio un error al generar el servicio. Intentelo nuevamente.");
					}
				}else{
					alertaBean.setImagen(Icono.ERROR);
					alertaBean.setMensaje("Se han asignado el número máximo de folios posibles (999999). Por favor consúltelo con su administrador.");
				}
			}
		}else{
			alertaBean.setImagen(Icono.WARNING);
			alertaBean.setMensaje("Necesita seleccionar un equipo, para poder solicitar el servicio correctivo");
		}
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public ProgramacionServicioDto getProgramacionServicio() {
		return programacionServicio;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getIdUniverso() {
		return idUniverso;
	}

	public void setIdUniverso(String idUniverso) {
		this.idUniverso = idUniverso;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getInventario() {
		return inventario;
	}

	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	public List<UniversoDto> getUniversos() {
		return universos;
	}

	public List<EquipoDto> getEquipos() {
		return equipos;
	}
	
	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public UniversoDto getUniversoSeleccionado() {
		return universoSeleccionado;
	}

	public void setUniversoSeleccionado(UniversoDto universoSeleccionado) {
		this.universoSeleccionado = universoSeleccionado;
	}

	public ContratoDetalleDto getContratoDetalleDto() {
		return contratoDetalleDto;
	}

	public String getDescripcionFalla() {
		return descripcionFalla;
	}

	public void setDescripcionFalla(String descripcionFalla) {
		this.descripcionFalla = descripcionFalla;
	}

	public ActaEntregaRecepcionDto getActaEntregaRecepcionDto() {
		return actaEntregaRecepcionDto;
	}

	public void setActaEntregaRecepcionDto(
			ActaEntregaRecepcionDto actaEntregaRecepcionDto) {
		this.actaEntregaRecepcionDto = actaEntregaRecepcionDto;
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

	public ProgramacionServicioDto getProgramacionServicioGuardada() {
		return programacionServicioGuardada;
	}

	public void setProgramacionServicioGuardada(
			ProgramacionServicioDto programacionServicioGuardada) {
		this.programacionServicioGuardada = programacionServicioGuardada;
	}

	public void descargarSolicitud(){
		solicitudServicioReporte.imprimeSolicitudServicio(programacionServicioGuardada, 2);
	}
	
}
