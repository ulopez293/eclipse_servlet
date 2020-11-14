package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusCentroTrabajo;
import mx.inntecsa.smem.service.CentroTrabajoService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class CentroTrabajoBean implements Serializable {
	
	private Logger log = Logger.getLogger(CentroTrabajoBean.class); // Bitacora

	@Autowired
	private CentroTrabajoService centroTrabajoService; // Servicio de Centros de trabajo
	
	@Autowired
	private UnidadRegionalService unidadRegionalService; // Servicio de unidades regionales
	
	@Autowired
	ProgramacionServicioService programacionServicioService;

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al centroTrabajo
	
	private List<CentroTrabajoDto> centrosTrabajo = new ArrayList<CentroTrabajoDto>(); // Lista de Centros de trabajo
	private CentroTrabajoDto centroTrabajo; // Centro de trabajo	
	private List<UnidadRegionalDto> unidadesRegionales; // Lista de tipos de unidades regionales	
	private int pagina; // Paginador para la tabla

	public String inicializar() {
		
		log.info("Inicializando el catalogo de centros de trabajo....");
		this.pagina = 1;
		centroTrabajo = new CentroTrabajoDto();		
		
		try{
			centrosTrabajo = centroTrabajoService.getCentrosTrabajoPorEstatus(Estatus.ACTIVO);
			unidadesRegionales = unidadRegionalService.getUnidadesRegionalesByEstatus(Estatus.ACTIVO);
		}catch(Exception e){
			log.error("Error en iniciarlizar centros de trabajo",e);
		}
		
		return "pantallaCentrosTrabajo";		
	}	
	
	public void limpiarCentroTrabajo() {
		this.centroTrabajo = new CentroTrabajoDto();
	}
		
    public void agregarCentroTrabajo() {
		log.info(">>>Guardando Centro de trabajo: " + centroTrabajo.getDescripcion());		
		if (centroTrabajo != null) {
			List<CentroTrabajoDto> centrosTrabajoAux = centroTrabajoService.getCentrosTrabajoActivosPorURCT(centroTrabajo.getUrct());
			if(centrosTrabajoAux!=null && centrosTrabajoAux.size()>0){
				alertaBean.setMensaje("No se puede agregar el centro de trabajo porque la URCT ya existe");
				alertaBean.setImagen(Icono.WARNING);
			}else{
				centroTrabajo.setBaja(Estatus.ACTIVO);
				centroTrabajo.setFechaRegistro(new Date());
				centroTrabajo.setEstatus(EstatusCentroTrabajo.DESBLOQUEADO);
				if(centroTrabajo.getResidenteCorreo()!=null){
					centroTrabajo.setResidenteCorreo(centroTrabajo.getResidenteCorreo().toLowerCase());
				}
				if(centroTrabajo.getResidenteCorreoJefe()!=null){
					centroTrabajo.setResidenteCorreoJefe(centroTrabajo.getResidenteCorreoJefe().toLowerCase());
				}
				CentroTrabajoDto centroTrabajoDto = centroTrabajoService.guardarCentroTrabajo(centroTrabajo);
				if(centroTrabajoDto != null) {
					alertaBean.setMensaje("Se ha agregado el Centro de Trabajo de manera exitosa.");
					alertaBean.setImagen(Icono.SUCCESS);
					centroTrabajo = null;
					centrosTrabajo = centroTrabajoService.getCentrosTrabajoPorEstatus(Estatus.ACTIVO);
				} else {
					alertaBean.setMensaje("Ha surgido un problema al tratar de guardar el centro de trabajo.");
					alertaBean.setImagen(Icono.ERROR);
				}	
			}				
		}		
	}
	
	public void editarCentroTrabajo() {
		log.info(">>>Editando centroTrabajo:" + centroTrabajo.getIdCentroTrabajo());
		
		if (centroTrabajo != null) {
			List<CentroTrabajoDto> centrosTrabajoAux = centroTrabajoService.getCentrosTrabajoActivosPorURCT(centroTrabajo.getUrct(),centroTrabajo.getIdCentroTrabajo());
			if(centrosTrabajoAux!=null && centrosTrabajoAux.size()>0){
				alertaBean.setMensaje("No se puede modificar el centro de trabajo porque la URCT ya existe");
				alertaBean.setImagen(Icono.WARNING);
				CentroTrabajoDto centroTrabajoAux=centroTrabajoService.getCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
				centroTrabajo.setUnidadRegional(centroTrabajoAux.getUnidadRegional());
				centroTrabajo.setUrct(centroTrabajoAux.getUrct());
				centroTrabajo.setDescripcion(centroTrabajoAux.getDescripcion());
				centroTrabajo.setResponsable(centroTrabajoAux.getResponsable());
				centroTrabajo.setTelefono(centroTrabajoAux.getTelefono());
				centroTrabajo.setDireccion(centroTrabajoAux.getDireccion());
				centroTrabajo.setResidenteCargo(centroTrabajoAux.getResidenteCargo());
				centroTrabajo.setResidenteCorreo(centroTrabajoAux.getResidenteCorreo());
				centroTrabajo.setResidenteJefe(centroTrabajoAux.getResidenteJefe());
				centroTrabajo.setResidenteTelefonoJefe(centroTrabajoAux.getResidenteTelefonoJefe());
				centroTrabajo.setResidenteCorreoJefe(centroTrabajoAux.getResidenteCorreoJefe());
				centroTrabajo.setResidenteCargoJefe(centroTrabajoAux.getResidenteCargoJefe());
			}else{
				centroTrabajo.setFechaModificacion(new Date());
				if(centroTrabajo.getResidenteCorreo()!=null){
					centroTrabajo.setResidenteCorreo(centroTrabajo.getResidenteCorreo().toLowerCase());
				}
				if(centroTrabajo.getResidenteCorreoJefe()!=null){
					centroTrabajo.setResidenteCorreoJefe(centroTrabajo.getResidenteCorreoJefe().toLowerCase());
				}
				if(centroTrabajoService.actualizarCentroTrabajo(centroTrabajo)) {
					alertaBean.setMensaje("El centro de trabajo ha sido dado actualizado de manera exitosa.");
					alertaBean.setImagen(Icono.SUCCESS);
					centroTrabajo = null;				
				} else {
					alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el centro de trabajo.");
					alertaBean.setImagen(Icono.ERROR);
				}	
			}
		}
	}	

	public void eliminarCentroTrabajo() {
		log.info(">>>Eliminando Centro Trabajo: "+ centroTrabajo.getIdCentroTrabajo());
		
		if (centroTrabajo != null) {
			
			if(programacionServicioService.getExistenServiciosPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo())) {
				alertaBean.setMensaje("No se puede eliminar el centro de trabajo, ya que tiene servicios activos.");
				alertaBean.setImagen(Icono.WARNING);
				centroTrabajo = null;
				
			} else {			
				centroTrabajo.setFechaBaja(new Date());
				centroTrabajo.setBaja(Estatus.INACTIVO);
				
				if(centroTrabajoService.eliminarCentroTrabajo(centroTrabajo)) {
					alertaBean.setMensaje("El centro de trabajo ha sido eliminado de manera exitosa.");
					alertaBean.setImagen(Icono.SUCCESS);
					centroTrabajo = null;				
					centrosTrabajo = centroTrabajoService.getCentrosTrabajoPorEstatus(Estatus.ACTIVO);
				
				} else {
					alertaBean.setMensaje("Ha surgido un problema al tratar de eliminar el centro de trabajo.");
					alertaBean.setImagen(Icono.ERROR);
				}
			}	
		}
	}	
	
	public List<CentroTrabajoDto> getCentrosTrabajo() {
		return centrosTrabajo;
	}

	public void setCentrosTrabajo(List<CentroTrabajoDto> centrosTrabajo) {
		this.centrosTrabajo = centrosTrabajo;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public CentroTrabajoDto getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(CentroTrabajoDto centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public List<UnidadRegionalDto> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	public void setUnidadesRegionales(List<UnidadRegionalDto> unidadesRegionales) {
		this.unidadesRegionales = unidadesRegionales;
	}	
}
