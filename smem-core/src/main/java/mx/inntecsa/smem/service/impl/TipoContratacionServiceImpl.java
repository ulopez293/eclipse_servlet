package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.TipoContratacionDao;
import mx.inntecsa.smem.dto.TipoContratacionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.TipoContratacion;
import mx.inntecsa.smem.service.TipoContratacionService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tipoContratacionService")
public class TipoContratacionServiceImpl implements TipoContratacionService {
	
	//private Logger log = Logger.getLogger(ClaveServiceImpl.class);	
	private Logger log = Logger.getLogger(TipoContratacionServiceImpl.class);
	
	@Autowired
	private TipoContratacionDao tipoContratacionDao;
	
	@Override
	public List<TipoContratacionDto> getTiposContratacion() {
		
		List <TipoContratacionDto> tiposContratacionDto = new ArrayList<TipoContratacionDto>(); //Lista de Claves(DTO)
			for (TipoContratacion tipoContratacion : tipoContratacionDao.getTiposContrataciones()) {
			TipoContratacionDto tipoContratacionDto = this.manualModelMapper(tipoContratacion);
			tiposContratacionDto.add(tipoContratacionDto);
		}
		return tiposContratacionDto;
	}

	@Override
	public TipoContratacion getTiposContratacionPorId(Integer idTipoContrato) {
		//List<TipoContratacionDto> tiposContratacionDto = new ArrayList<TipoContratacionDto>(); //Lista de Claves(DTO) 
		TipoContratacion tipoContratacion = tipoContratacionDao.getTiposContratacionPorId(idTipoContrato);
     return tipoContratacion;
    }	
	
	@Override                        
	public List<TipoContratacionDto> getTiposContratacionesPorNombre(String string) {

		List<TipoContratacionDto> tiposContratacionDto = new ArrayList<TipoContratacionDto>(); //Lista de TiposContrato(DTO)
		for (TipoContratacion tipoContratacion : tipoContratacionDao.getTiposContratacionesPorNombre(string)) {
			TipoContratacionDto tipoContratacionDto1 = this.manualModelMapper(tipoContratacion);
			tiposContratacionDto.add(tipoContratacionDto1);
		 }
		 return tiposContratacionDto;
  	 }	
	
	public List<TipoContratacionDto> getTiposContratacionesPorNombre(String string, Integer idTipoContrato) {
		   List<TipoContratacionDto> tiposContratacionDto = new ArrayList<TipoContratacionDto>(); //Lista de Tipos Contrato(DTO)
		
		for (TipoContratacion tipoContratacion : tipoContratacionDao.getTiposContratacionesPorNombre(string, idTipoContrato )) {
			TipoContratacionDto tipoContratacionDto1 = this.manualModelMapper(tipoContratacion);
			tiposContratacionDto.add(tipoContratacionDto1);
		 }		
		 return tiposContratacionDto;
  	 }
	
	

	@Override
	public List<TipoContratacionDto> getTiposContratacionPorEstatus(Estatus estatus) {
		List<TipoContratacionDto> tiposContratacionDto = new ArrayList<TipoContratacionDto>(); //Lista de TiposContratacion(DTO)

		for (TipoContratacion tipoContratacion : tipoContratacionDao.getTiposContratacionesPorEstatus(estatus)) {
			TipoContratacionDto tipoContratacionDto = this.manualModelMapper(tipoContratacion);
			tiposContratacionDto.add(tipoContratacionDto);
		}		
		return tiposContratacionDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public TipoContratacionDto guardarTipoContratacion(TipoContratacionDto tipoContratacionDto) {
		TipoContratacion tipoContratacion = new ModelMapper().map(tipoContratacionDto, TipoContratacion.class);
		try{
			Integer idTipoContratacion = (Integer) tipoContratacionDao.save(tipoContratacion);
			tipoContratacionDto.setIdTipoContratacion(idTipoContratacion);
		}catch(Exception e){
			log.error("",e);
		}		
		return tipoContratacionDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean actualizarTipoContratacion(TipoContratacionDto tipoContratacionDto) {
		TipoContratacion tipoContratacion = new ModelMapper().map(tipoContratacionDto, TipoContratacion.class);
		
		try{
			tipoContratacionDao.update(tipoContratacion);
			return true;
		}catch(Exception e){
			log.error("",e);
		}		
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean eliminarTipoContratacion(TipoContratacionDto tipoContratacionDto) {
		TipoContratacion tipoContratacion = new ModelMapper().map(tipoContratacionDto, TipoContratacion.class);
		tipoContratacion.setBaja(Estatus.INACTIVO);
		tipoContratacion.setFechaBaja(new Date());
		try{
			tipoContratacionDao.update(tipoContratacion);
			return true;
		}catch(Exception e){
			log.error("",e);
		}		
		return false;
	}

	private TipoContratacionDto manualModelMapper(TipoContratacion tipoContratacion) {
		
		TipoContratacionDto tipoContratacionDto = new TipoContratacionDto();
		tipoContratacionDto.setIdTipoContratacion(tipoContratacion.getIdTipoContratacion());
		tipoContratacionDto.setTipoContratacion(tipoContratacion.getTipoContratacion());
		tipoContratacionDto.setBaja(tipoContratacion.getBaja());
		tipoContratacionDto.setFechaRegistro(tipoContratacion.getFechaRegistro());
		
		return tipoContratacionDto;
	}
}

