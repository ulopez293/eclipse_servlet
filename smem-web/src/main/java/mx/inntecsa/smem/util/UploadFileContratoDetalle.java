package mx.inntecsa.smem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.ContratoDetalleService;
import mx.inntecsa.smem.service.ContratoService;
import mx.inntecsa.smem.service.UniversoService;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UploadFileContratoDetalle {
	
	@Autowired
	private ContratoService contratoService; // Servicio de contratosDetalles
	
	@Autowired
	private ContratoDetalleService contratoDetalleService; // Servicio de contratosDetalles
	
	@Autowired
	private UniversoService universoService; // Servicio de Universos	
	
	private Logger log = Logger.getLogger(UploadFileContratoDetalle.class);
	private List cellDataList = new ArrayList();
	private StringBuffer errores = new StringBuffer();
	private static ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	public static final String FOLDER = "ArchivosDetalle";	

	public StringBuffer getErrores() {
		return errores;
	}

	public void setErrores(StringBuffer errores) {
		this.errores = errores;
	}

	public void cargarArchivo(FileAux file) {
		
		try {
			errores = new StringBuffer();
			//obteniendo el path
			ServletContext servletContext = (ServletContext) externalContext.getContext();
			String absolutePath = servletContext.getRealPath("/");
			log.info(">>>path de la aplicacion " + absolutePath);
			String filePath = absolutePath + FOLDER;
			
			//crear folder si no existe
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			
			log.info(">>>PATH " + filePath);
			log.info(">>>Nombre el archivo " + file.getName());
			//creando el archivo
			String fileName = file.getName();
			if (!("").equals(fileName)) {
				File newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(file.getData());
				fos.flush();
				fos.close();
					
				//obteniendo archivo
				FileInputStream fileInputStream = new FileInputStream(newFile);
				POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
				
				//creando la hoja
				HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
				HSSFSheet hssfSheet = workBook.getSheetAt(0);
				Iterator rowIterator = hssfSheet.rowIterator();
				
				//Se inicializara le arreglo de celdas para que no contenga los datos de documentos anteriores
				cellDataList = new ArrayList();
				
				//obteniendo los datos de la hoja
				while (rowIterator.hasNext()) {
					HSSFRow hssfRow = (HSSFRow) rowIterator.next();				
					List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
					
				    for(int i = 0; i <= 18; i++) {
				        HSSFCell hssfCell = hssfRow.getCell(i);
				        
				        if(hssfCell == null) {
				        	hssfCell = hssfRow.createCell(i);
				        }			        			        
				        cellTempList.add(hssfCell);			      
				    }					
					cellDataList.add(cellTempList);
				}
			}
			
		} catch (Exception e) {
			log.info(">>>Error ", e);
			e.printStackTrace();
		}
	}	
	public void validaArchivo() {
		
		log.info(">>>INICIO validaArchivo*** ");
		List<ContratoDetalleDto> contratosDetallesDto = new ArrayList<ContratoDetalleDto>();
		
		for(int tuple = 1; tuple < cellDataList.size(); tuple++) {
			List cellTempList = (List) cellDataList.get(tuple);
			contratosDetallesDto.add(validaFormato(cellTempList));			
		}	
		
		log.info("***>>>LISTA DE ERRORES:");
		log.info(errores.toString());
	}
	
	private ContratoDetalleDto validaFormato(List renglon) {
		log.info("*** CONTRATO DETALLE VALIDA FORMATO ***");
		
		ContratoDetalleDto contratoDetalleDto = new ContratoDetalleDto(); 
		
		for(Object objeto: renglon) {
			HSSFCell celda = (HSSFCell) objeto;
			contratoDetalleDto.setFila(celda.getRowIndex() + 1);
//			log.info(">>>Valor " + celda.toString() + ", Columna " +  celda.getColumnIndex() 
//				+ ", Fila " +  (celda.getRowIndex() + 1));			
			
			switch (Columna.getColumna(celda.getColumnIndex())) {
			case ID_CONTRATO:
				Integer idContrato = validaEntero(celda);
				if(idContrato != null) {
					ContratoDto contratoDto = new ContratoDto();
					contratoDto.setIdContrato(idContrato);
					contratoDetalleDto.setContrato(contratoDto);
				} else {
					contratoDetalleDto.setAgregarExcel(false);
				}
				break;

			case ID_UNIVERSO:
				Integer idUniverso = validaEntero(celda);
				if(idUniverso != null) {
					UniversoDto universoDto = new UniversoDto();
					universoDto.setIdUniverso(idUniverso);
					contratoDetalleDto.setUniverso(universoDto);
				} else {
					contratoDetalleDto.setAgregarExcel(false);
				}
				break;

			case PERIODO:
				Integer periodo = validaEntero(celda);
				if(periodo != null) {
					contratoDetalleDto.setPeriodo(periodo);
				} 
				break;

			case CONSECUTIVO:
				Integer consecutivo = validaEntero(celda);
				if(consecutivo != null) {
					contratoDetalleDto.setConsecutivoContrato(consecutivo);
				} 
				break;

			case INICIO_PERIODO:
				Date inicioPeriodo = validaFecha(celda);
				if(inicioPeriodo != null) {
					contratoDetalleDto.setInicioPeriodo(inicioPeriodo);
				} 
				break;

			case FIN_PERIODO:
				Date finPeriodo = validaFecha(celda);
				if(finPeriodo != null) {
					contratoDetalleDto.setFinPeriodo(finPeriodo);
				} 
				break;

			case MES_ENERO:
				Float enero = validaMonto(celda);
				if(enero != null) {
					contratoDetalleDto.setEnero(enero);
				} 
				break;

			case MES_FEBRERO:
				Float febrero = validaMonto(celda);
				if(febrero != null) {
					contratoDetalleDto.setFebrero(febrero);
				} 
				break;

			case MES_MARZO:
				Float marzo = validaMonto(celda);
				if(marzo != null) {
					contratoDetalleDto.setMarzo(marzo);				
				} 
				break;

			case MES_ABRIL:
				Float abril = validaMonto(celda);
				if(abril != null) {
					contratoDetalleDto.setAbril(abril);
				} 
				break;

			case MES_MAYO:
				Float mayo = validaMonto(celda);
				if(mayo != null) {
					contratoDetalleDto.setMayo(mayo);
				} 
				break;

			case MES_JUNIO:
				Float junio = validaMonto(celda);				
				if(junio != null) {
					contratoDetalleDto.setJunio(junio);
				} 
				break;

			case MES_JULIO:
				Float julio = validaMonto(celda);
				if(julio != null) {
					contratoDetalleDto.setJulio(julio);
				} 
				break;

			case MES_AGOSTO:
				Float agosto = validaMonto(celda);
				if(agosto != null) {
					contratoDetalleDto.setAgosto(agosto);
				} 
				break;

			case MES_SEPTIEMBRE:
				Float septiembre = validaMonto(celda);
				if(septiembre != null) {
					contratoDetalleDto.setSeptiembre(septiembre);
				} 
				break;

			case MES_OCTUBRE:
				Float octubre = validaMonto(celda);
				if(octubre != null) {
					contratoDetalleDto.setOctubre(octubre);
				} 
				break;

			case MES_NOVIEMBRE:
				Float noviembre = validaMonto(celda);
				if(noviembre != null) {
					contratoDetalleDto.setNoviembre(noviembre);
				} 
				break;

			case MES_DICIEMBRE:
				Float diciembre = validaMonto(celda);
				if(diciembre != null) {
					contratoDetalleDto.setDiciembre(diciembre);
				} 
				break;

			case VIGENCIA:
				if(!"".equals(celda.getStringCellValue())) {
					Date inicioVigencia = validaFecha(celda);
					
					if (inicioVigencia != null) {
						contratoDetalleDto.setInicioVigencia(inicioVigencia);
					}
				}	
				break;

			default:
				log.info(">>>Columna fuera de Layout");
				break;
			}
		}
		
		if(contratoDetalleDto.getContrato() != null				
			&& validaNegocio(contratoDetalleDto)) {
			contratoDetalleDto.setFechaRegistro(new Date());
			contratoDetalleDto.setBaja(Estatus.ACTIVO);
			log.info(">>>Guardando!!!");
			contratoDetalleService.guardarContratoDetalle(contratoDetalleDto);
		}
		
		return contratoDetalleDto;
	}
	
	private Boolean validaNegocio(ContratoDetalleDto contratoDetalleDto) {
		
		 boolean IsValidado = true;  
		//Validando el idContrato
		
		log.info(" INICIO VALIDA NEGOCIO ***");
		ContratoDto contratoDto = contratoService.getContratoPorIdContrato(contratoDetalleDto.getContrato().getIdContrato());
		UniversoDto universoDto = universoService.getUniversoPorIdUniverso(contratoDetalleDto.getUniverso().getIdUniverso());
		
		//Validando el idUniverso
		if(universoDto == null) {
			errores.append("-Error en la fila " + contratoDetalleDto.getFila());
			errores.append(". El ID de Universo no existe. ");
			errores.append("\n");			
			IsValidado = false;
		   }		
		else{
		//Valida Fecha de garantia y requiere servicio
				Date utilDate = new Date();
				if(universoDto.getFechaGarantia()!=null){
				
				log.info("VALIDACION FECHA DE GARANTIA *** fecha garantia ["+universoDto.getFechaGarantia()+"] fecha actual ["+ utilDate+ "]");
				log.info("VALIDACION FECHA DE GARANTIA *** boolean ["+universoDto.getFechaGarantia().after(utilDate)+ "]");
				
				if(universoDto.getFechaGarantia().after(utilDate) ){
					errores.append("-Error en la fila " + (contratoDetalleDto.getFila()) + " Aun se encuentra en periodo de garantia");
					errores.append("\n");
					IsValidado = false;
				  }
				
				log.info("VALIDACION REQ SERVICIO *** ["+universoDto.getRequiereServicio().getIdEstatusRequiereServicio()+"] ");
				if(universoDto.getRequiereServicio().getIdEstatusRequiereServicio()!=null&&universoDto.getRequiereServicio().getIdEstatusRequiereServicio()==0){			
					errores.append("-Error en la fila " + (contratoDetalleDto.getFila()) + " No requiere servicio");
					errores.append("\n");
					IsValidado = false;
					}
			}
		}	
		if(contratoDto == null) {
			errores.append("-Error en la fila " + contratoDetalleDto.getFila());
			errores.append(". El ID del Contrato no existe. ");
			errores.append("\n");
			IsValidado = false;	
		}
		
		//Validando detalle con periodo y numero de contrato
		    ContratoDetalleDto VigenteContratoDetalleDto = contratoDetalleService.getContratoDetallePorParametros(contratoDetalleDto
			.getContrato().getIdContrato(), contratoDetalleDto.getPeriodo(), contratoDetalleDto.getUniverso().getIdUniverso());
		if(VigenteContratoDetalleDto != null) {
			errores.append("-Error en la fila " + contratoDetalleDto.getFila());
			errores.append(". Ya existe un detalle con el mismo periodo " + contratoDetalleDto.getPeriodo() + ", para el mismo " +
				"Universo y Número de Contrato. ");
			errores.append("\n");			
			IsValidado = false;
		}
		log.info("FIN VALIDA NEGOCIO ["+IsValidado+"]");
				  
		return IsValidado;		
	 }
		
	private Integer validaEntero(HSSFCell valor) {
		try {			
			Float numero = new Float(valor.toString());
			return numero.intValue();
			
		} catch(NumberFormatException e) {
			log.error(">>>Error al Castear un Entero");
			errores.append("-Error en la fila " + (valor.getRowIndex() + 1) + ", columna " 
				+ Columna.getColumna(valor.getColumnIndex()).getDescripcion());
			errores.append(". No es un Valor Numérico. ");
			errores.append("\n");
			return null;
		}		
	}
	
	private Date validaFecha(HSSFCell valor) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = dateFormat.parse(valor.toString());			
			return fecha;
			
		} catch(Exception e) {	
			log.error(">>>Error al Castear una Fecha");
			errores.append("-Error en la fila " + (valor.getRowIndex() + 1) + ", columna " 
				+ Columna.getColumna(valor.getColumnIndex()).getDescripcion());
			errores.append(". No es una Fecha Válida. ");
			errores.append("\n");			
			return null;
		}
	}
	
	private Float validaMonto(HSSFCell valor) {		
		if(valor.toString() != null) {
			try {			
				Float monto = new Float(valor.toString());
				return monto;
				
			} catch(Exception e) {
				log.error(">>>Error al Castear un Monto ");
				errores.append("-Error en la fila " + (valor.getRowIndex() + 1) + ", columna " 
					+ Columna.getColumna(valor.getColumnIndex()).getDescripcion());
				errores.append(". No es un Monto Válido. ");
				errores.append("\n ");				
				return null;
			}
		}			
		return 0F;
	}

}
