package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.service.ProveedorService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class ProveedorBean implements Serializable{

	private static final long serialVersionUID = -7113212946732426194L;
	private Logger log = Logger.getLogger(ProveedorBean.class);
	
	private int pagina; // Paginador para la tabla
	private List<ProveedorDto> proveedores; // lista de equipos
	private ProveedorDto proveedor;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;

	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de proveedores.");
		proveedores = proveedorService.getProveedoresByEstatus(Estatus.ACTIVO);
		estatus = Estatus.values();
		this.pagina = 1;
		
		return VistasEnum.PROVEEDORES.getIdVista();
	}

	public void limpiarProveedor(){
		proveedor = new ProveedorDto();
	}

	public void guardarProveedor() {
		if (proveedor != null) {
			List<ProveedorDto> proveedoresAux = proveedorService.getProveedoresPorNombre(proveedor.getProveedor());
			if(proveedoresAux!=null && proveedoresAux.size()>0){
				alertaBean.setMensaje("No se puede agregar el proveedor porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				proveedor.setIdProveedor(0);
				proveedor.setBaja(Estatus.ACTIVO);
				proveedor.setFechaRegistro(new Date());
				proveedor.setRfc(proveedor.getRfc().toUpperCase());
				proveedor.setEmail(proveedor.getEmail().toLowerCase());
				if(proveedor.getEmailGerenteServicio()!=null){
					proveedor.setEmailGerenteServicio(proveedor.getEmailGerenteServicio().toLowerCase());
				}
				ProveedorDto proveedorDto = proveedorService.guardarProveedor(proveedor);
				log.info("Guardando el proveedor: " + proveedor.getProveedor() );
				if(proveedorDto !=null){
					alertaBean.setMensaje("El proveedor ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					proveedor = null;
					proveedores = proveedorService.getProveedores(); //actualiza la lista de proveedores
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar el proveedor.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	} 
	
	public void actualizarProveedor() {
		if (proveedor != null) {
			List<ProveedorDto> proveedoresAux = proveedorService.getProveedoresPorNombre(proveedor.getProveedor(),proveedor.getIdProveedor());
			if(proveedoresAux!=null && proveedoresAux.size()>0){
				alertaBean.setMensaje("No se puede modificar el proveedor porque ya existe una con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				ProveedorDto proveedorAux=proveedorService.getProveedorPorIdProveedor(proveedor.getIdProveedor());
				proveedor.setRfc(proveedorAux.getRfc());
				proveedor.setProveedor(proveedorAux.getProveedor());
				proveedor.setTelefono(proveedorAux.getTelefono());
				proveedor.setEmail(proveedorAux.getEmail());
				proveedor.setDireccion(proveedorAux.getDireccion());
				proveedor.setRepresentanteLegal(proveedorAux.getRepresentanteLegal());
				proveedor.setTelefonoRepresentanteLegal(proveedorAux.getTelefonoRepresentanteLegal());
				proveedor.setNombreGerenteServicio(proveedorAux.getNombreGerenteServicio());
				proveedor.setTelefonoGerenteServicio(proveedorAux.getTelefonoGerenteServicio());
				proveedor.setEmailGerenteServicio(proveedorAux.getEmailGerenteServicio());
			}else{
				proveedor.setFechaModificacion(new Date());
				proveedor.setRfc(proveedor.getRfc().toUpperCase());
				proveedor.setEmail(proveedor.getEmail().toLowerCase());
				if(proveedor.getEmailGerenteServicio()!=null){
					proveedor.setEmailGerenteServicio(proveedor.getEmailGerenteServicio().toLowerCase());
				}
				boolean resultado = proveedorService.actualizarProveedor(proveedor);
				log.info("Actualizando el proveedor: " + proveedor.getProveedor() + " resultado: " + resultado);
				
				if(resultado){
					alertaBean.setMensaje("El proveedor ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					proveedor = null;					
				}else{
					alertaBean.setMensaje("Ocurrio un error al actualizar el equipo.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
	}

	public void eliminarProveedor() {
		
		if (proveedor != null) {
			
			boolean resultado = programacionServicioService.getExistenProgramacionesAbiertasPorProveedor(proveedor.getIdProveedor());
			
			if(resultado){
				alertaBean.setMensaje("El proveedor no se puede dar de baja porque tiene servicios en proceso.");
				alertaBean.setImagen(Icono.SUCCESS);
				proveedor = null;
			}else{
				proveedor.setFechaBaja(new Date());
				proveedor.setBaja(Estatus.INACTIVO);
				
				boolean resultadoBaja = proveedorService.eliminarProveedor(proveedor);
				log.info("Eliminando el proveedor: " + proveedor.getProveedor() + " resultado: " + resultado);
				
				if(resultadoBaja){
					alertaBean.setMensaje("El proveedor y sus contratos se han eliminado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					
					proveedores = proveedorService.getProveedoresByEstatus(Estatus.ACTIVO); //actualiza lista de proveedores
					proveedor = null;					
				}else{
					alertaBean.setMensaje("Ocurrio un error al eliminar el proveedor.");
					alertaBean.setImagen(Icono.ERROR);
				}				
			}
		}
	}

	public ProveedorDto getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDto proveedor) {
		this.proveedor = proveedor;
	}

	public List<ProveedorDto> getProveedores() {
		return proveedores;
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
