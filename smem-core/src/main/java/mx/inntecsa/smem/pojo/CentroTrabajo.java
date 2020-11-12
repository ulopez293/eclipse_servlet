package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusCentroTrabajo;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cat_centro_trabajo")
public class CentroTrabajo implements java.io.Serializable {

	private int idCentroTrabajo;
	private UnidadRegional unidadRegional;
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
	private Set<Universo> universos = new HashSet<Universo>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_centro_trabajo", unique = true, nullable = false)
	public int getIdCentroTrabajo() {
		return this.idCentroTrabajo;
	}

	public void setIdCentroTrabajo(int idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad_regional", nullable = false)
	public UnidadRegional getUnidadRegional() {
		return unidadRegional;
	}

	public void setUnidadRegional(UnidadRegional unidadRegional) {
		this.unidadRegional = unidadRegional;
	}

	@Column(name = "urct", nullable = false, length = 50)
	public String getUrct() {
		return this.urct;
	}

	public void setUrct(String urct) {
		this.urct = urct;
	}

	@Column(name = "descripcion", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "responsable", length = 200)
	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	@Column(name = "telefono", length = 20)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "estatus")
	public EstatusCentroTrabajo getEstatus() {
		return this.estatus;
	}

	public void setEstatus(EstatusCentroTrabajo estatus) {
		this.estatus = estatus;
	}

	@Column(name = "direccion", length = 250)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "residente_cargo", length = 50)
	public String getResidenteCargo() {
		return this.residenteCargo;
	}

	public void setResidenteCargo(String residenteCargo) {
		this.residenteCargo = residenteCargo;
	}

	@Column(name = "residente_correo", length = 100)
	public String getResidenteCorreo() {
		return this.residenteCorreo;
	}

	public void setResidenteCorreo(String residenteCorreo) {
		this.residenteCorreo = residenteCorreo;
	}

	@Column(name = "residente_jefe", length = 100)
	public String getResidenteJefe() {
		return this.residenteJefe;
	}

	public void setResidenteJefe(String residenteJefe) {
		this.residenteJefe = residenteJefe;
	}

	@Column(name = "residente_telefono_jefe", length = 50)
	public String getResidenteTelefonoJefe() {
		return this.residenteTelefonoJefe;
	}

	public void setResidenteTelefonoJefe(String residenteTelefonoJefe) {
		this.residenteTelefonoJefe = residenteTelefonoJefe;
	}

	@Column(name = "residente_correo_jefe", length = 100)
	public String getResidenteCorreoJefe() {
		return this.residenteCorreoJefe;
	}

	public void setResidenteCorreoJefe(String residenteCorreoJefe) {
		this.residenteCorreoJefe = residenteCorreoJefe;
	}

	@Column(name = "residente_cargo_jefe", length = 100)
	public String getResidenteCargoJefe() {
		return this.residenteCargoJefe;
	}

	public void setResidenteCargoJefe(String residenteCargoJefe) {
		this.residenteCargoJefe = residenteCargoJefe;
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
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "centroTrabajo")
	public Set<Universo> getUniversos() {
		return this.universos;
	}

	public void setUniversos(Set<Universo> universos) {
		this.universos = universos;
	}
	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "centroTrabajo", cascade = CascadeType.ALL)
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}
