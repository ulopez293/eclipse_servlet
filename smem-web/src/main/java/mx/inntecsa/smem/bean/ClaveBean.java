package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.ClaveService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class ClaveBean implements Serializable{

	private Logger log = Logger.getLogger(ClaveBean.class);
	private static final long serialVersionUID = -3789246001873034920L;
	private int pagina; // Paginador para la tabla
	private List<ClaveDto> claves; // lista de claves
	private ClaveDto clave;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ClaveService claveService;

	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de claves.");
		claves = claveService.getClaves();
		estatus = Estatus.values();
		this.pagina = 1;
		return VistasEnum.CLAVES.getIdVista();
	}

	public void limpiarClave(){
		clave = new ClaveDto();
	}

	public void guardarClave() {	
		if (clave != null) {	
			List<ClaveDto> clavesAux = claveService.getClavesPorNombre(clave.getClave());
			if(clavesAux!=null && clavesAux.size()>0){
				alertaBean.setMensaje("No se puede agregar la clave porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				clave.setIdClave(0);
				clave.setFechaRegistro(new Date());
				ClaveDto claveDto  = claveService.guardarClave(clave);
				log.info("Guardando la clave: " + clave.getClave() );
				if(claveDto !=null){
					alertaBean.setMensaje("La clave ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					clave = null;
					claves = claveService.getClaves(); //Actualizar lista de claves
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar la clave.");
					alertaBean.setImagen(Icono.ERROR);
				}	
			}		
		}
		
	}
	
	public void actualizarClave() {
		if (clave != null) {
			List<ClaveDto> clavesAux = claveService.getClavesPorNombre(clave.getClave(),clave.getIdClave());
			if(clavesAux!=null && clavesAux.size()>0){
				alertaBean.setMensaje("No se puede modificar la clave porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				ClaveDto claveAux=claveService.getClavePorId(clave.getIdClave());
				clave.setClave(claveAux.getClave());
				clave.setNombreGenerico(claveAux.getNombreGenerico());
				clave.setBaja(claveAux.getBaja());
			}else{
				clave.setFechaModificacion(new Date());
				boolean resultado = claveService.actualizarClave(clave);
				log.info("Actualizando la clave: " + clave.getIdClave() + " resultado: " + resultado);
				
				if(resultado){
					alertaBean.setMensaje("La clave ha sido actualizada correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					clave = null;					
				}else{
					alertaBean.setMensaje("Ocurrio un error al actualizar la clave");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}

	public void eliminarClave() {
		int idClave=clave.getIdClave();
		if(claveService.getClavesConGrupoClavePorIdClave(idClave).isEmpty()){
			clave.setFechaBaja(new Date());
			clave.setBaja(Estatus.INACTIVO);
			boolean resultado = claveService.eliminarClave(clave);
			log.info("Eliminando la clave: " + " resultado: " + resultado);
			
			if(resultado){
				alertaBean.setMensaje("La clave ha sido eliminada correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				clave = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar la clave.");
				alertaBean.setImagen(Icono.ERROR);
			}			
			
		}
		else{
			 alertaBean.setMensaje("Clave ligada a Grupo-Clave, No se puede borrar el registro.");		   
			 alertaBean.setImagen(Icono.ERROR);
		}
	}
		
	public ClaveDto getClave() {
		return clave;
	}

	public void setClave(ClaveDto clave) {
		this.clave = clave;
	}

	public List<ClaveDto> getClaves() {
		return claves;
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
