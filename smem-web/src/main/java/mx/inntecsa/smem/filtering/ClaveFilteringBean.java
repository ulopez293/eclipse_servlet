package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClaveFilteringBean extends CatalogoFilteringBean implements Serializable {

	private static final long serialVersionUID = -5680001353441022184L;
	
	private String nombreGenericoFilter;

	public String getNombreGenericoFilter() {
		return nombreGenericoFilter;
	}

	public void setNombreGenericoFilter(String nombreGenericoFilter) {
		this.nombreGenericoFilter = nombreGenericoFilter;
	}
	
}
