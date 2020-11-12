package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import mx.inntecsa.smem.dao.ProgramacionServicioDao;
import mx.inntecsa.smem.dao.SolicitudServicioDao;
import mx.inntecsa.smem.dao.UniversoDao;
import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.Funcionalidad;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.pojo.ProgramacionServicio;
import mx.inntecsa.smem.pojo.Universo;
import mx.inntecsa.smem.service.ConfiguracionService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("programacionServicioService")
public class ProgramacionServicioServiceImpl implements ProgramacionServicioService {
	
	private Logger log = Logger.getLogger(ProgramacionServicioServiceImpl.class);
	
	@Autowired
	private ProgramacionServicioDao programacionServicioDao;

	@Autowired
	private SolicitudServicioDao solicitudServicioDao;
	
	@Autowired
	private UniversoDao universoDao;
	
	@Autowired
	private ConfiguracionService configuracionService;
	
	private ConfiguracionDto configuracionDto;
	
	@Override
	public List<ProgramacionServicioDto> getProgramacionServiciosPorEstatus(String urct, EstatusServicio []estatus) {
		
		List<ProgramacionServicio> programaciones = programacionServicioDao.getProgramacionServiciosPorEstatus(urct, estatus);
		List<ProgramacionServicioDto> programacionesDto = new ArrayList<ProgramacionServicioDto>();
		
		configuracionDto = configuracionService.getConfiguracion();
		
		for(ProgramacionServicio programacion : programaciones){
			ProgramacionServicioDto programacionDto = manualModelMapper(programacion);
			programacionesDto.add(programacionDto);
		}
		
		return programacionesDto;
		
	}
	
	@Override
	public ProgramacionServicioDto getProgramacionServicioPorIdContratoDetalle(Integer idContratoDetalle) {
		
		ProgramacionServicio programacionServicio = programacionServicioDao.getProgramacionServicioPorIdContratoDetalle(idContratoDetalle);
		
		if(programacionServicio != null) {
			ProgramacionServicioDto programacionServicioDto = manualModelMapper(programacionServicio);
			return programacionServicioDto;
		}
		
		return null;
	}

	@Override
	public List<ProgramacionServicioDto> getProgramacionServiciosPorParametros(
			int idCentroTrabajo, TipoServicio tipoServicio,
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio) {
		
		List<ProgramacionServicio> programaciones = programacionServicioDao.getProgramacionServiciosPorParametros(idCentroTrabajo,
				tipoServicio, estatusServicio, fechaInicio, fechaFin, folio);
		List<ProgramacionServicioDto> programacionesDto = new ArrayList<ProgramacionServicioDto>();
		configuracionDto = configuracionService.getConfiguracion();
		
		for(ProgramacionServicio programacion : programaciones){
			ProgramacionServicioDto programacionDto = manualModelMapper(programacion);
			programacionesDto.add(programacionDto);
		}
		
		return programacionesDto;
	
	}

	@SuppressWarnings("unchecked")
	public ProgramacionServicioDto guardarProgramacionServicioPreventivo(ProgramacionServicioDto programacionServicioDto){
		
		ProgramacionServicio programacionServicio = new ModelMapper().map(programacionServicioDto, ProgramacionServicio.class);
		
		Integer idSolicitud = (Integer) solicitudServicioDao.save(programacionServicio.getSolicitudServicio());
		programacionServicio.getSolicitudServicio().setIdSolicitudServicio(idSolicitud);
		
		Integer idProgramacion = (Integer) programacionServicioDao.save(programacionServicio);
		programacionServicioDto.setIdProgramacionServicio(idProgramacion);
		
		return programacionServicioDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ProgramacionServicioDto guardarProgramacionServicioCorrectivo(ProgramacionServicioDto programacionServicioDto,
			UniversoDto universoDto) {
		
		ProgramacionServicio programacionServicio = new ModelMapper().map(programacionServicioDto, ProgramacionServicio.class);
		
		Integer idSolicitud = (Integer) solicitudServicioDao.save(programacionServicio.getSolicitudServicio());
		programacionServicio.getSolicitudServicio().setIdSolicitudServicio(idSolicitud);
		
		Integer idProgramacion = (Integer) programacionServicioDao.save(programacionServicio);
		
		programacionServicioDto.getSolicitudServicio().setIdSolicitudServicio(idSolicitud);
		programacionServicioDto.setIdProgramacionServicio(idProgramacion);
		
		//Actualiza la funcionalidad del universo a NO FUNCIONANDO
		universoDto.setFuncionalidad(Funcionalidad.NO_FUNCIONANDO);
		universoDto.setFechaModificacion(new Date());
		
		Universo universo = new ModelMapper().map(universoDto, Universo.class);
		if(universo.getGrupoClave()==null){
			universo.setIdGrupoClaveInt(null);
		}
		
		log.info("Actualizando funcionalidad de universo: " + universo.getIdUniverso() + " func: " + universo.getFuncionalidad().getDescripcion());
		universoDao.update(universo);
		
		log.info("Se guardo una programacion  con id: " + idProgramacion);
		
		return programacionServicioDto;
	}

	@Override
	public ProgramacionServicioDto getUltimaProgramacioPorUniverso(
			Long idUniverso) {
		
		ProgramacionServicio programacionServicio = programacionServicioDao.getUltimaProgramacioPorUniverso(idUniverso);
		configuracionDto = configuracionService.getConfiguracion();
		
		log.info("ultima programacion por universo: " + programacionServicio);
		
		if(programacionServicio != null){
			return manualModelMapper(programacionServicio);
		}
		
		return null;
	}

	@Override
	public boolean getExisteCorrectivoEnProcesoPorUniverso(Long idUniverso) {
		return programacionServicioDao.getExisteCorrectivoEnProcesoPorUniverso(idUniverso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarProgramacionServicio(
			ProgramacionServicioDto programacionServicioDto) {
		
		ProgramacionServicio programacionServicio = new ModelMapper().map(programacionServicioDto, ProgramacionServicio.class);
		log.info("Actualizando programacion: " + programacionServicio.getIdProgramacionServicio());
		
		try{
			programacionServicioDao.update(programacionServicio);
		}catch(Exception e){
			log.error("",e);
			return false;
		}
		
		return true;	
	}
	
	@Override
	public boolean getExistenServiciosPorIdCentroTrabajo(int idCentroTrabajo) {
		return programacionServicioDao.getExistenServiciosPorIdCentroTrabajo(idCentroTrabajo);
	}

	@Override
	public boolean getExistenProgramacionesAbiertasPorProveedor(int idProveedor) {
		
		return programacionServicioDao.getExistenProgramacionesAbiertasPorProveedor(idProveedor);
		
	}	

	private ProgramacionServicioDto manualModelMapper(ProgramacionServicio programacion){
		
		Date today = new Date();
							
		ProgramacionServicioDto programacionDto = new ProgramacionServicioDto();
		programacionDto.setIdProgramacionServicio(programacion.getIdProgramacionServicio());
		programacionDto.setFolio(programacion.getFolio());
		programacionDto.setEstatus(programacion.getEstatus());
		programacionDto.setTecnicoProv(programacion.getTecnicoProv());
		programacionDto.setTipoServicio(programacion.getTipoServicio());
		programacionDto.setFechaVisita(programacion.getFechaVisita());
		programacionDto.setTelTecnico(programacion.getTelTecnico());
		programacionDto.setNprogramacion(programacion.getNprogramacion());
		programacionDto.setObservaciones(programacion.getObservaciones());
		programacionDto.setNoControl(programacion.getNoControl());
		programacionDto.setFechaRegistro(programacion.getFechaRegistro());
		programacionDto.setFechaModificacion(programacion.getFechaModificacion());
		programacionDto.setFechaBaja(programacion.getFechaBaja());
		programacionDto.setBaja(programacion.getBaja());
		
		if(programacionDto.getEstatus() == EstatusServicio.INICIADO 
				||  programacionDto.getEstatus() == EstatusServicio.ENPROCESO 
				|| programacionDto.getEstatus() == EstatusServicio.CERRADO_NO_EXITOSO){
			
			programacionDto.setMostrarVistaActa(true);
			programacionDto.setMostrarVistaNotificacion(false);
		}
		else if(programacionDto.getEstatus() == EstatusServicio.ENPROGRAMACION){
			programacionDto.setMostrarVistaActa(false);
			programacionDto.setMostrarVistaNotificacion(true);
		}
		
		SolicitudServicioDto solicitudDto = new SolicitudServicioDto();
		
		if(programacion.getSolicitudServicio() != null) {
			solicitudDto.setIdSolicitudServicio(programacion.getSolicitudServicio().getIdSolicitudServicio());
			solicitudDto.setFechaInicio(programacion.getSolicitudServicio().getFechaInicio());
			solicitudDto.setFechaFin(programacion.getSolicitudServicio().getFechaFin());
			solicitudDto.setMotivoSolicitud(programacion.getSolicitudServicio().getMotivoSolicitud());
		
			if(solicitudDto.getFechaFin().before(today)){
				solicitudDto.setColorSemaforo("rojo");
			}else{
				solicitudDto.setColorSemaforo("verde");
			}

			long diferenciaDias = Fecha.calcularDiferenciaDiasEntreFechas(solicitudDto.getFechaInicio(), new Date());
			if(diferenciaDias <= configuracionDto.getDiasCancelaCorrectivos()){
				programacionDto.setEsCancelable(true);
			}
			
			ContratoDetalleDto contratoDetalleDto = new ContratoDetalleDto();
			if(programacion.getSolicitudServicio().getContratoDetalle() != null){
				ContratoDetalle cd = programacion.getSolicitudServicio().getContratoDetalle();
				ContratoDto contratoDto = new ContratoDto();
				UniversoDto universoDto = new UniversoDto();
				
				if(cd.getContrato() != null){
					
					contratoDto.setNumeroContrato(cd.getContrato().getNumeroContrato());
					
					ProveedorDto proveedorDto = new ProveedorDto();
					if(cd.getContrato().getProveedor() != null){
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
				
				if(cd.getUniverso() !=null){
					universoDto.setIdUniverso(cd.getUniverso().getIdUniverso());
					universoDto.setMarca(cd.getUniverso().getMarca());
					universoDto.setModelo(cd.getUniverso().getModelo());
				}
				
				contratoDetalleDto.setConsecutivoContrato(cd.getConsecutivoContrato());
				contratoDetalleDto.setContrato(contratoDto);
				contratoDetalleDto.setUniverso(universoDto);
			}
			
			solicitudDto.setContratoDetalle(contratoDetalleDto);
		}

		programacionDto.setSolicitudServicio(solicitudDto);
		
		return programacionDto;
	}

	@Override
	public boolean getServiciosEnProcesoPorUniverso(Long idUniverso) {
		return programacionServicioDao.getExisteCorrectivoEnProcesoPorUniverso(idUniverso);
	}
	
	public String getUltimoConsecutivoFolio(int anio){
		return programacionServicioDao.getUltimoConsecutivoFolio(anio);
	}

}
