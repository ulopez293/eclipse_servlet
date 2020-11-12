package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.SectorAdqDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.SectorAdqService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

@Scope("session")
@Component
public class SectorAdquisicionBean implements Serializable{

	private Logger log = Logger.getLogger(SectorAdquisicionBean.class);
	private static final long serialVersionUID = -3789246001873096675L;
	private int pagina; // Paginador para la tabla
	private List<SectorAdqDto> sectoresAdquisicion; // lista de  sectores de adquisicion
	private SectorAdqDto sectorAdquisicion;
	private Estatus []estatus;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private SectorAdqService sectorAdqService;

	public String inicializar(){
		
		log.info("Metodo inicializar. Obteniendo la lista de tipos de sectores de adquisicion.");
		sectoresAdquisicion = sectorAdqService.getSectoresAdq();
		estatus = Estatus.values();
		this.pagina = 1;
		return VistasEnum.SECTOR_ADQUISICION.getIdVista();
	}

	public void limpiarSectorAdquisicion(){
		sectorAdquisicion = new SectorAdqDto();
	}

	public void guardarSectorAdquisicion() {
		if (sectorAdquisicion != null) {
			List<SectorAdqDto> sectoresAdqAux = sectorAdqService.getSectorAdquisicionPorNombre(sectorAdquisicion.getSectorAdq());
			if(sectoresAdqAux!=null && sectoresAdqAux.size()>0){
				alertaBean.setMensaje("No se puede agregar el sector adquisición porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				sectorAdquisicion.setIdSectorAdq(0);
				sectorAdquisicion.setFechaRegistro(new Date());
				SectorAdqDto sectorAdqDto = sectorAdqService.guardarSectorAdquisicion(sectorAdquisicion);
				log.info("Guardando el sector adquisicion: " + sectorAdquisicion.getSectorAdq());
				if(sectorAdqDto !=null){
					alertaBean.setMensaje("El sector adquisición ha sido guardado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					sectorAdquisicion = null;
					sectoresAdquisicion = sectorAdqService.getSectoresAdq();
				}else{
					alertaBean.setMensaje("Ocurrio un error al guardar el sector adquisición.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}
	
	public void actualizarSectorAdquisicion() {
		if (sectorAdquisicion != null) {
			List<SectorAdqDto> sectoresAdqAux = sectorAdqService.getSectorAdquisicionPorNombre(sectorAdquisicion.getSectorAdq(),sectorAdquisicion.getIdSectorAdq());
			if(sectoresAdqAux!=null && sectoresAdqAux.size()>0){
				alertaBean.setMensaje("No se puede modificar el sector adquisición porque ya existe uno con el mismo nombre");
				alertaBean.setImagen(Icono.WARNING);
				SectorAdqDto sectorAdqAux=sectorAdqService.getSectorAdquisicionPorId(sectorAdquisicion.getIdSectorAdq());
				sectorAdquisicion.setSectorAdq(sectorAdqAux.getSectorAdq());
				sectorAdquisicion.setBaja(sectorAdqAux.getBaja());
			}else{
				sectorAdquisicion.setFechaModificacion(new Date());
				boolean resultado = sectorAdqService.actualizarSectorAdquisicion(sectorAdquisicion);
				log.info("Actualizando el sector de adquisicion: " + sectorAdquisicion.getIdSectorAdq() + " resultado: " + resultado);
				if(resultado){
					alertaBean.setMensaje("El sector adquisición ha sido actualizado correctamente.");
					alertaBean.setImagen(Icono.SUCCESS);
					sectorAdquisicion = null;					
				}else{
					alertaBean.setMensaje("Ocurrió un error al actualizar el sector de adquisición.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}
		}
		
	}

	public void eliminarSectorAdquisicion() {
		if (sectorAdquisicion != null) {
			sectorAdquisicion.setFechaBaja(new Date());
			sectorAdquisicion.setBaja(Estatus.INACTIVO);
			boolean resultado = sectorAdqService.eliminarSectorAdquisicion(sectorAdquisicion);
			log.info("Eliminando el sector de adquisición: " + sectorAdquisicion.getIdSectorAdq() + " resultado: " + resultado);
			if(resultado){
				alertaBean.setMensaje("El sector de adquisición ha sido eliminado correctamente.");
				alertaBean.setImagen(Icono.SUCCESS);
				sectorAdquisicion = null;					
			}else{
				alertaBean.setMensaje("Ocurrio un error al eliminar el sector de adquisición.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
		
	}
	

	public SectorAdqDto getSectorAdquisicion() {
		return sectorAdquisicion;
	}

	public void setSectorAdquisicion(SectorAdqDto sectorAdquisicion) {
		this.sectorAdquisicion = sectorAdquisicion;
	}

	public List<SectorAdqDto> getSectoresAdquisicion() {
		return sectoresAdquisicion;
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
