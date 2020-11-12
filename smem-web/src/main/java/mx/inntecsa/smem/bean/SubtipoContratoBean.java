package mx.inntecsa.smem.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.SubtipoContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SubtipoContrato;
import mx.inntecsa.smem.service.SubtipoContratoService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class SubtipoContratoBean implements Serializable{

	private Logger log = Logger.getLogger(SubtipoContratoBean.class);
	private static final long serialVersionUID = -3789246001873034910L;
	
	private int pagina; // Paginador para la tabla
	private List<SubtipoContratoDto> subtipoContratos; // lista de SubtipoContratos

	private SubtipoContratoDto subtipoContrato;
	
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private SubtipoContratoService subtipoContratoService;
	
	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de Subtipo de Contratos.");
		
		subtipoContratos = subtipoContratoService.getSubtiposContratos();
        		
		estatus = Estatus.values();
		this.pagina = 1;
		
		return VistasEnum.SUBTIPOCONTRATO.getIdVista();
	}

	public void limpiarSubtipoContrato(){
		subtipoContrato = new SubtipoContratoDto();
	}
	
	public void guardarSubtipoContrato() {
		
		  if (subtipoContrato!= null) { 
		   	  			  			 
		   List<SubtipoContratoDto> SubAux = subtipoContratoService.getSubtipoContratosPorNombre(subtipoContrato.getSubtipoContrato());
		   if(SubAux!=null &&SubAux.size()>0){
						 
		     alertaBean.setMensaje("No se puede agregar Subtipo porque ya existe uno con el mismo nombre");
		     alertaBean.setImagen(Icono.WARNING);
		     		     
		     }else{
		   	subtipoContrato.setIdSubtipoContrato(0);
			subtipoContrato.setFechaRegistro(new Date());
			
			SubtipoContratoDto subtipoContratoDto  = subtipoContratoService.guardarSubtipoContrato(subtipoContrato);
			log.info("Guardando el Subtipo de Contrato: " + subtipoContrato.getSubtipoContrato());
					
	   	    if(subtipoContratoDto !=null){
    		alertaBean.setMensaje("El Subtipo de Contrato ha sido guardado correctamente.");
			alertaBean.setImagen(Icono.SUCCESS);
			subtipoContrato = null;
			subtipoContratos = subtipoContratoService.getSubtiposContratos(); //Actualizar lista de SubtipoContratos
		    }else{
		     alertaBean.setMensaje("Ocurrio un error al guardar el Subtipo de Contrato.");
		     alertaBean.setImagen(Icono.ERROR);        
	         } 
	       }  
       }
    }
	
	public void actualizarSubtipoContrato() {
		if (subtipoContrato != null) {

		    List<SubtipoContratoDto> SubAux = subtipoContratoService.getSubtipoContratosPorNombre(subtipoContrato.getSubtipoContrato(), subtipoContrato.getIdSubtipoContrato());
		    if(SubAux!=null &&SubAux.size()>0){
				   alertaBean.setMensaje("No se puede modificar el Subtipo de Contratacion porque el Nombre ya existe");
				   alertaBean.setImagen(Icono.WARNING); 				   			   
				                                                          
				   SubtipoContrato subtipoAux = subtipoContratoService.getSubtiposContratosPorId(subtipoContrato.getIdSubtipoContrato());
				   subtipoContrato.setIdSubtipoContrato(subtipoAux.getIdSubtipoContrato());
				   subtipoContrato.setBaja(subtipoAux.getBaja());
				   subtipoContrato.setSubtipoContrato(subtipoAux.getSubtipoContrato());
				  		    
		    }else{
				   subtipoContrato.setFechaModificacion(new Date());
		           if(subtipoContrato.getIdSubtipoContrato()> 0 ){
				   boolean resultado = subtipoContratoService.actualizarSubtipoContrato(subtipoContrato);
			       log.info("Actualizando el Subtipo de Contrato: " + subtipoContrato.getIdSubtipoContrato() + " resultado: " + resultado);
          		   
			       if(resultado){
			    	   alertaBean.setMensaje("El Subtipo de Contrato ha sido actualizado correctamente.");
			    	   alertaBean.setImagen(Icono.SUCCESS);
				       subtipoContrato = null;
			         }
			      }else{
				    alertaBean.setMensaje("Ocurrio un error al actualizar el Subtipo de Contrato.");
				    alertaBean.setImagen(Icono.ERROR);
			  }
		   }
		}
	}

	public void eliminarSubtipoContrato() {
	
			
			if (subtipoContrato != null) {
			subtipoContrato.setFechaBaja(new Date());
			subtipoContrato.setBaja(Estatus.INACTIVO);
			
			boolean resultado = subtipoContratoService.eliminarSubtipoContrato(subtipoContrato);
			log.info("Eliminando el Subtipo de Contrato: " + subtipoContrato.getIdSubtipoContrato() + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("Subtipo de Contrato ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				subtipoContrato = null;
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el Subtipo de Contrato.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}
		
	public List<SubtipoContratoDto> getSubtipoContratos() {
		return this.subtipoContratos;
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
	
	public void setSubtipoContratos(List<SubtipoContratoDto> subtipoContratos) {
		this.subtipoContratos = subtipoContratos;
	}

	public SubtipoContratoDto getSubtipoContrato() {
		return subtipoContrato;
	}

	public void setSubtipoContrato(SubtipoContratoDto subtipoContrato) {
		this.subtipoContrato = subtipoContrato;
	}
}
