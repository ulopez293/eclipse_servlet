package mx.inntecsa.smem.aspect;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.inntecsa.smem.aspect.BitacoraAspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BitacoraWebAspect extends BitacoraAspect{
	
	private static Log log = LogFactory.getLog(BitacoraWebAspect.class);
	
	public final boolean guardar(JoinPoint jp, Object target, Object ret) {
		
		int ACCION_GUARDAR = 1;
		
	    String usuario = getUsuario();
	    String ipUsuario = getIPUsuario();
	    
	    log.info("guuarda en bitacora: " + usuario + " ip: " + ipUsuario);
		return guardarBitacora(jp, target, ret, ACCION_GUARDAR, ipUsuario, usuario);
	}

	public final boolean actualizar(JoinPoint jp, Object target, Object ret) {
		
		int ACCION_MODIFICAR = 2;
	    String usuario = getUsuario();
	    String ipUsuario = getIPUsuario();
	    
		log.info("modifica en bitacora: " + usuario + " ip: " + ipUsuario);
		return guardarBitacora(jp, target, ret, ACCION_MODIFICAR, ipUsuario, usuario);
	}

	public final boolean eliminar(JoinPoint jp, Object target, Object ret) {
		
		int ACCION_ELIMINAR = 3;
	    String usuario = getUsuario();
	    String ipUsuario = getIPUsuario();
	    
		log.info("elimina en bitacora: " + usuario + " ip: " + ipUsuario);
		return guardarBitacora(jp, target, ret, ACCION_ELIMINAR, ipUsuario, usuario);
	}
	
	public String getUsuario(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre="";
		
		if(auth != null){
			nombre = auth.getName(); //get logged in username
		}
		else{
			nombre = "Sistema";
		}

	    return nombre;
	}
	
	public String getIPUsuario(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String ipAddress = null;
		
		if(facesContext != null){
			HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			
			if (ipAddress == null) {
			    ipAddress = request.getRemoteAddr();
			}
			
		}else
		{
			ipAddress = "localhost";
		}
		
		return ipAddress;
	}	
}
