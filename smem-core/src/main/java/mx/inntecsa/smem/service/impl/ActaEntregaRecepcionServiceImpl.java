package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import mx.inntecsa.smem.dao.ActaEntregaRecepcionDao;
import mx.inntecsa.smem.dao.ProgramacionServicioDao;
import mx.inntecsa.smem.dao.UniversoDao;
import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusMantenimiento;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.Funcionalidad;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ActaEntregaRecepcion;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.pojo.ProgramacionServicio;
import mx.inntecsa.smem.pojo.Universo;
import mx.inntecsa.smem.service.ActaEntregaRecepcionService;
import mx.inntecsa.smem.service.ConfiguracionService;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("actaEntregaRecepcionService")
public class ActaEntregaRecepcionServiceImpl implements ActaEntregaRecepcionService {
	
	private Logger log = Logger.getLogger(ActaEntregaRecepcionServiceImpl.class);
			
	@Autowired
	private ActaEntregaRecepcionDao actaEntregaRecepcionDao;
	
	@Autowired
	private UniversoDao universoDao;	

	@Autowired
	private ProgramacionServicioDao programacionServicioDao;
	
	@Autowired
	private ConfiguracionService configuracionService;
	
	@Override
	public List<ActaEntregaRecepcionDto> getActasEntregasRecepcion() {
		List<ActaEntregaRecepcionDto> actaEntregaRecepcionesDto = new ArrayList<ActaEntregaRecepcionDto>(); //Lista de ActasEntregasRecepciones(DTO)

		for (ActaEntregaRecepcion actaEntregaRecepcion : actaEntregaRecepcionDao.getActasEntregasRecepcion()) {
			ActaEntregaRecepcionDto actaEntregaRecepcionDto = this.manualModelMapper(actaEntregaRecepcion);
			actaEntregaRecepcionesDto.add(actaEntregaRecepcionDto);
		}
		
		return actaEntregaRecepcionesDto;
	}

	@Override
	public List<ActaEntregaRecepcionDto> getActasEntregasRecepcionByEstatus(Estatus estatus) {
		List<ActaEntregaRecepcionDto> actaEntregaRecepcionesDto = new ArrayList<ActaEntregaRecepcionDto>(); //Lista de ActasEntregasRecepciones(DTO)

		for (ActaEntregaRecepcion actaEntregaRecepcion : actaEntregaRecepcionDao.getActasEntregasRecepcionByEstatus(estatus)) {
			ActaEntregaRecepcionDto actaEntregaRecepcionDto = this.manualModelMapper(actaEntregaRecepcion);
			actaEntregaRecepcionesDto.add(actaEntregaRecepcionDto);
		}
		
		return actaEntregaRecepcionesDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActaEntregaRecepcionDto guardarActaEntrega(ActaEntregaRecepcionDto actaEntregaDto) {
		
		boolean actualizaUniverso = false;
		ActaEntregaRecepcion actaEntrega = new ModelMapper().map(actaEntregaDto, ActaEntregaRecepcion.class);
		
		ProgramacionServicio programacionServicio = actaEntrega.getProgramacionServicio();
		Universo universo = programacionServicio.getSolicitudServicio().getContratoDetalle().getUniverso();
		
		//guardar acta entrega
		Integer idActa = (Integer) actaEntregaRecepcionDao.save(actaEntrega);
		actaEntregaDto.setIdActaEntregaRecepcion(idActa);

		if(actaEntregaDto.getCancelaActa()){
			programacionServicio.setEstatus(EstatusServicio.CANCELADO);
			actualizaUniverso = true;
		}else{
			if(actaEntrega.getMantenimientoExitoso() == EstatusMantenimiento.EXITOSO.getIdEstatus()){
				programacionServicio.setEstatus(EstatusServicio.CERRADO_EXITOSO);
				actualizaUniverso=true;				
			}else{
				programacionServicio.setEstatus(EstatusServicio.CERRADO_NO_EXITOSO);
				actualizaUniverso=false;
			}

		}

		//actualiza estatus programacion servicio
		log.info("Actualizando el estatus de la programacion de servicio: " + programacionServicio.getIdProgramacionServicio() + " estatus: " + 
					programacionServicio.getEstatus().getDescripcion());
		programacionServicio.setFechaModificacion(new Date());
		programacionServicioDao.update(programacionServicio);

		if(actualizaUniverso){
			//Actualiza la funcionalidad del universo a FUNCIONANDO
			log.info("Actualizando funcionalidad de universo: " + universo.getIdUniverso() + " func: " + universo.getFuncionalidad().getDescripcion());
			universo.setFuncionalidad(Funcionalidad.FUNCIONANDO);
			universo.setFechaModificacion(new Date());
			universoDao.update(universo);
		}
		
		return actaEntregaDto;
	}


	@Override
	public ActaEntregaRecepcionDto getUltimaActaEntregaPorIdProgramacion(int idProgramacionServicio) {
			ActaEntregaRecepcionDto actaEntregaRecepcionDto=null;
			ActaEntregaRecepcion actaEntrega = actaEntregaRecepcionDao.getUltimaActaEntregaPorIdProgramacion(idProgramacionServicio);
			if(actaEntrega!=null){
				actaEntregaRecepcionDto=manualModelMapper(actaEntrega);
			}
			return actaEntregaRecepcionDto;
	}
	
	@Override
	public ActaEntregaRecepcionDto getUltimaActaEntregaPorIdUniverso(long idUniverso){
			ActaEntregaRecepcionDto actaEntregaRecepcionDto=null;
			ActaEntregaRecepcion actaEntrega = actaEntregaRecepcionDao.getUltimaActaEntregaPorIdUniverso(idUniverso);
			if(actaEntrega!=null){
				actaEntregaRecepcionDto=manualModelMapper(actaEntrega);
			}
			return actaEntregaRecepcionDto;
	}
	
	private ActaEntregaRecepcionDto manualModelMapper(ActaEntregaRecepcion acta){
		
		ActaEntregaRecepcionDto actaEntregaDto = new ActaEntregaRecepcionDto();
		actaEntregaDto.setTecnicoEnviado(acta.getTecnicoEnviado());
		actaEntregaDto.setFechaInicioServicio(acta.getFechaInicioServicio());
		actaEntregaDto.setFechaFinalServicio(acta.getFechaFinalServicio());
		actaEntregaDto.setResponsableEquipo(acta.getResponsableEquipo());
		actaEntregaDto.setDescripcionCompletaServicio(acta.getDescripcionCompletaServicio());
		actaEntregaDto.setKitRefUtilizadas(acta.getKitRefUtilizadas());
		actaEntregaDto.setRecomendaciones(acta.getRecomendaciones());
		actaEntregaDto.setHorasRealServicio(acta.getHorasRealServicio());
		actaEntregaDto.setMantenimientoExitoso(acta.getMantenimientoExitoso());
		actaEntregaDto.setAtribuible(acta.getAtribuible());
		actaEntregaDto.setBaja(acta.getBaja());
		actaEntregaDto.setFechaBaja(acta.getFechaBaja());
		actaEntregaDto.setFechaModificacion(acta.getFechaModificacion());
		actaEntregaDto.setFechaRegistro(acta.getFechaRegistro());
		actaEntregaDto.setIdActaEntregaRecepcion(acta.getIdActaEntregaRecepcion());
		
		if(acta.getProgramacionServicio()!=null){
			ProgramacionServicioDto programacionDto = new ProgramacionServicioDto();
			programacionDto.setIdProgramacionServicio(acta.getProgramacionServicio().getIdProgramacionServicio());
			programacionDto.setFolio(acta.getProgramacionServicio().getFolio());
			programacionDto.setEstatus(acta.getProgramacionServicio().getEstatus());
			programacionDto.setTecnicoProv(acta.getProgramacionServicio().getTecnicoProv());
			programacionDto.setTipoServicio(acta.getProgramacionServicio().getTipoServicio());
			programacionDto.setFechaVisita(acta.getProgramacionServicio().getFechaVisita());
			programacionDto.setTelTecnico(acta.getProgramacionServicio().getTelTecnico());
			programacionDto.setNprogramacion(acta.getProgramacionServicio().getNprogramacion());
			programacionDto.setObservaciones(acta.getProgramacionServicio().getObservaciones());
			programacionDto.setNoControl(acta.getProgramacionServicio().getNoControl());
			programacionDto.setFechaRegistro(acta.getProgramacionServicio().getFechaRegistro());
			programacionDto.setFechaModificacion(acta.getProgramacionServicio().getFechaModificacion());
			programacionDto.setFechaBaja(acta.getProgramacionServicio().getFechaBaja());
			programacionDto.setBaja(acta.getProgramacionServicio().getBaja());
			if(programacionDto.getEstatus() == EstatusServicio.INICIADO 
					||  programacionDto.getEstatus() == EstatusServicio.ENPROCESO 
					|| programacionDto.getEstatus() == EstatusServicio.CERRADO_NO_EXITOSO){
				programacionDto.setMostrarVistaActa(true);
				programacionDto.setMostrarVistaNotificacion(false);
			}else if(programacionDto.getEstatus() == EstatusServicio.ENPROGRAMACION){
				programacionDto.setMostrarVistaActa(false);
				programacionDto.setMostrarVistaNotificacion(true);
			}
			
			SolicitudServicioDto solicitudDto = new SolicitudServicioDto();
			if(acta.getProgramacionServicio().getSolicitudServicio() != null) {
				solicitudDto.setIdSolicitudServicio(acta.getProgramacionServicio().getSolicitudServicio().getIdSolicitudServicio());
				solicitudDto.setFechaInicio(acta.getProgramacionServicio().getSolicitudServicio().getFechaInicio());
				solicitudDto.setFechaFin(acta.getProgramacionServicio().getSolicitudServicio().getFechaFin());
				solicitudDto.setMotivoSolicitud(acta.getProgramacionServicio().getSolicitudServicio().getMotivoSolicitud());
				if(solicitudDto.getFechaFin().before(new Date())){
					solicitudDto.setColorSemaforo("rojo");
				}else{
					solicitudDto.setColorSemaforo("verde");
				}
				long diferenciaDias = Fecha.calcularDiferenciaDiasEntreFechas(solicitudDto.getFechaInicio(), new Date());
				ConfiguracionDto configuracionDto = configuracionService.getConfiguracion();
				if(diferenciaDias <= configuracionDto.getDiasCancelaCorrectivos()){
					programacionDto.setEsCancelable(true);
				}
				
				ContratoDetalleDto contratoDetalleDto = new ContratoDetalleDto();
				if(acta.getProgramacionServicio().getSolicitudServicio().getContratoDetalle() != null){
					ContratoDetalle cd = acta.getProgramacionServicio().getSolicitudServicio().getContratoDetalle();
					
					ContratoDto contratoDto = new ContratoDto();
					if(cd.getContrato() != null){
						contratoDto.setIdContrato(cd.getContrato().getIdContrato());
						contratoDto.setNumeroContrato(cd.getContrato().getNumeroContrato());
						contratoDto.setVigenciaInicioContrato(cd.getContrato().getVigenciaInicioContrato());
						contratoDto.setVigenciaFinContrato(cd.getContrato().getVigenciaFinContrato());
						ProveedorDto proveedorDto = new ProveedorDto();
						if(cd.getContrato().getProveedor() != null){
							proveedorDto.setIdProveedor(cd.getContrato().getProveedor().getIdProveedor());
							proveedorDto.setProveedor(cd.getContrato().getProveedor().getProveedor());
							proveedorDto.setEmail(cd.getContrato().getProveedor().getEmail());
							proveedorDto.setRfc(cd.getContrato().getProveedor().getRfc());
							proveedorDto.setDireccion(cd.getContrato().getProveedor().getDireccion());
							proveedorDto.setTelefono(cd.getContrato().getProveedor().getTelefono());
							proveedorDto.setRepresentanteLegal(cd.getContrato().getProveedor().getRepresentanteLegal());
							proveedorDto.setTelefonoRepresentanteLegal(cd.getContrato().getProveedor().getTelefonoRepresentanteLegal());
							proveedorDto.setNombreGerenteServicio(cd.getContrato().getProveedor().getNombreGerenteServicio());
							proveedorDto.setTelefonoGerenteServicio(cd.getContrato().getProveedor().getTelefonoGerenteServicio());
							proveedorDto.setEmailGerenteServicio(cd.getContrato().getProveedor().getEmailGerenteServicio());						
						}	
						contratoDto.setProveedor(proveedorDto);
					}
					
					UniversoDto universoDto = new UniversoDto();
					if(cd.getUniverso() !=null){
						universoDto.setIdUniverso(cd.getUniverso().getIdUniverso());
						universoDto.setMarca(cd.getUniverso().getMarca());
						universoDto.setModelo(cd.getUniverso().getModelo());
						universoDto.setSerie(cd.getUniverso().getSerie());
						universoDto.setInventario(cd.getUniverso().getInventario());
						universoDto.setObsubica(cd.getUniverso().getObsubica());
						CentroTrabajoDto centroTrabajoDto=new CentroTrabajoDto();
						if(cd.getUniverso().getCentroTrabajo()!=null){
							centroTrabajoDto.setIdCentroTrabajo(cd.getUniverso().getCentroTrabajo().getIdCentroTrabajo());
							centroTrabajoDto.setDescripcion(cd.getUniverso().getCentroTrabajo().getDescripcion());
							centroTrabajoDto.setDireccion(cd.getUniverso().getCentroTrabajo().getDireccion());
							centroTrabajoDto.setEstatus(cd.getUniverso().getCentroTrabajo().getEstatus());
							centroTrabajoDto.setResidenteCargo(cd.getUniverso().getCentroTrabajo().getResidenteCargo());
							centroTrabajoDto.setResidenteCargoJefe(cd.getUniverso().getCentroTrabajo().getResidenteCargoJefe());
							centroTrabajoDto.setResidenteCorreo(cd.getUniverso().getCentroTrabajo().getResidenteCorreo());
							centroTrabajoDto.setResidenteCorreoJefe(cd.getUniverso().getCentroTrabajo().getResidenteCorreoJefe());
							centroTrabajoDto.setResidenteJefe(cd.getUniverso().getCentroTrabajo().getResidenteJefe());
							centroTrabajoDto.setResidenteTelefonoJefe(cd.getUniverso().getCentroTrabajo().getResidenteTelefonoJefe());
							centroTrabajoDto.setResponsable(cd.getUniverso().getCentroTrabajo().getResponsable());
							centroTrabajoDto.setTelefono(cd.getUniverso().getCentroTrabajo().getTelefono());
							UnidadRegionalDto unidadRegionalDto=new UnidadRegionalDto();
							if(cd.getUniverso().getCentroTrabajo().getUnidadRegional()!=null){
								unidadRegionalDto.setIdUnidadRegional(cd.getUniverso().getCentroTrabajo().getUnidadRegional().getIdUnidadRegional());
								unidadRegionalDto.setUnidadRegional(cd.getUniverso().getCentroTrabajo().getUnidadRegional().getUnidadRegional());
								EntidadDto entidadDto=new EntidadDto();
								if(cd.getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad()!=null){
									entidadDto.setIdEntidad(cd.getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad().getIdEntidad());
									entidadDto.setEntidad(cd.getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
								}
								unidadRegionalDto.setEntidad(entidadDto);
							}
							centroTrabajoDto.setUnidadRegional(unidadRegionalDto);
						}
						universoDto.setCentroTrabajo(centroTrabajoDto);
						EquipoDto equipoDto=new EquipoDto();
						if(cd.getUniverso().getEquipo()!=null){
							equipoDto.setIdEquipo(cd.getUniverso().getEquipo().getIdEquipo());
							equipoDto.setEquipo(cd.getUniverso().getEquipo().getEquipo());
						}
						universoDto.setEquipo(equipoDto);
					}
					contratoDetalleDto.setIdContratoDetalle(cd.getIdContratoDetalle());
					contratoDetalleDto.setConsecutivoContrato(cd.getConsecutivoContrato());
					contratoDetalleDto.setContrato(contratoDto);
					contratoDetalleDto.setUniverso(universoDto);
					contratoDetalleDto.setInicioPeriodo(cd.getInicioPeriodo());
					contratoDetalleDto.setFinPeriodo(cd.getFinPeriodo());
					contratoDetalleDto.setInicioVigencia(cd.getInicioVigencia());
					contratoDetalleDto.setPeriodo(cd.getPeriodo());
				}
				solicitudDto.setContratoDetalle(contratoDetalleDto);
			}
			programacionDto.setSolicitudServicio(solicitudDto);
			actaEntregaDto.setProgramacionServicio(programacionDto);
		}
		return actaEntregaDto;
	}
	
	public List<ActaEntregaRecepcionDto> getActasEntregaRecepcionPorUrctYEstatus(String urct, EstatusServicio []estatus) {
		List<ActaEntregaRecepcionDto> actaEntregaRecepcionesDto = new ArrayList<ActaEntregaRecepcionDto>(); 
		for (ActaEntregaRecepcion actaEntregaRecepcion : actaEntregaRecepcionDao.getActasEntregaRecepcionPorUrctYEstatus(urct, estatus)) {
			ActaEntregaRecepcionDto actaEntregaRecepcionDto = this.manualModelMapper(actaEntregaRecepcion);
			actaEntregaRecepcionesDto.add(actaEntregaRecepcionDto);
		}
		return actaEntregaRecepcionesDto;
	}
	
	public List<ActaEntregaRecepcionDto> getActasEntregaRecepcionPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio){
		List<ActaEntregaRecepcionDto> actaEntregaRecepcionesDto = new ArrayList<ActaEntregaRecepcionDto>(); 
		for (ActaEntregaRecepcion actaEntregaRecepcion : actaEntregaRecepcionDao.getActasEntregaRecepcionPorParametros(idCentroTrabajo, tipoServicio, estatusServicio, fechaInicio, fechaFin, folio)) {
			ActaEntregaRecepcionDto actaEntregaRecepcionDto = this.manualModelMapper(actaEntregaRecepcion);
			actaEntregaRecepcionesDto.add(actaEntregaRecepcionDto);
		}
		return actaEntregaRecepcionesDto;
	}
}
