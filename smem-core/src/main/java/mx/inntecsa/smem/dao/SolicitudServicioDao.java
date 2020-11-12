package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SolicitudServicio;

@SuppressWarnings("rawtypes")
public interface SolicitudServicioDao extends GenericDao {
	
	public List<SolicitudServicio> getSolicitudesServicios();
	public List<SolicitudServicio> getSolicitudesServiciosByEstatus(Estatus estatus);

}
