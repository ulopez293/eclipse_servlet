package mx.inntecsa.smem.dto;

import java.util.Date;

public class DetalleFacturaDto implements java.io.Serializable {
	
	private Integer idequipo;
	private String equipo;
	private String marca;
	private String modelo;
	private String serie;
	private String inventario;
	private Integer idtiposervicio;
	private String folio;
	private String tiposervicio;
	private String centrotrabajo;
	private String numerocontrato;
	private Integer consecutivocontrato;
	private Date fechainicial;
	private Date fechalimite;
	private Date fechareal;
	private Double penalizacion;
	private Double descuento;
	private String porcentajepenalizacion;
	private Float costo;
	private String ejercicio;
	private Long diasatraso;
	private String textocosto;
	private String textopenalizacion;
	private String textodescuento;
	
	public Integer getIdequipo() {
		return idequipo;
	}
	public void setIdequipo(Integer idequipo) {
		this.idequipo = idequipo;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getInventario() {
		return inventario;
	}
	public void setInventario(String inventario) {
		this.inventario = inventario;
	}
	public Integer getIdtiposervicio() {
		return idtiposervicio;
	}
	public void setIdtiposervicio(Integer idtiposervicio) {
		this.idtiposervicio = idtiposervicio;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getTiposervicio() {
		return tiposervicio;
	}
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	public String getCentrotrabajo() {
		return centrotrabajo;
	}
	public void setCentrotrabajo(String centrotrabajo) {
		this.centrotrabajo = centrotrabajo;
	}
	public String getNumerocontrato() {
		return numerocontrato;
	}
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}
	public Integer getConsecutivocontrato() {
		return consecutivocontrato;
	}
	public void setConsecutivocontrato(Integer consecutivocontrato) {
		this.consecutivocontrato = consecutivocontrato;
	}
	public Date getFechainicial() {
		return fechainicial;
	}
	public void setFechainicial(Date fechainicial) {
		this.fechainicial = fechainicial;
	}
	public Date getFechalimite() {
		return fechalimite;
	}
	public void setFechalimite(Date fechalimite) {
		this.fechalimite = fechalimite;
	}
	public Date getFechareal() {
		return fechareal;
	}
	public void setFechareal(Date fechareal) {
		this.fechareal = fechareal;
	}
	public Double getPenalizacion() {
		return penalizacion;
	}
	public void setPenalizacion(Double penalizacion) {
		this.penalizacion = penalizacion;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public String getPorcentajepenalizacion() {
		return porcentajepenalizacion;
	}
	public void setPorcentajepenalizacion(String porcentajepenalizacion) {
		this.porcentajepenalizacion = porcentajepenalizacion;
	}
	public Float getCosto() {
		return costo;
	}
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public Long getDiasatraso() {
		return diasatraso;
	}
	public void setDiasatraso(Long diasatraso) {
		this.diasatraso = diasatraso;
	}
	public String getTextocosto() {
		return textocosto;
	}
	public void setTextocosto(String textocosto) {
		this.textocosto = textocosto;
	}
	public String getTextopenalizacion() {
		return textopenalizacion;
	}
	public void setTextopenalizacion(String textopenalizacion) {
		this.textopenalizacion = textopenalizacion;
	}
	public String getTextodescuento() {
		return textodescuento;
	}
	public void setTextodescuento(String textodescuento) {
		this.textodescuento = textodescuento;
	}
	
}
