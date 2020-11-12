package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ContratoDao;
import mx.inntecsa.smem.dao.ContratoDetalleDao;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SubtipoContratoDto;
import mx.inntecsa.smem.dto.TipoContratacionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.pojo.Contrato;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.service.ContratoService;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("contratoService")
@SuppressWarnings("unchecked")
public class ContratoServiceImpl implements ContratoService {
	
	@Autowired
	private ContratoDao contratoDao;
	
	@Autowired
	private ContratoDetalleDao contratoDetalleDao;
	
	private Logger log = Logger.getLogger(ContratoServiceImpl.class);

	@Override
	public List<ContratoDto> getContratos() {
		List<ContratoDto> contratoesDto = new ArrayList<ContratoDto>(); //Lista de Contratos(DTO)

		for (Contrato contrato : contratoDao.getContratos()) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);		
			contratoesDto.add(contratoDto);
		}
		
		return contratoesDto;
	}

	@Override
	public List<ContratoDto> getContratosPorEstatus(Estatus estatus) {
		List<ContratoDto> contratoesDto = new ArrayList<ContratoDto>(); //Lista de Contratos(DTO)

		for (Contrato contrato : contratoDao.getContratosPorEstatus(estatus)) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);		
			contratoesDto.add(contratoDto);
		}
		
		return contratoesDto;
	}
	
	@Override
	public List<ContratoDto> getContratoPorEstatusContrato(EstatusContrato estatusContrato) {
		List<ContratoDto> contratosDto = new ArrayList<ContratoDto>(); //Lista de Contratos(DTO)

		for (Contrato contrato : contratoDao.getContratoPorEstatusContrato(estatusContrato)) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);		
			contratosDto.add(contratoDto);
		}
		
		return contratosDto;
	}

	@Override
	public List<ContratoDto> getContratosPorParametros(EstatusContrato estatusContrato,
		Integer idProveedor, Date fechaInicio, Date fechaFin) {
		List<ContratoDto> contratoesDto = new ArrayList<ContratoDto>(); //Lista de Contratos(DTO)

		for (Contrato contrato : contratoDao.getContratosPorParametros(estatusContrato, idProveedor, fechaInicio, fechaFin)) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);			
			contratoesDto.add(contratoDto);
		}
		
		return contratoesDto;
	}
	
	@Override
	public ContratoDto getContratoPorNumeroContrato(String numeroContrato) {
		Contrato contrato = contratoDao.getContratoPorNumeroContrato(numeroContrato);
		
		if(contrato != null) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);
			return contratoDto;
		}
		
		return null;
	}
	
	@Override
	public ContratoDto getContratoPorIdContrato(Integer idContrato) {		
		Contrato contrato = contratoDao.getContratoPorIdContrato(idContrato);
		
		if(contrato != null) {
			ContratoDto contratoDto = this.manualModelMapper(contrato);
			return contratoDto;
		}
		
		return null;
	}
	
	@Override
	public ContratoDto guardarContrato(ContratoDto contratoDto) {
		Contrato contrato = new ModelMapper().map(contratoDto, Contrato.class);		
		String anios = Fecha.calculaAniosFechas(contrato.getVigenciaInicioContrato(), 
			contrato.getVigenciaFinContrato());
		contrato.setEjercicio(anios);
		contrato.setBaja(contratoDto.getBaja());
			
		try {
			Integer idContrato = (Integer) contratoDao.save(contrato);
			log.info(">>>ID del contrato  " + idContrato);
			contratoDto.setIdContrato(idContrato);
			return contratoDto;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return null;
		}
	}

	@Override
	public boolean actualizarContrato(ContratoDto contratoDto) {
		Contrato contrato = new ModelMapper().map(contratoDto, Contrato.class);		
		String anios = Fecha.calculaAniosFechas(contrato.getVigenciaInicioContrato(), 
			contrato.getVigenciaFinContrato());
		contrato.setEjercicio(anios);
		contrato.setBaja(contratoDto.getBaja());
		
		try {
			contratoDao.update(contrato);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}

	@Override
	public boolean eliminarContrato(ContratoDto contratoDto) {
		Contrato contrato = new ModelMapper().map(contratoDto, Contrato.class);		
		
		try {
			contratoDao.update(contrato);
			List<ContratoDetalle> contratosDetalle = contratoDetalleDao
				.getContratosDetallesPorIdContrato(contrato.getIdContrato());
			
			for(ContratoDetalle contratoDetalle : contratosDetalle) {
				contratoDetalle.setBaja(Estatus.INACTIVO);
				contratoDetalle.setFechaBaja(new Date());
			}
			
			contratoDetalleDao.updateAll(contratosDetalle);			
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}
	
	private ContratoDto manualModelMapper(Contrato contrato) {
		ContratoDto contratoDto = new ContratoDto();
		contratoDto.setIdContrato(contrato.getIdContrato());
		contratoDto.setTipoServicio(contrato.getTipoServicio());
		contratoDto.setNumeroContrato(contrato.getNumeroContrato());
		contratoDto.setEjercicio(contrato.getEjercicio());
		contratoDto.setFechaSuscripcion(contrato.getFechaSuscripcion());
		contratoDto.setOficioSufPresupuestal(contrato.getOficioSufPresupuestal());
		contratoDto.setPartida(contrato.getPartida());
		contratoDto.setMontoMinimo(contrato.getMontoMinimo());
		contratoDto.setMontoMaximo(contrato.getMontoMaximo());
		contratoDto.setVigenciaInicioContrato(contrato.getVigenciaInicioContrato());
		contratoDto.setVigenciaFinContrato(contrato.getVigenciaFinContrato());
		contratoDto.setEstatus(contrato.getEstatus());
		contratoDto.setFechaRegistro(contrato.getFechaRegistro());
		contratoDto.setFechaModificacion(contrato.getFechaModificacion());
		contratoDto.setFechaBaja(contrato.getFechaBaja());
		
		ProveedorDto proveedorDto = new ProveedorDto();
		
		if(contrato.getProveedor() != null) {
			proveedorDto.setIdProveedor(contrato.getProveedor().getIdProveedor());
			proveedorDto.setProveedor(contrato.getProveedor().getProveedor());
		}	
		
		
		TipoContratacionDto tipoContratacionDto = new TipoContratacionDto();
		
		if(contrato.getTipoContratacion() != null) {
			tipoContratacionDto.setIdTipoContratacion(contrato.getTipoContratacion().getIdTipoContratacion());
			tipoContratacionDto.setTipoContratacion(contrato.getTipoContratacion().getTipoContratacion());
		}	
		
		SubtipoContratoDto subtipoContratoDto = new SubtipoContratoDto(); 
		
		if(contrato.getSubtipoContrato() != null) {
			subtipoContratoDto.setIdSubtipoContrato(contrato.getSubtipoContrato().getIdSubtipoContrato());
			subtipoContratoDto.setSubtipoContrato(contrato.getSubtipoContrato().getSubtipoContrato());
		}
		
		contratoDto.setProveedor(proveedorDto);
		contratoDto.setTipoContratacion(tipoContratacionDto);
		contratoDto.setSubtipoContrato(subtipoContratoDto);
		
		return contratoDto;
	}		

}
