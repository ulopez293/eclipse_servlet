package mx.inntecsa.smem.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bitacora")
public class Bitacora implements java.io.Serializable {

	
	private int idBitacora;
	private Date fecha;
	private String operacion;
	private String descripcion;
	private String usuario;
	private String ipUsuario;

	@Id
	@GeneratedValue
	@Column(name = "id_bitacora", unique = true, nullable = false)
	public int getIdBitacora() {
		return this.idBitacora;
	}

	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false, length = 23)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "operacion", nullable = false, length = 20)
	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	@Column(name = "descripcion", nullable = false, length = 300)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "usuario", length = 50)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "ip_usuario", length = 50)
	public String getIpUsuario() {
		return this.ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

}
