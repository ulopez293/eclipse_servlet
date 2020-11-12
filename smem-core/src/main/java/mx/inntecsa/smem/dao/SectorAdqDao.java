package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SectorAdq;

@SuppressWarnings("rawtypes")
public interface SectorAdqDao extends GenericDao {
	
	public List<SectorAdq> getSectoresAdq();
	public List<SectorAdq> getSectoresAdqPorEstatus(Estatus estatus);
	public List<SectorAdq> getSectorAdquisicionPorNombre(String nombre);
	public List<SectorAdq> getSectorAdquisicionPorNombre(String nombre, Integer idSectorAdq);
	public SectorAdq getSectorAdquisicionPorId(Integer idSectorAdq);

}
