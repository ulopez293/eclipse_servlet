package mx.inntecsa.smem.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


@SuppressWarnings("serial")
public class UnidadRegionalDto extends GenericDto implements Serializable{

	private int idUnidadRegional;
	private EntidadDto entidad = new EntidadDto();
	private SupervisorDto supervisor = new SupervisorDto();
	private String unidadRegional;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<CentroTrabajoDto> centrosTrabajos = new HashSet<CentroTrabajoDto>(0);

	public int getIdUnidadRegional() {
		return idUnidadRegional;
	}

	public void setIdUnidadRegional(int idUnidadRegional) {
		this.idUnidadRegional = idUnidadRegional;
	}

	public EntidadDto getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDto entidad) {
		this.entidad = entidad;
	}

	public SupervisorDto getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(SupervisorDto supervisor) {
		this.supervisor = supervisor;
	}

	public String getUnidadRegional() {
		return unidadRegional;
	}

	public void setUnidadRegional(String unidadRegional) {
		this.unidadRegional = unidadRegional;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Estatus getBaja() {
		return baja;
	}

	public void setBaja(Estatus baja) {
		this.baja = baja;
	}

	public Set<CentroTrabajoDto> getCentrosTrabajos() {
		return centrosTrabajos;
	}

	public void setCentrosTrabajos(Set<CentroTrabajoDto> centrosTrabajos) {
		this.centrosTrabajos = centrosTrabajos;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Unidad Regional con id: " + this.getIdUnidadRegional()  + " nombre: " + this.getUnidadRegional();
	}

}
