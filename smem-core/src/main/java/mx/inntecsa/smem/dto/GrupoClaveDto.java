package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;

@SuppressWarnings("unchecked")
public class GrupoClaveDto extends GenericDto implements java.io.Serializable {

	private Integer idGrupoClave;
	private EquipoDto equipo= new EquipoDto();
	private ClaveDto clave= new ClaveDto();
	private GrupoDto grupo= new GrupoDto();
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<UniversoDto> universos = new HashSet<UniversoDto>(0);

	public Integer getIdGrupoClave() {
		return idGrupoClave;
	}

	public void setIdGrupoClave(Integer idGrupoClave) {
		this.idGrupoClave = idGrupoClave;
	}

	public EquipoDto getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoDto equipo) {
		this.equipo = equipo;
	}

	public ClaveDto getClave() {
		return clave;
	}

	public void setClave(ClaveDto clave) {
		this.clave = clave;
	}

	public GrupoDto getGrupo() {
		return this.grupo;
	}

	public void setGrupo(GrupoDto grupo) {
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

	public Set<UniversoDto> getUniversos() {
		return universos;
	}

	public void setUniversos(Set<UniversoDto> universos) {
		this.universos = universos;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Grupo Clave con id: " + this.getIdGrupoClave();
	}

}
