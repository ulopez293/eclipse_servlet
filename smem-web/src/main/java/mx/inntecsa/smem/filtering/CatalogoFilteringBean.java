package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
 
@ManagedBean
@ViewScoped
public class CatalogoFilteringBean implements Serializable {

	/**
     *
     */
    private static final long serialVersionUID = -5680001353441022183L;
    private String nombreFilter;
    private String estatusFilter;
	
    public String getNombreFilter() {
		return nombreFilter;
	}
	
	public void setNombreFilter(String nombreFilter) {
		this.nombreFilter = nombreFilter;
	}

	public String getEstatusFilter() {
		return estatusFilter;
	}

	public void setEstatusFilter(String estatusFilter) {
		this.estatusFilter = estatusFilter;
	}
 
}