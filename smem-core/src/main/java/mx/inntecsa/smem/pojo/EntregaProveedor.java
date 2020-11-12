package mx.inntecsa.smem.pojo;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "entrega_proveedor")
public class EntregaProveedor implements java.io.Serializable {

	private int idEntregaProvedor;
	private ProgramacionServicio programacionServicio;
	private Date fechaFin;
	private String comentarios;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	@Id
	@Column(name = "id_entrega_provedor", unique = true, nullable = false)
	public int getIdEntregaProvedor() {
		return this.idEntregaProvedor;
	}

	public void setIdEntregaProvedor(int idEntregaProvedor) {
		this.idEntregaProvedor = idEntregaProvedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programacion_servicio")
	public ProgramacionServicio getProgramacionServicio() {
		return this.programacionServicio;
	}

	public void setProgramacionServicio(
		ProgramacionServicio programacionServicio) {
		this.programacionServicio = programacionServicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 23)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "comentarios", length = 500)
	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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


}
