package mx.inntecsa.smem.bean;

import java.io.Serializable;

import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.service.ConfiguracionService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class ConfiguracionBean implements Serializable {
	
	private Logger log = Logger.getLogger(ConfiguracionBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlertaBean alertaBean;
	
	@Autowired
	private ConfiguracionService configuracionService;
	
	private ConfiguracionDto configuracionDto;
	
	public String inicializar(){
		
		log.info("Configuraciones. inicializar");
		
		configuracionDto = configuracionService.getConfiguracion();
		
		return VistasEnum.CONFIGURACION.getIdVista();
	}
	
	public void guardarConfiguracion(){
			
		boolean resultado = configuracionService.actualizarConfiguracion(configuracionDto);
		log.info("Actualizando la configuracion resultado: " + resultado);
		
		if(resultado){
			alertaBean.setMensaje("Configuraciones actualizadas correctamente.");
			alertaBean.setImagen(Icono.SUCCESS);
		}else{
			alertaBean.setMensaje("Ocurrio un error al actualizar la configuracion.");
			alertaBean.setImagen(Icono.ERROR);
		}
	}

	public ConfiguracionDto getConfiguracionDto() {
		return configuracionDto;
	}

	public void setConfiguracionDto(ConfiguracionDto configuracionDto) {
		this.configuracionDto = configuracionDto;
	}

}
