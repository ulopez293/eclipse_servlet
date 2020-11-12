package mx.inntecsa.smem.bean;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UsuarioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.enums.TipoUsuario;
import mx.inntecsa.smem.service.CentroTrabajoService;
import mx.inntecsa.smem.service.EntidadService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.service.UsuarioService;
import mx.inntecsa.smem.util.DownloadFile;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;
//import mx.inntecsa.smem.utils.MD5;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -6873404658271319507L;

	@Autowired
	private UsuarioService usuarioService; // Servicio de usuarios
	
	@Autowired
	private EntidadService entidadService; // Servicio de entidades
	
	@Autowired
	private CentroTrabajoService centroTrabajoService; // Servicio de Centros de trabajo
	
	@Autowired
	private UnidadRegionalService unidadRegionalService; // Servicio de unidades regionales

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	private Logger log = Logger.getLogger(UsuarioBean.class); // Bitacora
	private List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>(); // Lista de Usuarios
	private int pagina; // Paginador para la tabla
	private UsuarioDto usuario; // Usuario
	private Rol[] roles = {Rol.ADMINISTRADOR, Rol.ADMINSTRADOR_GENERAL, Rol.UNIDAD_TRABAJO}; // Lista de roles
	private List<EntidadDto> entidades; // Lista de tipos de Entidades
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales
	private List<CentroTrabajoDto> centrosTrabajo; // Lista de tipos de centros trabajo
	private Estatus[] estatus; // Lista de estatus
	private boolean campoLectura; // para habilitar como lectura el usuario y nombre usuario
	private boolean cambiarContrasenia;

	public List<UsuarioDto> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDto> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Rol[] getRoles() {
		return roles;
	}

	public void setRoles(Rol[] roles) {
		this.roles = roles;
	}

	public List<EntidadDto> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadDto> entidades) {
		this.entidades = entidades;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
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

	public boolean isCampoLectura() {
		return campoLectura;
	}

	public void setCampoLectura(boolean campoLectura) {
		this.campoLectura = campoLectura;
	}

	public Estatus[] getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus[] estatus) {
		this.estatus = estatus;
	}
	
	public void limpiarUsuario() {
		this.usuario = new UsuarioDto();
	}
	
	public void buscarEntidades() {
		log.info(">>>Buscando Entidades, con rol " + usuario.getRol());
		this.usuario.setCentroTrabajo(new CentroTrabajoDto());
		this.usuario.setUsuario("");
		this.usuario.setNombreUsuario("");		
		
		//Si el rol es de oficinas centrales, buscaremos el centro de trabajo
		if(!usuario.getRol().equals(Rol.UNIDAD_TRABAJO)) {
			CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajoPorDescripcion("OFICINAS CENTRALES");
			usuario.setCentroTrabajo(centroTrabajoService.getCentroTrabajoPorDescripcion("OFICINAS CENTRALES"));
			entidades = new ArrayList<EntidadDto>();
			entidades.add(centroTrabajo.getUnidadRegional().getEntidad());
			unidadesRegionales = new ArrayList<UnidadRegionalDto>();
			unidadesRegionales.add(centroTrabajo.getUnidadRegional());
			centrosTrabajo = new ArrayList<CentroTrabajoDto>();
			centrosTrabajo.add(centroTrabajo);
			campoLectura = false;
			//usuario.setCentroTrabajo(centroTrabajo);
			
		} else {
			entidades = entidadService.getEntidadesPorEstatus(Estatus.ACTIVO);
			//buscando las unidades regionales de la primera entidad y centros de trabajo
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesPorIdEntidad(entidades.get(0).getIdEntidad());
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
			//llenando campos
			usuario.setUsuario(centrosTrabajo.get(0).getUrct());
			usuario.setNombreUsuario(centrosTrabajo.get(0).getDescripcion());
			campoLectura = true;			
			//usuario.setCentroTrabajo(centrosTrabajo.get(0));			
		}		
	}
	
	public void buscarUnidadesRegionales() {
		log.info(">>>Buscando Unidades >>> " + usuario.getCentroTrabajo().getUnidadRegional().getEntidad().getIdEntidad());		
		unidadesRegionales = unidadRegionalService.getUnidadesRegionalesPorIdEntidad(usuario.getCentroTrabajo().getUnidadRegional().getEntidad().getIdEntidad());
		centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(unidadesRegionales.get(0).getIdUnidadRegional());
		
		if(!centrosTrabajo.isEmpty()) {
			usuario.setUsuario(centrosTrabajo.get(0).getUrct());
			usuario.setNombreUsuario(centrosTrabajo.get(0).getDescripcion());
			
		} else {	
			usuario.setUsuario("");
			usuario.setNombreUsuario("");
		}			
	}
	
	public void buscarCentrosTrabajo() {
		log.info(">>>Buscando CT >>> " + usuario.getCentroTrabajo().getUnidadRegional().getIdUnidadRegional());
		centrosTrabajo = centroTrabajoService.getCentrosTrabajoPoridUnidadRegional(usuario.getCentroTrabajo().getUnidadRegional().getIdUnidadRegional());
		
		if(!centrosTrabajo.isEmpty()) {
			usuario.setUsuario(centrosTrabajo.get(0).getUrct());
			usuario.setNombreUsuario(centrosTrabajo.get(0).getDescripcion());
			//usuario.setCentroTrabajo(centrosTrabajo.get(0));
			
		} else {
			usuario.setUsuario("");
			usuario.setNombreUsuario("");
		}
	}
	
	public void generaNombreUsuario() {
	
		if(usuario.getRol().equals(Rol.UNIDAD_TRABAJO)) {
			//Voy por el centro de trabajo
			CentroTrabajoDto centroTrabajo = centroTrabajoService.getCentroTrabajo(usuario.getCentroTrabajo().getIdCentroTrabajo());
			usuario.setUsuario(centroTrabajo.getUrct()); 
			usuario.setNombreUsuario(centroTrabajo.getDescripcion());
		}
	}

	public void agregarUsuario() {
		log.info(">>>Guardando Usuario: " + usuario.getUsuario());
		log.info(">>>Con centro de trabajo: " + usuario.getCentroTrabajo().getIdCentroTrabajo());
		
		if (validaUsuario() && (usuario != null)) {
			usuario.setBaja(Estatus.ACTIVO);
			usuario.setFechaRegistro(new Date());
			usuario.setTipoUsuario(TipoUsuario.ISSSTE);
			usuario.setIdCentroTrabajo(usuario.getCentroTrabajo().getIdCentroTrabajo());
			//usuario.setContrasenia(MD5.getMD5(usuario.getContrasenia()));		
			usuario.setContrasenia(usuario.getContrasenia());
			
			UsuarioDto usuarioDto = usuarioService.guardarUsuario(usuario);
			
			if(usuarioDto != null) {
				alertaBean.setMensaje("El Usuario ha sido actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuario = null;
				usuarios = usuarioService.getUsuariosPorTipoUsuario(TipoUsuario.ISSSTE);
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Usuario.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}		
	}
	
	public void editarUsuario() {
		log.info(">>>Editando Usuario:" + usuario.getUsuario());
		
		if (usuario != null) {
			usuario.setFechaModificacion(new Date());
			
			if(cambiarContrasenia) {
				//usuario.setContrasenia(MD5.getMD5(usuario.getContrasenia()));				
				usuario.setContrasenia(usuario.getContrasenia());
			}
			
			if(usuarioService.actualizarUsuario(usuario)) {
				alertaBean.setMensaje("El Usuario ha sido dado actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuario = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Usuario.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	
	
	public void eliminarUsuario() {
		log.info(">>>Eliminando Usuario: "+ usuario.getUsuario());
		
		if (usuario != null) {
			usuario.setFechaBaja(new Date());
			usuario.setBaja(Estatus.INACTIVO);
			
			if(usuarioService.eliminarUsuario(usuario)) {
				alertaBean.setMensaje("El Usuario ha sido eliminado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				usuario = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de eliminar el Usuario.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	
	
	public String inicializar() {
		this.pagina = 1;
		usuario = new UsuarioDto();
		estatus = Estatus.values();
		usuarios = usuarioService.getUsuariosPorTipoUsuario(TipoUsuario.ISSSTE);
		return VistasEnum.USUARIOS.getIdVista();		
	}
	
	//Si algun día encuentra como validar un campo disabled, por favor ilumineme
		//para poder quitar esta validacion del bean
		public boolean validaUsuario() {
			log.info(">>>Validando Usuario " + usuario.getUsuario());
			if (usuarioService.getUsuarioPorUsuario(usuario.getUsuario()) != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage("El usuario ya existe.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage("modal_agregar_usuarios:formAgregarUsuarios:usuario", msg);
				return false;
			}	
			
			return true;
		}
		
		public void descargarReporteSMEM() {
			log.info(">>>Descargando reporte de usuarios activos");	
			List<String> encabezado = new ArrayList<String>(Arrays.asList("USUARIO", "CONTRASEÑA", "URCT",	
					"NOMBRE URCT", "ENTIDAD", "UNIDAD REGIONAL"));
			List<UsuarioDto> usuarios = usuarioService.getUsuariosSMEMParaReporte();
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				DownloadFile downloadFile = new DownloadFile("usuarios_SMEM.xls",encabezado);
				downloadFile.write(getDatosReporteSMEM(usuarios));
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition", "attachment; filename=\"" + "usuarios_SMEM.xls" + "\"");
				OutputStream os = response.getOutputStream();
				os.write(downloadFile.getByteFile());
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				log.error(">>>Error : " , e);
			}
		}
		
		private List<List<String>> getDatosReporteSMEM(List<UsuarioDto> usuarios) {
			List<List<String>> datosUsuarios = new ArrayList<List<String>>();
			for (UsuarioDto usuarioDto : usuarios) {
				List<String> listaDatosUsuario = new ArrayList<String>();
				listaDatosUsuario.add(usuarioDto.getUsuario());
				listaDatosUsuario.add(usuarioDto.getContrasenia());
				listaDatosUsuario.add(usuarioDto.getCentroTrabajo().getUrct());
				listaDatosUsuario.add(usuarioDto.getCentroTrabajo().getDescripcion());
				listaDatosUsuario.add(usuarioDto.getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
				listaDatosUsuario.add(usuarioDto.getCentroTrabajo().getUnidadRegional().getUnidadRegional());
				datosUsuarios.add(listaDatosUsuario);
			}		
			return datosUsuarios;
		}
		
		public void descargarReporteProveedores() {
			log.info(">>>Descargando reporte de proveedores activos");	
			List<String> encabezado = new ArrayList<String>(Arrays.asList("PROVEEDOR", "USUARIO", "CONTRASEÑA"));
			List<UsuarioDto> usuarios = usuarioService.getUsuariosProveedoresParaReporte();
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				DownloadFile downloadFile = new DownloadFile("usuarios_proveedores.xls",encabezado);
				downloadFile.write(getDatosReporteProveedores(usuarios));
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition", "attachment; filename=\"" + "usuarios_proveedores.xls" + "\"");
				OutputStream os = response.getOutputStream();
				os.write(downloadFile.getByteFile());
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				log.error(">>>Error : " , e);
			}
		}
		
		private List<List<String>> getDatosReporteProveedores(List<UsuarioDto> usuarios) {
			List<List<String>> datosUsuarios = new ArrayList<List<String>>();
			for (UsuarioDto usuarioDto : usuarios) {
				List<String> listaDatosUsuario = new ArrayList<String>();
				listaDatosUsuario.add(usuarioDto.getProveedor().getProveedor());
				listaDatosUsuario.add(usuarioDto.getUsuario());
				listaDatosUsuario.add(usuarioDto.getContrasenia());
				datosUsuarios.add(listaDatosUsuario);
			}		
			return datosUsuarios;
		}
		
		public boolean isCambiarContrasenia() {
			return cambiarContrasenia;
		}

		public void setCambiarContrasenia(boolean cambiarContrasenia) {
			this.cambiarContrasenia = cambiarContrasenia;
		}
		
		public void inicializaContrasenia() {
			cambiarContrasenia = false;
		}
		
		public void muestraCampos() {
			if(cambiarContrasenia) {
				usuario.setContrasenia("");
				usuario.setConfirmarContrasenia("");
				
			}else{
				usuario.setContrasenia(usuario.getContraseniaAuxiliar());
				usuario.setConfirmarContrasenia(usuario.getContraseniaAuxiliar());
			}
		}
}
