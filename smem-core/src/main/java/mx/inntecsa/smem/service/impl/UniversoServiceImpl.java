
package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.UniversoDao;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.EspecialidadDto;
import mx.inntecsa.smem.dto.GrupoClaveDto;
import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.dto.NivelAtencionDto;
import mx.inntecsa.smem.dto.SectorAdqDto;
import mx.inntecsa.smem.dto.SupervisorDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UniversoContratoDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Universo;
import mx.inntecsa.smem.service.UniversoService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings("unchecked")
@Service("universoService")
public class UniversoServiceImpl implements UniversoService {
	
	private Logger log = Logger.getLogger(UniversoServiceImpl.class);
	
	@Autowired
	private UniversoDao universoDao;
	
	@Override
	public List<UniversoDto> getUniversos() {
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		
		for(Universo universo : universoDao.getUniversos()) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}

	@Override
	public List<UniversoDto> getUniversosPorEstatus(Estatus estatus) {
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		
		for(Universo universo : universoDao.getUniversosPorEstatus(estatus)) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}

	@Override
	public List<UniversoDto> getUniversosEnContratoPorParametros(String urct,Integer idEquipo, 
			Long identificador, String serie,String inventario) {
		
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		List<Universo> universos = universoDao.getUniversosEnContratoPorParametros(urct, idEquipo, identificador, serie, inventario);
		
		for(Universo universo : universos) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}
	
	@Override
	public List<UniversoDto> getUniversosEnContratoSinServicios(String urct,Integer idEquipo, Long identificador, String serie,String inventario) {
		
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		List<Universo> universos = universoDao.getUniversosEnContratoSinServicios(urct, idEquipo, identificador, serie, inventario);
		
		for(Universo universo : universos) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}
	
	@Override
	public List<UniversoDto> getUniversosPorParametros(Integer idUnidadRegional, Integer idCentroTrabajo,
		Integer inventario, String serie, Long idUniverso) {
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		
		for(Universo universo : universoDao.getUniversosPorParametros(idUnidadRegional, idCentroTrabajo, inventario, serie, idUniverso)) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}

	@Override
	public UniversoDto getUniversoPorIdUniverso(Long idUniverso) {
		log.info(">>>Obteniendo Universo " + idUniverso);
		Universo universo = universoDao.getUniversoPorIdUniverso(idUniverso);
		
		if(universo != null) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			return universoDto;
		}		
		
		return null;
	}
	
	@Override
	public UniversoDto getUniversoPorInventario(Integer inventario) {
		log.info(">>>Obteniendo Universo por inventario" + inventario);
		Universo universo = universoDao.getUniversoPorInventario(inventario);
		
		if(universo != null) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			return universoDto;
		}		
		
		return null;
	}	
	
	@Override
	public UniversoDto getUniversoSICOBIMPorInventario(Integer inventario) {
		log.info(">>>Obteniendo Universo SICOBIM por inventario" + inventario);
//		UniversoSICOBIMDto universoSICOBIM = universoSICOBIMDao.getUniversosSICOBIMPorInventario(inventario);
//		
//		if(universoSICOBIM != null) {
//			UniversoDto universoDto = new UniversoDto();
//			universoDto.setInventario(String.valueOf(universoSICOBIM.getInventario()));
//			universoDto.setClaveUr(universoSICOBIM.getClaveur());
//			universoDto.setClaveCt(universoSICOBIM.getClavect());
//			universoDto.setClavePf(universoSICOBIM.getClavepf());			
//			universoDto.setCabm(universoSICOBIM.getCabm() != null ? universoSICOBIM.getCabm() : "");
//			universoDto.setPartida(universoSICOBIM.getPartida().toString());
//			universoDto.setMarca(universoSICOBIM.getMarca());
//			universoDto.setModelo(universoSICOBIM.getModelo());
//			universoDto.setSerie(universoSICOBIM.getSerie());
//			universoDto.setObsubica(universoSICOBIM.getObsubica());
//			universoDto.setProveedorVentaEquipo(universoSICOBIM.getProveedorventaequipo());
//			universoDto.setPrecioEquipoSinIva(universoSICOBIM.getPrecioequiposiniva().toString());
//			//obteniendo el anio
//			Calendar calendario = Calendar.getInstance();
//			calendario.setTime(universoSICOBIM.getFechaadquisicion());
//			universoDto.setAnioAdq(String.valueOf(calendario.get(Calendar.YEAR)));
//			//Viene del SICOBIM
//			universoDto.setEsSibobim(true);
//			
//			return universoDto;
//		}		
		
		return null;
	}
	
	@Override
	public List<UniversoDto> getUniversoPorIdCentroTrabajo(Integer idCentroTrabajo) {
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		
		for(Universo universo : universoDao.getUniversosPorIdCentroTrabajo(idCentroTrabajo)) {
			UniversoDto universoDto = this.manualModelMapper(universo);
			universosDto.add(universoDto);
		}
		
		return universosDto;
	}


	@Override
	public boolean actualizarUniverso(UniversoDto universoDto) {		
		Universo universo = new ModelMapper().map(universoDto, Universo.class);
		
		try{
			universoDao.update(universo);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}		
	}
	
	
	@Override
	public UniversoDto guardarUniverso(UniversoDto universoDto) {		
		Universo universo = new ModelMapper().map(universoDto, Universo.class);		
		
		try {
            Long idUniverso = (Long) universoDao.save(universo);
            universoDto.setIdUniverso(idUniverso);
			return universoDto;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return null;
		}
		
	}

	@Override
	public boolean eliminarUniverso(UniversoDto universoDto) {
		Universo universo = new ModelMapper().map(universoDto, Universo.class);	
		universo.setFechaBaja(universo.getFechaBaja());
		
		try{
			universoDao.update(universo);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}	

	private UniversoDto manualModelMapper(Universo universo) {
		UniversoDto universoDto = new UniversoDto();
		universoDto.setIdUniverso(universo.getIdUniverso());
		universoDto.setFuncionalidad(universo.getFuncionalidad());
		universoDto.setInventario(universo.getInventario());
		universoDto.setMarca(universo.getMarca());
		universoDto.setModelo(universo.getModelo());
		universoDto.setSerie(universo.getSerie());
		universoDto.setCabm(universo.getCabm() != null ? universo.getCabm() : "");
		universoDto.setObsubica(universo.getObsubica());
		universoDto.setFechaInstalacion(universo.getFechaInstalacion());
		universoDto.setFechaApertura(universo.getFechaApertura());
		universoDto.setFechaGarantia(universo.getFechaGarantia());
		universoDto.setRequiereServicio(universo.getRequiereServicio());
		universoDto.setObservaciones(universo.getObservaciones());
		universoDto.setNumeroLicitacion(universo.getNumeroLicitacion());
		universoDto.setPartida(universo.getPartida());
		universoDto.setNoContratoAdq(universo.getNoContratoAdq());
		universoDto.setActualizacionTecnologica(universo.getActualizacionTecnologica());
		universoDto.setProveedorVentaEquipo(universo.getProveedorVentaEquipo());
		universoDto.setAnioAdq(universo.getAnioAdq());
		universoDto.setPrecioEquipoSinIva(universo.getPrecioEquipoSinIva());
		universoDto.setEsSibobim(universo.getEsSibobim());
		universoDto.setEstaActualizado(universo.getEstaActualizado());
		universoDto.setFechaRegistro(universo.getFechaRegistro());
		universoDto.setFechaModificacion(universo.getFechaModificacion());
		universoDto.setFechaBaja(universo.getFechaBaja());
		universoDto.setBaja(universo.getBaja());
		universoDto.setNumeroBitacora(universo.getNumeroBitacora());
		
		CentroTrabajoDto centroTrabajoDto = new CentroTrabajoDto();
		
		if(universo.getCentroTrabajo() != null) {
			
			centroTrabajoDto.setIdCentroTrabajo(universo.getCentroTrabajo().getIdCentroTrabajo());
			centroTrabajoDto.setDescripcion(universo.getCentroTrabajo().getDescripcion());
			centroTrabajoDto.setUrct(universo.getCentroTrabajo().getUrct());
			centroTrabajoDto.setDireccion(universo.getCentroTrabajo().getDireccion());
			centroTrabajoDto.setResponsable(universo.getCentroTrabajo().getResponsable());
			centroTrabajoDto.setResidenteCargo(universo.getCentroTrabajo().getResidenteCargo());
			centroTrabajoDto.setTelefono(universo.getCentroTrabajo().getTelefono());
			centroTrabajoDto.setResidenteCorreo(universo.getCentroTrabajo().getResidenteCorreo());
			centroTrabajoDto.setResidenteJefe(universo.getCentroTrabajo().getResidenteJefe());
			centroTrabajoDto.setResidenteTelefonoJefe(universo.getCentroTrabajo().getResidenteTelefonoJefe());
			centroTrabajoDto.setResidenteCargoJefe(universo.getCentroTrabajo().getResidenteCargoJefe());
			centroTrabajoDto.setResidenteCorreoJefe(universo.getCentroTrabajo().getResidenteCorreoJefe());
			
			UnidadRegionalDto unidadRegionalDto = new UnidadRegionalDto();
			
			if(universo.getCentroTrabajo().getUnidadRegional() != null) {
				unidadRegionalDto.setIdUnidadRegional(universo.getCentroTrabajo().getUnidadRegional().getIdUnidadRegional());
				unidadRegionalDto.setUnidadRegional(universo.getCentroTrabajo().getUnidadRegional().getUnidadRegional());
				
				EntidadDto entidadDto = new EntidadDto();
				
				if(universo.getCentroTrabajo().getUnidadRegional().getEntidad() != null) {
					entidadDto.setIdEntidad(universo.getCentroTrabajo().getUnidadRegional().getEntidad().getIdEntidad());
					entidadDto.setEntidad(universo.getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
				}

				SupervisorDto supervisorDto = new SupervisorDto();
				
				if(universo.getCentroTrabajo().getUnidadRegional().getSupervisor() != null) {
					supervisorDto.setNombre(universo.getCentroTrabajo().getUnidadRegional().getSupervisor().getNombre());
					supervisorDto.setTelefono(universo.getCentroTrabajo().getUnidadRegional().getSupervisor().getTelefono());
					supervisorDto.setCorreo(universo.getCentroTrabajo().getUnidadRegional().getSupervisor().getCorreo());
				}
				
				unidadRegionalDto.setEntidad(entidadDto);
				unidadRegionalDto.setSupervisor(supervisorDto);
			}
			
			centroTrabajoDto.setUnidadRegional(unidadRegionalDto);
		}
		
		SectorAdqDto sectorAdqDto = new SectorAdqDto();
		
		if(universo.getSectorAdq() != null) {
			sectorAdqDto.setIdSectorAdq(universo.getSectorAdq().getIdSectorAdq());
			sectorAdqDto.setSectorAdq(universo.getSectorAdq().getSectorAdq());
		}
		
		EspecialidadDto especialidadDto = new EspecialidadDto();
		
		if(universo.getEspecialidad() != null) {
			especialidadDto.setIdEspecialidad(universo.getEspecialidad().getIdEspecialidad());
			especialidadDto.setEspecialidad(universo.getEspecialidad().getEspecialidad());
		}
		
		EquipoDto equipoDto = new EquipoDto();
		if(universo.getEquipo() != null) {
			equipoDto.setIdEquipo(universo.getEquipo().getIdEquipo());
			equipoDto.setEquipo(universo.getEquipo().getEquipo());
		}

		GrupoClaveDto grupoClaveDto = new GrupoClaveDto();
		if(universo.getGrupoClave() != null) {
			grupoClaveDto.setIdGrupoClave(universo.getGrupoClave().getIdGrupoClave());			
			GrupoDto grupoDto = new GrupoDto();
			
			if(universo.getGrupoClave().getGrupo() != null) {
				grupoDto.setIdGrupo(universo.getGrupoClave().getGrupo().getIdGrupo());
				grupoDto.setGrupo(universo.getGrupoClave().getGrupo().getGrupo());
			}
			
			ClaveDto claveDto = new ClaveDto();
			
			if(universo.getGrupoClave().getClave() != null) {
				claveDto.setIdClave(universo.getGrupoClave().getClave().getIdClave());
				claveDto.setClave(universo.getGrupoClave().getClave().getClave());
			}
			
			equipoDto = new EquipoDto();
			
			if(universo.getGrupoClave().getEquipo() != null) {
				equipoDto.setIdEquipo(universo.getGrupoClave().getEquipo().getIdEquipo());
				equipoDto.setEquipo(universo.getGrupoClave().getEquipo().getEquipo());
			}
			
			grupoClaveDto.setGrupo(grupoDto);
			grupoClaveDto.setClave(claveDto);
			grupoClaveDto.setEquipo(equipoDto);
			
		} else {
			grupoClaveDto = null;
		}
		
		NivelAtencionDto nivelAtencionDto = new NivelAtencionDto();
		
		if(universo.getNivelAtencion() != null) {
			nivelAtencionDto.setIdNivelAtencion(universo.getNivelAtencion().getIdNivelAtencion());
			nivelAtencionDto.setDescripcion(universo.getNivelAtencion().getDescripcion());
		}
		
		universoDto.setCentroTrabajo(centroTrabajoDto);
		universoDto.setSectorAdq(sectorAdqDto);
		universoDto.setEspecialidad(especialidadDto);
		universoDto.setEquipo(equipoDto);
		universoDto.setNivelAtencion(nivelAtencionDto);
		universoDto.setGrupoClave(grupoClaveDto);
		return universoDto;
	}
	
	@Override
	public List<UniversoDto> getUniversosCompletosPorIdCentroTrabajo(Integer idCentroTrabajo) {
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		
		for(UniversoContratoDto universo: universoDao.getUniversosCompletosPorIdCentroTrabajo(idCentroTrabajo)){
			UniversoDto universoDto = this.getUniversoPorIdUniverso(universo.getIduniverso().longValue());
			universoDto.setNumeroContrato(universo.getNumerocontrato());
			universoDto.setConsecutivoContrato(universo.getConsecutivocontrato());
			universoDto.setInicioPeriodo1(universo.getInicioperiodo1());
			universoDto.setFinPeriodo1(universo.getFinperiodo1());
			universoDto.setInicioPeriodo2(universo.getInicioperiodo2());
			universoDto.setFinPeriodo2(universo.getFinperiodo2());
			universoDto.setInicioPeriodo3(universo.getInicioperiodo3());
			universoDto.setFinPeriodo3(universo.getFinperiodo3());
			universoDto.setInicioPeriodo4(universo.getInicioperiodo4());
			universoDto.setFinPeriodo4(universo.getFinperiodo4());
			universosDto.add(universoDto);
		}
		return universosDto;
	}
	
	@Override
	public List<UniversoDto> getUniversosCompletosPorParametros(Integer idUnidadRegional,
			Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso){
		List<UniversoDto> universosDto = new ArrayList<UniversoDto>();
		for(UniversoContratoDto universo: universoDao.getUniversosCompletosPorParametros(idUnidadRegional, idCentroTrabajo, inventario, serie, idUniverso)){
			UniversoDto universoDto = this.getUniversoPorIdUniverso(universo.getIduniverso().longValue());
			universoDto.setNumeroContrato(universo.getNumerocontrato());
			universoDto.setConsecutivoContrato(universo.getConsecutivocontrato());
			universoDto.setInicioPeriodo1(universo.getInicioperiodo1());
			universoDto.setFinPeriodo1(universo.getFinperiodo1());
			universoDto.setInicioPeriodo2(universo.getInicioperiodo2());
			universoDto.setFinPeriodo2(universo.getFinperiodo2());
			universoDto.setInicioPeriodo3(universo.getInicioperiodo3());
			universoDto.setFinPeriodo3(universo.getFinperiodo3());
			universoDto.setInicioPeriodo4(universo.getInicioperiodo4());
			universoDto.setFinPeriodo4(universo.getFinperiodo4());
			universosDto.add(universoDto);
		}
		return universosDto;
	}
}
