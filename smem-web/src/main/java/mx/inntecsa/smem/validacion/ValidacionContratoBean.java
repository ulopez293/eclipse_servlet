package mx.inntecsa.smem.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.service.ContratoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ValidacionContratoBean {
	
	
	@Autowired
	private ContratoService contratoService; // Servicio de contratos
	
	private Logger log = Logger.getLogger(ValidacionContratoBean.class); // Bitacora
	
	public void validaNumeroContrato(FacesContext context, UIComponent component, Object value) {
		log.info(">>>Validando numero de contrato");
		String numeroContrato = (String) value;
		log.info(">>>Numero de contrato " + numeroContrato);
		UIInput componenteContrato = (UIInput) component.findComponent("idContratoHidden");
		String idContrato = (String) componenteContrato.getSubmittedValue();
		log.info(">>>Contrato escondido " + idContrato);
		ContratoDto contratoDto = contratoService.getContratoPorNumeroContrato(numeroContrato);
		
		if (contratoDto != null && contratoDto.getIdContrato().intValue() != Integer.valueOf(idContrato)) {
			UIInput componente = (UIInput) component.findComponent("numeroContrato");
			componente.setValid(false);
			FacesMessage msg = new FacesMessage("Ya existe un Contrato son ese mismo Número de Contrato.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}
