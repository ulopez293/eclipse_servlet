/**
 * 
 */
package mx.inntecsa.smem.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.enums.EstatusCentroTrabajo;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.service.CentroTrabajoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author INNTECSA
 *
 */
public class BloqueoUrctHandlerImpl implements AuthenticationSuccessHandler {
	
	private Logger logger = Logger.getLogger(BloqueoUrctHandlerImpl.class);
	
	@Autowired
	private CentroTrabajoService ctService;

   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication aut)throws IOException, ServletException {
   		
	   if (aut != null && aut.isAuthenticated()) {
   			
		   boolean esAdmin = false;
   			
		   for (GrantedAuthority auth : aut.getAuthorities()) {
   				
			   logger.info("revisando usuario - esadmin: " + auth.getAuthority() );
   	            if (auth.getAuthority().equals(Rol.ADMINISTRADOR.getPerfilRol()) || 
   	            		auth.getAuthority().equals(Rol.ADMINSTRADOR_GENERAL.getPerfilRol())){
   	            	esAdmin = true;
   	            }
   			}
   			
		   
		   logger.info("revisando usuario - esadmin: " + esAdmin);

   			if (esAdmin) {
   				response.sendRedirect("home.xhtml");
   			} else {
   				String urct = aut.getName();
   				CentroTrabajoDto ct = ctService.getCentroTrabajoPorURCT(urct);
   				logger.info("ct: " + ct.toString());
   				
   				if (ct != null && ct.getEstatus() == EstatusCentroTrabajo.BLOQUEADO) {
   					response.sendRedirect("bloqueo.xhtml");
   				} else {
   					response.sendRedirect("home.xhtml");
   				}
   			}
   		}
   }

}
