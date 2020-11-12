package mx.inntecsa.smem.dto;


import java.io.Serializable;
import java.util.Date;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.enums.TipoUsuario;


@SuppressWarnings("serial")
public class UsuarioDto extends GenericDto implements Serializable {

	private int idUsuario;
	private String usuario;
	private String nombreUsuario;
	private String contrasenia;
	private String confirmarContrasenia;
	private String contraseniaAuxiliar;
	private Integer idProveedor;
	private Integer idCentroTrabajo;
	private Rol rol;
	private TipoUsuario tipoUsuario;
	private CentroTrabajoDto centroTrabajo = new CentroTrabajoDto();
	private ProveedorDto proveedor = new ProveedorDto();
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	public CentroTrabajoDto getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(CentroTrabajoDto centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}		

	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	
	public ProveedorDto getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDto proveedor) {
		this.proveedor = proveedor;
	}	

	public String getContraseniaAuxiliar() {
		return contraseniaAuxiliar;
	}

	public void setContraseniaAuxiliar(String contraseniaAuxiliar) {
		this.contraseniaAuxiliar = contraseniaAuxiliar;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Usuario con nombre: " + this.getUsuario();
	}
	
}
