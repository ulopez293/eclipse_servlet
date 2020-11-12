package mx.inntecsa.smem.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import mx.inntecsa.smem.enums.Estatus;

@Entity
@Table(name = "acta_entrega_recepcion")
public class ActaEntregaRecepcion implements java.io.Serializable {

	private int idActaEntregaRecepcion;
	private ProgramacionServicio programacionServicio;
	private String tecnicoEnviado;
	private Date fechaInicioServicio;
	private Date fechaFinalServicio;
	private Integer mantenimientoExitoso;
	private String atribuible;
	private Float horasRealServicio;
	private String kitRefUtilizadas;
	private String recomendaciones;
	private String descripcionCompletaServicio;
	private String responsableEquipo;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;

	@Id
	@GeneratedValue
	@Column(name = "id_acta_entrega_recepcion", unique = true, nullable = false)
	public int getIdActaEntregaRecepcion() {
		return this.idActaEntregaRecepcion;
	}

	public void setIdActaEntregaRecepcion(int idActaEntregaRecepcion) {
		this.idActaEntregaRecepcion = idActaEntregaRecepcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programacion_servicio")
	public ProgramacionServicio getProgramacionServicio() {
		return this.programacionServicio;
	}

	public void setProgramacionServicio(ProgramacionServicio programacionServicio) {
		this.programacionServicio = programacionServicio;
	}

	@Column(name = "tecnico_enviado", length = 200)
	public String getTecnicoEnviado() {
		return this.tecnicoEnviado;
	}

	public void setTecnicoEnviado(String tecnicoEnviado) {
		this.tecnicoEnviado = tecnicoEnviado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio_servicio", length = 23)
	public Date getFechaInicioServicio() {
		return this.fechaInicioServicio;
	}

	public void setFechaInicioServicio(Date fechaInicioServicio) {
		this.fechaInicioServicio = fechaInicioServicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_final_servicio", length = 23)
	public Date getFechaFinalServicio() {
		return this.fechaFinalServicio;
	}

	public void setFechaFinalServicio(Date fechaFinalServicio) {
		this.fechaFinalServicio = fechaFinalServicio;
	}

	@Column(name = "mantenimiento_exitoso")
	public Integer getMantenimientoExitoso() {
		return this.mantenimientoExitoso;
	}

	public void setMantenimientoExitoso(Integer mantenimientoExitoso) {
		this.mantenimientoExitoso = mantenimientoExitoso;
	}

	@Column(name = "atribuible", length = 50)
	public String getAtribuible() {
		return this.atribuible;
	}

	public void setAtribuible(String atribuible) {
		this.atribuible = atribuible;
	}

	@Column(name = "horas_real_servicio", precision = 24, scale = 0)
	public Float getHorasRealServicio() {
		return this.horasRealServicio;
	}

	public void setHorasRealServicio(Float horasRealServicio) {
		this.horasRealServicio = horasRealServicio;
	}

	@Column(name = "kit_ref_utilizadas", length = 250)
	public String getKitRefUtilizadas() {
		return this.kitRefUtilizadas;
	}

	public void setKitRefUtilizadas(String kitRefUtilizadas) {
		this.kitRefUtilizadas = kitRefUtilizadas;
	}

	@Column(name = "recomendaciones", length = 500)
	public String getRecomendaciones() {
		return this.recomendaciones;
	}

	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}

	@Column(name = "descripcion_completa_servicio", length = 500)
	public String getDescripcionCompletaServicio() {
		return this.descripcionCompletaServicio;
	}

	public void setDescripcionCompletaServicio(
			String descripcionCompletaServicio) {
		this.descripcionCompletaServicio = descripcionCompletaServicio;
	}

	@Column(name = "responsable_equipo", length = 200)
	public String getResponsableEquipo() {
		return this.responsableEquipo;
	}

	public void setResponsableEquipo(String responsableEquipo) {
		this.responsableEquipo = responsableEquipo;
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
