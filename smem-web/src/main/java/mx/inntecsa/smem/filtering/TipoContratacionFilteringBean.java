package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TipoContratacionFilteringBean extends CatalogoFilteringBean implements Serializable {

	private static final long serialVersionUID = -5680001353441022132L;
	
	private String tipoContratacionFilter;

	public String getTipoContratacionFilter() {
		return tipoContratacionFilter;
	}

	
	public void setTipoContratacionFilter(String tipoContratacionFilter) {
		this.tipoContratacionFilter = tipoContratacionFilter;
	}
	
}
