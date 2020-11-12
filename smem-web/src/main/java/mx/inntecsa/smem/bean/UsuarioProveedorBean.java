
package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UsuarioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.enums.TipoUsuario;
import mx.inntecsa.smem.service.ProveedorService;
import mx.inntecsa.smem.service.UsuarioService;
import mx.inntecsa.smem.utils.MD5;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class UsuarioProveedorBean implements Serializable {


	private static final long serialVersionUID = -7050282645917280721L;

	@Autowired
	private UsuarioService usuarioService; // Servicio de usuarioProveedorsProveedor
		
	@Autowired
	private ProveedorService proveedorService; // Servicio de Centros de trabajo

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuarioProveedor
	
	private Logger log = Logger.getLogger(UsuarioProveedorBean.class); // Bitacora
	private List<UsuarioDto> usuariosProveedor = new ArrayList<UsuarioDto>(); // Lista de UsuarioProveedores
	private int pagina; // Paginador para la tabla
	private UsuarioDto usuarioProveedor; // UsuarioProveedor
	private Rol[] roles = {Rol.ADMINISTRADOR, Rol.ADMINSTRADOR_GENERAL, Rol.UNIDAD_TRABAJO}; // Lista de roles
	private List<ProveedorDto> proveedores; // Lista de proveedores
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales
	private List<CentroTrabajoDto> centrosTrabajo; // Lista de tipos de centros trabajo
	private Estatus[] estatus; // Lista de estatus	
	private boolean cambiarContrasenia;

	
	public List<UsuarioDto> getUsuariosProveedor() {
		return usuariosProveedor;
	}

	public void setUsuariosProveedor(List<UsuarioDto> usuariosProveedor) {
		this.usuariosProveedor = usuariosProveedor;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public UsuarioDto getUsuarioProveedor() {
		return usuarioProveedor;
	}

	public void setUsuarioProveedor(UsuarioDto usuarioProveedor) {
		this.usuarioProveedor = usuarioProveedor;
	}

	public Rol[] getRoles() {
		return roles;
	}

	public void setRoles(Rol[] roles) {
		this.roles = roles;
	}

	public List<ProveedorDto> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorDto> proveedores) {
		this.proveedores = proveedores;
	}

	public List<UnidadRegionalDto> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	public void setUnidadesRegionales(List<UnidadRegionalDto> unidadesRegionales) {
		this.unidadesRegionales = unidadesRegionales;
	}

	public List<CentroTrabajoDto> getCentrosTrabajo() {
		return centrosTrabajo;
	}

	public void setCentrosTrabajo(List<CentroTrabajoDto> centrosTrabajo) {
		this.centrosTrabajo = centrosTrabajo;
	}

	public Estatus[] getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus[] estatus) {
		this.estatus = estatus;
	}	

	public boolean isCambiarContrasenia() {
		return cambiarContrasenia;
	}

	public void setCambiarContrasenia(boolean cambiarContrasenia) {
		this.cambiarContrasenia = cambiarContrasenia;
	}

	public void limpiarUsuarioProveedor() {
		this.usuarioProveedor = new UsuarioDto();
	}
	
	public void inicializaContrasenia() {
		cambiarContrasenia = false;
	}
	
	public String inicializar() {		
		this.pagina = 1;
		estatus = Estatus.values();		
		usuarioProveedor = new UsuarioDto();
		proveedores = proveedorService.getProveedoresByEstatus(Estatus.ACTIVO);
		usuariosProveedor = usuarioService.getUsuariosPorTipoUsuario(TipoUsuario.PROVEEDOR);
		return VistasEnum.USUARIOS_PROVEEDOR.getIdVista();		
	}
	
	public void generaNombreUsuarioProveedor() {
		//Voy por el proveedor
		ProveedorDto proveedor = proveedorService.getProveedorPorIdProveedor(usuarioProveedor.getProveedor().getIdProveedor());
		usuarioProveedor.setUsuario(proveedor.getRfc()); 
		usuarioProveedor.setNombreUsuario(proveedor.getProveedor());	
	}

	public void agregarUsuarioProveedor() {
		log.info(">>>Guardando Usuario Proveedor: " + usuarioProveedor.getUsuario());
		
		if (validaUsuario() && (usuarioProveedor != null)) {
			usuarioProveedor.setRol(Rol.PROVEEDOR);
			usuarioProveedor.setBaja(Estatus.ACTIVO);
			usuarioProveedor.setFechaRegistro(new Date());			
			usuarioProveedor.setTipoUsuario(TipoUsuario.PROVEEDOR);
			usuarioProveedor.setIdProveedor(usuarioProveedor.getProveedor().getIdProveedor());
			
			//cambio de contrasenia a MD5
			usuarioProveedor.setContrasenia(MD5.getMD5(usuarioProveedor.getContrasenia()));			
			UsuarioDto usuarioProveedorDto = usuarioService.guardarUsuario(usuarioProveedor);
			
			if(usuarioProveedorDto != null) {
				alertaBean.setMensaje("El Usuario Proveedor ha sido actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuariosProveedor = usuarioService.getUsuariosPorTipoUsuario(TipoUsuario.PROVEEDOR);
				usuarioProveedor = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el UsuarioProveedor.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}		
	}
	
	public void editarUsuarioProveedor() {
		log.info(">>>Editando Usuario Proveedor:" + usuarioProveedor.getUsuario());
		
		if (usuarioProveedor != null) {
			usuarioProveedor.setFechaModificacion(new Date());
			
			if(cambiarContrasenia) {
				//cambio de contrasenia a MD5
				usuarioProveedor.setContrasenia(MD5.getMD5(usuarioProveedor.getContrasenia()));				
			}
			
			if(usuarioService.actualizarUsuario(usuarioProveedor)) {
				alertaBean.setMensaje("El UsuarioProveedor ha sido dado actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuarioProveedor = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Usuario Proveedor.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	
	
	public void eliminarUsuarioProveedor() {
		log.info(">>>Eliminando Usuario Proveedor: "+ usuarioProveedor.getUsuario());
		
		if (usuarioProveedor != null) {
			usuarioProveedor.setFechaBaja(new Date());
			usuarioProveedor.setBaja(Estatus.INACTIVO);
			
			if(usuarioService.eliminarUsuario(usuarioProveedor)) {
				alertaBean.setMensaje("El Usuario Proveedor ha sido dado de alta de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuarioProveedor = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de guardar el Usuario Proveedor.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	
		
	//Si algun día encuentra como validar un campo disabled, por favor ilumineme
	//para poder quitar esta validacion del bean
	public boolean validaUsuario() {
		log.info(">>>Validando Usuario " + usuarioProveedor.getUsuario());
		if (usuarioService.getUsuarioPorUsuario(usuarioProveedor.getUsuario()) != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("El usuario ya existe.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("modal_agregar_usuarios_proveedor:formAgregarUsuariosProveedor:usuario", msg);
			return false;
		}	
		
		return true;
	}
	
	public void muestraCampos() {
		//asignar valores a las contrasenias, dependiendo del valor del checkbox
		if(cambiarContrasenia) {
			log.info(">>> En cambiar contrasenia es verdadero");
			usuarioProveedor.setContrasenia("");
			usuarioProveedor.setConfirmarContrasenia("");
			
		} else {
			log.info(">>>en cambiar contrasenia,  Es falso");
			usuarioProveedor.setContrasenia(usuarioProveedor.getContraseniaAuxiliar());
			usuarioProveedor.setConfirmarContrasenia(usuarioProveedor.getContraseniaAuxiliar());
		}
	}	

}
