package mx.inntecsa.smem.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.enums.TipoUsuario;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
* Clase (POJO) mapeada con la tabla de usuario. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {

	private int idUsuario;
	private String usuario;
	private String nombreUsuario;
	private String contrasenia;
	private Integer idProveedor;
	private Integer idCentroTrabajo;
	private String rol;
	private TipoUsuario tipoUsuario;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private CentroTrabajo centroTrabajo;
	private Proveedor proveedor;
	
	@Id
	@GeneratedValue
	@Column(name = "id_usuario", unique = true, nullable = false)
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "usuario", nullable = false, length = 20)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}		

	@Column(name = "nombre_usuario", nullable = false, length = 80)
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "contrasenia", nullable = false, length = 100)
	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Column(name = "id_proveedor")
	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column(name = "id_centro_trabajo")
	public Integer getIdCentroTrabajo() {
		return this.idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	@Column(name = "rol", length = 20)	
	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Column(name = "tipo_usuario", nullable = false)
	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	@OneToOne
	@JoinColumn(name = "id_centro_trabajo", insertable=false, updatable=false)
	public CentroTrabajo getCentroTrabajo() {
		return centroTrabajo;
	}

	
	public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	@OneToOne
	@JoinColumn(name = "id_proveedor", insertable=false, updatable=false)		
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	

}
