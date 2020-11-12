package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


/**
* Clase (POJO) mapeada con la tabla de proveedor. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "proveedor")
public class Proveedor implements java.io.Serializable {

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

	@Id
	@GeneratedValue
	@Column(name = "id_proveedor", unique = true, nullable = false)
	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column(name = "proveedor", nullable = false, length = 500)
	public String getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Column(name = "rfc", length = 50)
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "telefono", length = 50)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "direccion", length = 500)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "representante_legal", length = 250)
	public String getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	@Column(name = "telefono_representante_legal", length = 50)
	public String getTelefonoRepresentanteLegal() {
		return this.telefonoRepresentanteLegal;
	}

	public void setTelefonoRepresentanteLegal(String telefonoRepresentanteLegal) {
		this.telefonoRepresentanteLegal = telefonoRepresentanteLegal;
	}

	@Column(name = "nombre_gerente_servicio", length = 150)
	public String getNombreGerenteServicio() {
		return this.nombreGerenteServicio;
	}

	public void setNombreGerenteServicio(String nombreGerenteServicio) {
		this.nombreGerenteServicio = nombreGerenteServicio;
	}

	@Column(name = "telefono_gerente_servicio", length = 50)
	public String getTelefonoGerenteServicio() {
		return this.telefonoGerenteServicio;
	}

	public void setTelefonoGerenteServicio(String telefonoGerenteServicio) {
		this.telefonoGerenteServicio = telefonoGerenteServicio;
	}

	@Column(name = "email_gerente_servicio", length = 100)
	public String getEmailGerenteServicio() {
		return this.emailGerenteServicio;
	}

	public void setEmailGerenteServicio(String emailGerenteServicio) {
		this.emailGerenteServicio = emailGerenteServicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", length = 23)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion", length = 23)
	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_baja", length = 23)
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "baja")	
	public Estatus getBaja() {
		return this.baja;
	}

	public void setBaja(Estatus baja) {
		this.baja = baja;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor")
	public Set<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

}
