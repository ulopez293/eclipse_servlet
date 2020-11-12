package mx.inntecsa.smem.util;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DownloadFile {
	
	private static Log log = LogFactory.getLog(DownloadFile.class);
	private static ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	private static final String FOLDER = "archivosExcel";	
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private List<String> header = new ArrayList<String>(Arrays.asList("ID CONTRATO", "ID DETALLLE", "PERIODO",	
		"CONSECUTIVO", "INICIO PERIODO", "FIN PERIODO", "ENERO", "FEBRERO", "MARZO", "ABRIL",	
		"MAYO",	"JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"));;
	private String inputFile;
	private File file;	
	
	public DownloadFile(String inputFile, List<String> header) {
		this.inputFile = inputFile;
		this.header = header;
	}
	
	public DownloadFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void write(List<List<String>> registros)  {
		try {
			ServletContext servletContext = (ServletContext) externalContext.getContext();
			String absolutePath = servletContext.getRealPath("/");
			log.info(">>>path de la aplicacion " + absolutePath);
			
			// creando folder en caso de que no exista folder
			File folder = new File(absolutePath + FOLDER);
			if (!folder.exists()) {			
				folder.mkdir();
				log.info(">>>Creando folder " + FOLDER);
			}
			String path = absolutePath + FOLDER + "/";
			//creando y utilizadno archivo
			file = new File(path + inputFile);
			if (file.exists()){
				file.delete();
			}
			
			if (file.createNewFile()){
		        log.info(">>>archivo creado! " + path);
		    }else{
		    	log.info(">>>Error al crear archivo! " + path);
		    }
			
			//creando la hoja de trabajo
			WorkbookSettings wbSettings = new WorkbookSettings();
			wbSettings.setLocale(new Locale("en", "EN"));
			WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
			workbook.createSheet("Reporte", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			//crear encabezados y estilos
			createLabel(excelSheet);
			//escribir el contenido
			createContent(excelSheet, registros);
			//ajustar contenido
			AutoSizedColumn(excelSheet);
			//escribir registros
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			log.info(">>>Error : " , e);
		}
	}

	private void createLabel(WritableSheet sheet) throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 9);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);
		times.setBorder(Border.ALL, BorderLineStyle.THIN);

		// Create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
			WritableFont.ARIAL, 9, WritableFont.BOLD, false,
			UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);
		timesBoldUnderline.setBackground(Colour.GRAY_25);		
		timesBoldUnderline.setBorder(Border.ALL, BorderLineStyle.THIN);
		timesBoldUnderline.setAlignment(Alignment.CENTRE);

		// agregando encabezados
		for (int x = 0; x < header.size(); x++) {
			addCaption(sheet, x, 0, header.get(x));
		}
	}

	private void createContent(WritableSheet sheet, List<List<String>> registros) throws WriteException, RowsExceededException {
		int row = 1;
		for (List<String> items : registros) {
			int column = 0;

			for (String data : items) {
				addLabel(sheet, column, row, data);
				column++;
			}
			row++;
		}
	}

	private void addCaption(WritableSheet sheet, int column, int row, String s) throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	@SuppressWarnings("unused")
	private void addNumber(WritableSheet sheet, int column, int row, Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s) throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}
	
	public void AutoSizedColumn(WritableSheet sheet) {
		for(int x = 0; x < header.size(); x ++) {
		    CellView cell = sheet.getColumnView(x);
		    cell.setAutosize(true);
		    sheet.setColumnView(x, cell);
		}
	}
	
	public File getFileExcel() {
		return file;
	}
	
	public byte[] getByteFile() {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(">>>ERROR DE ARCHIVO ", e);
		}

		return bFile;
	}

}
