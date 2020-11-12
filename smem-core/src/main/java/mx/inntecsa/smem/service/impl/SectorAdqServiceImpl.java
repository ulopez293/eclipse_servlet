package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.SectorAdqDao;
import mx.inntecsa.smem.dto.SectorAdqDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SectorAdq;
import mx.inntecsa.smem.service.SectorAdqService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sectorAdqService")
public class SectorAdqServiceImpl implements SectorAdqService {
	
	private Logger log = Logger.getLogger(SectorAdqServiceImpl.class);
	
	@Autowired
	private SectorAdqDao sectorAdqDao;
	
	@Override
	public List<SectorAdqDto> getSectoresAdq() {
		List<SectorAdqDto> sectoresAdqDto = new ArrayList<SectorAdqDto>();
		
		for(SectorAdq sectorAdq : sectorAdqDao.getSectoresAdq()) {
			SectorAdqDto sectorAdqDto = this.manualModelMapper(sectorAdq);
			sectoresAdqDto.add(sectorAdqDto);
		}
		
		return sectoresAdqDto;
	}

	@Override
	public List<SectorAdqDto> getSectoresAdqPorEstatus(Estatus estatus) {
		log.info(">>>Sectores por estatus");
		List<SectorAdqDto> sectoresAdqDto = new ArrayList<SectorAdqDto>();
		
		for(SectorAdq sectorAdq : sectorAdqDao.getSectoresAdqPorEstatus(estatus)) {
			SectorAdqDto sectorAdqDto = this.manualModelMapper(sectorAdq);
			sectoresAdqDto.add(sectorAdqDto);
		}
		
		return sectoresAdqDto;
	}
	
	
	private SectorAdqDto manualModelMapper(SectorAdq sectorAdq) {
		SectorAdqDto sectorAdqDto = new SectorAdqDto();
		sectorAdqDto.setIdSectorAdq(sectorAdq.getIdSectorAdq());
		sectorAdqDto.setSectorAdq(sectorAdq.getSectorAdq());
		sectorAdqDto.setFechaRegistro(sectorAdq.getFechaRegistro());
		sectorAdqDto.setFechaModificacion(sectorAdq.getFechaModificacion());
		sectorAdqDto.setFechaBaja(sectorAdq.getFechaBaja());
		sectorAdqDto.setBaja(sectorAdq.getBaja());
		return sectorAdqDto;
	}
	
		
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public SectorAdqDto guardarSectorAdquisicion(SectorAdqDto sectorAdqDto) {
		SectorAdq sectorAdquisicion = new ModelMapper().map(sectorAdqDto, SectorAdq.class);
		try{
			Integer idSectorAdquisicion=(Integer)sectorAdqDao.save(sectorAdquisicion);
			sectorAdqDto.setIdSectorAdq(idSectorAdquisicion);
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return sectorAdqDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean actualizarSectorAdquisicion(SectorAdqDto sectorAdqDto) {
		SectorAdq sectorAdquisicion = new ModelMapper().map(sectorAdqDto, SectorAdq.class);
		try{
			sectorAdqDao.update(sectorAdquisicion);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean eliminarSectorAdquisicion(SectorAdqDto sectorAdqDto) {
		SectorAdq sectorAdquisicion = new ModelMapper().map(sectorAdqDto, SectorAdq.class);
		sectorAdquisicion.setBaja(Estatus.INACTIVO);
		sectorAdquisicion.setFechaBaja(new Date());
		try{
			sectorAdqDao.update(sectorAdquisicion);
			return true;
		}catch(Exception e){
			log.error(">>>Error ",e);
		}
		return false;
	}
	
	@Override
	public List<SectorAdqDto> getSectorAdquisicionPorNombre(String nombre) {
		List<SectorAdqDto> sectoresAdqDto = new ArrayList<SectorAdqDto>();
		for (SectorAdq sectorAdq : sectorAdqDao.getSectorAdquisicionPorNombre(nombre)) {
			SectorAdqDto sectorAdqDto = this.manualModelMapper(sectorAdq);
			sectoresAdqDto.add(sectorAdqDto);
		}
		return sectoresAdqDto;
	}
	
	@Override
	public List<SectorAdqDto> getSectorAdquisicionPorNombre(String nombre, Integer idSectorAdq) {
		List<SectorAdqDto> sectoresAdqDto = new ArrayList<SectorAdqDto>();
		for (SectorAdq sectorAdq : sectorAdqDao.getSectorAdquisicionPorNombre(nombre,idSectorAdq)) {
			SectorAdqDto sectorAdqDto = this.manualModelMapper(sectorAdq);
			sectoresAdqDto.add(sectorAdqDto);
		}
		return sectoresAdqDto;
	}
	
	@Override
	public SectorAdqDto getSectorAdquisicionPorId(Integer idSectorAdq){
		SectorAdq sectorAdq = sectorAdqDao.getSectorAdquisicionPorId(idSectorAdq);
		SectorAdqDto sectorAdqDto = this.manualModelMapper(sectorAdq); 			
		return sectorAdqDto;
	}

}
