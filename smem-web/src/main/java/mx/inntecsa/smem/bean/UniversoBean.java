package mx.inntecsa.smem.bean;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.EspecialidadDto;
import mx.inntecsa.smem.dto.GrupoClaveDto;
import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.dto.NivelAtencionDto;
import mx.inntecsa.smem.dto.SectorAdqDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusRequiereServicio;
import mx.inntecsa.smem.enums.Funcionalidad;
import mx.inntecsa.smem.service.CentroTrabajoService;
import mx.inntecsa.smem.service.ClaveService;
import mx.inntecsa.smem.service.ContratoDetalleService;
import mx.inntecsa.smem.service.EquipoService;
import mx.inntecsa.smem.service.EspecialidadService;
import mx.inntecsa.smem.service.GrupoClaveService;
import mx.inntecsa.smem.service.GrupoService;
import mx.inntecsa.smem.service.NivelAtencionService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.service.SectorAdqService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.service.UniversoService;
import mx.inntecsa.smem.util.DownloadFile;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class UniversoBean implements Serializable {
    
	private static final long serialVersionUID = -644791066118167694L;
    
	@Autowired
	private UniversoService universoService; // Servicio de universo
	
	@Autowired
	private GrupoClaveService grupoClaveService; // Servicio de grupos claves
	
	@Autowired
	private CentroTrabajoService centroTrabajoService; // Servicio de centros de trabajo
	
	@Autowired	
	private UnidadRegionalService unidadRegionalService; // Servicio de unidades regionales
	
	@Autowired
	private SectorAdqService sectorAdqService;
	
	@Autowired
	private EspecialidadService especialidadService;
	
	@Autowired
	private NivelAtencionService nivelAtencionService;
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private ClaveService claveService;
	
	@Autowired
	private ContratoDetalleService contratoDetalleService;
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes
	
	@Autowired
	private LoginBean loginBean;	
	
	private Logger log = Logger.getLogger(UniversoBean.class); // Bitacora
	private List<CentroTrabajoDto> centrosTrabajo = new ArrayList<CentroTrabajoDto>(); // Lista de Centros de universos
	private List<UniversoDto> universos = new ArrayList<UniversoDto>(); // Lista de universos
	private List<EquipoDto> equipos = new ArrayList<EquipoDto>(); // Lista de universos
	private List<GrupoDto> grupos = new ArrayList<GrupoDto>(); // Lista de universos
	private List<ClaveDto> claves = new ArrayList<ClaveDto>(); // Lista de universos
	private List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
	private List<NivelAtencionDto> nivelesAtencion = new ArrayList<NivelAtencionDto>();
	private List<SectorAdqDto> sectoresAdq = new ArrayList<SectorAdqDto>();
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales
	private int pagina; // Paginador para la tabla
	private UniversoDto universo; // universo
	private String serieBusqueda;
	private Integer inventarioBusqueda;
	private Integer centroTrabajoBusqueda;
	private Integer unidadRegionalBusqueda;
	private Integer inventario;
	private Integer idEquipo;
	private Integer idGrupo;
	private Integer idClave;
	private Boolean muestraModificacion;
	private Boolean requiereServicio;
	private Boolean muestraAgregar;
	private Boolean IsInventario=false;
	private Boolean IsValidado=false;
	private Long idUniverso;
	
		
	public List<CentroTrabajoDto> getCentrosTrabajo() {
		return centrosTrabajo;
	}

	public void setCentrosTrabajo(List<CentroTrabajoDto> centrosTrabajo) {
		this.centrosTrabajo = centrosTrabajo;
	}

	public List<UniversoDto> getUniversos() {
		return universos;
	}

	public void setUniversos(List<UniversoDto> universos) {
		this.universos = universos;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public UniversoDto getUniverso() {
		return universo;
	}

	public void setUniverso(UniversoDto universo) {
		this.universo = universo;
	}
		
	public String getSerieBusqueda() {
		return serieBusqueda;
	}

	public void setSerieBusqueda(String serieBusqueda) {
		this.serieBusqueda = serieBusqueda;
	}

	public Integer getInventarioBusqueda() {
		return inventarioBusqueda;
	}

	public void setInventarioBusqueda(Integer inventarioBusqueda) {
		this.inventarioBusqueda = inventarioBusqueda;
	}

	public Integer getCentroTrabajoBusqueda() {
		return centroTrabajoBusqueda;
	}

	public void setCentroTrabajoBusqueda(Integer centroTrabajoBusqueda) {
		this.centroTrabajoBusqueda = centroTrabajoBusqueda;
	}

	public Integer getUnidadRegionalBusqueda() {
		return unidadRegionalBusqueda;
	}

	public void setUnidadRegionalBusqueda(Integer unidadRegionalBusqueda) {
		this.unidadRegionalBusqueda = unidadRegionalBusqueda;
	}
	
	public List<UnidadRegionalDto> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	public void setUnidadesRegionales(List<UnidadRegionalDto> unidadesRegionales) {
		this.unidadesRegionales = unidadesRegionales;
	}
	
	public Integer getInventario() {
		return inventario;
	}

	public void setInventario(Integer inventario) {
		this.inventario = inventario;
	}		

	public List<EquipoDto> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoDto> equipos) {
		this.equipos = equipos;
	}
	
	public List<GrupoDto> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoDto> grupos) {
		this.grupos = grupos;
	}

	public List<ClaveDto> getClaves() {
		return claves;
	}

	public void setClaves(List<ClaveDto> claves) {
		this.claves = claves;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}		

	public Integer getIdClave() {
		return idClave;
	}

	public void setIdClave(Integer idClave) {
		this.idClave = idClave;
	}
	
	public Long getIdUniverso() {
		return idUniverso;
	}

	public void setIdUniverso(Long idUniverso) {
		this.idUniverso = idUniverso;
	}

	public List<EspecialidadDto> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadDto> especialidades) {
		this.especialidades = especialidades;
	}

	public List<NivelAtencionDto> getNivelesAtencion() {
		return nivelesAtencion;
	}

	public void setNivelesAtencion(List<NivelAtencionDto> nivelesAtencion) {
		this.nivelesAtencion = nivelesAtencion;
	}

	public List<SectorAdqDto> getSectoresAdq() {
		return sectoresAdq;
	}

	public void setSectoresAdq(List<SectorAdqDto> sectoresAdq) {
		this.sectoresAdq = sectoresAdq;
	}	

	public Boolean getMuestraModificacion() {
		return muestraModificacion;
	}

	public void setMuestraModificacion(Boolean muestraModificacion) {
		this.muestraModificacion = muestraModificacion;
	}	

	public Boolean getRequiereServicio() {
		return requiereServicio;
	}

	public void setRequiereServicio(Boolean requiereServicio) {
		this.requiereServicio = requiereServicio;
	}	

	public Boolean getMuestraAgregar() {
		return muestraAgregar;
	}

	public void setMuestraAgregar(Boolean muestraAgregar) {
		this.muestraAgregar = muestraAgregar;
	}

	public void buscarCentrosTrabajo() {
		centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadRegionalBusqueda);
	}
	
	public void buscarUniversos() {
		log.info("*****Busqueda de universos con informacion de contratos por parametros");		
		universos = universoService.getUniversosCompletosPorParametros(unidadRegionalBusqueda, centroTrabajoBusqueda,
		inventarioBusqueda, serieBusqueda , idUniverso);
	}
		
	public void buscaUniverso(){
		
		int cont = 0;	
		IsInventario=false;
		setIsValidado(false);
	
		String array[]= new String[3];
		String IsMarca="MARCA";
		String IsSerie="SERIE";
		String IsModelo="MODELO";
				
		log.info("Buscando universo por inventario en el bean: " + universo.getInventario());		
		universo = universoService.getUniversoSICOBIMPorInventario(Integer.valueOf(universo.getInventario()));
		 
		    //if (universo.getMarca()==null|| universo.getMarca()="S/MARCA" || universo.getSerie()==null ||universo.getModelo()==null){
		if (universo.getMarca()==null|| universo.getSerie()==null ||universo.getModelo()==null){
		       log.info("Buscando universo por inventario en el bean: Marca[ " + universo.getMarca()+"]  Serie["+universo.getSerie()+"]  Modelo["+universo.getModelo());	
		    if(universo.getMarca()!=null&&universo.getMarca()!="S/MARCA" )
		      IsMarca="";	
		    else{array[cont]=IsMarca; cont++;}
		    if(universo.getModelo()!=null)
			      IsModelo="";	
		    else {array[cont]=IsModelo; cont++;}
		    if(universo.getSerie()!=null)
			      IsSerie="";	
		    else {array[cont]=IsSerie; cont++;}
		    
		    IsInventario = true;
		    setIsValidado(true);
		    
		    if(cont==1){alertaBean.setMensaje("Dato de inventario no encontrado: "+array[0]); alertaBean.setImagen(Icono.ERROR);}
		    if(cont==2){alertaBean.setMensaje("Datos de inventario no encontrados: "+array[0]+" y "+array[1]); alertaBean.setImagen(Icono.ERROR);}
		    if(cont==3){alertaBean.setMensaje("Datos de inventario no encontrados: "+array[0]+", "+array[1]+ " y "+array[2]); alertaBean.setImagen(Icono.ERROR);}
		    
		    //alertaBean.setMensaje("Datos de inventario no encontrados:" +IsMarca+" "+IsModelo+" "+IsSerie+" por favor contactese con personal de SICOBIM");
			//alertaBean.setImagen(Icono.ERROR);	  
	      
	  }
		/*    else if(universo != null){
		    	setIsValidado(true);
		    	}*/ 		
	}
	
	public void buscarGrupos() {
		grupos = grupoService.getGruposConGrupoClavePorIdEquipo(idEquipo);
		claves = claveService.getClavesConGrupoClavePorIdGrupoYIdEquipo(idEquipo, grupos.get(0).getIdGrupo());
	}
	
	public void buscarClaves() {
		claves = claveService.getClavesConGrupoClavePorIdGrupoYIdEquipo(idEquipo, idGrupo);
	}	

	public void limpiarUniverso() {
		idEquipo = null;
		idGrupo = null;
		idClave = null;
		this.requiereServicio = false;		
		this.universo = new UniversoDto();
	}		
    
	public String inicializar() {
		
		//limpiarUniverso();
		if(loginBean.esUsuarioAdministrador()) {
			//Si eres administrador, puedes ver todos los centros de trabajo y unidades regionales
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesByEstatus(Estatus.ACTIVO);
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
			universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centrosTrabajo.get(0).getIdCentroTrabajo());
			muestraAgregar = false;
		} else {
			//si eres CT, sólo puedes ver las unidades regionales que te corresponden
			CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajoPorURCT(loginBean.getUsername());
			unidadesRegionales = new ArrayList<UnidadRegionalDto>();
			unidadesRegionales.add(centroTrabajo.getUnidadRegional());
			centrosTrabajo = new ArrayList<CentroTrabajoDto>();
			centrosTrabajo.add(centroTrabajo);
			universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
			//buscando grupos claves
			equipos = equipoService.getEquiposConGrupoClave();
			grupos = grupoService.getGruposConGrupoClavePorIdEquipo(equipos.get(0).getIdEquipo());
			claves = claveService.getClavesConGrupoClavePorIdGrupoYIdEquipo(equipos.get(0).getIdEquipo(), grupos.get(0).getIdGrupo());			
			muestraAgregar = true;
		}
		
		//Cargando más combos
		especialidades = especialidadService.getEspecialidadesByEstatus(Estatus.ACTIVO);
		nivelesAtencion = nivelAtencionService.getNivelesAtencionByEstatus(Estatus.ACTIVO);
		sectoresAdq = sectorAdqService.getSectoresAdqPorEstatus(Estatus.ACTIVO);
		
		pagina = 1;		
		serieBusqueda = null;					
		inventarioBusqueda = null;
		requiereServicio = false;
		universo = new UniversoDto();
		IsInventario=false;
		IsValidado=false;
		return VistasEnum.UNIVERSO.getIdVista();
		
	}		
			
    public void agregarUniverso() {		
		
    	//limpiarUniverso();
    	if (universo != null  && IsInventario == false) {
			log.info(">>>Guardando Universo: " + universo.getInventario());
			universo.setBaja(Estatus.ACTIVO);
			universo.setFechaRegistro(new Date());	
			universo.setFuncionalidad(Funcionalidad.FUNCIONANDO);
			universo.getEquipo().setIdEquipo(idEquipo);
			
			//Estatus del servicio
			EstatusRequiereServicio estatus = EstatusRequiereServicio
				.getEstatusRequiereServicio(requiereServicio ? 1 : 0);
			universo.setRequiereServicio(estatus);
			
			//buscando el Centro de trabajo al que pertenece el Usuario.
			CentroTrabajoDto centroTrabajo = centroTrabajoService
				.getCentroTrabajoPorURCT(loginBean.getUsername());
			universo.setCentroTrabajo(centroTrabajo);
			
			//Buscando el grupo clave 
			try{
			GrupoClaveDto grupoClave = grupoClaveService.getGrupoClavePorParametros(idEquipo, idGrupo, idClave);			
			universo.setIdGrupoClaveInt(grupoClave.getIdGrupoClave());
			}
			catch(Exception e){ 
				log.info("***Excepcion universo  ");
				} 
			universo=universoService.guardarUniverso(universo);
			if(universo != null && IsInventario == false) {
								
				alertaBean.setMensaje("Se ha agregado de manera exitosa un Equipo Médico con el Id: "+universo.getIdUniverso());
				alertaBean.setImagen(Icono.SUCCESS);
				universo = null;
				if(loginBean.esUsuarioAdministrador()) {
					universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centrosTrabajo.get(0).getIdCentroTrabajo());
				} else {
					universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
				}
			} else {
				if(IsInventario == true){
				alertaBean.setMensaje("Datos faltantes de inventario SICOBIM el equipo medico no se guardara");
				alertaBean.setImagen(Icono.ERROR);
				}
				else{
					alertaBean.setMensaje("Ha surgido un problema al tratar de guardar el Equipo Médico.");
					alertaBean.setImagen(Icono.ERROR);
								
				}
			}						
		}		
	}	
    
	public void editarUniverso() {		
		
		if (universo != null) {
			log.info(">>>Editando universo:" + universo.getIdUniverso());
			universo.setFechaModificacion(new Date());
			universo.getEquipo().setIdEquipo(idEquipo);
			
			//estatus del servicio
			EstatusRequiereServicio estatus = EstatusRequiereServicio
				.getEstatusRequiereServicio(requiereServicio ? 1 : 0);
			universo.setRequiereServicio(estatus);
			
			//buscando grupo clave
			GrupoClaveDto grupoClave = grupoClaveService.getGrupoClavePorParametros(idEquipo, idGrupo, idClave);			
			universo.setIdGrupoClaveInt(grupoClave.getIdGrupoClave());
							
			if(universoService.actualizarUniverso(universo)) {
				alertaBean.setMensaje("El Equipo Médico ha sido actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				universo = null;				
				if(loginBean.esUsuarioAdministrador()) {
					universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centrosTrabajo.get(0).getIdCentroTrabajo());
				} else {
					CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajoPorURCT(loginBean.getUsername());
					universos = universoService.getUniversosCompletosPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
				}
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Equipo Médico");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	

	public void eliminarUniverso() {		
		
		if (universo != null) {
			log.info(">>>Eliminando Universo : " + universo.getIdUniverso());
			
			//No se puede eliminar mientras existan servicios activos
			if(programacionServicioService.getExisteCorrectivoEnProcesoPorUniverso(universo.getIdUniverso())) {
				alertaBean.setMensaje("No se puede eliminar el Equipo Médico, ya que tiene Servicios activos.");
				alertaBean.setImagen(Icono.WARNING);
				universo = null;
				
			} else {			
				universo.setFechaBaja(new Date());
				universo.setBaja(Estatus.INACTIVO);
				
				if(universoService.eliminarUniverso(universo)) {
					alertaBean.setMensaje("El Equipo Médico ha sido eliminado de manera exitosa.");
					alertaBean.setImagen(Icono.SUCCESS);
					universo = null;				
					universos = universoService.getUniversosPorEstatus(Estatus.ACTIVO);
				
				} else {
					alertaBean.setMensaje("Ha surgido un problema al tratar de eliminar el Equipo Médico.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}	
		}
	}	
	
	public void modificaUniverso() {
		
		log.info(" getContratoDetallePorIdUniverso ["+contratoDetalleService.getContratoDetallePorIdUniverso(universo.getIdUniverso())+"]");
		//Verificar si tiene contrato vigente
		if( contratoDetalleService.getContratoDetallePorIdUniverso(universo.getIdUniverso()) != null) {
			alertaBean.setMensaje("No se puede Editar el Equipo Médico, ya que " +
				"tiene un contrato vigente. Contacte al Administrador.");
			alertaBean.setImagen(Icono.WARNING);
			this.muestraModificacion = false;
			
		} else {
			
			this.muestraModificacion = true;
			int idEstatus = universo.getRequiereServicio().getIdEstatusRequiereServicio();
			requiereServicio = idEstatus == 1 ? true : false;
			
			if(universo.getGrupoClave() != null)
				if(universo.getGrupoClave().getGrupo().getIdGrupo() != null)	
					if(universo.getGrupoClave().getEquipo() != null)
					{
			//asginando valor por default a los combos anidados
			idEquipo = universo.getGrupoClave().getEquipo().getIdEquipo();
			idGrupo = universo.getGrupoClave().getGrupo().getIdGrupo();
			idClave = universo.getGrupoClave().getClave().getIdClave();
			
			//Refrescando los combos para que se refresquen con los de clave grupo
			equipos = equipoService.getEquiposConGrupoClave();              
			
			grupos = grupoService.getGruposConGrupoClavePorIdEquipo(idEquipo); 
			claves = claveService.getClavesConGrupoClavePorIdGrupoYIdEquipo(idEquipo, idGrupo); 
			} else{
				log.info("-modificaUniverso- Datos nulos");
			}						
		}
   }

	public Boolean getIsValidado() {
		return IsValidado;
	}

	public void setIsValidado(Boolean isValidado) {
		IsValidado = isValidado;
	}
	
	private List<List<String>> getDetalleParaExcel(List<UniversoDto> universos) {
		List<List<String>> datos = new ArrayList<List<String>>();
		
		for (UniversoDto universo : universos) {
			List<String> listaDetalle = new ArrayList<String>();

			listaDetalle.add(universo.getNivelAtencion().getDescripcion());
			listaDetalle.add(String.valueOf(universo.getIdUniverso()));
			listaDetalle.add(universo.getEquipo().getEquipo());			
			listaDetalle.add(universo.getMarca());			
			listaDetalle.add(universo.getModelo());
			listaDetalle.add(universo.getSerie());
			listaDetalle.add(universo.getInventario());
			listaDetalle.add(universo.getNumeroBitacora());
			listaDetalle.add(universo.getObsubica());
			listaDetalle.add(universo.getEspecialidad().getEspecialidad());
			
			if (universo.getGrupoClave()!=null){
				
				if (universo.getGrupoClave().getGrupo()!=null){
					listaDetalle.add(universo.getGrupoClave().getGrupo().getGrupo());
				}else{
					listaDetalle.add("");
				}				
				
				if (universo.getGrupoClave().getClave()!=null){
					listaDetalle.add(universo.getGrupoClave().getClave().getClave());
					listaDetalle.add(universo.getGrupoClave().getClave().getNombreGenerico());
				}else{
					listaDetalle.add("");
					listaDetalle.add("");
				}
				
			}else{
				listaDetalle.add("");	
				listaDetalle.add("");
				listaDetalle.add("");
			}					
			
			listaDetalle.add(universo.getFuncionalidad().getDescripcion());
			listaDetalle.add(universo.getBaja().getDescripcion());
			listaDetalle.add(universo.getRequiereServicio().getDescripcion());
			listaDetalle.add(universo.getNumeroContrato());
			listaDetalle.add(String.valueOf(universo.getConsecutivoContrato()));
			listaDetalle.add(universo.getActualizacionTecnologica());
			listaDetalle.add(universo.getNumeroLicitacion());
			listaDetalle.add(universo.getPartida());
			listaDetalle.add(universo.getNoContratoAdq());
			listaDetalle.add(validaDate(universo.getInicioPeriodo1()));
			listaDetalle.add(validaDate(universo.getFinPeriodo1()));
			listaDetalle.add(validaDate(universo.getInicioPeriodo2()));
			listaDetalle.add(validaDate(universo.getFinPeriodo2()));
			listaDetalle.add(validaDate(universo.getInicioPeriodo3()));
			listaDetalle.add(validaDate(universo.getFinPeriodo3()));
			listaDetalle.add(validaDate(universo.getInicioPeriodo4()));
			listaDetalle.add(validaDate(universo.getFinPeriodo4()));
			listaDetalle.add(universo.getSectorAdq().getSectorAdq());
			listaDetalle.add(universo.getAnioAdq());
			listaDetalle.add(validaDate(universo.getFechaGarantia()));
			listaDetalle.add(validaDate(universo.getFechaInstalacion()));
			listaDetalle.add(validaDate(universo.getFechaApertura()));
			listaDetalle.add(universo.getProveedorVentaEquipo());
			listaDetalle.add(universo.getPrecioEquipoSinIva());
			listaDetalle.add(universo.getObservaciones());
			listaDetalle.add(universo.getCentroTrabajo().getUrct());
			listaDetalle.add(universo.getCabm() != null ? universo.getCabm() : "");								
			
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
		log.info(">>>Descargando archivo Universo.xls");	
		List<String> encabezado = new ArrayList<String>(Arrays.asList("NIVEL DE ATENCION", "ID", "EQUIPO", "MARCA", "MODELO", "SERIE", "NO. DE INVENTARIO", "NO. DE BITACORA", "UBICACION", "ESPECIALIDAD", "GRUPO", "CLAVE", "NOMBRE GENERICO", "FUNCIONALIDAD", "ESTATUS", "REQUIERE SERVICIO", "CONTRATO SERVICIO", "CONSECUTIVO CONTRATO", "ACTUALIZACION TECNOLOGIA", "NUMERO DE LICITACION", "PARTIDA PRESUPUESTAL", "NO. CONTRATO ADQUISICION", "INICIO PERIODO 1", "FIN PERIODO 1", "INICIO PERIODO 2", "FIN PERIODO 2", "INICIO PERIODO 3", "FIN PERIODO 3", "INICIO PERIODO 4", "FIN PERIODO 4", "SECTOR ADQUISICION", "ANIO DE AQUISICION", "TERMINO DE GARANTIA", "PUESTA EN MARCHA", "FECHA DE APERTURA", "PROVEEDOR DE VENTA", "PRECIO DE ADQUISICION", "OBSERVACIONES", "URCT", "CABM"));		
		try {						
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			DownloadFile downloadFile = new DownloadFile("universo.xls",encabezado);
			downloadFile.write(this.getDetalleParaExcel(universos));
			response.setContentType("application/vnd.ms-excel");			
			response.setHeader("Content-disposition", "attachment; filename=\"" + "universo.xls" + "\"");
			OutputStream os = response.getOutputStream();
			os.write(downloadFile.getByteFile());
			os.flush();
			os.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			log.error(">>>Error : " , e);
		}
	}
	
	public void descargarTodoExcel(){
		log.info(">>>Descargando archivo UniversoTodo.xls");	
		List<String> encabezado = new ArrayList<String>(Arrays.asList("NIVEL DE ATENCION", "ID", "EQUIPO", "MARCA", "MODELO", "SERIE", "NO. DE INVENTARIO", "NO. DE BITACORA", "UBICACION", "ESPECIALIDAD", "GRUPO", "CLAVE", "NOMBRE GENERICO", "FUNCIONALIDAD", "ESTATUS", "REQUIERE SERVICIO", "CONTRATO SERVICIO", "CONSECUTIVO CONTRATO", "ACTUALIZACION TECNOLOGIA", "NUMERO DE LICITACION", "PARTIDA PRESUPUESTAL", "NO. CONTRATO ADQUISICION", "INICIO PERIODO 1", "FIN PERIODO 1", "INICIO PERIODO 2", "FIN PERIODO 2", "INICIO PERIODO 3", "FIN PERIODO 3", "INICIO PERIODO 4", "FIN PERIODO 4", "SECTOR ADQUISICION", "ANIO DE AQUISICION", "TERMINO DE GARANTIA", "PUESTA EN MARCHA", "FECHA DE APERTURA", "PROVEEDOR DE VENTA", "PRECIO DE ADQUISICION", "OBSERVACIONES", "URCT", "CABM"));		
		try {						
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			DownloadFile downloadFile = new DownloadFile("universoTodo.xls",encabezado);
			universos = universoService.getUniversos();
			downloadFile.write(this.getDetalleParaExcel(universos));
			response.setContentType("application/vnd.ms-excel");			
			response.setHeader("Content-disposition", "attachment; filename=\"" + "universoTodo.xls" + "\"");
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
