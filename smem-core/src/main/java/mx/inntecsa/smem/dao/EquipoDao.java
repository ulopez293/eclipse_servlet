package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.pojo.Equipo;

@SuppressWarnings("rawtypes")
public interface EquipoDao extends GenericDao {
	
	public List<Equipo> getEquipos();
	public List<Equipo> getEquiposActivos();
	public List<Equipo> getEquiposConGrupoClave();
}
