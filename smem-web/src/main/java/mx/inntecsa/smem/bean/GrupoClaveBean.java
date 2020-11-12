package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.dto.GrupoClaveDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.EquipoService;
import mx.inntecsa.smem.service.GrupoClaveService;
import mx.inntecsa.smem.service.GrupoService;
import mx.inntecsa.smem.service.ClaveService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Component
@Scope("session")
public class GrupoClaveBean implements Serializable{

	private Logger log = Logger.getLogger(GrupoClaveBean.class);
	private static final long serialVersionUID = -3789246001873094263L;
	
	private int pagina; // Paginador para la tabla
	private List<GrupoClaveDto> gruposClave=new ArrayList<GrupoClaveDto>(); // lista de grupo claves
	private GrupoClaveDto grupoClave;
	private List<GrupoDto> grupos;
	private List<ClaveDto> claves;
	private List<EquipoDto> equipos;
	private Estatus []estatus;

	@Autowired	
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private GrupoClaveService grupoClaveService;
	
	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ClaveService claveService;
	
	@Autowired
	private EquipoService equipoService;

	public String inicializar(){
		grupoClave=new GrupoClaveDto();
		log.info("Metodo inicializar. Obteniendo la lista de Grupos Clave.");
		grupos = grupoService.getGruposActivos();
		System.out.println(">>>>>>>>>>>>>>>> Grupos activos"); 
		claves = claveService.getClavesActivas();
		System.out.println(">>>>>>>>>>>>>>>>> Claves activas");
		equipos = equipoService.getEquiposActivos();
		System.out.println(">>>>>>>>>>>>>>>>> Equipos activos");
		gruposClave = grupoClaveService.getGruposClaves();
		System.out.println(">>>>>>>>>>>>>>>>> Grupos claves");
		estatus = Estatus.values();
		this.pagina = 1;
		return VistasEnum.GRUPO_CLAVE.getIdVista();
	}

	public void limpiarGrupoClave(){
		grupoClave = new GrupoClaveDto();
	}

	public void guardarGrupoClave() {
		if (grupoClave != null) {
			System.out.println("*****Se buscara que el registro no exista para poder guardarlo");
			List<GrupoClaveDto> gruposClaveAux = grupoClaveService.getGruposClavesPorParametros(grupoClave.getGrupo().getIdGrupo(), grupoClave.getClave().getIdClave(), grupoClave.getEquipo().getIdEquipo());
			if(gruposClaveAux!=null && gruposClaveAux.size()>0){
				alertaBean.setMensaje("No se puede agregar el grupo clave porque ya existe uno con el mismo grupo, clave y equipo");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				grupoClave.setIdGrupoClave(0);
				grupoClave.setFechaRegistro(new Date());
				GrupoClaveDto grupoClaveDto = grupoClaveService.guardarGrupoClave(grupoClave);
				log.info("Guaradando el grupo clave: " + grupoClave.getIdGrupoClave());
				if(grupoClaveDto !=null){
					alertaBean.setMensaje("El grupo clave ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					grupoClave = null;
					gruposClave = grupoClaveService.getGruposClaves(); //Actualizar lista de grupos claves
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar el grupo clave.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
	}
	
	public void actualizarGrupoClave() {
		if (grupoClave != null) {
			System.out.println("*****Se buscara que el registro no exista para poder modificarlo");
			List<GrupoClaveDto> gruposClaveAux = grupoClaveService.getGruposClavesPorParametros(grupoClave.getGrupo().getIdGrupo(), grupoClave.getClave().getIdClave(), grupoClave.getEquipo().getIdEquipo(),grupoClave.getIdGrupoClave());
			if(gruposClaveAux!=null && gruposClaveAux.size()>0){
				alertaBean.setMensaje("No se puede modificar el grupo clave porque ya existe uno con el mismo grupo, clave y equipo");
				alertaBean.setImagen(Icono.WARNING);
				GrupoClaveDto grupoClaveAux=grupoClaveService.getGrupoClavePorId(grupoClave.getIdGrupoClave());
				grupoClave.setGrupo(grupoClaveAux.getGrupo());
				grupoClave.setClave(grupoClaveAux.getClave());
				grupoClave.setEquipo(grupoClaveAux.getEquipo());
				grupoClave.setBaja(grupoClaveAux.getBaja());
			}else{
				grupoClave.setFechaModificacion(new Date());
				boolean resultado = grupoClaveService.actualizarGrupoClave(grupoClave);
				log.info("Actualizando el grupo clave: " + grupoClave.getIdGrupoClave() + " resultado: " + resultado);
				if(resultado){
					alertaBean.setMensaje("El grupo clave ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					grupoClave = null;	
					gruposClave = grupoClaveService.getGruposClaves();
				}else{
					alertaBean.setMensaje("Ocurrió un error al actualizar el grupo clave.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
	}

	public void eliminarGrupoClave() {
		if (grupoClave != null) {
			grupoClave.setFechaBaja(new Date());
			grupoClave.setBaja(Estatus.INACTIVO);
			boolean resultado = grupoClaveService.eliminarGrupoClave(grupoClave);
			log.info("Eliminando el grupo clave: " + grupoClave.getIdGrupoClave() + " resultado: " + resultado);
			if(resultado){
				alertaBean.setMensaje("El grupo clave ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				grupoClave = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el grupo clave.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}
	

	public GrupoClaveDto getGrupoClave() {
		return this.grupoClave;
	}

	public void setGrupoClave(GrupoClaveDto grupoClave) {
		this.grupoClave = grupoClave;
	}

	public List<GrupoClaveDto> getGruposClave() {
		return gruposClave;
	}
	
	public void setGruposClave(List<GrupoClaveDto> gruposClave) {
		this.gruposClave = gruposClave;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public Estatus[] getEstatus() {
		return estatus;
	}

	public List<GrupoDto> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoDto> grupos) {
		this.grupos = grupos;
	}

	public List<ClaveDto> getClaves() {
		return claves;
	}

	public void setClaves(List<ClaveDto> claves) {
		this.claves = claves;
	}
	
	public List<EquipoDto> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoDto> equipos) {
		this.equipos = equipos;
	}
	
}
