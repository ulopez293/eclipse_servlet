package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;


public class SubtipoContratoDto extends GenericDto implements java.io.Serializable {

	private int idSubtipoContrato;
	private String subtipoContrato;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ContratoDto> contratos = new HashSet<ContratoDto>(0);

	public int getIdSubtipoContrato() {
		return idSubtipoContrato;
	}

	public void setIdSubtipoContrato(int idSubtipoContrato) {
		this.idSubtipoContrato = idSubtipoContrato;
	}

	public String getSubtipoContrato() {
		return subtipoContrato;
	}

	public void setSubtipoContrato(String subtipoContrato) {
		this.subtipoContrato = subtipoContrato;
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
		return "Subtipo Contratacion con id: " + this.getIdSubtipoContrato() + " nombre: " + this.getSubtipoContrato();
	}

}
