package mx.inntecsa.smem.bean;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.ContratoDetalleService;
import mx.inntecsa.smem.service.TipoContratacionService;
import mx.inntecsa.smem.util.DownloadFile;
import mx.inntecsa.smem.util.FileAux;
import mx.inntecsa.smem.util.Icono;
import mx.inntecsa.smem.util.UploadFileContratoDetalle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import org.richfaces.event.FileUploadEvent;
//import org.richfaces.model.UploadedFile;

@Component
@Scope("session")
public class ContratoDetalleBean implements Serializable {

	private static final long serialVersionUID = 7751786820019144629L;

	@Autowired
	private ContratoDetalleService contratoDetalleService; // Servicio de contratosDetalles

	@Autowired
	private TipoContratacionService tipoContratacionService; // Servicio de tipos de contratacion

	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al contratoDetalle
	
	@Autowired
	private UploadFileContratoDetalle archivo; // Bean para mensajes al contratoDetalle
	
	private Logger log = Logger.getLogger(ContratoDetalleBean.class); // Bitacora
	private List<ContratoDetalleDto> contratosDetalles = new ArrayList<ContratoDetalleDto>(); // Lista de ContratoDetalles
	private int pagina=1; // Paginador para la tabla	
	private ContratoDto contrato; // contrato	
	private Long idUniverso;
	private Integer periodo;
	private Integer consecutivo;
	private List<ContratoDetalleDto> detalles = new ArrayList<ContratoDetalleDto>();
	private ContratoDetalleDto contratoDetalle = new ContratoDetalleDto();
	private FileAux file;
	private StringBuffer errores;
	private Boolean IsNull=false; 	
	public List<ContratoDetalleDto> getContratosDetalles() {
		return contratosDetalles;
	}

	public void setContratosDetalles(List<ContratoDetalleDto> contratosDetalles) {
		this.contratosDetalles = contratosDetalles;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}		
	
	public ContratoDto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDto contrato) {
		this.contrato = contrato;
	}	

	public Long getIdUniverso() {
		return idUniverso;
	}

	public void setIdUniverso(Long idUniverso) {
		this.idUniverso = idUniverso;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	public List<ContratoDetalleDto> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ContratoDetalleDto> detalles) {
		this.detalles = detalles;
	}	

	public ContratoDetalleDto getContratoDetalle() {
		return contratoDetalle;
	}

	public void setContratoDetalle(ContratoDetalleDto contratoDetalle) {
		this.contratoDetalle = contratoDetalle;
	}	

	public FileAux getFile() {
		return file;
	}

	public void setFile(FileAux file) {
		this.file = file;
	}

	public StringBuffer getErrores() {
		return errores;
	}

	public void setErrores(StringBuffer errores) {
		this.errores = errores;
	}

	public void limpiarContratoDetalle() {
		periodo = null;
		idUniverso = null;		
		consecutivo = null;
		detalles = new ArrayList<ContratoDetalleDto>();
		file = null;
		errores = null;
		contratoDetalle = new ContratoDetalleDto();
	}	

	public void inicializar() {
		pagina = 1;
		detalles = new ArrayList<ContratoDetalleDto>();
		contratoDetalle = new ContratoDetalleDto();		
		contratosDetalles = new ArrayList<ContratoDetalleDto>();
		file = new FileAux();
		//contrato = new ContratoDto();	
	}
	
	public void cargarDetalle() {
		this.inicializar(); 
		if(contrato!=null){
		setIsNull(false);
		contratosDetalles = contratoDetalleService.getContratosDetallesPorIdContrato(contrato.getIdContrato());                
        log.info(">>>SIZE DE CONTRATOS DETALLES " +contratosDetalles.size());
        log.info(">>>*** contrato.getIdContrato ["+contrato.getIdContrato()+"]");
		}
		else{
			setIsNull(true);
			log.info(">>>*** Se debe seleccionar un contrato primero");
			alertaBean.setMensaje("Se debe seleccionar un contrato primero");
			alertaBean.setImagen(Icono.WARNING);		
		}
	}
	
	public boolean getTamanioContratosDetalle() {
		
		if(contratosDetalles != null && contratosDetalles.size() > 0)
			return true;			
		return false;
	}
		
	public void buscarConsecutivo() {
		log.info(">>>buscando el Consecutivo");
		consecutivo = contratoDetalleService.getConsecutivoVigentePorIdUniverso(idUniverso);
		if(consecutivo == 0) consecutivo = null;
	}
	
	public void asignarDetalle() {
		log.info(">>>Asignando el detalle");
		log.info(">>>DETALLE " + contratoDetalle.getIdContratoDetalle());	
		detalles = new ArrayList<ContratoDetalleDto>();
		detalles.add(contratoDetalle);
	}

	public void generarDetalles() {
		log.info(">>>Generando Detalles " + periodo);
		detalles = new ArrayList<ContratoDetalleDto>();
		Integer maximoPeriodo = contratoDetalleService.getMaxPeriodoPorIdUniverso(idUniverso);
		log.info(">>>Maximo Periodo "+ maximoPeriodo);
		
		for(int numeroPeriodo = maximoPeriodo + 1; numeroPeriodo <= periodo + maximoPeriodo; numeroPeriodo++) {
			ContratoDetalleDto contratoDetalleDto = new ContratoDetalleDto();
			contratoDetalleDto.setPeriodo(numeroPeriodo);
			contratoDetalleDto.getUniverso().setIdUniverso(idUniverso);
			contratoDetalleDto.setConsecutivoContrato(consecutivo);
			contratoDetalleDto.setBaja(Estatus.ACTIVO);
			contratoDetalleDto.setFechaRegistro(new Date());	
			contratoDetalleDto.getContrato().setIdContrato(contrato.getIdContrato());
			detalles.add(contratoDetalleDto);
		}
		
	}
	
//	public void fileUploadlistener(FileUploadEvent event) {
//		log.info(">>>setenado el archivo");
//		UploadedFile item = event.getUploadedFile();
//		file = new FileAux();
//		file.setLength(item.getData().length);
//		file.setName(item.getName());
//		file.setData(item.getData());	
//		file.setContentType(item.getContentType());
//	}
	
	public void cargarArchivo() {
		log.info(">>>cargando Archivo");
		
		archivo.setErrores(new StringBuffer());
		
		//no venga vacio
		if(file != null && file.getLength() != 0) {
			
			archivo.cargarArchivo(file);		
			archivo.validaArchivo();		
			errores = archivo.getErrores();
					
			if(errores.length() <= 0) {
				alertaBean.setMensaje("Se ha cargado el archivo de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contratosDetalles = contratoDetalleService.getContratosDetallesPorIdContrato(contrato.getIdContrato());
				errores.append("Archivo sin errores");
			
			} else {
				alertaBean.setMensaje("Han surgido inconsistencias al momento de cargar el Archivo.");
				alertaBean.setImagen(Icono.WARNING);
			}
			
		} else {
			alertaBean.setMensaje("Es necesario dar clic en cargar archivo.");
			alertaBean.setImagen(Icono.WARNING);
		}
		
		file = new FileAux();
	}
	
	public void descargarArchivo() {
		log.info(">>>Descargando archivo");		
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			DownloadFile descargarArchivo = new DownloadFile("Detalle_contrato.xls");
			descargarArchivo.write(this.getDetalleParaExcel(contratosDetalles));
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=\"" + "detalle_contrato.xls" + "\"");

			OutputStream os = response.getOutputStream();
			os.write(descargarArchivo.getByteFile());
			os.flush();
			os.close();
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (Exception e) {
			log.error(">>>Error : " , e);
		}
	}
	 

	public void agregarContratoDetalle() {
		if (detalles != null && detalles.size() > 0) {
			Boolean exito = contratoDetalleService.guardarContratosDetalles(detalles);

			if (exito) {
				alertaBean.setMensaje("El ContratoDetalle ha sido actualizado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contratosDetalles = contratoDetalleService.getContratosDetallesPorIdContrato(contrato.getIdContrato());
				detalles = new ArrayList<ContratoDetalleDto>();
				contratoDetalle = null;

			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el ContratoDetalle.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}

	public void editarContratoDetalle() {
		log.info(">>>Editando ContratoDetalle: "+ detalles.get(0).getIdContratoDetalle());

		if (detalles != null && detalles.size() > 0) {
			ContratoDetalleDto nuevoDetalle = detalles.get(0);
			nuevoDetalle.setFechaModificacion(new Date());

			if (contratoDetalleService.actualizarContratoDetalle(nuevoDetalle)) {
				alertaBean.setMensaje("El Contrato Detalle ha sido dado de alta de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contratoDetalle = null;

			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de guardar el Contrato Detalle.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}

	public void eliminarContratoDetalle() {
		log.info(">>>Eliminando ContratoDetalle: " + contratoDetalle.getIdContratoDetalle());

		if (contratoDetalle != null) {
			contratoDetalle.setFechaBaja(new Date());
			contratoDetalle.setBaja(Estatus.INACTIVO);

			if (contratoDetalleService.eliminarContratoDetalle(contratoDetalle)) {
				alertaBean.setMensaje("El Contrato Detalle ha sido eliminado de manera exitosa.");
				alertaBean.setImagen(Icono.SUCCESS);
				contratoDetalle = null;
				contratosDetalles = contratoDetalleService.getContratosDetallesPorIdContrato(contrato.getIdContrato()); 

			} else {
				alertaBean.setMensaje("Ha surgido un problema al tratar de eliminar el Contrato Detalle.");
				alertaBean.setImagen(Icono.ERROR);
			}
		}
	}
	
	private List<List<String>> getDetalleParaExcel(List<ContratoDetalleDto> contratosDetallesDto) {
		List<List<String>> datos = new ArrayList<List<String>>();
		
		for (ContratoDetalleDto contratoDetalleDto : contratosDetallesDto) {
			List<String> listaDetalle = new ArrayList<String>();
			listaDetalle.add(contratoDetalleDto.getContrato().getIdContrato().toString());
			listaDetalle.add(String.valueOf(contratoDetalleDto.getIdContratoDetalle()));
			listaDetalle.add(contratoDetalleDto.getPeriodo().toString());			
			listaDetalle.add(contratoDetalleDto.getConsecutivoContrato().toString());			
			listaDetalle.add(contratoDetalleDto.getInicioPeriodo().toString());
			listaDetalle.add(contratoDetalleDto.getFinPeriodo().toString());
			listaDetalle.add(contratoDetalleDto.getEnero().toString());
			listaDetalle.add(contratoDetalleDto.getFebrero().toString());
			listaDetalle.add(contratoDetalleDto.getMarzo().toString());
			listaDetalle.add(contratoDetalleDto.getAbril().toString());
			listaDetalle.add(contratoDetalleDto.getMayo().toString());
			listaDetalle.add(contratoDetalleDto.getJunio().toString());
			listaDetalle.add(contratoDetalleDto.getJulio().toString());			
			listaDetalle.add(contratoDetalleDto.getAgosto().toString());
			listaDetalle.add(contratoDetalleDto.getSeptiembre().toString());
			listaDetalle.add(contratoDetalleDto.getOctubre().toString());
			listaDetalle.add(contratoDetalleDto.getNoviembre().toString());
			listaDetalle.add(contratoDetalleDto.getDiciembre().toString());
			//agregando lista
			datos.add(listaDetalle);
		}	
		
		return datos;
	}
	
	public void actualizarContratoDetalle() {
		contratoDetalle.setFechaModificacion(new Date());
		if (contratoDetalleService.actualizarContratoDetalle(contratoDetalle)) {
			alertaBean.setMensaje("El Detalle del Contrato ha sido actualizado de manera exitosa.");
			alertaBean.setImagen(Icono.SUCCESS);				
			contratoDetalle = null;
		} else {
			alertaBean.setMensaje("Ha surgido un problema al tratar de actualizar el Detalle del Contrato.");
			alertaBean.setImagen(Icono.ERROR);
		}
	}

	public Boolean getIsNull() {
		return IsNull;
	}

	public void setIsNull(Boolean isNull) {
		IsNull = isNull;
	}

}
