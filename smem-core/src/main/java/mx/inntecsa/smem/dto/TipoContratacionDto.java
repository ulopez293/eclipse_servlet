package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


public class TipoContratacionDto extends GenericDto implements java.io.Serializable {

	private int idTipoContratacion;
	private String tipoContratacion;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ContratoDto> contratos = new HashSet<ContratoDto>(0);

	public int getIdTipoContratacion() {
		return idTipoContratacion;
	}

	public void setIdTipoContratacion(int idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
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

	public Set<ContratoDto> getContratos() {
		return contratos;
	}

	public void setContratos(Set<ContratoDto> contratos) {
		this.contratos = contratos;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Tipo Contrato con id:  "+ this.getIdTipoContratacion()  + " descripcion: " + this.getTipoContratacion();
	}

}
