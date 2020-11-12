package mx.inntecsa.smem.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.inntecsa.smem.service.UsuarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ValidacionUsuarioBean {

	@Autowired
	private UsuarioService usuarioService; // Servicio de usuarios
	
	private Logger log = Logger.getLogger(ValidacionUsuarioBean.class); // Bitacora
	
	public void validaContrasenia(FacesContext context, UIComponent component, Object value) {
		log.info(">>>Validar contrasenia");
		UIInput componente = (UIInput) component.findComponent("confirmarContrasenia");
		String confirmaContrasenia = componente.getSubmittedValue().toString();
		String contrasenia = (String) value;

		if (!contrasenia.equals(confirmaContrasenia)) {
			componente.setValid(false);
			FacesMessage msg = new FacesMessage("Las contraseñas no coinciden, por favor verifiquelas.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
}
