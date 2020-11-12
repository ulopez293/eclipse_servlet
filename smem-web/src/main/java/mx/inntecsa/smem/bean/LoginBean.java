package mx.inntecsa.smem.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import mx.inntecsa.smem.dto.UsuarioDto;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.service.UsuarioService;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase (Bean) utilizada para la para la validación de usuario y Password. También
 * noes permite redireccionar a la página de menú y salir de la aplicación.
 * @version 1.0
 * @author INNTECSA.
 */
@SessionScoped
@ManagedBean(name="loginBean")
@Component
public class LoginBean {

	private Logger logger = Logger.getLogger(LoginBean.class); // Bitácora

	private String username = ""; // Usuario de la aplicación
	private String password = ""; // Password de acceso
	private String nombreUsuario;

	@Autowired
	private UsuarioService usuarioService; // Servicio de usuarios
	

	/**
    * Método para retornar el nombre de un usuario.
    * @return nombre de usuario.
    */					
	public String getUsername() {
		return username;
	}
	
	
	/**
    * Método para inicializar el nombre del usuario.
    * @param username, nombre de usuario.
    */						
	public void setUsername(String username) {
		this.username = username;
	}

	
	/**
    * Método para retornar el password de un usuario.
    * @return password.
    */						
	public String getPassword() {
		return password;
	}

	
	/**
    * Método para incializar una clave de usuario.
    * @param password, clave o contraseña ingresada.
    */							
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getNombreUsuario() {
		if(nombreUsuario == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			logger.info("Obteniendo el nombre de usuario por alias: " + authentication.getName());
			UsuarioDto usuario = usuarioService.getUsuarioPorUsuario(authentication.getName());
			nombreUsuario = usuario.getNombreUsuario();
		}
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public boolean esUsuarioAdministrador(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals(Rol.ADMINISTRADOR.getPerfilRol()) || 
            		auth.getAuthority().equals(Rol.ADMINSTRADOR_GENERAL.getPerfilRol())){
            	return true;
            }
		}
		
		return false;
	}
	
	/**
    * Método para auntentificar el usuario y contraseña y redirigar al menú principal.
    * @return null en caso de que no se encuentre el form de login de Spring security.
    */							
	public String doLogin() throws IOException, ServletException {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		logger.info(">>>Entrando al login usuario "+ username + " pass" + password);

		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();
		
		// It's OK to return null here because Faces is just going to exit.
		return null;

	}

	
	/**
    * Método para auntentificar el usuario y contraseña y redirigar al menú principal.
    * @return null, en caso de que no se encuentre el form de login de Spring security.
    */							
	public String doLogout() throws IOException, ServletException {

		//eliminando del nombre y alias
		this.username = "";
		this.nombreUsuario = null;
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward((ServletRequest) context.getRequest(),(ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();
		return null;

	}
	
}
