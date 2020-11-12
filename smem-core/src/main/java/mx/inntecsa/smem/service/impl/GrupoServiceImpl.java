
package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.GrupoDao;
import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Grupo;
import mx.inntecsa.smem.service.GrupoService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service("grupoService")
public class GrupoServiceImpl implements GrupoService {
	private Logger log = Logger.getLogger(ClaveServiceImpl.class);	
	
	@Autowired
	private GrupoDao grupoDao;

	@Override
	public List<GrupoDto> getGrupos() {
		List<GrupoDto> grupoesDto = new ArrayList<GrupoDto>(); //Lista de Grupos(DTO)

		for (Grupo grupo : grupoDao.getGrupos()) {
			GrupoDto grupoDto = this.manualModelMapper(grupo);
			grupoesDto.add(grupoDto);
		}
		 
		return grupoesDto;
	}
	
	@Override
	public List<GrupoDto> getGruposActivos() {
		
		List<GrupoDto> gruposDto = new ArrayList<GrupoDto>(); //Lista de grupos activos (DTO)
		List<Grupo> grupos = grupoDao.getGruposActivos();
		
		for (Grupo grupo : grupos) {
			GrupoDto grupoDto = new GrupoDto();
			grupoDto.setIdGrupo(grupo.getIdGrupo());
			grupoDto.setGrupo(grupo.getGrupo());
			grupoDto.setFechaRegistro(grupo.getFechaRegistro());
			gruposDto.add(grupoDto);
		}
		
		return gruposDto;
	}

	@Override
	public List<GrupoDto> getGruposByEstatus(Estatus estatus) {
		List<GrupoDto> grupoesDto = new ArrayList<GrupoDto>(); //Lista de Grupos(DTO)

		for (Grupo grupo : grupoDao.getGruposByEstatus(estatus)) {
			GrupoDto grupoDto = this.manualModelMapper(grupo);
			grupoesDto.add(grupoDto);
		}
		
		return grupoesDto;
	}

	@Override
	public List<GrupoDto> getGruposConGrupoClavePorIdEquipo(Integer idEquipo) {
		List<GrupoDto> grupoesDto = new ArrayList<GrupoDto>(); //Lista de Grupos(DTO)

		for (Grupo grupo : grupoDao.getGruposConGrupoClavePorIdEquipo(idEquipo)) {
			GrupoDto grupoDto = this.manualModelMapper(grupo);
			grupoesDto.add(grupoDto);
		}
		
		return grupoesDto;
	}
	
	public GrupoDto manualModelMapper(Grupo grupo) {
		GrupoDto grupoDto = new GrupoDto();
		grupoDto.setIdGrupo(grupo.getIdGrupo());
		grupoDto.setGrupo(grupo.getGrupo());
		grupoDto.setFechaRegistro(grupo.getFechaRegistro());
		grupoDto.setFechaModificacion(grupo.getFechaModificacion());
		grupoDto.setFechaBaja(grupo.getFechaBaja());
		grupoDto.setBaja(grupo.getBaja());
		return grupoDto;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public GrupoDto guardarGrupo(GrupoDto grupoDto) {
		Grupo grupo = new ModelMapper().map(grupoDto, Grupo.class);
		try{
			Integer idGrupo = (Integer) grupoDao.save(grupo);
			grupoDto.setIdGrupo(idGrupo);
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return grupoDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean actualizarGrupo(GrupoDto grupoDto) {
		Grupo grupo = new ModelMapper().map(grupoDto, Grupo.class);
		try{
			grupoDao.update(grupo);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean eliminarGrupo(GrupoDto grupoDto) {
		Grupo grupo = new ModelMapper().map(grupoDto, Grupo.class);
		grupo.setBaja(Estatus.INACTIVO);
		grupo.setFechaBaja(new Date());
		try{
			grupoDao.update(grupo);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return false;
	}

	@Override
	public List<GrupoDto> getGruposPorNombre(String nombre) {
		List<GrupoDto> gruposDto = new ArrayList<GrupoDto>();
		for (Grupo grupo : grupoDao.getGruposPorNombre(nombre)) {
			GrupoDto grupoDto = this.manualModelMapper(grupo);
			gruposDto.add(grupoDto);
		}
		return gruposDto;
	}
	
	@Override
	public List<GrupoDto> getGruposPorNombre(String nombre, Integer idGrupo) {
		List<GrupoDto> gruposDto = new ArrayList<GrupoDto>();
		for (Grupo grupo : grupoDao.getGruposPorNombre(nombre,idGrupo)) {
			GrupoDto grupoDto = this.manualModelMapper(grupo);
			gruposDto.add(grupoDto);
		}
		return gruposDto;
	}
	
	@Override
	public GrupoDto getGrupoPorId(Integer idGrupo){
		Grupo grupo = grupoDao.getGrupoPorId(idGrupo);
		GrupoDto grupoDto = this.manualModelMapper(grupo); 			
		return grupoDto;
	}
}
