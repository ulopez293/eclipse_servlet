package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.GrupoClaveDao;
import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.GrupoClaveDto;
import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.GrupoClave;
import mx.inntecsa.smem.service.GrupoClaveService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("grupoClaveService")
public class GrupoClaveServiceImpl implements GrupoClaveService {
	
	private Logger log = Logger.getLogger(ClaveServiceImpl.class);	
	
	@Autowired
	private GrupoClaveDao grupoClaveDao;
	
	//private List<GrupoDto> grupos;
	//private List<ClaveDto> claves;

	@Override
	public List<GrupoClaveDto> getGruposClaves() {
		List<GrupoClaveDto> grupoClaveesDto = new ArrayList<GrupoClaveDto>(); //Lista de GruposClaves(DTO)

		for (GrupoClave grupoClave : grupoClaveDao.getGruposClaves()) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			grupoClaveesDto.add(grupoClaveDto);
		}
		
		return grupoClaveesDto;
	}

	@Override
	public List<GrupoClaveDto> getGruposClavesByEstatus(Estatus estatus) {
		List<GrupoClaveDto> grupoClaveesDto = new ArrayList<GrupoClaveDto>(); //Lista de GruposClaves(DTO)

		for (GrupoClave grupoClave : grupoClaveDao.getGruposClavesByEstatus(estatus)) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			grupoClaveesDto.add(grupoClaveDto);
		}
		
		return grupoClaveesDto;
	}

	@Override
	public List<GrupoClaveDto> getGruposClavesPorIdEquipo(Integer idEquipo) {
		List<GrupoClaveDto> grupoClaveesDto = new ArrayList<GrupoClaveDto>(); //Lista de GruposClaves(DTO)

		for (GrupoClave grupoClave : grupoClaveDao.getGruposClavesPorIdEquipo(idEquipo)) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			grupoClaveesDto.add(grupoClaveDto);
		}
		
		return grupoClaveesDto;
	}

	@Override
	public List<GrupoClaveDto> getGruposClavesPorIdGrupo(Integer idGrupo) {
		List<GrupoClaveDto> grupoClaveesDto = new ArrayList<GrupoClaveDto>(); //Lista de GruposClaves(DTO)

		for (GrupoClave grupoClave : grupoClaveDao.getGruposClavesPorIdGrupo(idGrupo)) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			grupoClaveesDto.add(grupoClaveDto);
		}
		
		return grupoClaveesDto;	
	}
	
	@Override
	public GrupoClaveDto getGrupoClavePorParametros(Integer idEquipo,
		Integer idGrupo, Integer idClave) {
		GrupoClave grupoClave = grupoClaveDao.getGrupoClavePorParametros(idEquipo, idGrupo, idClave);
		
		if(grupoClave != null) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			return grupoClaveDto;
		}		
		
		return null;
	}
	
	private GrupoClaveDto manualModelMapper(GrupoClave grupoClave) {
		GrupoClaveDto grupoClaveDto = new GrupoClaveDto();
		grupoClaveDto.setIdGrupoClave(grupoClave.getIdGrupoClave());
		grupoClaveDto.setFechaRegistro(grupoClave.getFechaRegistro());
		grupoClaveDto.setFechaModificacion(grupoClave.getFechaModificacion());
		grupoClaveDto.setFechaBaja(grupoClave.getFechaBaja());
		grupoClaveDto.setBaja(grupoClave.getBaja());			
		EquipoDto equipoDto = new EquipoDto();
		if(grupoClave.getEquipo() != null) {
			equipoDto.setIdEquipo(grupoClave.getEquipo().getIdEquipo());
			equipoDto.setEquipo(grupoClave.getEquipo().getEquipo());
		}

		GrupoDto grupoDto = new GrupoDto();		
		if(grupoClave.getGrupo() != null) {
			grupoDto.setIdGrupo(grupoClave.getGrupo().getIdGrupo());
			grupoDto.setGrupo(grupoClave.getGrupo().getGrupo());
		}
			
		ClaveDto claveDto = new ClaveDto();		
		if(grupoClave.getClave() != null) {
			claveDto.setIdClave(grupoClave.getClave().getIdClave());
			claveDto.setClave(grupoClave.getClave().getClave());
		}
		
		grupoClaveDto.setEquipo(equipoDto);
		grupoClaveDto.setGrupo(grupoDto);
		grupoClaveDto.setClave(claveDto);
			
		return grupoClaveDto;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GrupoClaveDto guardarGrupoClave(GrupoClaveDto grupoClaveDto) {
	    GrupoClave grupoClave = new ModelMapper().map(grupoClaveDto, GrupoClave.class);
		try{
			 Integer idGrupoClave = (Integer) grupoClaveDao.save(grupoClave);
			 grupoClaveDto.setIdGrupoClave(idGrupoClave);
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return grupoClaveDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean actualizarGrupoClave(GrupoClaveDto grupoClaveDto) {
		GrupoClave grupoClave = new ModelMapper().map(grupoClaveDto, GrupoClave.class);
		try{
			grupoClaveDao.update(grupoClave);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean eliminarGrupoClave(GrupoClaveDto grupoClaveDto) {
		GrupoClave grupoClave = new ModelMapper().map(grupoClaveDto, GrupoClave.class);
		grupoClave.setBaja(Estatus.INACTIVO);
		grupoClave.setFechaBaja(new Date());
		try{
			grupoClaveDao.update(grupoClave);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		
		return false;
	}
	
	public List<GrupoClaveDto> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo) {
		List<GrupoClaveDto> gruposClaveDto = new ArrayList<GrupoClaveDto>();
		for (GrupoClave grupoClave : grupoClaveDao.getGruposClavesPorParametros(idGrupo, idClave, idEquipo)) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			gruposClaveDto.add(grupoClaveDto);
		}
		return gruposClaveDto;
	}
	
	@Override
	public List<GrupoClaveDto> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo, Integer idGrupoClave) {
		List<GrupoClaveDto> gruposClaveDto = new ArrayList<GrupoClaveDto>();
		for (GrupoClave grupoClave : grupoClaveDao.getGruposClavesPorParametros(idGrupo, idClave, idEquipo, idGrupoClave)) {
			GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave);
			gruposClaveDto.add(grupoClaveDto);
		}
		return gruposClaveDto;
	}
	
	@Override
	public GrupoClaveDto getGrupoClavePorId(Integer idGrupoClave){
		GrupoClave grupoClave = grupoClaveDao.getGrupoClavePorId(idGrupoClave);
		GrupoClaveDto grupoClaveDto = this.manualModelMapper(grupoClave); 			
		return grupoClaveDto;
	}
}
