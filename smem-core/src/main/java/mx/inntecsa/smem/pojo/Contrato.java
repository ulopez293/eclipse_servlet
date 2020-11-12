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
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.TipoServicio;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "contrato")
public class Contrato implements java.io.Serializable {

	private Integer idContrato;
	private Proveedor proveedor;
	private SubtipoContrato subtipoContrato;
	private TipoContratacion tipoContratacion;
	private TipoServicio tipoServicio;
	private String numeroContrato;
	private String ejercicio;
	private Date fechaSuscripcion;
	private String oficioSufPresupuestal;
	private String partida;
	private Float montoMinimo;
	private Float montoMaximo;
	private Date vigenciaInicioContrato;
	private Date vigenciaFinContrato;
	private EstatusContrato estatus;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<ContratoDetalle> contratosDetalle = new HashSet<ContratoDetalle>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_contrato", unique = true, nullable = false)
	public Integer getIdContrato() {
		return this.idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", nullable = false)
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_subtipo_contrato")
	public SubtipoContrato getSubtipoContrato() {
		return subtipoContrato;
	}

	public void setSubtipoContrato(SubtipoContrato subtipoContrato) {
		this.subtipoContrato = subtipoContrato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_contratacion", nullable = false)
	public TipoContratacion getTipoContratacion() {
		return tipoContratacion;
	}	

	public void setTipoContratacion(TipoContratacion tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	@Column(name = "id_tipo_servicio")
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Column(name = "numero_contrato", length = 50)
	public String getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	@Column(name = "ejercicio")
	public String getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_suscripcion", length = 23)
	public Date getFechaSuscripcion() {
		return this.fechaSuscripcion;
	}

	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}

	@Column(name = "oficio_suf_presupuestal", length = 100)
	public String getOficioSufPresupuestal() {
		return this.oficioSufPresupuestal;
	}

	public void setOficioSufPresupuestal(String oficioSufPresupuestal) {
		this.oficioSufPresupuestal = oficioSufPresupuestal;
	}

	@Column(name = "partida", length = 50)
	public String getPartida() {
		return this.partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	@Column(name = "monto_minimo", precision = 24, scale = 0)
	public Float getMontoMinimo() {
		return this.montoMinimo;
	}

	public void setMontoMinimo(Float montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	@Column(name = "monto_maximo", precision = 24, scale = 0)
	public Float getMontoMaximo() {
		return this.montoMaximo;
	}

	public void setMontoMaximo(Float montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vigencia_inicio_contrato", length = 23)
	public Date getVigenciaInicioContrato() {
		return this.vigenciaInicioContrato;
	}

	public void setVigenciaInicioContrato(Date vigenciaInicioContrato) {
		this.vigenciaInicioContrato = vigenciaInicioContrato;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vigencia_fin_contrato", length = 23)
	public Date getVigenciaFinContrato() {
		return this.vigenciaFinContrato;
	}

	public void setVigenciaFinContrato(Date vigenciaFinContrato) {
		this.vigenciaFinContrato = vigenciaFinContrato;
	}
	
	@Column(name = "estatus")
	public EstatusContrato getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusContrato estatus) {
		this.estatus = estatus;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato")
	public Set<ContratoDetalle> getContratosDetalle() {
		return contratosDetalle;
	}

	public void setContratosDetalle(Set<ContratoDetalle> contratosDetalle) {
		this.contratosDetalle = contratosDetalle;
	}

}
