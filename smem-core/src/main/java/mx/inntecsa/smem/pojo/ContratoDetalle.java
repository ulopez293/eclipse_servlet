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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "contrato_detalle")
public class ContratoDetalle implements java.io.Serializable {

	private int idContratoDetalle;
	private Integer periodo;
	private Integer consecutivoContrato;
	private Date inicioPeriodo;
	private Date finPeriodo;
	private Float enero;
	private Float febrero;
	private Float marzo;
	private Float abril;
	private Float mayo;
	private Float junio;
	private Float julio;
	private Float agosto;
	private Float septiembre;
	private Float octubre;
	private Float noviembre;
	private Float diciembre;
	private Date inicioVigencia;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<SolicitudServicio> solicitudServicios = new HashSet<SolicitudServicio>(0);
	private Contrato contrato;
	private Universo universo;

	@Id
	@GeneratedValue
	@Column(name = "id_contrato_detalle", unique = true, nullable = false)
	public int getIdContratoDetalle() {
		return this.idContratoDetalle;
	}

	public void setIdContratoDetalle(int idContratoDetalle) {
		this.idContratoDetalle = idContratoDetalle;
	}

	@Column(name = "periodo")
	public Integer getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	@Column(name = "consecutivo_contrato")
	public Integer getConsecutivoContrato() {
		return this.consecutivoContrato;
	}

	public void setConsecutivoContrato(Integer consecutivoContrato) {
		this.consecutivoContrato = consecutivoContrato;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "inicio_periodo", length = 23)
	public Date getInicioPeriodo() {
		return this.inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fin_periodo", length = 23)
	public Date getFinPeriodo() {
		return this.finPeriodo;
	}

	public void setFinPeriodo(Date finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	@Column(name = "enero", precision = 24, scale = 0)
	public Float getEnero() {
		return this.enero;
	}

	public void setEnero(Float enero) {
		this.enero = enero;
	}

	@Column(name = "febrero", precision = 24, scale = 0)
	public Float getFebrero() {
		return this.febrero;
	}

	public void setFebrero(Float febrero) {
		this.febrero = febrero;
	}

	@Column(name = "marzo", precision = 24, scale = 0)
	public Float getMarzo() {
		return this.marzo;
	}

	public void setMarzo(Float marzo) {
		this.marzo = marzo;
	}

	@Column(name = "abril", precision = 24, scale = 0)
	public Float getAbril() {
		return this.abril;
	}

	public void setAbril(Float abril) {
		this.abril = abril;
	}

	@Column(name = "mayo", precision = 24, scale = 0)
	public Float getMayo() {
		return this.mayo;
	}

	public void setMayo(Float mayo) {
		this.mayo = mayo;
	}

	@Column(name = "junio", precision = 24, scale = 0)
	public Float getJunio() {
		return this.junio;
	}

	public void setJunio(Float junio) {
		this.junio = junio;
	}

	@Column(name = "julio", precision = 24, scale = 0)
	public Float getJulio() {
		return this.julio;
	}

	public void setJulio(Float julio) {
		this.julio = julio;
	}

	@Column(name = "agosto", precision = 24, scale = 0)
	public Float getAgosto() {
		return this.agosto;
	}

	public void setAgosto(Float agosto) {
		this.agosto = agosto;
	}

	@Column(name = "septiembre", precision = 24, scale = 0)
	public Float getSeptiembre() {
		return this.septiembre;
	}

	public void setSeptiembre(Float septiembre) {
		this.septiembre = septiembre;
	}

	@Column(name = "octubre", precision = 24, scale = 0)
	public Float getOctubre() {
		return this.octubre;
	}

	public void setOctubre(Float octubre) {
		this.octubre = octubre;
	}

	@Column(name = "noviembre", precision = 24, scale = 0)
	public Float getNoviembre() {
		return this.noviembre;
	}

	public void setNoviembre(Float noviembre) {
		this.noviembre = noviembre;
	}

	@Column(name = "diciembre", precision = 24, scale = 0)
	public Float getDiciembre() {
		return this.diciembre;
	}

	public void setDiciembre(Float diciembre) {
		this.diciembre = diciembre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "inicio_vigencia", length = 23)
	public Date getInicioVigencia() {
		return this.inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contratoDetalle")
	public Set<SolicitudServicio> getSolicitudServicios() {
		return this.solicitudServicios;
	}

	public void setSolicitudServicios(Set<SolicitudServicio> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contrato", nullable = false)	
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_universo", nullable = false)	
	public Universo getUniverso() {
		return universo;
	}

	public void setUniverso(Universo universo) {
		this.universo = universo;
	}

	@Override
	public String toString() {
		return "ContratoDetalle [idContratoDetalle=" + idContratoDetalle
				+ ", periodo=" + periodo + ", consecutivoContrato="
				+ consecutivoContrato + ", inicioPeriodo=" + inicioPeriodo
				+ ", finPeriodo=" + finPeriodo + ", enero=" + enero
				+ ", febrero=" + febrero + ", marzo=" + marzo + ", abril="
				+ abril + ", mayo=" + mayo + ", junio=" + junio + ", julio="
				+ julio + ", agosto=" + agosto + ", septiembre=" + septiembre
				+ ", octubre=" + octubre + ", noviembre=" + noviembre
				+ ", diciembre=" + diciembre + ", inicioVigencia="
				+ inicioVigencia + ", fechaRegistro=" + fechaRegistro
				+ ", fechaModificacion=" + fechaModificacion + ", fechaBaja="
				+ fechaBaja + ", baja=" + baja + ", solicitudServicios="
				+ solicitudServicios + ", contrato=" + contrato.getIdContrato() + ", universo="
				+ universo.getIdUniverso() + "]";
	}
	
	
	
}
