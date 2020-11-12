package mx.inntecsa.smem.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import mx.inntecsa.smem.enums.Estatus;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
* Clase (POJO) mapeada con la tabla de cat_sector_adq. 
* @author INNTECSA
* @version 1.0
*/
@Entity
@Table(name = "cat_sector_adq")
public class SectorAdq implements java.io.Serializable {

	private int idSectorAdq;
	private String sectorAdq;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Estatus baja;
	private Set<Universo> universos = new HashSet<Universo>(0);

	@Id
	@GeneratedValue
	@Column(name = "id_sector_adq", unique = true, nullable = false)
	public int getIdSectorAdq() {
		return this.idSectorAdq;
	}

	public void setIdSectorAdq(int idSectorAdq) {
		this.idSectorAdq = idSectorAdq;
	}

	@Column(name = "sector_adq", nullable = false, length = 50)
	public String getSectorAdq() {
		return this.sectorAdq;
	}

	public void setSectorAdq(String sectorAdq) {
		this.sectorAdq = sectorAdq;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sectorAdq")
	public Set<Universo> getUniversos() {
		return this.universos;
	}

	public void setUniversos(Set<Universo> universos) {
		this.universos = universos;
	}

}
