package mx.inntecsa.smem.dto;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class UniversoContratoDto implements java.io.Serializable {

	private Integer iduniverso;
	private String equipo;
	private String marca;
	private String modelo;
	private String serie;
	private String inventario;
	private String obsubica;
	private String especialidad;
	private String sectoradq;
	private Date fechainstalacion;
	private Date fechaapertura;
	private Date fechagarantia;
	private Integer requiereservicio; //Pasarlo al ENUM
	private Integer idfuncionalidad; //Pasarlo al ENUM
	private String observaciones;
	private Integer idgrupo;
	private Integer idgrupoclave;
	private Date fechabaja;
	//private Boolean baja; //Pasarlo al ENUM
	private String numerolicitacion;
	private String partida;
	private Integer nivelatencion;
	private String numerocontratoadq;
	private String actualizaciontecnologica;
	private String proveedorventaequipo;
	private String anioadq;
	private String precioequiposiniva;
	private String numerocontrato;
	private Integer consecutivocontrato;
	private Date inicioperiodo1;
	private Date finperiodo1;
	private Date inicioperiodo2;
	private Date finperiodo2;
	private Date inicioperiodo3;
	private Date finperiodo3;
	private Date inicioperiodo4;
	private Date finperiodo4;
	private String grupo;
	private String clave;
	private String nombregenerico;
	private UniversoDto universo;
	
	public Integer getIduniverso() {
		return iduniverso;
	}
	public void setIduniverso(Integer iduniverso) {
		this.iduniverso = iduniverso;
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
	public String getObsubica() {
		return obsubica;
	}
	public void setObsubica(String obsubica) {
		this.obsubica = obsubica;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getSectoradq() {
		return sectoradq;
	}
	public void setSectoradq(String sectoradq) {
		this.sectoradq = sectoradq;
	}
	public Date getFechainstalacion() {
		return fechainstalacion;
	}
	public void setFechainstalacion(Date fechainstalacion) {
		this.fechainstalacion = fechainstalacion;
	}
	public Date getFechaapertura() {
		return fechaapertura;
	}
	public void setFechaapertura(Date fechaapertura) {
		this.fechaapertura = fechaapertura;
	}
	public Date getFechagarantia() {
		return fechagarantia;
	}
	public void setFechagarantia(Date fechagarantia) {
		this.fechagarantia = fechagarantia;
	}
	public Integer getRequiereservicio() {
		return requiereservicio;
	}
	public void setRequiereservicio(Integer requiereservicio) {
		this.requiereservicio = requiereservicio;
	}
	public Integer getIdfuncionalidad() {
		return idfuncionalidad;
	}
	public void setIdfuncionalidad(Integer idfuncionalidad) {
		this.idfuncionalidad = idfuncionalidad;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}
	public Integer getIdgrupoclave() {
		return idgrupoclave;
	}
	public void setIdgrupoclave(Integer idgrupoclave) {
		this.idgrupoclave = idgrupoclave;
	}
	public Date getFechabaja() {
		return fechabaja;
	}
	public void setFechabaja(Date fechabaja) {
		this.fechabaja = fechabaja;
	}
	public String getNumerolicitacion() {
		return numerolicitacion;
	}
	public void setNumerolicitacion(String numerolicitacion) {
		this.numerolicitacion = numerolicitacion;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public Integer getNivelatencion() {
		return nivelatencion;
	}
	public void setNivelatencion(Integer nivelatencion) {
		this.nivelatencion = nivelatencion;
	}
	public String getNumerocontratoadq() {
		return numerocontratoadq;
	}
	public void setNumerocontratoadq(String numerocontratoadq) {
		this.numerocontratoadq = numerocontratoadq;
	}
	public String getActualizaciontecnologica() {
		return actualizaciontecnologica;
	}
	public void setActualizaciontecnologica(String actualizaciontecnologica) {
		this.actualizaciontecnologica = actualizaciontecnologica;
	}
	public String getProveedorventaequipo() {
		return proveedorventaequipo;
	}
	public void setProveedorventaequipo(String proveedorventaequipo) {
		this.proveedorventaequipo = proveedorventaequipo;
	}
	public String getAnioadq() {
		return anioadq;
	}
	public void setAnioadq(String anioadq) {
		this.anioadq = anioadq;
	}
	public String getPrecioequiposiniva() {
		return precioequiposiniva;
	}
	public void setPrecioequiposiniva(String precioequiposiniva) {
		this.precioequiposiniva = precioequiposiniva;
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
	public Date getInicioperiodo1() {
		return inicioperiodo1;
	}
	public void setInicioperiodo1(Date inicioperiodo1) {
		this.inicioperiodo1 = inicioperiodo1;
	}
	public Date getFinperiodo1() {
		return finperiodo1;
	}
	public void setFinperiodo1(Date finperiodo1) {
		this.finperiodo1 = finperiodo1;
	}
	public Date getInicioperiodo2() {
		return inicioperiodo2;
	}
	public void setInicioperiodo2(Date inicioperiodo2) {
		this.inicioperiodo2 = inicioperiodo2;
	}
	public Date getFinperiodo2() {
		return finperiodo2;
	}
	public void setFinperiodo2(Date finperiodo2) {
		this.finperiodo2 = finperiodo2;
	}
	public Date getInicioperiodo3() {
		return inicioperiodo3;
	}
	public void setInicioperiodo3(Date inicioperiodo3) {
		this.inicioperiodo3 = inicioperiodo3;
	}
	public Date getFinperiodo3() {
		return finperiodo3;
	}
	public void setFinperiodo3(Date finperiodo3) {
		this.finperiodo3 = finperiodo3;
	}
	public Date getInicioperiodo4() {
		return inicioperiodo4;
	}
	public void setInicioperiodo4(Date inicioperiodo4) {
		this.inicioperiodo4 = inicioperiodo4;
	}
	public Date getFinperiodo4() {
		return finperiodo4;
	}
	public void setFinperiodo4(Date finperiodo4) {
		this.finperiodo4 = finperiodo4;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombregenerico() {
		return nombregenerico;
	}
	public void setNombregenerico(String nombregenerico) {
		this.nombregenerico = nombregenerico;
	}
	public UniversoDto getUniverso() {
		return universo;
	}
	public void setUniverso(UniversoDto universo) {
		this.universo = universo;
	}
	
}
