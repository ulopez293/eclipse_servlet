package mx.inntecsa.smem.validacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ValidacionBean {
	
	private Logger log = Logger.getLogger(ValidacionBean.class); // Bitacora
	private SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	
	public void validaFechas(FacesContext context, UIComponent component, Object value) throws ParseException {

			UIInput componente = (UIInput) component.findComponent("fechaFin");
			String fechaFinS = (String) componente.getSubmittedValue();
			
			if((value == "" && fechaFinS != "") || (fechaFinS == "" && value != "")) {
				componente.setValid(false);
				FacesMessage msg = new FacesMessage("Para realizar una Búsqueda por fechas, es necesario " +
					"selccionar ambas fechas.");				
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
				
			}
			
			Date fechaInicio = (Date)value;
			Date fechaFin = df.parse(fechaFinS);			
			log.info("Validando fechas: " + fechaInicio + " fechaFin: " + fechaFin);					
			
			if (fechaFin.before(fechaInicio)) {
				componente.setValid(false);
				FacesMessage msg = new FacesMessage("La fecha fin, debe de ser mayor a la fecha inicio.");				
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
	}

}
