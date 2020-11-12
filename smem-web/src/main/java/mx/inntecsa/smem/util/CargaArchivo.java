package mx.inntecsa.smem.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;


public final class CargaArchivo {

	private static ExternalContext externalContext = FacesContext
			.getCurrentInstance().getExternalContext();
	public static final String FOLDER = "ArchivosOrdenesCompra";
	private static final Logger LOG = Logger.getLogger(CargaArchivo.class); // Bitácora

	public static void upLoadFile(FileAux file) {
		// obteniendo el pat
		ServletContext servletContext = (ServletContext) externalContext
				.getContext();
		String absolutePath = servletContext.getRealPath("/");
		LOG.info(">>>path de la aplicacion " + absolutePath);
		String filePath = absolutePath + FOLDER;

		try {
			// crear folder si no existe
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdir();
			}

			String fileName = file.getName();
			if (!("").equals(fileName)) {
				File newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(file.getData());
				fos.flush();
				fos.close();
				// nombre = newFile.getName();
			}
		} catch (IOException e) {
			LOG.info(">>>Error al tratar de cargar el archivo ", e);

		}
	}
}
