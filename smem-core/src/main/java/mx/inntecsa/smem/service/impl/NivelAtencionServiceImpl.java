package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;


import mx.inntecsa.smem.dao.NivelAtencionDao;
import mx.inntecsa.smem.dto.NivelAtencionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.NivelAtencion;
import mx.inntecsa.smem.service.NivelAtencionService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("nivelAtencionService")
public class NivelAtencionServiceImpl implements NivelAtencionService {
	
	private Logger log = Logger.getLogger(NivelAtencionServiceImpl.class);
	
	@Autowired
	private NivelAtencionDao nivelAtencionDao;

	@Override
	public List<NivelAtencionDto> getNivelesAtencion() {
		List<NivelAtencionDto> nivelesAtencionDto = new ArrayList<NivelAtencionDto>(); //Lista de Especialidades(DTO)
		
		for (NivelAtencion nivelAtencion : nivelAtencionDao.getNivelesAtencion()) {
			NivelAtencionDto nivelAtencionDto = new ModelMapper().map(nivelAtencion, NivelAtencionDto.class);
			nivelesAtencionDto.add(nivelAtencionDto);
		}
		log.info("niveles atencion: " + nivelesAtencionDto.size());
		return nivelesAtencionDto;
	}

	@Override
	public List<NivelAtencionDto> getNivelesAtencionByEstatus(Estatus estatus) {
		List<NivelAtencionDto> nivelesAtencionDto = new ArrayList<NivelAtencionDto>(); //Lista de Especialidades(DTO)

		for (NivelAtencion nivelAtencion : nivelAtencionDao.getNivelesAtencionByEstatus(estatus)) {
			NivelAtencionDto nivelAtencionDto = new ModelMapper().map(nivelAtencion, NivelAtencionDto.class);
			nivelesAtencionDto.add(nivelAtencionDto);
		}
		
		return nivelesAtencionDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public NivelAtencionDto guardarNivelAtencion(NivelAtencionDto nivelAtencionDto) {
		NivelAtencion nivelAtencion = new ModelMapper().map(nivelAtencionDto, NivelAtencion.class);
		try{
			Integer idNivelAtencion = (Integer) nivelAtencionDao.save(nivelAtencion);
			nivelAtencionDto.setIdNivelAtencion(idNivelAtencion);
		}catch(Exception e){
			log.error("",e);
		}
		
		return nivelAtencionDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarNivelAtencion(NivelAtencionDto nivelAtencionDto) {
		NivelAtencion nivelAtencion = new ModelMapper().map(nivelAtencionDto, NivelAtencion.class);
		
		try{
			nivelAtencionDao.update(nivelAtencion);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarNivelAtencion(NivelAtencionDto nivelAtencionDto) {
		NivelAtencion nivelAtencion = new ModelMapper().map(nivelAtencionDto, NivelAtencion.class);
		
		try{
			nivelAtencionDao.update(nivelAtencion);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	} 
	
	@Override
	public List<NivelAtencionDto> getNivelesAtencionPorDescripcion(String descripcion) {
		List<NivelAtencionDto> nivelesAtencionDto = new ArrayList<NivelAtencionDto>();
		for (NivelAtencion nivelAtencion : nivelAtencionDao.getNivelesAtencionPorDescripcion(descripcion)) {
			NivelAtencionDto nivelAtencionDto = new ModelMapper().map(nivelAtencion, NivelAtencionDto.class);
			nivelesAtencionDto.add(nivelAtencionDto);
		}
		return nivelesAtencionDto;
	}
	
	@Override
	public List<NivelAtencionDto> getNivelesAtencionPorDescripcion(String descripcion, Integer idNivelAtencion) {
		List<NivelAtencionDto> nivelesAtencionDto = new ArrayList<NivelAtencionDto>();
		for (NivelAtencion nivelAtencion : nivelAtencionDao.getNivelesAtencionPorDescripcion(descripcion,idNivelAtencion)) {
			NivelAtencionDto nivelAtencionDto = new ModelMapper().map(nivelAtencion, NivelAtencionDto.class);
			nivelesAtencionDto.add(nivelAtencionDto);
		}
		return nivelesAtencionDto;
	}
	
	@Override
	public NivelAtencionDto getNivelAtencionPorId(Integer idNivelAtencion){
		NivelAtencion nivelAtencion = nivelAtencionDao.getNivelAtencionPorId(idNivelAtencion);
		NivelAtencionDto nivelAtencionDto = new ModelMapper().map(nivelAtencion, NivelAtencionDto.class);
		return nivelAtencionDto;
	}

}
