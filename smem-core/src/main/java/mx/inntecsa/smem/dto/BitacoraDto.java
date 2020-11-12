package mx.inntecsa.smem.dto;

import java.util.Date;
@SuppressWarnings("serial")

public class BitacoraDto implements java.io.Serializable {

	private int idBitacora;
	private Date fecha;
	private String operacion;
	private String descripcion;
	private String usuario;
	private String ipUsuario;

	public BitacoraDto() {
	}

	public BitacoraDto(int idBitacora, Date fecha, String operacion,
		String descripcion) {
		this.idBitacora = idBitacora;
		this.fecha = fecha;
		this.operacion = operacion;
		this.descripcion = descripcion;
	}

	public BitacoraDto(int idBitacora, Date fecha, String operacion,
		String descripcion, String usuario, String ipUsuario) {
		this.idBitacora = idBitacora;
		this.fecha = fecha;
		this.operacion = operacion;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.ipUsuario = ipUsuario;
	}

	public int getIdBitacora() {
		return this.idBitacora;
	}

	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIpUsuario() {
		return this.ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

}
