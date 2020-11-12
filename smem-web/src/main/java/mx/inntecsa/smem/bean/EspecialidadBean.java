package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.EspecialidadDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.EspecialidadService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class EspecialidadBean implements Serializable{

	private Logger log = Logger.getLogger(EspecialidadBean.class);
	private static final long serialVersionUID = -3789246001873034910L;
	
	private int pagina; // Paginador para la tabla
	private List<EspecialidadDto> especialidades; // lista de especialidades
	private EspecialidadDto especialidad;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private EspecialidadService especialidadService;
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de especialidades.");
		especialidades = especialidadService.getEspecialidades();
		estatus = Estatus.values();
		this.pagina = 1;
		
		return VistasEnum.ESPECIALIDAD.getIdVista();
	}

	public void limpiarEspecialidad(){
		especialidad = new EspecialidadDto();
	}

	public void guardarEspecialidad() {
		
		if (especialidad != null) {
			List<EspecialidadDto> especialidadesAux = especialidadService.getEspecialidadesPorNombre(especialidad.getEspecialidad());
			if(especialidadesAux!=null && especialidadesAux.size()>0){
				alertaBean.setMensaje("No se puede agregar la especialidad porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				especialidad.setIdEspecialidad(0);
				especialidad.setFechaRegistro(new Date());
				EspecialidadDto especialidadDto  = especialidadService.guardarEspecialidad(especialidad);
				log.info("Guardando la especialidad: " + especialidad.getEspecialidad() );
				if(especialidadDto !=null){
					alertaBean.setMensaje("La especialidad ha sido guardada correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					especialidad = null;
					
					especialidades = especialidadService.getEspecialidades(); //Actualizar lista de especialidades
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar la especialidad.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}
	
	public void actualizarEspecialidad() {
		if (especialidad != null) {
			List<EspecialidadDto> especialidadesAux = especialidadService.getEspecialidadesPorNombre(especialidad.getEspecialidad(),especialidad.getIdEspecialidad());
			if(especialidadesAux!=null && especialidadesAux.size()>0){
				alertaBean.setMensaje("No se puede modificar la especialidad porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				EspecialidadDto especialidadAux=especialidadService.getEspecialidadPorId(especialidad.getIdEspecialidad());
				especialidad.setEspecialidad(especialidadAux.getEspecialidad());
				especialidad.setBaja(especialidadAux.getBaja());
			}else{
				especialidad.setFechaModificacion(new Date());
				boolean resultado = especialidadService.actualizarEspecialidad(especialidad);
				log.info("Actualizando la especialidad: " + especialidad.getIdEspecialidad() + " resultado: " + resultado);
				
				if(resultado){
					alertaBean.setMensaje("La especialidad ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					especialidad = null;
				}else{
					alertaBean.setMensaje("Ocurrio un error al actualizar la especialidad.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
	}

	public void eliminarEspecialidad() {
		if (especialidad != null) {
			
			especialidad.setFechaBaja(new Date());
			especialidad.setBaja(Estatus.INACTIVO);
			boolean resultado = especialidadService.eliminarEspecialidad(especialidad);
			log.info("Eliminando la especialidad: " + especialidad.getIdEspecialidad() + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("La especialidad ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				especialidad = null;
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar la especialidad.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}

	public EspecialidadDto getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(EspecialidadDto especialidad) {
		this.especialidad = especialidad;
	}


	public List<EspecialidadDto> getEspecialidades() {
		return this.especialidades;
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
