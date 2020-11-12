package mx.inntecsa.smem.dao;


import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.UnidadRegional;



@SuppressWarnings("rawtypes")
public interface UnidadRegionalDao extends GenericDao {
	
	public List<UnidadRegional> getUnidadesRegionales();
	public List<UnidadRegional> getUnidadesRegionalesPorEstatus(Estatus estatus);
	public List<UnidadRegional> getUnidadesRegionalesPorIdEntidad(Integer idEntidad);

}
