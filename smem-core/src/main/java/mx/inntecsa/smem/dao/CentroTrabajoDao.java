package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.CentroTrabajo;

@SuppressWarnings("rawtypes")
public interface CentroTrabajoDao extends GenericDao {
	
	public List<CentroTrabajo> getCentrosTrabajo();
	public List<CentroTrabajo> getCentrosTrabajoPorEstatus(Estatus estatus);
	public CentroTrabajo getCentroTrabajoPorDescripcion(String descripcion);
	public List<CentroTrabajo> getCentrosTrabajoPoridUnidadRegional(Integer idUnidadRegional);
	public CentroTrabajo getCentroTrabajo(Integer idCentroTrabajo);
	public CentroTrabajo getCentroTrabajoPorURCT(String urct);
	public List<CentroTrabajo> getCentrosTrabajoActivosPorURCT(String urct);
	public List<CentroTrabajo> getCentrosTrabajoActivosPorURCT(String urct,Integer idCentroTrabajo);
}
