package mx.inntecsa.smem.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Contrato;


public class ProveedorDto extends GenericDto implements java.io.Serializable {

	private int idProveedor;
	private String proveedor;
	private String rfc;
	private String telefono;
	private String email;
	private String direccion;
	private String representanteLegal;
	private String telefonoRepresentanteLegal;
	private String nombreGerenteServicio;
	private String telefonoGerenteServicio;
	private String emailGerenteServicio;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<Contrato> contratos = new HashSet<Contrato>(0);

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getTelefonoRepresentanteLegal() {
		return telefonoRepresentanteLegal;
	}

	public void setTelefonoRepresentanteLegal(String telefonoRepresentanteLegal) {
		this.telefonoRepresentanteLegal = telefonoRepresentanteLegal;
	}

	public String getNombreGerenteServicio() {
		return nombreGerenteServicio;
	}

	public void setNombreGerenteServicio(String nombreGerenteServicio) {
		this.nombreGerenteServicio = nombreGerenteServicio;
	}

	public String getTelefonoGerenteServicio() {
		return telefonoGerenteServicio;
	}

	public void setTelefonoGerenteServicio(String telefonoGerenteServicio) {
		this.telefonoGerenteServicio = telefonoGerenteServicio;
	}

	public String getEmailGerenteServicio() {
		return emailGerenteServicio;
	}

	public void setEmailGerenteServicio(String emailGerenteServicio) {
		this.emailGerenteServicio = emailGerenteServicio;
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

	public Set<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Proveedor con id: " + this.getIdProveedor() + " rfc: " + this.getRfc();
	}

}
