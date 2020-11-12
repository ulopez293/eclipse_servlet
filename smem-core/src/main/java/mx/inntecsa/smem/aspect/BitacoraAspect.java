package mx.inntecsa.smem.aspect;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.BitacoraDto;
import mx.inntecsa.smem.dto.GenericDto;
import mx.inntecsa.smem.service.BitacoraService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BitacoraAspect{
	
	private static Log log = LogFactory.getLog(BitacoraAspect.class);
	
	@Autowired
	private BitacoraService bitacoraService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected boolean guardarBitacora(JoinPoint jp, Object target, Object retorno, int accion, String ip, String usuario) {
		
		try {
			
			Object objectDto= ((Object) jp.getArgs()[0]);
			
			if(objectDto == null){
				log.warn("Aspect PO Notification, order can't be null.");
				return false;
			}
			
			BitacoraDto bitacora = new BitacoraDto();
			String operacion = setOperacion(bitacora, accion);
	    	bitacora.setFecha(new Date());
	    	bitacora.setIdBitacora(0);
	    	bitacora.setUsuario(usuario);
	    	bitacora.setIpUsuario(ip);
	    	
			if(objectDto instanceof GenericDto){
			    GenericDto genericDto = (GenericDto) objectDto;
		    	bitacora.setDescripcion("Se " + operacion + " " +genericDto.getDescripcionBitacora());
			    	
				if (retorno instanceof GenericDto){	
					genericDto = (GenericDto) retorno;
			    	bitacora.setDescripcion("Se " + operacion + " " +genericDto.getDescripcionBitacora());
				}				
								
			}
			
			if( objectDto instanceof List) {				
				
				if ( ((List) objectDto).get(0) instanceof GenericDto) {
					List<GenericDto> genericDto = (List<GenericDto>) objectDto;
					bitacora.setDescripcion("Se " + operacion +" " + genericDto.size() 
						+ " registros, " + genericDto.get(0).getDescripcionBitacora());
				} else {
					List<Object> genericBit = ((List) objectDto);
					bitacora.setDescripcion("Se " + operacion +" " + genericBit.size() 
							+ " registros para desbloquear con identificadores de CT, " + genericBit.toString());
				}
			}

	    	log.info("Antes de guardar la bitacora. Usuario " + usuario);
	    	bitacoraService.registraBitacora(bitacora);
	    	
		} catch (Exception e) {
			log.error("No se completa Aspecto notifyPoServices, methodo: retorno: " + retorno, e);
		}
		
		return false;
	}
	
	private String setOperacion(BitacoraDto bitacora , int accion){
	    
		if(accion == 1){
	    	bitacora.setOperacion("GUARDO");
	    	return "guardo";
	    }
	    else if(accion==2){
	    	bitacora.setOperacion("ACTUALIZO");
	    	return "actualizo";
	    }
	    else if(accion==3){
	    	bitacora.setOperacion("ELIMINO");
	    	return "elimino";
	    }
	    
	    return "";
	}
	
}
