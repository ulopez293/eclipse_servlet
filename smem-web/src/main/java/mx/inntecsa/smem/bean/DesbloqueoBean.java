package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mx.inntecsa.smem.dto.CentroTrabajoBloqueadoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.BloqueoCTService;
import mx.inntecsa.smem.service.EntidadService;
import mx.inntecsa.smem.service.UnidadRegionalService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class DesbloqueoBean implements Serializable {
	
	private Logger log = Logger.getLogger(DesbloqueoBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlertaBean alertaBean;
	
	@Autowired
	private EntidadService entidadService;
	
	@Autowired
	private UnidadRegionalService unidadRegionalService;
	
	@Autowired
	private BloqueoCTService bloqueoCTService;
	
	private int entidad;
	private int unidadRegional;
	private int pagina;
	private List<EntidadDto> entidades;
	private List<UnidadRegionalDto> unidadesRegionales;
	private List<CentroTrabajoBloqueadoDto> centrosTrabajo;
	private Collection<Object> selection;

	public String inicializar(){
		
		log.info("DesbloqueoBean.inicializar");
		
		setEntidades(entidadService.getEntidadesPorEstatus(Estatus.ACTIVO));
		setUnidadesRegionales(unidadRegionalService.
		getUnidadesRegionalesPorIdEntidad(entidades.get(0).getIdEntidad()));
		setEntidad(0);
		setUnidadRegional(0);
		setCentrosTrabajo(bloqueoCTService.getConsultaCtblq(entidad, unidadRegional));
		
		log.info("termina de cargar catalogos");
		if (selection!=null && selection.size()>0){
			selection.clear();
		}
		return VistasEnum.DESBLOQUEO.getIdVista();	
	}
	
	public void buscarUnidadesRegionales() {

		log.info(">>>Buscando Unidades por entidad: " + entidad);
		
		if(entidad != 0){
			unidadesRegionales = unidadRegionalService.
			getUnidadesRegionalesPorIdEntidad(entidad);
		}		
		selection.clear();
	}
	
	public void buscarRegistros(){
		log.info(">>>Buscando centros trabajo por entidad y unidad regional: " 
				+ entidad + ", " + unidadRegional);
		
		setCentrosTrabajo(bloqueoCTService.getConsultaCtblq(entidad, unidadRegional));
		selection.clear(); 		
		log.info("Centros bloqueados: " + getCentrosTrabajo().size());
	}
	 
	public void desbloquear(){
		log.info("Desbloquear: " + selection.size());		
		if (!selection.isEmpty()) {
			List<Integer> ctDesbloqueo = new ArrayList<Integer>();
			for(Object selectionKey : selection){
				log.info("selection: " + selectionKey);
				int idSel = new Integer(selectionKey.toString()).intValue();
				Integer id = centrosTrabajo.get(idSel).getId();
				ctDesbloqueo.add(id);
			}
			bloqueoCTService.actualizarEstatusCT(ctDesbloqueo, false);
			setEntidad(0);
			setUnidadRegional(0);
			setCentrosTrabajo(bloqueoCTService.getConsultaCtblq(entidad, unidadRegional));
			alertaBean.setMensaje("Los centros de trabajo seleccionados han sido desbloqueados");
			alertaBean.setImagen(Icono.SUCCESS);
			selection.clear();
		}else {
			alertaBean.setMensaje("Debe seleccionar al menos un centro de trabajo");
			alertaBean.setImagen(Icono.WARNING);
		}
	}

	/**
	 * @return the entidad
	 */
	public int getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the unidadRegional
	 */
	public int getUnidadRegional() {
		return unidadRegional;
	}

	/**
	 * @param unidadRegional the unidadRegional to set
	 */
	public void setUnidadRegional(int unidadRegional) {
		this.unidadRegional = unidadRegional;
	}

	/**
	 * @return the entidades
	 */
	public List<EntidadDto> getEntidades() {
		return entidades;
	}

	/**
	 * @param entidades the entidades to set
	 */
	public void setEntidades(List<EntidadDto> entidades) {
		this.entidades = entidades;
	}

	/**
	 * @return the unidadesRegionales
	 */
	public List<UnidadRegionalDto> getUnidadesRegionales() {
		return unidadesRegionales;
	}

	/**
	 * @param unidadesRegionales the unidadesRegionales to set
	 */
	public void setUnidadesRegionales(List<UnidadRegionalDto> unidadesRegionales) {
		this.unidadesRegionales = unidadesRegionales;
	}

	public void setCentrosTrabajo(List<CentroTrabajoBloqueadoDto> centrosTrabajo) {
		this.centrosTrabajo = centrosTrabajo;
	}

	public List<CentroTrabajoBloqueadoDto> getCentrosTrabajo() {
		return centrosTrabajo;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getPagina() {
		return pagina;
	}
	
	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public Collection<Object> getSelection() {
		return selection;
	}
}
