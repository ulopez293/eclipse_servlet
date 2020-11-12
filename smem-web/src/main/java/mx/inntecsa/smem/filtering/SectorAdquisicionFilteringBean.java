package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SectorAdquisicionFilteringBean extends CatalogoFilteringBean implements Serializable {

	private static final long serialVersionUID = -5680001353441022132L;
	
	private String sectorAdquisicionFilter;

	public String getSectorAdquisicionFilter() {
		return sectorAdquisicionFilter;
	}

	
	public void setSectorAdquisicionFilter(String sectorAdquisicionFilter) {
		this.sectorAdquisicionFilter = sectorAdquisicionFilter;
	}
	
}
