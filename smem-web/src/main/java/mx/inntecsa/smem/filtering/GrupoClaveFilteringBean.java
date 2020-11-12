package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GrupoClaveFilteringBean extends CatalogoFilteringBean implements Serializable {

	private static final long serialVersionUID = -5680001353441022222L;
	
	private String grupoClaveFilter;
	private String grupoFilter;
	private String claveFilter;
	private String equipoFilter;

	public String getGrupoFilter() {
		return grupoFilter;
	}

	
	public void setGrupoFilter(String grupoFilter) {
		this.grupoFilter = grupoFilter;
	}
	
	public String getClaveFilter() {
		return claveFilter;
	}

	
	public void setClaveFilter(String claveFilter) {
		this.claveFilter = claveFilter;
	}
	
	
	public String getGrupoClaveFilter() {
		return grupoClaveFilter;
	}

	
	public void setGrupoClaveFilter(String grupoClaveFilter) {
		this.grupoClaveFilter = grupoClaveFilter;
	}

	public String getEquipoFilter() {
		return equipoFilter;
	}

	
	public void setEquipoFilter(String equipoFilter) {
		this.equipoFilter = equipoFilter;
	}
}
