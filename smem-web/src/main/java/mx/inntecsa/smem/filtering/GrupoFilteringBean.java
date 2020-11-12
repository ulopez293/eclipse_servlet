package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GrupoFilteringBean extends CatalogoFilteringBean implements Serializable {

	private static final long serialVersionUID = -5680001353441022234L;
	
	private String grupoFilter;

	public String getGrupoFilter() {
		return grupoFilter;
	}

	
	public void setGrupoFilter(String grupoFilter) {
		this.grupoFilter = grupoFilter;
	}
	
}
