package mx.inntecsa.smem.util;

/**
 * Enum para definir los tipos de iconos que
 * se pueden asociar a un mensaje de alerta. 
 * @version 1.0, 18/02/2013.
 * @author INNTECSA.
 */

public enum Icono {
	
	//enums
	SUCCESS("/resources/images/icons/ok.png"),
	ERROR("/resources/images/icons/error.png"),
	WARNING("/resources/images/icons/warning.jpg");
	
	private String ruta; //ruta de cada imagen
	
	/**
    * Metodo para inicializar el la ruta de algun tipo
    * de icono.
    * @param ruta, direccion de la imagen.
    */	
	private Icono(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Metodo que regresa la ruta de la imagen.
	 * @return ruta, ruta de la imagen o icono.	  
	 */
	public String getRuta() {
		return ruta;
	}

}
