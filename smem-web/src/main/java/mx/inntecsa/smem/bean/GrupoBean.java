package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.GrupoClaveService;
import mx.inntecsa.smem.service.GrupoService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class GrupoBean implements Serializable{

	private Logger log = Logger.getLogger(GrupoBean.class);
	private static final long serialVersionUID = -3789246001873096564L;
	
	private int pagina; // Paginador para la tabla
	private List<GrupoDto> grupos; // lista de grupos
	private GrupoDto grupo;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoClaveService grupoClaveService;

	public String inicializar(){
		log.info("Metodo inicializar. Obteniendo la lista de grupos.");
		grupos = grupoService.getGrupos();
		estatus = Estatus.values();
		this.pagina = 1;
		return VistasEnum.GRUPO.getIdVista();
	}

	public void limpiarGrupo(){
		grupo = new GrupoDto();
	}

	public void guardarGrupo() {
		if (grupo != null) {
			List<GrupoDto> gruposAux = grupoService.getGruposPorNombre(grupo.getGrupo());
			if(gruposAux!=null && gruposAux.size()>0){
				alertaBean.setMensaje("No se puede agregar el grupo porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				grupo.setIdGrupo(0);
				grupo.setFechaRegistro(new Date());
				GrupoDto grupoDto = grupoService.guardarGrupo(grupo);
				if(grupoDto !=null){
					alertaBean.setMensaje("El grupo ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					grupo = null;
					grupos = grupoService.getGrupos(); //Actualizar lista de grupos
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar el grupo.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}
	
	public void actualizarGrupo() {
		if (grupo != null) {
			List<GrupoDto> gruposAux = grupoService.getGruposPorNombre(grupo.getGrupo(),grupo.getIdGrupo());
			if(gruposAux!=null && gruposAux.size()>0){
				alertaBean.setMensaje("No se puede modificar el grupo porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				GrupoDto grupoAux=grupoService.getGrupoPorId(grupo.getIdGrupo());
				grupo.setGrupo(grupoAux.getGrupo());
				grupo.setBaja(grupoAux.getBaja());
			}else{
				grupo.setFechaModificacion(new Date());
				boolean resultado = grupoService.actualizarGrupo(grupo);
				log.info("Actualizando el grupo: " + grupo.getIdGrupo() + " resultado: " + resultado);
				if(resultado){
					alertaBean.setMensaje("El grupo ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					grupo = null;					
				}else{
					alertaBean.setMensaje("Ocurrió un error al actualizar el grupo.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}

	public void eliminarGrupo() {
		if (grupo != null) {
			if(grupoClaveService.getGruposClavesPorIdGrupo(grupo.getIdGrupo()).isEmpty()){
				grupo.setFechaBaja(new Date());
				grupo.setBaja(Estatus.INACTIVO);
				boolean resultado = grupoService.eliminarGrupo(grupo);
				log.info("Eliminando el grupo: " + grupo.getIdGrupo() + " resultado: " + resultado);
				if(resultado){
					alertaBean.setMensaje("El grupo ha sido eliminado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					grupo = null;					
				}else{
					alertaBean.setMensaje("Ocurrio un error al eliminar el grupo.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}else{
				alertaBean.setMensaje("Grupo ligado a Grupo-Clave, No se puede borrar el registro.");		   
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}
	

	public GrupoDto getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoDto grupo) {
		this.grupo = grupo;
	}

	public List<GrupoDto> getGrupos() {
		return grupos;
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
	
}
