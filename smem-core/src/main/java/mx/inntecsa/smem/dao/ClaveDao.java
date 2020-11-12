package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Clave;

@SuppressWarnings("rawtypes")
public interface ClaveDao extends GenericDao {
	
	public List<Clave> getClaves();
	public List<Clave> getClavesByEstatus(Estatus estatus);
	public List<Clave> getClavesConGrupoClavePorIdGrupoYIdEquipo(Integer idEquipo, Integer idGrupo);
	public List<Clave> getClavesConGrupoClavePorIdClave(Integer idClave);
	public List<Clave> getClavesPorNombre(String clave);
	public Clave getClavePorId(Integer idClave);
	public List<Clave> getClavesActivas();
	public List<Clave> getClavesPorNombre(String clave, Integer idClave);

}
