package mx.inntecsa.smem.filtering;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioFilteringBean implements Serializable {

	private static final long serialVersionUID = 4434438788393405217L;
	
	private String estatusFilter;	
	private String centroTrabajoFilter;
	private String unidadRegionalFilter;
	private String usuarioFilter;
	private String nombreUsuarioFilter;
	
	public String getEstatusFilter() {
		return estatusFilter;
	}

	public void setEstatusFilter(String estatusFilter) {
		this.estatusFilter = estatusFilter;
	}

	public String getCentroTrabajoFilter() {
		return centroTrabajoFilter;
	}

	public void setCentroTrabajoFilter(String centroTrabajoFilter) {
		this.centroTrabajoFilter = centroTrabajoFilter;
	}

	public String getUnidadRegionalFilter() {
		return unidadRegionalFilter;
	}

	public void setUnidadRegionalFilter(String unidadRegionalFilter) {
		this.unidadRegionalFilter = unidadRegionalFilter;
	}

	public String getUsuarioFilter() {
		return usuarioFilter;
	}

	public void setUsuarioFilter(String usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

	public String getNombreUsuarioFilter() {
		return nombreUsuarioFilter;
	}

	public void setNombreUsuarioFilter(String nombreUsuarioFilter) {
		this.nombreUsuarioFilter = nombreUsuarioFilter;
	}
	
	
}
