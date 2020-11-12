package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.TipoContratacionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.TipoContratacion;
import mx.inntecsa.smem.service.TipoContratacionService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class TipoContratacionBean implements Serializable{

	private Logger log = Logger.getLogger(TipoContratacionBean.class);
	private static final long serialVersionUID = -3789246001873096424L;
	
	private int pagina; // Paginador para la tabla
	private List<TipoContratacionDto> tiposContratacion; // lista de Tipos de Contrataciones
	private TipoContratacionDto tipoContratacion;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private TipoContratacionService tipoContratacionService;
	public String inicializar(){
		
		log.info("Inicializ TipoContratacion 0***");
		tiposContratacion = tipoContratacionService.getTiposContratacion();
		estatus = Estatus.values();
		this.pagina = 1;
		
		return VistasEnum.TIPO_CONTRATACION.getIdVista();
	}

	public void limpiarTipoContratacion(){
		tipoContratacion = new TipoContratacionDto();
	}
	
	public void guardarTipoContratacion() {
				
		  if (tipoContratacion!= null) { 
			  
			   List<TipoContratacionDto> SubAux = tipoContratacionService.getTiposContratacionesPorNombre(tipoContratacion.getTipoContratacion());
			    if(SubAux!=null &&SubAux.size()>0){
				 alertaBean.setMensaje("No se puede agregar Tipo porque ya existe uno con el mismo nombre");
			     alertaBean.setImagen(Icono.WARNING);
			     		     
			     }else{
			    
		    	tipoContratacion.setIdTipoContratacion(0);
			    tipoContratacion.setFechaRegistro(new Date());
 		   TipoContratacionDto tipoContratacionDto = tipoContratacionService.guardarTipoContratacion(tipoContratacion);
			   
			  if(tipoContratacionDto !=null){
				
				alertaBean.setMensaje("El tipo contratación ha sido guardado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				tipoContratacion = null;
				tiposContratacion = tipoContratacionService.getTiposContratacion();
			  }else{
				alertaBean.setMensaje("Ocurrio un error al guardar el tipo contratación.");
				alertaBean.setImagen(Icono.ERROR);
			 }
		  }
       }
	}
  	
	public void actualizarTipoContratacion() {
		
		if (tipoContratacion != null) {
			
			List<TipoContratacionDto> SubAux = tipoContratacionService.getTiposContratacionesPorNombre(tipoContratacion.getTipoContratacion(), tipoContratacion.getIdTipoContratacion());
		    if(SubAux!=null&&SubAux.size()>0){
		    	alertaBean.setMensaje("No se puede modificar el Tipo de contrato porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			
				   TipoContratacion tipoAux = tipoContratacionService.getTiposContratacionPorId(tipoContratacion.getIdTipoContratacion());
				   tipoContratacion.setIdTipoContratacion(tipoAux.getIdTipoContratacion());
				   tipoContratacion.setBaja(tipoAux.getBaja());
				   tipoContratacion.setTipoContratacion(tipoAux.getTipoContratacion());
		 					
		    }else{
			tipoContratacion.setFechaModificacion(new Date());
			if(tipoContratacion.getIdTipoContratacion()>0){
			   boolean resultado = tipoContratacionService.actualizarTipoContratacion(tipoContratacion);
			   log.info("Actualizando el tipo de contratacion: " + tipoContratacion.getIdTipoContratacion() + " resultado: " + resultado);
			   
			if(resultado){
				alertaBean.setMensaje("El tipo de contratación ha sido actualizado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				tipoContratacion = null;					
			}else{
				alertaBean.setMensaje("Ocurrió un error al actualizar el tipo de contratación.");
				alertaBean.setImagen(Icono.ERROR);
		     }
	      }
	   }
    }
 }
	
	public void eliminarTipoContratacion() {
		log.info("Eliminando el tipo de contrataión antes de entrar: " + tipoContratacion);
		if (tipoContratacion != null) {
			
			tipoContratacion.setFechaBaja(new Date());
			tipoContratacion.setBaja(Estatus.INACTIVO);
			boolean resultado = tipoContratacionService.eliminarTipoContratacion(tipoContratacion);
			log.info("Eliminando el tipo de contrataión: " + tipoContratacion.getIdTipoContratacion() + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("El tipo de contratación ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				tipoContratacion = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el tipo contratación.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}		
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

	public void setTiposContratacion(List<TipoContratacionDto> tiposContratacion) {
		this.tiposContratacion = tiposContratacion;
	}	
	public List<TipoContratacionDto> getTiposContratacion() {
		return tiposContratacion;
	}
		
	public TipoContratacionDto getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(TipoContratacionDto tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}
	
}
