/**
 * 
 */
package mx.inntecsa.smem.dto;

import java.io.Serializable;

/**
 * @author INNTECSA
 *
 */

@SuppressWarnings("unchecked")
public class CentroTrabajoBloqueadoDto implements Serializable {

	private int id;
	private String entidad;
	private String urct;
	private String unidad;
	private String supervisor;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return the urct
	 */
	public String getUrct() {
		return urct;
	}
	/**
	 * @param urct the urct to set
	 */
	public void setUrct(String urct) {
		this.urct = urct;
	}
	/**
	 * @return the unidad
	 */
	public String getUnidad() {
		return unidad;
	}
	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	/**
	 * @return the supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}
	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CentroTrabajoBloqueadoDto ["
				+ (entidad != null ? "entidad=" + entidad + ", " : "") + "id="
				+ id + ", "
				+ (supervisor != null ? "supervisor=" + supervisor + ", " : "")
				+ (unidad != null ? "unidad=" + unidad + ", " : "")
				+ (urct != null ? "urct=" + urct : "") + "]";
	}
	
	
}
