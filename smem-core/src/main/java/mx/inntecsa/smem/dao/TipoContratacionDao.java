package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.TipoContratacion;

@SuppressWarnings("rawtypes")
public interface TipoContratacionDao extends GenericDao {
	
	public List<TipoContratacion> getTiposContrataciones();
	public List<TipoContratacion> getTiposContratacionesPorEstatus(Estatus estatus);
	public List<TipoContratacion> getTiposContratacionesPorNombre(String string);
	public List<TipoContratacion> getTiposContratacionesPorNombre(String string,Integer idTipoContratacion);
	public TipoContratacion getTiposContratacionPorId(Integer idTipoContrato);

}
