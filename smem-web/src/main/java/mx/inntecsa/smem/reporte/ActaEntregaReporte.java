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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.utils.Fecha;
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
public class ActaEntregaReporte {

	private static Logger log = Logger.getLogger(ActaEntregaReporte.class);
			
	private static final String IMAGE_PATH = "resources"+ File.separator + "images"+ File.separator;
	private final String NOMBRE_REPORTE = "actaEntregaRecepcion.jasper";
	private final String COMPILE_DIR = "/reportes/";
	
	private String rutaArchivoTemporal;
	
	public enum ExportOption { PDF }
    
	/**
	 * Imprime el acta
	 * @param actaEntregaDto objeto del acta a imprimir
	 * @param tipoVisor  1 : Si se imprime en pantalla, 2: Si se manda como ventana guardar como
	 * @return reporte
	 */
	public String imprimeActa(ActaEntregaRecepcionDto actaEntregaDto, int tipoVisor) {
       
    	 try {
    		
    		ProgramacionServicioDto programacionDto = actaEntregaDto.getProgramacionServicio();
    		SolicitudServicioDto solicitudDto = programacionDto.getSolicitudServicio();
    		ContratoDetalleDto contratoDetalleDto = solicitudDto.getContratoDetalle();
    		ContratoDto contratoDto = contratoDetalleDto.getContrato();
    		ProveedorDto proveedorDto = contratoDto.getProveedor();
    		UniversoDto universoDto = contratoDetalleDto.getUniverso();
    		CentroTrabajoDto centroTrabajoDto = universoDto.getCentroTrabajo();
    		
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        ServletContext context = (ServletContext) externalContext.getContext();
	        File reportFile = new File(context.getRealPath(COMPILE_DIR + NOMBRE_REPORTE));
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("IMAGE_PATH", context.getRealPath("/") + IMAGE_PATH);
	        parametros.put("EMPRESA",proveedorDto.getProveedor());
	        parametros.put("DOMICILIO_EMPRESA",proveedorDto.getDireccion());
	        parametros.put("TELEFONO_EMPRESA", proveedorDto.getTelefono());
	        parametros.put("TECNICO",actaEntregaDto.getTecnicoEnviado());
	        parametros.put("FECHA_INICIO",formatoFecha.format(solicitudDto.getFechaInicio()));
	        parametros.put("FECHA_FIN",formatoFecha.format(solicitudDto.getFechaFin()));
	        parametros.put("FECHA_INICIO_REAL",formatoFecha.format(actaEntregaDto.getFechaInicioServicio()));
	        parametros.put("FECHA_FIN_REAL",formatoFecha.format(actaEntregaDto.getFechaFinalServicio()));
	        parametros.put("RESPONSABLE_EQUIPO",actaEntregaDto.getResponsableEquipo());
	        parametros.put("RESPONSABLE_CT",centroTrabajoDto.getResponsable());
	        parametros.put("UNIDAD",centroTrabajoDto.getDescripcion());
	        parametros.put("DOMICILIO_UNIDAD",centroTrabajoDto.getDireccion());
	        parametros.put("ENTIDAD",centroTrabajoDto.getUnidadRegional().getEntidad().getEntidad());
	        parametros.put("NO_CONTRATO",contratoDto.getNumeroContrato());
	        parametros.put("CONSECUTIVO",contratoDetalleDto.getConsecutivoContrato().toString());
	        parametros.put("EQUIPO",universoDto.getEquipo().getEquipo());
	        parametros.put("UBICACION",universoDto.getObsubica());
	        parametros.put("MARCA",universoDto.getMarca());
	        parametros.put("MODELO",universoDto.getModelo());
	        parametros.put("SERIE",universoDto.getSerie());
	        parametros.put("INVENTARIO",universoDto.getInventario());
	        parametros.put("FOLIO",programacionDto.getFolio());
	        parametros.put("DESCRIPCION_SERVICIO", actaEntregaDto.getDescripcionCompletaServicio());
	        parametros.put("KIT_REFACCIONES",actaEntregaDto.getKitRefUtilizadas());
	        parametros.put("HORAS_SERVICIO", actaEntregaDto.getHorasRealServicio().toString());
	        parametros.put("TIPO_MANTENIMIENTO",programacionDto.getTipoServicio().getDescripcion());
	        parametros.put("RECOMENDACIONES",actaEntregaDto.getRecomendaciones());
	        parametros.put("OBSERVACIONES","");
	        parametros.put("BaseDir", reportFile.getParentFile());

	        if(actaEntregaDto.getFechaFinalServicio().after(solicitudDto.getFechaFin())){
	        	long diasAtraso = Fecha.calcularDiferenciaDiasEntreFechas(solicitudDto.getFechaFin(), actaEntregaDto.getFechaFinalServicio());
	        	parametros.put("DIAS_ATRASO",String.valueOf(diasAtraso));
	        }else{
	        	parametros.put("DIAS_ATRASO","0");
	        }

	        if(actaEntregaDto.getMantenimientoExitoso()==1){
	        	parametros.put("CONDICIONES_EQUIPO","FUNCIONANDO");
	        }else{
	        	parametros.put("CONDICIONES_EQUIPO","NO FUNCIONANDO");
	        }
	        
	        if(tipoVisor == 1){
		        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parametros, new JREmptyDataSource());
		        JRExporter exporter = new JRPdfExporter();
		        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaArchivoTemporal + "actaEntrega.pdf");
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoTemporal + "actaEntrega.pdf");
		        JasperViewer.viewReport(jasperPrint,false);
		        
		        jasperPrint = null;
		        exporter = null;	        	        	
	        }
	        else{
		        byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, new JREmptyDataSource());
		        FacesContext facesContext = FacesContext.getCurrentInstance();
		        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		        response.setHeader("Content-Disposition", "attachment; filename=\"actaEntrega.pdf\"");
				response.setContentType("application/pdf");	
				response.setContentLength(bytes.length);
				ServletOutputStream os = response.getOutputStream();
				os.write(bytes, 0, bytes.length);
		        os.flush();
				os.close();
				FacesContext.getCurrentInstance().renderResponse();  
				FacesContext.getCurrentInstance().responseComplete();
	        }

        } catch (Exception e) {
            log.error("error",e);
        }
 
        return null;
    }

	public void setRutaArchivoTemporal(String rutaArchivoTemporal) {
		this.rutaArchivoTemporal = rutaArchivoTemporal;
	}
}
