package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


public class SectorAdqDto extends GenericDto implements java.io.Serializable {

	private int idSectorAdq;
	private String sectorAdq;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<UniversoDto> universos = new HashSet<UniversoDto>(0);

	public int getIdSectorAdq() {
		return idSectorAdq;
	}

	public void setIdSectorAdq(int idSectorAdq) {
		this.idSectorAdq = idSectorAdq;
	}

	public String getSectorAdq() {
		return sectorAdq;
	}

	public void setSectorAdq(String sectorAdq) {
		this.sectorAdq = sectorAdq;
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
		return "SectorAdq con id: " + this.getIdSectorAdq()  + " descripcion: " + this.getSectorAdq();
	}

}
