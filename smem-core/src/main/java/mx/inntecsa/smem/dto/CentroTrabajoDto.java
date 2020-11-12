package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusCentroTrabajo;

@SuppressWarnings("unchecked")
public class CentroTrabajoDto extends GenericDto  implements java.io.Serializable {

	private int idCentroTrabajo;
	private UnidadRegionalDto unidadRegional =  new UnidadRegionalDto();
	private String urct;
	private String descripcion;
	private String responsable;
	private String telefono;
	private EstatusCentroTrabajo estatus;
	private String direccion;
	private String residenteCargo;
	private String residenteCorreo;
	private String residenteJefe;
	private String residenteTelefonoJefe;
	private String residenteCorreoJefe;
	private String residenteCargoJefe;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<UniversoDto> universos = new HashSet<UniversoDto>(0);

	public int getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(int idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public UnidadRegionalDto getUnidadRegional() {
		return unidadRegional;
	}

	public void setUnidadRegional(UnidadRegionalDto unidadRegional) {
		this.unidadRegional = unidadRegional;
	}

	public String getUrct() {
		return urct;
	}

	public void setUrct(String urct) {
		this.urct = urct;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public EstatusCentroTrabajo getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusCentroTrabajo estatus) {
		this.estatus = estatus;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getResidenteCargo() {
		return residenteCargo;
	}

	public void setResidenteCargo(String residenteCargo) {
		this.residenteCargo = residenteCargo;
	}

	public String getResidenteCorreo() {
		return residenteCorreo;
	}

	public void setResidenteCorreo(String residenteCorreo) {
		this.residenteCorreo = residenteCorreo;
	}

	public String getResidenteJefe() {
		return residenteJefe;
	}

	public void setResidenteJefe(String residenteJefe) {
		this.residenteJefe = residenteJefe;
	}

	public String getResidenteTelefonoJefe() {
		return residenteTelefonoJefe;
	}

	public void setResidenteTelefonoJefe(String residenteTelefonoJefe) {
		this.residenteTelefonoJefe = residenteTelefonoJefe;
	}

	public String getResidenteCorreoJefe() {
		return residenteCorreoJefe;
	}

	public void setResidenteCorreoJefe(String residenteCorreoJefe) {
		this.residenteCorreoJefe = residenteCorreoJefe;
	}

	public String getResidenteCargoJefe() {
		return residenteCargoJefe;
	}

	public void setResidenteCargoJefe(String residenteCargoJefe) {
		this.residenteCargoJefe = residenteCargoJefe;
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
		return "Centro de Trabajo con id: " + this.getIdCentroTrabajo()  + " URCT: " + this.getUrct();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CentroTrabajoDto ["
				+ (baja != null ? "baja=" + baja + ", " : "")
				+ (descripcion != null ? "descripcion=" + descripcion + ", "
						: "")
				+ (direccion != null ? "direccion=" + direccion + ", " : "")
				+ (estatus != null ? "estatus=" + estatus + ", " : "")
				+ (fechaBaja != null ? "fechaBaja=" + fechaBaja + ", " : "")
				+ (fechaModificacion != null ? "fechaModificacion="
						+ fechaModificacion + ", " : "")
				+ (fechaRegistro != null ? "fechaRegistro=" + fechaRegistro
						+ ", " : "")
				+ "idCentroTrabajo="
				+ idCentroTrabajo
				+ ", "
				+ (residenteCargo != null ? "residenteCargo=" + residenteCargo
						+ ", " : "")
				+ (residenteCargoJefe != null ? "residenteCargoJefe="
						+ residenteCargoJefe + ", " : "")
				+ (residenteCorreo != null ? "residenteCorreo="
						+ residenteCorreo + ", " : "")
				+ (residenteCorreoJefe != null ? "residenteCorreoJefe="
						+ residenteCorreoJefe + ", " : "")
				+ (residenteJefe != null ? "residenteJefe=" + residenteJefe
						+ ", " : "")
				+ (residenteTelefonoJefe != null ? "residenteTelefonoJefe="
						+ residenteTelefonoJefe + ", " : "")
				+ (responsable != null ? "responsable=" + responsable + ", "
						: "")
				+ (telefono != null ? "telefono=" + telefono + ", " : "")
				+ (unidadRegional != null ? "unidadRegional=" + unidadRegional
						+ ", " : "")
				+ (universos != null ? "universos=" + universos + ", " : "")
				+ (urct != null ? "urct=" + urct : "") + "]";
	}
	
}
