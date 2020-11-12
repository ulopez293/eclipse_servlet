package mx.inntecsa.smem.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.GrupoClave;



public class EquipoDto extends GenericDto implements Serializable{
	
	private int idEquipo;
	private String equipo;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<GrupoClave> grupoClaves = new HashSet<GrupoClave>(0);

	public EquipoDto() {
	}

	public EquipoDto(int idEquipo, String equipo) {
		this.idEquipo = idEquipo;
		this.equipo = equipo;
	}

	public EquipoDto(int idEquipo, String equipo, Date fechaRegistro,
		Date fechaModificacion, Date fechaBaja, Estatus baja,
		Set<GrupoClave> grupoClaves) {
		this.idEquipo = idEquipo;
		this.equipo = equipo;
		this.fechaRegistro = fechaRegistro;
		this.fechaModificacion = fechaModificacion;
		this.fechaBaja = fechaBaja;
		this.baja = baja;
		this.grupoClaves = grupoClaves;
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getEquipo() {
		return this.equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Estatus getBaja() {
		return this.baja;
	}

	public void setBaja(Estatus baja) {
		this.baja = baja;
	}

	public Set<GrupoClave> getGrupoClaves() {
		return grupoClaves;
	}

	public void setGrupoClaves(Set<GrupoClave> grupoClaves) {
		this.grupoClaves = grupoClaves;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Equipo con id: " + this.getIdEquipo()  + " nombre: " + this.getEquipo();
	}	
}
