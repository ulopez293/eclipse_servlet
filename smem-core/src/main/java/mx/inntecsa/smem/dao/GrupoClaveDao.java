package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.GrupoClave;

@SuppressWarnings("rawtypes")
public interface GrupoClaveDao extends GenericDao {
	
	public List<GrupoClave> getGruposClaves();
	public List<GrupoClave> getGruposClavesByEstatus(Estatus estatus);
	public List<GrupoClave> getGruposClavesPorIdEquipo(Integer idEquipo);
	public List<GrupoClave> getGruposClavesPorIdGrupo(Integer idGrupo);	
	public GrupoClave getGrupoClavePorParametros(Integer idEquipo, Integer idGrupo, Integer idClave);
	public List<GrupoClave> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo);
	public List<GrupoClave> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo, Integer idGrupoClave);
	public GrupoClave getGrupoClavePorId(Integer idGrupoClave);

}
