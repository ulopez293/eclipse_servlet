package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.NivelAtencionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.NivelAtencionService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class NivelAtencionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7353182314959691785L;

	private Logger log = Logger.getLogger(NivelAtencionBean.class);
	
	private int pagina; // Paginador para la tabla
	private List<NivelAtencionDto> nivelesAtencion; // lista de equipos
	private NivelAtencionDto nivelAtencion;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private NivelAtencionService nivelAtencionService;
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de niveles de atencion.");
		this.nivelesAtencion = nivelAtencionService.getNivelesAtencion();
		estatus = Estatus.values();
		this.pagina = 1;
		log.info("niveles atencion: " + this.nivelesAtencion.size());
		
		return VistasEnum.NIVEL_ATENCION.getIdVista();
	}

	public void limpiarNivelAtencion(){
		this.nivelAtencion = new NivelAtencionDto();
	}

	public void guardarNivelAtencion() {
		
		if (nivelAtencion != null) {
			List<NivelAtencionDto> nivelesAtencionDto = nivelAtencionService.getNivelesAtencionPorDescripcion(nivelAtencion.getDescripcion());
			if(nivelesAtencionDto!=null && nivelesAtencionDto.size()>0){
				alertaBean.setMensaje("No se puede agregar el nivel de atención porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				nivelAtencion.setIdNivelAtencion(0);
				nivelAtencion.setFechaRegistro(new Date());
				
				NivelAtencionDto nivelAtencionDto  = nivelAtencionService.guardarNivelAtencion(nivelAtencion);
				log.info("Guardando el nivel de atencion: " + nivelAtencion.getDescripcion() );
				
				if(nivelAtencionDto !=null){
					alertaBean.setMensaje("El nivel de atención ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					nivelAtencion = null;
					
					nivelesAtencion = nivelAtencionService.getNivelesAtencion(); //Actualizar lista de equipos
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar el nivel de atención.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}
	
	public void actualizarNivelAtencion() {
		if (nivelAtencion != null) {
			List<NivelAtencionDto> nivelesAtencionDto = nivelAtencionService.getNivelesAtencionPorDescripcion(nivelAtencion.getDescripcion(),nivelAtencion.getIdNivelAtencion());
			if(nivelesAtencionDto!=null && nivelesAtencionDto.size()>0){
				alertaBean.setMensaje("No se puede modificar el nivel de atención porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				NivelAtencionDto nivelAtencionDto=nivelAtencionService.getNivelAtencionPorId(nivelAtencion.getIdNivelAtencion());
				nivelAtencion.setBaja(nivelAtencionDto.getBaja());
				nivelAtencion.setDescripcion(nivelAtencionDto.getDescripcion());
			}else{
				nivelAtencion.setFechaModificacion(new Date());
				boolean resultado = nivelAtencionService.actualizarNivelAtencion(nivelAtencion);
				log.info("Actualizando el nivel de atencion: " + nivelAtencion.getIdNivelAtencion() + " resultado: " + resultado);
				
				if(resultado){
					alertaBean.setMensaje("El nivel de atención ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					nivelAtencion = null;
				}else{
					alertaBean.setMensaje("Ocurrio un error al actualizar el nivel de atención.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
	}

	public void eliminarNivelAtencion() {
		if (nivelAtencion != null) {
			
			nivelAtencion.setFechaBaja(new Date());
			nivelAtencion.setBaja(Estatus.INACTIVO);
			boolean resultado = nivelAtencionService.eliminarNivelAtencion(nivelAtencion);
			log.info("Eliminando el nivel de atencion: " + nivelAtencion.getIdNivelAtencion() + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("El nivel de atención ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				nivelAtencion = null;
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el aivel de atención.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}

	public NivelAtencionDto getNivelAtencion() {
		return this.nivelAtencion;
	}

	public void setNivelAtencion(NivelAtencionDto nivelAtencion) {
		this.nivelAtencion = nivelAtencion;
	}
	
	public List<NivelAtencionDto> getNivelesAtencion() {
		return this.nivelesAtencion;
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
