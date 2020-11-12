package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.ClaveDao;
import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Clave;
import mx.inntecsa.smem.service.ClaveService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("claveService")
public class ClaveServiceImpl implements ClaveService {
	
	private Logger log = Logger.getLogger(ClaveServiceImpl.class);	
	
	@Autowired
	private ClaveDao claveDao;

	@Override
	public List<ClaveDto> getClaves() {
		List<ClaveDto> claveesDto = new ArrayList<ClaveDto>(); //Lista de Claves(DTO)

		for (Clave clave : claveDao.getClaves()) {
			ClaveDto claveDto = this.manualModelMapper(clave);			
			claveesDto.add(claveDto);
		}
		
		return claveesDto;
	}
	
	@Override
	public List<ClaveDto> getClavesActivas() {
		
		List<ClaveDto> clavesDto = new ArrayList<ClaveDto>(); //Lista de claves activas (DTO)
		List<Clave> claves = claveDao.getClavesActivas();
		
		for (Clave clave : claves) {
			ClaveDto claveDto = new ClaveDto();
			claveDto.setIdClave(clave.getIdClave());
			claveDto.setClave(clave.getClave());
			claveDto.setFechaRegistro(clave.getFechaRegistro());
			clavesDto.add(claveDto);
		}
		
		return clavesDto;
	}

	@Override
	public List<ClaveDto> getClavesByEstatus(Estatus estatus) {
		List<ClaveDto> claveesDto = new ArrayList<ClaveDto>(); //Lista de Claves(DTO)

		for (Clave clave : claveDao.getClavesByEstatus(estatus)) {
			ClaveDto claveDto = this.manualModelMapper(clave);
			claveesDto.add(claveDto);
		}
		
		return claveesDto;
	}
	
	@Override
	public List<ClaveDto> getClavesConGrupoClavePorIdGrupoYIdEquipo(Integer idEquipo, Integer idGrupo) {
		List<ClaveDto> claveesDto = new ArrayList<ClaveDto>(); //Lista de Claves(DTO)

		for (Clave clave : claveDao.getClavesConGrupoClavePorIdGrupoYIdEquipo(idEquipo, idGrupo)) {
			ClaveDto claveDto = this.manualModelMapper(clave);
			claveesDto.add(claveDto);
		}
		
		return claveesDto;
	}

	@Override
	public List<ClaveDto> getClavesConGrupoClavePorIdClave(Integer idClave) {
		List<ClaveDto> claveesDto = new ArrayList<ClaveDto>(); //Lista de Claves(DTO)

		for (Clave clave : claveDao.getClavesConGrupoClavePorIdClave(idClave)) {
			ClaveDto claveDto = this.manualModelMapper(clave);
			claveesDto.add(claveDto);
		}
		
		return claveesDto;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ClaveDto guardarClave(ClaveDto claveDto) {
		Clave clave = new ModelMapper().map(claveDto, Clave.class);
		
		try{
			Integer idClave = (Integer) claveDao.save(clave);
			claveDto.setIdClave(idClave);
		}catch(Exception e){
			log.error(">>>Error ", e);
		}
		
		return claveDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean actualizarClave(ClaveDto claveDto) {
		Clave clave = new ModelMapper().map(claveDto, Clave.class);
		try{
			claveDao.update(clave);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean eliminarClave(ClaveDto claveDto) {
		Clave clave = new ModelMapper().map(claveDto, Clave.class);
		try{
			claveDao.update(clave);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return false;
	}	
	
	private ClaveDto manualModelMapper(Clave clave) {
		ClaveDto claveDto = new ClaveDto();
		claveDto.setIdClave(clave.getIdClave());
		claveDto.setClave(clave.getClave());
		claveDto.setNombreGenerico(clave.getNombreGenerico());
		claveDto.setFechaRegistro(clave.getFechaRegistro());
		claveDto.setFechaModificacion(clave.getFechaModificacion());
		claveDto.setFechaBaja(clave.getFechaBaja());
		claveDto.setBaja(clave.getBaja());
		return claveDto;		
	}

	@Override
	public List<ClaveDto> getClavesPorNombre(String nombreClave) {
		List<ClaveDto> clavesDto = new ArrayList<ClaveDto>();
		for (Clave clave : claveDao.getClavesPorNombre(nombreClave)) {
			ClaveDto claveDto = this.manualModelMapper(clave);
			clavesDto.add(claveDto);
		}
		return clavesDto;
	}
	
	@Override
	public List<ClaveDto> getClavesPorNombre(String nombreClave, Integer idClave) {
		List<ClaveDto> clavesDto = new ArrayList<ClaveDto>();
		for (Clave clave : claveDao.getClavesPorNombre(nombreClave,idClave)) {
			ClaveDto claveDto = this.manualModelMapper(clave);
			clavesDto.add(claveDto);
		}
		return clavesDto;
	}
	
	@Override
	public ClaveDto getClavePorId(Integer idClave){
		Clave clave = claveDao.getClavePorId(idClave);
		ClaveDto claveDto = this.manualModelMapper(clave); 			
		return claveDto;
	}
}
