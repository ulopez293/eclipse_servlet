package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;


import mx.inntecsa.smem.dao.EspecialidadDao;
import mx.inntecsa.smem.dto.EspecialidadDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Especialidad;
import mx.inntecsa.smem.service.EspecialidadService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("especialidadService")
public class EspecialidadServiceImpl implements EspecialidadService {
	
	private Logger log = Logger.getLogger(EspecialidadServiceImpl.class);
	
	@Autowired
	private EspecialidadDao especialidadDao;

	@Override
	public List<EspecialidadDto> getEspecialidades() {
		List<EspecialidadDto> especialidadesDto = new ArrayList<EspecialidadDto>(); //Lista de Especialidades(DTO)

		for (Especialidad especialidad : especialidadDao.getEspecialidades()) {
			EspecialidadDto especialidadDto = this.manualModelMapper(especialidad);
			especialidadesDto.add(especialidadDto);
		}
		
		return especialidadesDto;
	}

	@Override
	public List<EspecialidadDto> getEspecialidadesByEstatus(Estatus estatus) {
		List<EspecialidadDto> especialidadesDto = new ArrayList<EspecialidadDto>(); //Lista de Especialidades(DTO)

		for (Especialidad especialidad : especialidadDao.getEspecialidadesByEstatus(estatus)) {
			EspecialidadDto especialidadDto = this.manualModelMapper(especialidad);
			especialidadesDto.add(especialidadDto);
		}
		
		return especialidadesDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EspecialidadDto guardarEspecialidad(EspecialidadDto especialidadDto) {
		Especialidad especialidad = new ModelMapper().map(especialidadDto, Especialidad.class);
		try{
			Integer idEspecialidad = (Integer) especialidadDao.save(especialidad);
			especialidadDto.setIdEspecialidad(idEspecialidad);
		}catch(Exception e){
			log.error("",e);
		}
		
		return especialidadDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarEspecialidad(EspecialidadDto especialidadDto) {
		Especialidad especialidad = new ModelMapper().map(especialidadDto, Especialidad.class);
		
		try{
			especialidadDao.update(especialidad);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarEspecialidad(EspecialidadDto especialidadDto) {
		Especialidad especialidad = new ModelMapper().map(especialidadDto, Especialidad.class);
		
		try{
			especialidadDao.update(especialidad);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	}

	private EspecialidadDto manualModelMapper(Especialidad especialidad) {
		EspecialidadDto especialidadDto = new EspecialidadDto();
		especialidadDto.setIdEspecialidad(especialidad.getIdEspecialidad());
		especialidadDto.setEspecialidad(especialidad.getEspecialidad());
		especialidadDto.setFechaRegistro(especialidad.getFechaRegistro());
		especialidadDto.setFechaModificacion(especialidad.getFechaModificacion());
		especialidadDto.setFechaBaja(especialidad.getFechaBaja());
		especialidadDto.setBaja(especialidad.getBaja());
		return especialidadDto;		
	}
	
	@Override
	public List<EspecialidadDto> getEspecialidadesPorNombre(String nombre) {
		List<EspecialidadDto> especialidadesDto = new ArrayList<EspecialidadDto>();
		for (Especialidad especialidad : especialidadDao.getEspecialidadesPorNombre(nombre)) {
			EspecialidadDto especialidadDto = this.manualModelMapper(especialidad);
			especialidadesDto.add(especialidadDto);
		}
		return especialidadesDto;
	}
	
	@Override
	public List<EspecialidadDto> getEspecialidadesPorNombre(String nombre,Integer idEspecialidad) {
		List<EspecialidadDto> especialidadesDto = new ArrayList<EspecialidadDto>();
		for (Especialidad especialidad : especialidadDao.getEspecialidadesPorNombre(nombre,idEspecialidad)) {
			EspecialidadDto especialidadDto = this.manualModelMapper(especialidad);
			especialidadesDto.add(especialidadDto);
		}
		return especialidadesDto;
	}
	
	@Override
	public EspecialidadDto getEspecialidadPorId(Integer idEspecialidad){
		Especialidad especialidad = especialidadDao.getEspecialidadPorId(idEspecialidad);
		EspecialidadDto especialidadDto = this.manualModelMapper(especialidad); 			
		return especialidadDto;
	}
	
}
