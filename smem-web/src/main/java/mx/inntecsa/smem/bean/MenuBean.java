package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Clase (Bean) utilizada para la para el redireccionamiento de las
 * páginas HTML mediante peticiones Ajax.
 * @version 1.0, 10/12/2012.
 * @author INNTECSA.
 */

@SessionScoped
@ManagedBean
public class MenuBean implements Serializable {
	
	private static final long serialVersionUID = 2147465158474626557L; // Serialización de la clase
	private String contentView; // nombre de la página
	private Logger log = Logger.getLogger(MenuBean.class); // Bitácora	
	

    public void init() {        
        contentView = "/views/inicio/work.xhtml"; 
    }
	

	
	/**
    * Metodo para retornar el nombre de la pagina HTML.
    * @return nombre de la pagina (reporte, catalgo, etc.)
    */	
	public String getContentView() {
		return contentView;
	}	
	
	/**
    * Metodo para inicializar el nombre de la pagina.
    * @param contentView, nombre de la pagina.
    */	
	public void setContentView(String contentView) {
		this.contentView = contentView;
	}	
	
	/**
    * M�todo para asignar el nombre de alguna pagina HTML 
    * a la variable contentView.
    */	
	public void asignateView(){
		//obteniendo el contexto de faces
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.contentView = getPageView(facesContext);		 
		log.info(">>>vista " + this.contentView);
	}
		
	/**
    * Metodo para obtener del contexto de faces algun parametro,
    * en este caso obtendremos el parametro pagina, el cual nos servir 
    * para redireccionar las páginas seleccionadas del men
    * @param facesContext, contexto de Faces.
    * @return valor del atributo seleccionado (en este caso es el nombre 
    * de la pagina).
    */	
	public String getPageView(FacesContext facesContext){	 
		//obteniendo la lista de parámetros del contexto de faces.
		Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
		return params.get("pagina");	 
	}
	
}
