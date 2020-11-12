package mx.inntecsa.smem.service.impl;


import mx.inntecsa.smem.dao.ConfiguracionDao;
import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.pojo.Configuracion;
import mx.inntecsa.smem.pojo.ConfiguracionId;
import mx.inntecsa.smem.service.ConfiguracionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("configuracionService")
public class ConfiguracionServiceImpl implements ConfiguracionService{
	
	private Logger log = Logger.getLogger(ConfiguracionServiceImpl.class);
			
	@Autowired
	private ConfiguracionDao configuracionDao;
	
	public ConfiguracionDto getConfiguracion(){
		
		ConfiguracionDto configuracionDto =  ConfiguracionDto.getInstance();
		log.info("Configuracion en el service: " + configuracionDto);
		
		if(configuracionDto.getDiasPreventivos()==null && configuracionDto.getDiasCorrectivos()==null 
				&& configuracionDto.getDiasCancelaCorrectivos()==null){
			
			Configuracion configuracion = configuracionDao.getConfiguracion();
			configuracionDto.setDiasCorrectivos(configuracion.getId().getDiasCorrectivos());
			configuracionDto.setDiasPreventivos(configuracion.getId().getDiasPreventivos());
			configuracionDto.setDiasCancelaCorrectivos(configuracion.getId().getDiasCancelaCorrectivos());
			
		}
		
		return configuracionDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarConfiguracion(ConfiguracionDto configuracionDto) {
		
		Configuracion configuracion = new Configuracion();
		ConfiguracionId id = new ConfiguracionId();
		id.setDiasPreventivos(configuracionDto.getDiasPreventivos());
		id.setDiasCorrectivos(configuracionDto.getDiasCorrectivos());
		id.setDiasCancelaCorrectivos(configuracionDto.getDiasCancelaCorrectivos());
		
		configuracion.setId(id);
		
		try{
			configuracionDao.update(configuracion);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;	
	}
}
