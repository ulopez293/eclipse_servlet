package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


public class GrupoDto extends GenericDto implements java.io.Serializable {

	private Integer idGrupo;
	private String grupo;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<GrupoClaveDto> gruposClaves = new HashSet<GrupoClaveDto>(0);

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
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

	public Set<GrupoClaveDto> getGruposClaves() {
		return gruposClaves;
	}

	public void setGruposClaves(Set<GrupoClaveDto> gruposClaves) {
		this.gruposClaves = gruposClaves;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Grupo con id: " + this.getIdGrupo() + " nombre: " + this.getGrupo();
	}

}
