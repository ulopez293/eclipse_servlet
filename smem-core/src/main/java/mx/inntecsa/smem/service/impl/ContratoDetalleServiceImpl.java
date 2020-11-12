package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ContratoDetalleDao;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SupervisorDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.service.ContratoDetalleService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("contratoDetalleService")
@SuppressWarnings("unchecked")
public class ContratoDetalleServiceImpl implements ContratoDetalleService {
	
	@Autowired
	private ContratoDetalleDao contratoDetalleDao;
	
	private Logger log = Logger.getLogger(ContratoDetalleServiceImpl.class);

	@Override
	public List<ContratoDetalleDto> getContratosDetalles() {
		List<ContratoDetalleDto> contratoDetalleesDto = new ArrayList<ContratoDetalleDto>(); //Lista de ContratosDetalles(DTO)

		for (ContratoDetalle contratoDetalle : contratoDetalleDao.getContratosDetalles()) {
			ContratoDetalleDto contratoDetalleDto = this.manualModelMapper(contratoDetalle);
			contratoDetalleesDto.add(contratoDetalleDto);
		}
		
		return contratoDetalleesDto;
	}

	@Override
	public List<ContratoDetalleDto> getContratosDetallesPorEstatus(Estatus estatus) {
		List<ContratoDetalleDto> contratoDetalleesDto = new ArrayList<ContratoDetalleDto>(); //Lista de ContratosDetalles(DTO)

		for (ContratoDetalle contratoDetalle : contratoDetalleDao.getContratosDetallesPorEstatus(estatus)) {
			ContratoDetalleDto contratoDetalleDto = this.manualModelMapper(contratoDetalle);
			contratoDetalleesDto.add(contratoDetalleDto);
		}
		
		return contratoDetalleesDto;
	}

	@Override
	public List<ContratoDetalleDto> getContratosDetallesPorFechaInicio(Date fechaInicio) {
		List<ContratoDetalleDto> contratoDetallesDto = new ArrayList<ContratoDetalleDto>(); //Lista de ContratosDetalles(DTO)
		List<ContratoDetalle> contratosDetalle = contratoDetalleDao.getContratosDetallesPorFechaInicio(fechaInicio);
		
		for (ContratoDetalle contratoDetalle : contratosDetalle) {
			ContratoDetalleDto contratoDetalleDto = this.manualModelMapper(contratoDetalle);			
			contratoDetallesDto.add(contratoDetalleDto);
		}
		
		return contratoDetallesDto;
	}

	@Override
	public List<ContratoDetalleDto> getContratosDetallesPorIdContrato(Integer idContrato) {
		List<ContratoDetalleDto> contratosDestallesDto = new ArrayList<ContratoDetalleDto>();
		
		for(ContratoDetalle contratoDetalle : contratoDetalleDao.getContratosDetallesPorIdContrato(idContrato)) {
			ContratoDetalleDto contratoDetalleDto = this.manualModelMapper(contratoDetalle);
			contratosDestallesDto.add(contratoDetalleDto);
		}
		
		return contratosDestallesDto;
	}
	
	@Override
	public Integer getMaxPeriodoPorIdUniverso(Long idUniverso) {
		return contratoDetalleDao.getMaxPeriodoPorIdUniverso(idUniverso);
	}

	@Override
	public Boolean guardarContratosDetalles(List<ContratoDetalleDto> contratosDetallesDto) {
		List<ContratoDetalle> contratosDetalles = new ArrayList<ContratoDetalle>();
		
		for(ContratoDetalleDto contratoDetalleDto: contratosDetallesDto) {
			ContratoDetalle contratoDetalle = new ModelMapper().map(contratoDetalleDto, ContratoDetalle.class);
			contratoDetalle.setFechaRegistro(contratoDetalleDto.getFechaRegistro());
			contratoDetalle.setFechaModificacion(contratoDetalleDto.getFechaModificacion());
			contratoDetalle.setFechaBaja(contratoDetalleDto.getFechaBaja());
			contratosDetalles.add(contratoDetalle);
		}	
		
		try {
			contratoDetalleDao.saveAll(contratosDetalles);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
		
	}

	@Override
	public ContratoDetalleDto getContratoDetallePorIdUniverso(Long idUniverso) {
		
		ContratoDetalle contratoDetalle = contratoDetalleDao.getContratoDetallePorIdUniverso(idUniverso);
		ContratoDetalleDto contratoDetalleDto = null;
		
		if(contratoDetalle != null){
			contratoDetalleDto = this.manualModelMapper(contratoDetalle);
		}
		
		return contratoDetalleDto;
	}
	
	@Override
	public Boolean eliminarContratoDetalle(ContratoDetalleDto contratoDetalleDto) {
		ContratoDetalle contratoDetalle = new ModelMapper().map(contratoDetalleDto, ContratoDetalle.class);
		contratoDetalle.setFechaRegistro(contratoDetalleDto.getFechaRegistro());
		contratoDetalle.setFechaModificacion(contratoDetalleDto.getFechaModificacion());
		contratoDetalle.setFechaBaja(contratoDetalleDto.getFechaBaja());
		
		try{
			contratoDetalleDao.update(contratoDetalle);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}
	
	@Override
	public Boolean guardarContratoDetalle(ContratoDetalleDto contratoDetalleDto) {
		ContratoDetalle contratoDetalle = new ModelMapper().map(contratoDetalleDto, ContratoDetalle.class);
		contratoDetalle.setFechaRegistro(contratoDetalleDto.getFechaRegistro());
		contratoDetalle.setFechaModificacion(contratoDetalleDto.getFechaModificacion());
		contratoDetalle.setFechaBaja(contratoDetalleDto.getFechaBaja());
		
		try{
			contratoDetalleDao.save(contratoDetalle);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}

	@Override
	public ContratoDetalleDto getContratoDetallePorParametros(Integer idContrato, Integer periodo, Long idUniverso) {
		ContratoDetalle contratoDetalle = contratoDetalleDao.getContratoDetallePorParametros(idContrato, periodo, idUniverso);
		
		if(contratoDetalle != null) {
			ContratoDetalleDto contratoDetalleDto = this.manualModelMapper(contratoDetalle);
			return contratoDetalleDto;
		}
		return null;
	}
	
	@Override
	public Integer getConsecutivoVigentePorIdUniverso(Long idUniverso) {		
		return contratoDetalleDao.getConsecutivoVigentePorIdUniverso(idUniverso);
	}

	@Override
	public Boolean actualizarContratoDetalle(ContratoDetalleDto contratoDetalleDto) {
		ContratoDetalle contratoDetalle = new ModelMapper().map(contratoDetalleDto, ContratoDetalle.class);
		contratoDetalle.setFechaRegistro(contratoDetalleDto.getFechaRegistro());
		contratoDetalle.setFechaModificacion(contratoDetalleDto.getFechaModificacion());
		contratoDetalle.setFechaBaja(contratoDetalleDto.getFechaBaja());
		
		try{
			contratoDetalleDao.update(contratoDetalle);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}

	private ContratoDetalleDto manualModelMapper(ContratoDetalle contratoDetalle) {
		
		ContratoDetalleDto contratoDetalleDto = new ContratoDetalleDto();
		contratoDetalleDto.setIdContratoDetalle(contratoDetalle.getIdContratoDetalle());
		contratoDetalleDto.setPeriodo(contratoDetalle.getPeriodo());
		contratoDetalleDto.setConsecutivoContrato(contratoDetalle.getConsecutivoContrato());
		contratoDetalleDto.setInicioPeriodo(contratoDetalle.getInicioPeriodo());
		contratoDetalleDto.setFinPeriodo(contratoDetalle.getFinPeriodo());
		contratoDetalleDto.setEnero(contratoDetalle.getEnero());
		contratoDetalleDto.setFebrero(contratoDetalle.getFebrero());
		contratoDetalleDto.setMarzo(contratoDetalle.getMarzo());
		contratoDetalleDto.setAbril(contratoDetalle.getAbril());
		contratoDetalleDto.setMayo(contratoDetalle.getMayo());
		contratoDetalleDto.setJunio(contratoDetalle.getJunio());
		contratoDetalleDto.setJulio(contratoDetalle.getJulio());
		contratoDetalleDto.setAgosto(contratoDetalle.getAgosto());
		contratoDetalleDto.setSeptiembre(contratoDetalle.getSeptiembre());
		contratoDetalleDto.setOctubre(contratoDetalle.getOctubre());
		contratoDetalleDto.setNoviembre(contratoDetalle.getNoviembre());
		contratoDetalleDto.setDiciembre(contratoDetalle.getDiciembre());
		contratoDetalleDto.setInicioVigencia(contratoDetalle.getInicioVigencia());
		contratoDetalleDto.setFechaRegistro(contratoDetalle.getFechaRegistro());
		contratoDetalleDto.setFechaModificacion(contratoDetalle.getFechaModificacion());
		contratoDetalleDto.setFechaBaja(contratoDetalle.getFechaBaja());
		contratoDetalleDto.setBaja(contratoDetalle.getBaja());			
		
		ContratoDto contratoDto = new ContratoDto();		
		
		if(contratoDetalle.getContrato() != null) {
			contratoDto.setIdContrato(contratoDetalle.getContrato().getIdContrato());
			contratoDto.setNumeroContrato(contratoDetalle.getContrato().getNumeroContrato());
			contratoDto.setEjercicio(contratoDetalle.getContrato().getEjercicio());
			
			ProveedorDto proveedorDto = new ProveedorDto();		
			
			if(contratoDetalle.getContrato().getProveedor() != null) {
				proveedorDto.setProveedor(contratoDetalle.getContrato().getProveedor().getProveedor());
				proveedorDto.setRfc(contratoDetalle.getContrato().getProveedor().getRfc());
				proveedorDto.setDireccion(contratoDetalle.getContrato().getProveedor().getDireccion());
				proveedorDto.setTelefono(contratoDetalle.getContrato().getProveedor().getTelefono());
				proveedorDto.setEmail(contratoDetalle.getContrato().getProveedor().getEmail());
				proveedorDto.setRepresentanteLegal(contratoDetalle.getContrato().getProveedor().getRepresentanteLegal());
				proveedorDto.setTelefonoRepresentanteLegal(contratoDetalle.getContrato().getProveedor().getTelefonoRepresentanteLegal());
				proveedorDto.setNombreGerenteServicio(contratoDetalle.getContrato().getProveedor().getNombreGerenteServicio());
				proveedorDto.setTelefonoGerenteServicio(contratoDetalle.getContrato().getProveedor().getTelefonoGerenteServicio());
				proveedorDto.setEmailGerenteServicio(contratoDetalle.getContrato().getProveedor().getEmailGerenteServicio());
			}		
			contratoDto.setProveedor(proveedorDto);
		}		
		
		UniversoDto universoDto = new UniversoDto();
		
		if(contratoDetalle.getUniverso() != null) {				
			universoDto.setIdUniverso(contratoDetalle.getUniverso().getIdUniverso());
			universoDto.setInventario(contratoDetalle.getUniverso().getInventario());
			universoDto.setMarca(contratoDetalle.getUniverso().getMarca());
			universoDto.setModelo(contratoDetalle.getUniverso().getModelo());
			universoDto.setSerie(contratoDetalle.getUniverso().getSerie());
			universoDto.setObsubica(contratoDetalle.getUniverso().getObsubica());
			
			EquipoDto equipoDto=new EquipoDto();
			if(contratoDetalle.getUniverso().getEquipo() != null) {
				equipoDto.setEquipo(contratoDetalle.getUniverso().getEquipo().getEquipo());
			}
			universoDto.setEquipo(equipoDto);
			
			CentroTrabajoDto centroTrabajoDto = new CentroTrabajoDto();			
			if(contratoDetalle.getUniverso().getCentroTrabajo() != null) {
				centroTrabajoDto.setIdCentroTrabajo(contratoDetalle.getUniverso().getCentroTrabajo().getIdCentroTrabajo());
				centroTrabajoDto.setUrct(contratoDetalle.getUniverso().getCentroTrabajo().getUrct());
				centroTrabajoDto.setDescripcion(contratoDetalle.getUniverso().getCentroTrabajo().getDescripcion());
				centroTrabajoDto.setDireccion(contratoDetalle.getUniverso().getCentroTrabajo().getDireccion());
				centroTrabajoDto.setResponsable(contratoDetalle.getUniverso().getCentroTrabajo().getResponsable());
				centroTrabajoDto.setResidenteCargo(contratoDetalle.getUniverso().getCentroTrabajo().getResidenteCargo());
				centroTrabajoDto.setTelefono(contratoDetalle.getUniverso().getCentroTrabajo().getTelefono());
				
				UnidadRegionalDto unidadRegionalDto=new UnidadRegionalDto();
				if(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional() != null) {
					
					EntidadDto entidadDto=new EntidadDto();
					if(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad() != null) {
						entidadDto.setEntidad(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
					}
					unidadRegionalDto.setEntidad(entidadDto);
					
					SupervisorDto supervisorDto=new SupervisorDto();
					if(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor() != null) {
						supervisorDto.setNombre(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getNombre());
						supervisorDto.setTelefono(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getTelefono());
						supervisorDto.setCorreo(contratoDetalle.getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getCorreo());
					}
					unidadRegionalDto.setSupervisor(supervisorDto);
					
				}
				centroTrabajoDto.setUnidadRegional(unidadRegionalDto);
			}
			universoDto.setCentroTrabajo(centroTrabajoDto);
		}
		
		contratoDetalleDto.setContrato(contratoDto);
		contratoDetalleDto.setUniverso(universoDto);
		return contratoDetalleDto;
	}	

}
