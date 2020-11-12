package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Entidad;

@SuppressWarnings("rawtypes")
public interface EntidadDao extends GenericDao {
	
	public List<Entidad> getEntidades();
	public List<Entidad> getEntidadesPorEstatus(Estatus estatus);

}
