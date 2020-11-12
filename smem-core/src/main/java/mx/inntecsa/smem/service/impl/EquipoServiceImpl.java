package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;


import mx.inntecsa.smem.dao.EquipoDao;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.pojo.Equipo;
import mx.inntecsa.smem.service.EquipoService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipoService")
public class EquipoServiceImpl implements EquipoService {

	private Logger log = Logger.getLogger(EquipoServiceImpl.class);
	
	@Autowired
	private EquipoDao equipoDao;
	
	@Override
	public List<EquipoDto> getEquipos() {
		
		List<EquipoDto> equiposDto = new ArrayList<EquipoDto>(); //Lista de equipos (DTO)
		List<Equipo> equipos = equipoDao.getEquipos();
		
		for (Equipo equipo : equipos) {
			EquipoDto equipoDto = new EquipoDto();
			equipoDto.setIdEquipo(equipo.getIdEquipo());
			equipoDto.setEquipo(equipo.getEquipo());
			equipoDto.setBaja(equipo.getBaja());
			equipoDto.setFechaRegistro(equipo.getFechaRegistro());
			equiposDto.add(equipoDto);
		}
		
		return equiposDto;
	}

	@Override
	public List<EquipoDto> getEquiposActivos() {
		
		List<EquipoDto> equiposDto = new ArrayList<EquipoDto>(); //Lista de equipos (DTO)
		List<Equipo> equipos = equipoDao.getEquiposActivos();
		
		for (Equipo equipo : equipos) {
			EquipoDto equipoDto = new EquipoDto();
			equipoDto.setIdEquipo(equipo.getIdEquipo());
			equipoDto.setEquipo(equipo.getEquipo());
			equipoDto.setFechaRegistro(equipo.getFechaRegistro());
			equiposDto.add(equipoDto);
		}
		
		return equiposDto;
	}
	
	@Override
	public List<EquipoDto> getEquiposConGrupoClave() {
		List<EquipoDto> equiposDto = new ArrayList<EquipoDto>(); //Lista de equipos (DTO)
		List<Equipo> equipos = equipoDao.getEquiposConGrupoClave();
		
		for (Equipo equipo : equipos) {
			EquipoDto equipoDto = new EquipoDto();
			equipoDto.setIdEquipo(equipo.getIdEquipo());
			equipoDto.setEquipo(equipo.getEquipo());
			equipoDto.setBaja(equipo.getBaja());
			equipoDto.setFechaRegistro(equipo.getFechaRegistro());
			equiposDto.add(equipoDto);
		}
		
		return equiposDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EquipoDto guardarEquipo(EquipoDto equipoDto) {
		Equipo equipo = new ModelMapper().map(equipoDto, Equipo.class);
		try{
			Integer idEquipo = (Integer) equipoDao.save(equipo);
			equipoDto.setIdEquipo(idEquipo);
		}catch(Exception e){
			log.error("",e);
		}
		
		return equipoDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarEquipo(EquipoDto equipoDto) {
		Equipo equipo = new ModelMapper().map(equipoDto, Equipo.class);
		
		try{
			equipoDao.update(equipo);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarEquipo(EquipoDto equipoDto) {
		Equipo equipo = new ModelMapper().map(equipoDto, Equipo.class);
		
		try{
			equipoDao.update(equipo);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;
	}
	
}
