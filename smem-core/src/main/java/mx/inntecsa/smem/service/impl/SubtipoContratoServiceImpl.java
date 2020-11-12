package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;


import mx.inntecsa.smem.dao.SubtipoContratoDao;
import mx.inntecsa.smem.dto.SubtipoContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SubtipoContrato;
import mx.inntecsa.smem.service.SubtipoContratoService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subtipoContratoService")
public class SubtipoContratoServiceImpl implements SubtipoContratoService {
	
	private Logger log = Logger.getLogger(SubtipoContratoServiceImpl.class);
	
	@Autowired
	private SubtipoContratoDao subtipoContratoDao;
		
	@Override
	public List<SubtipoContratoDto> getSubtiposContratos() {
		List<SubtipoContratoDto> subtipocontratosDto = new ArrayList<SubtipoContratoDto>(); //Lista de Contratos(DTO)

		for(SubtipoContrato subtipoContrato : subtipoContratoDao.getSubtiposContratos()) {
			SubtipoContratoDto subtipoContratoDto = this.manualModelMapper(subtipoContrato);
			subtipocontratosDto.add(subtipoContratoDto);
		 }
		return subtipocontratosDto;
	   }
    		
	@Override
	public SubtipoContrato getSubtiposContratosPorId(Integer idSubtipoContrato) {
	   	SubtipoContrato subtipocontrato = subtipoContratoDao.getSubtiposContratosPorId(idSubtipoContrato);
	   
		return subtipocontrato;
    }
	
	@Override                                              
	public List<SubtipoContratoDto> getSubtipoContratosPorNombre(String string) {
		List<SubtipoContratoDto> subtipocontratosDto = new ArrayList<SubtipoContratoDto>(); //Lista de Contratos(DTO)
		
		for(SubtipoContrato subtipoContrato : subtipoContratoDao.getSubtipoContratosPorNombre(string)){
		SubtipoContratoDto subtipoContratoDto1 = this.manualModelMapper(subtipoContrato);
		subtipocontratosDto.add(subtipoContratoDto1);
		}
		return subtipocontratosDto;
	 }
	
	@Override                                            
	public List<SubtipoContratoDto> getSubtipoContratosPorNombre(String string, Integer idSubtipoContrato) {
		   List<SubtipoContratoDto> subtipocontratosDto = new ArrayList<SubtipoContratoDto>(); //Lista de Contratos(DTO)
		for(SubtipoContrato subtipoContrato : subtipoContratoDao.getSubtipoContratosPorNombre(string, idSubtipoContrato)){
		SubtipoContratoDto subtipoContratoDto1 = this.manualModelMapper(subtipoContrato);
		subtipocontratosDto.add(subtipoContratoDto1);
		}		
		return subtipocontratosDto;
	 }
	
	
	@Override
	public List<SubtipoContratoDto> getSubtiposContratosPorEstatus(Estatus estatus) {
		List<SubtipoContratoDto> subtipocontratosDto = new ArrayList<SubtipoContratoDto>(); //Lista de Contratos(DTO)

		for(SubtipoContrato subtipoContrato : subtipoContratoDao.getSubtiposContratosPorEstatus(estatus)) {
			SubtipoContratoDto subtipoContratoDto = this.manualModelMapper(subtipoContrato);			
			subtipocontratosDto.add(subtipoContratoDto);
		}		
		return subtipocontratosDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SubtipoContratoDto guardarSubtipoContrato(SubtipoContratoDto subtipoContratoDto) {
		SubtipoContrato subtipoContrato = new ModelMapper().map(subtipoContratoDto, SubtipoContrato.class);
		try{
			Integer idSubtipoContrato = (Integer) subtipoContratoDao.save(subtipoContrato);
			subtipoContratoDto.setIdSubtipoContrato(idSubtipoContrato);
		}catch(Exception e){
			log.error("",e);
		}		
		return subtipoContratoDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarSubtipoContrato(SubtipoContratoDto subtipoContratoDto) {
		SubtipoContrato subtipoContrato = new ModelMapper().map(subtipoContratoDto, SubtipoContrato.class);
		
		try{
			subtipoContratoDao.update(subtipoContrato);
			return true;
		}catch(Exception e){
			log.error("",e);
		}		
	   return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarSubtipoContrato(SubtipoContratoDto subtipoContratoDto) {
		SubtipoContrato subtipoContrato = new ModelMapper().map(subtipoContratoDto, SubtipoContrato.class);
		
		try{
			subtipoContratoDao.update(subtipoContrato);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
			
		return false;
	}	
	
	private SubtipoContratoDto manualModelMapper(SubtipoContrato subtipoContrato) {
		SubtipoContratoDto subtipoContratoDto = new SubtipoContratoDto();
		subtipoContratoDto.setIdSubtipoContrato(subtipoContrato.getIdSubtipoContrato());
		subtipoContratoDto.setSubtipoContrato(subtipoContrato.getSubtipoContrato());
		subtipoContratoDto.setFechaRegistro(subtipoContrato.getFechaRegistro());
		subtipoContratoDto.setFechaModificacion(subtipoContrato.getFechaModificacion());
		subtipoContratoDto.setFechaBaja(subtipoContrato.getFechaBaja());
		subtipoContratoDto.setBaja(subtipoContrato.getBaja());			
		return subtipoContratoDto;
	}
}