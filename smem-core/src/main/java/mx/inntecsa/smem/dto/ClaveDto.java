package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;

@SuppressWarnings("unchecked")
public class ClaveDto extends GenericDto implements java.io.Serializable {

	private Integer idClave;
	private String clave;
	private String nombreGenerico;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<GrupoClaveDto> grupoClaves = new HashSet<GrupoClaveDto>(0);

	public Integer getIdClave() {
		return idClave;
	}

	public void setIdClave(Integer idClave) {
		this.idClave = idClave;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
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

	public Set<GrupoClaveDto> getGrupoClaves() {
		return grupoClaves;
	}

	public void setGrupoClaves(Set<GrupoClaveDto> grupoClaves) {
		this.grupoClaves = grupoClaves;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Clave con id: " + this.getIdClave() + " nombre: " + this.getClave();
	}

}
