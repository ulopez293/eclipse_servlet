package mx.inntecsa.smem.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.service.UniversoService;
import mx.inntecsa.smem.bean.AlertaBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ValidacionUniversoBean {

	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes
	
	@Autowired
	private UniversoService universoService; // Servicio de universo
	
	private Logger log = Logger.getLogger(ValidacionUniversoBean.class); // Bitacora
	
	public void validaIdUniverso(FacesContext context, UIComponent component, Object value) {
		log.info(">>>Validando ID universo");
		UIInput componente = (UIInput) component.findComponent("idUniverso");
		Long idUniverso = (Long) value;

		if (universoService.getUniversoPorIdUniverso(idUniverso) == null) {
			componente.setValid(false);
			FacesMessage msg = new FacesMessage("El ID de Universo no existe, favor de verificar.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}	
	public void validaInventario(FacesContext context, UIComponent component, Object value) {
					
		log.info(">>>Validando Inventario " + value.toString());
		UIInput componente = (UIInput) component.findComponent("inventario");
		Integer inventario = Integer.parseInt(value.toString());
		
		//validar si ya existe un equipo medico con ese inventario
		UniversoDto universo = universoService.getUniversoPorInventario(inventario);
		log.info("busqueda universo smem: " + universo);
				
		if (universo != null) {
			
			componente.setValid(false);
			FacesMessage msg = new FacesMessage("Ya existe un Equipo Médico con ese Número de inventario");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		    }	
		
		//validar que el equipo exista en SICOBIM
	    universo = universoService.getUniversoSICOBIMPorInventario(inventario);
	    log.info("busqueda universo sicobim: " + universo);
		 
		 if(universo == null) {
			componente.setValid(false);
			FacesMessage msg = new FacesMessage("No ha sido dado de alta el Equipo Médico en SICOBIM, " +
			                                	"por favor contactese con personal a cargo.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		  
		    }		     
		 } 
	  }	
   
 
