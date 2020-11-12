package mx.inntecsa.smem.reporte;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.service.ActaEntregaRecepcionService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

 
@Scope("session")
@Component
public class SolicitudServicioReporte{
	
	@Autowired
	private ActaEntregaRecepcionService actaEntregaRecepcionService;
	
	private static Logger log = Logger.getLogger(SolicitudServicioReporte.class);
	private static final String IMAGE_PATH = "resources"+ File.separator + "images"+ File.separator;
	private final String NOMBRE_REPORTE = "SolicitudServicio.jasper";
	private final String COMPILE_DIR = "/reportes/";
	private String rutaArchivoTemporal;
	public enum ExportOption { PDF }
	
	/**
	 * Imprime la solicitud de servicio
	 * @param programacionServicioDto objeto de la solicitud de servicio a imprimir
	 * @param tipo: 1.-Se imprime en pantalla, 2.-Se manda abre la opcion de guardar como o descarga de acuerdo al navegador, 3.- Se abre en una nueva ventana del navegador
	 * @return reporte
	 */
	public void imprimeSolicitudServicio(ProgramacionServicioDto programacionServicioDto, int tipo) {
		try {
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        ServletContext context = (ServletContext) externalContext.getContext();
	        File reportFile = new File(context.getRealPath(COMPILE_DIR + NOMBRE_REPORTE));
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("IMAGE_PATH", context.getRealPath("/") + IMAGE_PATH);
	        parametros.put("NUM_CASO", programacionServicioDto.getFolio());
	        parametros.put("DELEGACION", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
	        parametros.put("UNIDAD", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getDescripcion());
	        parametros.put("DOMICILIO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getDireccion());
			parametros.put("ID_UNIVERSO", Long.toString(programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getIdUniverso()));
			parametros.put("EQUIPO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getEquipo().getEquipo());
			parametros.put("MARCA", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getMarca());
			parametros.put("MODELO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getModelo());
			parametros.put("SERIE", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getSerie());
			parametros.put("INVENTARIO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getInventario());
			parametros.put("UBICADO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getObsubica());
			parametros.put("CONTRATO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getContrato().getNumeroContrato());
			parametros.put("CONS_CONTRATO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getConsecutivoContrato().toString());
			parametros.put("FECHA_SOLICITUD", formatoFecha.format(programacionServicioDto.getSolicitudServicio().getFechaInicio()));
			parametros.put("EMPRESA", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getContrato().getProveedor().getProveedor());
			parametros.put("TELEFONO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getContrato().getProveedor().getTelefono());
			parametros.put("GERENTE", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getContrato().getProveedor().getNombreGerenteServicio());			
			parametros.put("SUPERVISOR", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getNombre());
			parametros.put("TELEFONO_S", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getTelefono());
			parametros.put("CORREO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getUnidadRegional().getSupervisor().getCorreo());
			parametros.put("RESIDENTE", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getResponsable());
			parametros.put("RESIDENTE_CARGO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getResidenteCargo());
			parametros.put("RESIDENTE_TELEFONO", programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getCentroTrabajo().getTelefono());
			parametros.put("DESCRIPCION", programacionServicioDto.getSolicitudServicio().getMotivoSolicitud());
			ActaEntregaRecepcionDto actaEntregaRecepcionDto = actaEntregaRecepcionService.getUltimaActaEntregaPorIdUniverso(programacionServicioDto.getSolicitudServicio().getContratoDetalle().getUniverso().getIdUniverso());
			if(actaEntregaRecepcionDto != null) {				
				parametros.put("FH_ULT_SERVICIO", formatoFecha.format(actaEntregaRecepcionDto.getFechaFinalServicio()));
				parametros.put("FALLA_ATENDIDA", actaEntregaRecepcionDto.getDescripcionCompletaServicio());
				parametros.put("COMPANIA", actaEntregaRecepcionDto.getProgramacionServicio().getSolicitudServicio().getContratoDetalle().getContrato().getProveedor().getProveedor());
				parametros.put("TECNICO", actaEntregaRecepcionDto.getTecnicoEnviado());
			} else {
				parametros.put("FH_ULT_SERVICIO", "");
				parametros.put("FALLA_ATENDIDA", "");
				parametros.put("COMPANIA", "");
				parametros.put("TECNICO", "");
			}
	        if(tipo==1){
		        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parametros, new JREmptyDataSource());
		        JRExporter exporter = new JRPdfExporter();
		        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaArchivoTemporal + "SolicitudServicio.pdf");
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoTemporal + "SolicitudServicio.pdf");
		        JasperViewer.viewReport(jasperPrint,false);
		        jasperPrint = null;
		        exporter = null;	        	        	
	        }else if(tipo==2){
		        byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, new JREmptyDataSource());
		        FacesContext facesContext = FacesContext.getCurrentInstance();
		        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		        response.setHeader("Content-Disposition", "attachment; filename=\"SolicitudServicio.pdf\"");
				response.setContentType("application/pdf");	
				response.setContentLength(bytes.length);
				ServletOutputStream os = response.getOutputStream();
				os.write(bytes, 0, bytes.length);
		        os.flush();
				os.close();
				FacesContext.getCurrentInstance().renderResponse();  
				FacesContext.getCurrentInstance().responseComplete();
	        }else if(tipo==3){
	        	byte[] dataToSend=JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, new JREmptyDataSource());  
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
                ServletOutputStream out = response.getOutputStream();  
                response.setContentType("application/pdf");  
                response.setContentLength(dataToSend.length);  
                response.setHeader("Content-Disposition",  
                        "inline; filename=\"SolicitudServicio.pdf\"");  
                out.write(dataToSend);  
                out.flush();  
               FacesContext.getCurrentInstance().responseComplete();  
	        }
        } catch (Exception e) {
            log.error("error",e);
        }
    }

	public void setRutaArchivoTemporal(String rutaArchivoTemporal) {
		this.rutaArchivoTemporal = rutaArchivoTemporal;
	}
	
}
