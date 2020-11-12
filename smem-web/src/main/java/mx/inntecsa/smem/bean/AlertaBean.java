package mx.inntecsa.smem.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import mx.inntecsa.smem.util.Icono;

/**
 * Clase (Bean) utilizada para el despliegue de
 * alertas o mensajes de xito, error o precaucion.
 * @version 1.0
 * @author INNTECSA.
 */
 
@Component("alertaBean")
public class AlertaBean implements Serializable {


	private static final long serialVersionUID = 4814277468545881621L; // Objeto serializado.
	private String mensaje; // mensaje que llevar la alerta.
	private String imagen; // ruta de la imagen que acompanar al mensaje.

	
	/**
    * Metodo para retornar el mensaje de la alerta.
    * @return mensaje, nombre del mensaje.
    */		
	public String getMensaje() {
		return mensaje;
	}

	
	/**
    * Metodo para inicializar el mensaje de la alerta.
    * @param mensaje, mensaje que se desplegar en la alerta.
    */	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	/**
    * Metodo retornar la ruta de la imagen.
    * @return Imagen, ruta del icono a mostrar.
    */		
	public String getImagen() {
		return imagen;
	}

	
	/**
    * Metodo para inicializar la ruta de la imagen
    * que se mostrar en la alerta.
    * @param imagen, enum con 2 argumentos, 
    * tipo de mensaje y ruta de la imagen.
    */	
	public void setImagen(Icono imagen) {
		this.imagen = imagen.getRuta();
	}

}
