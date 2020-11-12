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
import mx.inntecsa.smem.enums.EstatusRequiereServicio;
import mx.inntecsa.smem.enums.Funcionalidad;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


/**
* Clase (POJO) mapeada con la tabla de universo. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "universo")
public class Universo implements java.io.Serializable {

	private long idUniverso;
	private Integer idGrupoClaveInt;
	private CentroTrabajo centroTrabajo;
	private SectorAdq sectorAdq;
	private Funcionalidad funcionalidad;
	private Especialidad especialidad;
	private GrupoClave grupoClave;
	private NivelAtencion nivelAtencion;
	private Equipo equipo;
	private String inventario;
	private String marca;
	private String modelo;
	private String serie;
	private String cabm;
	private String obsubica;
	private Date fechaInstalacion;
	private Date fechaApertura;
	private Date fechaGarantia;
	private EstatusRequiereServicio requiereServicio;
	private String observaciones;
	private String numeroLicitacion;
	private String partida;
	private String noContratoAdq;
	private String actualizacionTecnologica;
	private String proveedorVentaEquipo;
	private String anioAdq;
	private String precioEquipoSinIva;
	private boolean esSibobim;
	private boolean estaActualizado;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private String numeroBitacora;
	
	private Set<ContratoDetalle> contratosDetalle = new  HashSet<ContratoDetalle>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_universo", unique = true, nullable = false)
	public long getIdUniverso() {
		return this.idUniverso;
	}

	public void setIdUniverso(long idUniverso) {
		this.idUniverso = idUniverso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sector_adq", nullable = false)
	public SectorAdq getSectorAdq() {
		return sectorAdq;
	}

	public void setSectorAdq(SectorAdq sectorAdq) {
		this.sectorAdq = sectorAdq;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo_clave", insertable=false, updatable=false)
	public GrupoClave getGrupoClave() {
		return grupoClave;
	}

	public void setGrupoClave(GrupoClave grupoClave) {
		this.grupoClave = grupoClave;
	}
	
	@Column(name="id_grupo_clave")
	public Integer getIdGrupoClaveInt() {
		return idGrupoClaveInt;
	}

	public void setIdGrupoClaveInt(Integer idGrupoClaveInt) {
		this.idGrupoClaveInt = idGrupoClaveInt;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipo")		
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Column(name = "marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name = "modelo")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_centro_trabajo", nullable = false)
	public CentroTrabajo getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialidad", nullable = false)
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nivel_atencion", nullable = false)
	public NivelAtencion getNivelAtencion() {
		return this.nivelAtencion;
	}

	public void setNivelAtencion(NivelAtencion nivelAtencion) {
		this.nivelAtencion = nivelAtencion;
	}
	
	@Column(name = "id_funcionalidad")
	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	@Column(name = "inventario")
	public String getInventario() {
		return this.inventario;
	}

	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	@Column(name = "serie", length = 50)
	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@Column(name = "cabm")
	public String getCabm() {
		return cabm;
	}

	public void setCabm(String cabm) {
		this.cabm = cabm;
	}

	@Column(name = "obsubica", length = 250)
	public String getObsubica() {
		return this.obsubica;
	}

	public void setObsubica(String obsubica) {
		this.obsubica = obsubica;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_instalacion", length = 23)
	public Date getFechaInstalacion() {
		return this.fechaInstalacion;
	}

	public void setFechaInstalacion(Date fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_apertura", length = 23)
	public Date getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Column(name = "requiere_servicio")	
	public EstatusRequiereServicio getRequiereServicio() {
		return this.requiereServicio;
	}

	public void setRequiereServicio(EstatusRequiereServicio requiereServicio) {
		this.requiereServicio = requiereServicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_garantia", length = 23)
	public Date getFechaGarantia() {
		return this.fechaGarantia;
	}

	public void setFechaGarantia(Date fechaGarantia) {
		this.fechaGarantia = fechaGarantia;
	}

	@Column(name = "observaciones")
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "numero_licitacion", length = 100)
	public String getNumeroLicitacion() {
		return this.numeroLicitacion;
	}

	public void setNumeroLicitacion(String numeroLicitacion) {
		this.numeroLicitacion = numeroLicitacion;
	}

	@Column(name = "partida", length = 50)
	public String getPartida() {
		return this.partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	@Column(name = "no_contrato_adq")
	public String getNoContratoAdq() {
		return this.noContratoAdq;
	}

	public void setNoContratoAdq(String noContratoAdq) {
		this.noContratoAdq = noContratoAdq;
	}

	@Column(name = "actualizacion_tecnologica")
	public String getActualizacionTecnologica() {
		return this.actualizacionTecnologica;
	}

	public void setActualizacionTecnologica(String actualizacionTecnologica) {
		this.actualizacionTecnologica = actualizacionTecnologica;
	}

	@Column(name = "proveedor_venta_equipo", length = 100)
	public String getProveedorVentaEquipo() {
		return this.proveedorVentaEquipo;
	}

	public void setProveedorVentaEquipo(String proveedorVentaEquipo) {
		this.proveedorVentaEquipo = proveedorVentaEquipo;
	}

	@Column(name = "anio_adq", length = 30)
	public String getAnioAdq() {
		return this.anioAdq;
	}

	public void setAnioAdq(String anioAdq) {
		this.anioAdq = anioAdq;
	}

	@Column(name = "precio_equipo_sin_iva", length = 30)
	public String getPrecioEquipoSinIva() {
		return this.precioEquipoSinIva;
	}

	public void setPrecioEquipoSinIva(String precioEquipoSinIva) {
		this.precioEquipoSinIva = precioEquipoSinIva;
	}

	@Column(name = "es_sicobim")
	public boolean getEsSibobim() {
		return esSibobim;
	}

	public void setEsSibobim(boolean esSibobim) {
		this.esSibobim = esSibobim;
	}

	@Column(name = "esta_actualizado")
	public boolean getEstaActualizado() {
		return estaActualizado;
	}

	public void setEstaActualizado(boolean estaActualizado) {
		this.estaActualizado = estaActualizado;
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

	@OneToMany(fetch = FetchType.LAZY,  mappedBy="universo")
	public Set<ContratoDetalle> getContratosDetalle() {
		return contratosDetalle;
	}

	public void setContratosDetalle(Set<ContratoDetalle> contratosDetalle) {
		this.contratosDetalle = contratosDetalle;
	}

	@Column(name="numero_bitacora")
	public String getNumeroBitacora() {
		return numeroBitacora;
	}

	public void setNumeroBitacora(String numeroBitacora) {
		this.numeroBitacora = numeroBitacora;
	}
	
	
}
