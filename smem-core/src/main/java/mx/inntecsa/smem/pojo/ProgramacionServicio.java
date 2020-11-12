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
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


/**
* Clase (POJO) mapeada con la tabla de programacion_servicio. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "programacion_servicio")
public class ProgramacionServicio implements java.io.Serializable {

	private int idProgramacionServicio;
	private SolicitudServicio solicitudServicio;
	private TipoServicio tipoServicio;
	private String folio;
	private EstatusServicio estatus;
	private Integer nprogramacion;
	private String tecnicoProv;
	private String telTecnico;
	private String observaciones;
	private String noControl;
	private Date fechaVisita;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ActaEntregaRecepcion> actasEntregaRecepciones = new HashSet<ActaEntregaRecepcion>(0);
	private Set<EntregaProveedor> entregasProveedores = new HashSet<EntregaProveedor>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_programacion_servicio", unique = true, nullable = false)
	public int getIdProgramacionServicio() {
		return this.idProgramacionServicio;
	}

	public void setIdProgramacionServicio(int idProgramacionServicio) {
		this.idProgramacionServicio = idProgramacionServicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitud_servicio")
	public SolicitudServicio getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}

	@Column(name = "id_tipo_servicio")		
	public TipoServicio getTipoServicio() {
		return this.tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Column(name = "folio", nullable = false, length = 50)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "estatus")
	public EstatusServicio getEstatus() {
		return this.estatus;
	}

	public void setEstatus(EstatusServicio estatus) {
		this.estatus = estatus;
	}

	@Column(name = "nprogramacion")
	public Integer getNprogramacion() {
		return this.nprogramacion;
	}

	public void setNprogramacion(Integer nprogramacion) {
		this.nprogramacion = nprogramacion;
	}

	@Column(name = "tecnico_prov", length = 250)
	public String getTecnicoProv() {
		return this.tecnicoProv;
	}

	public void setTecnicoProv(String tecnicoProv) {
		this.tecnicoProv = tecnicoProv;
	}

	@Column(name = "tel_tecnico", length = 50)
	public String getTelTecnico() {
		return this.telTecnico;
	}

	public void setTelTecnico(String telTecnico) {
		this.telTecnico = telTecnico;
	}

	@Column(name = "observaciones", length = 500)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "no_control", length = 50)
	public String getNoControl() {
		return this.noControl;
	}

	public void setNoControl(String noControl) {
		this.noControl = noControl;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_visita", length = 23)
	public Date getFechaVisita() {
		return this.fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programacionServicio")
	public Set<ActaEntregaRecepcion> getActasEntregaRecepciones() {
		return actasEntregaRecepciones;
	}

	public void setActasEntregaRecepciones(
			Set<ActaEntregaRecepcion> actasEntregaRecepciones) {
		this.actasEntregaRecepciones = actasEntregaRecepciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programacionServicio")
	public Set<EntregaProveedor> getEntregasProveedores() {
		return entregasProveedores;
	}

	public void setEntregasProveedores(Set<EntregaProveedor> entregasProveedores) {
		this.entregasProveedores = entregasProveedores;
	}
	
}
