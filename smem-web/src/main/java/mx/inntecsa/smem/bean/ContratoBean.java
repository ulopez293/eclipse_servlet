package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SubtipoContratoDto;
import mx.inntecsa.smem.dto.TipoContratacionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.ContratoService;
import mx.inntecsa.smem.service.ProveedorService;
import mx.inntecsa.smem.service.SubtipoContratoService;
import mx.inntecsa.smem.service.TipoContratacionService;
import mx.inntecsa.smem.service.UniversoService;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import org.richfaces.component.UIExtendedDataTable;

@Component
@Scope("session")
public class ContratoBean implements Serializable {

	private static final long serialVersionUID = -6873404658271319507L;

	@Autowired
	private ContratoService contratoService; // Servicio de contratos
	
	@Autowired
	private ProveedorService proveedorService; // Servicio de Proveedores
	
	@Autowired
	private SubtipoContratoService subtipoContratoService; // Servicio de Subtipos de Contratos
	
	@Autowired
	private TipoContratacionService tipoContratacionService; // Servicio de tipos de contratacion

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al contrato
	
	@Autowired
	private ContratoDetalleBean contratoDetalleBean; // Bean para mensajes al contrato
	
	@Autowired
	private UniversoService universoService;
	
	private Logger log = Logger.getLogger(ContratoBean.class); // Bitacora
	private List<ContratoDto> contratos = new ArrayList<ContratoDto>(); // Lista de Contratos
	private int pagina; // Paginador para la tabla
	private ContratoDto contrato; // Contrato
	private EstatusContrato[] estatusContrato; // Lista de estatus
	private Collection<Object> selection; // seleccion utilizada para la eleccion de algun curso
	private Integer idProveedorBusqueda;
	private EstatusContrato estatusBusqueda;
	private Date fechaInicioBusqueda;
	private Date fechaFinBusqueda;	
	private List<ProveedorDto> proveedores;
	private List<SubtipoContratoDto> subtiposContrato;
	private TipoServicio[] tiposServicio;
	private List<TipoContratacionDto> tiposContratacion;
	
	public List<ContratoDto> getContratos() {
		return contratos;
	}

	public void setContratos(List<ContratoDto> contratos) {
		this.contratos = contratos;
	}

	public ContratoDto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDto contrato) {
		this.contrato = contrato;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}	
			
		
	public EstatusContrato[] getEstatusContrato() {
		return estatusContrato;
	}

	public void setEstatusContrato(EstatusContrato[] estatusContrato) {
		this.estatusContrato = estatusContrato;
	}

	public Integer getIdProveedorBusqueda() {
		return idProveedorBusqueda;
	}

	public void setIdProveedorBusqueda(Integer idProveedorBusqueda) {
		this.idProveedorBusqueda = idProveedorBusqueda;
	}

	public EstatusContrato getEstatusBusqueda() {
		return estatusBusqueda;
	}

	public void setEstatusBusqueda(EstatusContrato estatusBusqueda) {
		this.estatusBusqueda = estatusBusqueda;
	}

	public Date getFechaInicioBusqueda() {
		return fechaInicioBusqueda;
	}

	public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
		this.fechaInicioBusqueda = fechaInicioBusqueda;
	}

	public Date getFechaFinBusqueda() {
		return fechaFinBusqueda;
	}

	public void setFechaFinBusqueda(Date fechaFinBusqueda) {
		this.fechaFinBusqueda = fechaFinBusqueda;
	}	
	
	public List<ProveedorDto> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorDto> proveedores) {
		this.proveedores = proveedores;
	}
	
	public List<SubtipoContratoDto> getSubtiposContrato() {
		return subtiposContrato;
	}

	public void setSubtiposContrato(List<SubtipoContratoDto> subtiposContrato) {
		this.subtiposContrato = subtiposContrato;
	}		

	public TipoServicio[] getTiposServicio() {
		return tiposServicio;
	}

	public void setTiposServicio(TipoServicio[] tiposServicio) {
		this.tiposServicio = tiposServicio;
	}		

	public List<TipoContratacionDto> getTiposContratacion() {
		return tiposContratacion;
	}

	public void setTiposContratacion(List<TipoContratacionDto> tiposContratacion) {
		this.tiposContratacion = tiposContratacion;
	}	

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void limpiarContrato() {
		this.contrato = new ContratoDto();
	}
	
	public String inicializar() {
		pagina = 1;
		contratoDetalleBean.inicializar();
		contrato = new ContratoDto();
		tiposServicio =  TipoServicio.values();
		estatusContrato = EstatusContrato.values();
		contratos = contratoService.getContratosPorEstatus(Estatus.ACTIVO);
		log.info(">>>CONTRATOS INICIALES " + contratos.size());
		proveedores = proveedorService.getProveedoresByEstatus(Estatus.ACTIVO);
		subtiposContrato = subtipoContratoService.getSubtiposContratosPorEstatus(Estatus.ACTIVO);
		tiposContratacion = tipoContratacionService.getTiposContratacionPorEstatus(Estatus.ACTIVO);
		if(selection!=null && selection.size()>0){
			selection.clear();
		}
		return VistasEnum.CONTRATOS.getIdVista();		
	}
	
//	public void selectionListener(AjaxBehaviorEvent event) {
//		UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
//
//        for (Object selectionKey : selection) {
//            dataTable.setRowKey(selectionKey);
//            
//            if (dataTable.isRowAvailable()) {
//                ContratoDto contratoSeleccionado = (ContratoDto) dataTable.getRowData();
//                contratoDetalleBean.setContrato(contratoSeleccionado);
//                contratoDetalleBean.setContratosDetalles(new ArrayList<ContratoDetalleDto>());
//                log.info(">>>selection " + contratoSeleccionado.getIdContrato());
//                break;
//            }
//        }
//    }		


	public void buscarContratos() {
		log.info(">>>Estatus " + estatusBusqueda + " proveedor "+ idProveedorBusqueda + 
			" fecha inicio " + fechaInicioBusqueda + "fecha fin " + fechaFinBusqueda);
		contratos = contratoService.getContratosPorParametros(estatusBusqueda, 
			idProveedorBusqueda, fechaInicioBusqueda, fechaFinBusqueda);
		if(selection!=null && selection.size()>0){
			selection.clear();
		}
	}

	public void agregarContrato() {
		log.info(">>>Guardando Contrato: " + contrato.getNumeroContrato());		
		
		if(contrato != null) {
			contrato.setBaja(Estatus.ACTIVO);
			contrato.setFechaRegistro(new Date());			
			contrato.setEstatus(EstatusContrato.VIGENTE);
			ContratoDto contratoDto = contratoService.guardarContrato(contrato);
			
			if(contratoDto != null) {
				alertaBean.setMensaje("El Contrato ha sido guardado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contratos = contratoService.getContratosPorEstatus(Estatus.ACTIVO);
				contrato = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de guardar el Contrato.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}		
	}
	
	public void editarContrato() {
		log.info(">>>Editando Contrato: " + contrato.getNumeroContrato());
		
		if (contrato != null) {
			contrato.setFechaModificacion(new Date());
			
			if(contratoService.actualizarContrato(contrato)) {
				alertaBean.setMensaje("El Contrato ha sido actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contrato = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Contrato.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	
	
	public void eliminarContrato() {
		log.info(">>>Eliminando Contrato: "+ contrato.getNumeroContrato());
		
		if (contrato != null) {
			contrato.setFechaBaja(new Date());
			contrato.setBaja(Estatus.INACTIVO);
			contrato.setEstatus(EstatusContrato.NO_VIGENTE);
			
			if(contratoService.eliminarContrato(contrato)) {
				alertaBean.setMensaje("El Contrato ha sido eliminado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contrato = null;				
			
			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de eliminar el Contrato.");
				alertaBean.setImagen(Icono.ERROR);
			}						
		}
	}	

}
