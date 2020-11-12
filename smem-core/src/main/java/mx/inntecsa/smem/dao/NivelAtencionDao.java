package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.NivelAtencion;

@SuppressWarnings("rawtypes")
public interface NivelAtencionDao extends GenericDao {
	
	public List<NivelAtencion> getNivelesAtencion();
	public List<NivelAtencion> getNivelesAtencionByEstatus(Estatus estatus);
	public List<NivelAtencion> getNivelesAtencionPorDescripcion(String descripcion);
	public NivelAtencion getNivelAtencionPorId(Integer idNivelAtencion);
	public List<NivelAtencion> getNivelesAtencionPorDescripcion(String descripcion, Integer idNivelAtencion);

}
