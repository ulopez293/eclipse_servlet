package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Grupo;

@SuppressWarnings("rawtypes")
public interface GrupoDao extends GenericDao {
	
	public List<Grupo> getGrupos();
	public List<Grupo> getGruposByEstatus(Estatus estatus);
	public List<Grupo> getGruposConGrupoClavePorIdEquipo(Integer idEquipo);
	public List<Grupo> getGruposActivos();
	public List<Grupo> getGruposPorNombre(String nombre);
	public List<Grupo> getGruposPorNombre(String nombre, Integer idGrupo);
	public Grupo getGrupoPorId(Integer idGrupo);

}
