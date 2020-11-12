package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.EquipoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.EquipoService;
import mx.inntecsa.smem.service.GrupoClaveService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class EquipoBean implements Serializable{

	private Logger log = Logger.getLogger(EquipoBean.class);
	private static final long serialVersionUID = -3789246001873034910L;
	
	private int pagina; // Paginador para la tabla
	private List<EquipoDto> equipos; // lista de equipos
	private EquipoDto equipo;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private EquipoService equipoService;

	@Autowired
	private GrupoClaveService grupoClaveService;
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de equipos.");
		equipos = equipoService.getEquipos();
		estatus = Estatus.values();
		this.pagina = 1;
		
		return VistasEnum.EQUIPOS.getIdVista();
	}

	public void limpiarEquipo(){
		equipo = new EquipoDto();
	}

	public void guardarEquipo() {
		
		if (equipo != null) {
			equipo.setIdEquipo(0);
			equipo.setFechaRegistro(new Date());
			
			EquipoDto equipoDto  = equipoService.guardarEquipo(equipo);
			log.info("Guardando el equipo: " + equipo.getEquipo() );
			
			if(equipoDto !=null){
				alertaBean.setMensaje("El equipo ha sido guardado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				equipo = null;
				
				equipos = equipoService.getEquipos(); //Actualizar lista de equipos
			}else{
				alertaBean.setMensaje("Ocurrio un error al guardar el equipo.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
		
	}
	
	public void actualizarEquipo() {
		
		if (equipo != null) {
			
			equipo.setFechaModificacion(new Date());
			boolean resultado = equipoService.actualizarEquipo(equipo);
			log.info("Actualizando el equipo: " + equipo.getIdEquipo() + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("El equipo ha sido actualizado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				equipo = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al actualizar el equipo.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
		
	}

	public void eliminarEquipo() {
		
		int idEquipo=equipo.getIdEquipo();
		 
		if(grupoClaveService.getGruposClavesPorIdEquipo(idEquipo).isEmpty()){
			equipo.setFechaBaja(new Date());
			equipo.setBaja(Estatus.INACTIVO);
			boolean resultado = equipoService.eliminarEquipo(equipo);
			log.info("Eliminando el equipo: " + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("El equipo ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				equipo = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el equipo.");
				alertaBean.setImagen(Icono.ERROR);
			}			
			
		}
		else{
			 alertaBean.setMensaje("Equipo ligado a Grupo-Clave, No se puede borrar el registro.");		   
			 alertaBean.setImagen(Icono.ERROR);
		}
		
	}
	
	public EquipoDto getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoDto equipo) {
		this.equipo = equipo;
	}


	public List<EquipoDto> getEquipos() {
		return equipos;
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
