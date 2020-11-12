package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CentroTrabajoFilteringBean extends CatalogoFilteringBean implements Serializable {
	
	private static final long serialVersionUID = 7177761094874289421L;
	private String unidadRegionalFilter;
	
	public String getUnidadRegionalFilter() {
		return unidadRegionalFilter;
	}
	
	public void setUnidadRegionalFilter(String unidadRegionalFilter) {
		this.unidadRegionalFilter = unidadRegionalFilter;
	}
	
	
}
