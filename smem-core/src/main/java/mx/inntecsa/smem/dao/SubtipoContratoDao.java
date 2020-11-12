package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SubtipoContrato;

@SuppressWarnings("rawtypes")
public interface SubtipoContratoDao extends GenericDao {
	
	public List<SubtipoContrato> getSubtiposContratos();
	public List<SubtipoContrato> getSubtiposContratosPorEstatus(Estatus estatus);
	public List<SubtipoContrato> getSubtipoContratosPorNombre(String string);
	public List<SubtipoContrato> getSubtipoContratosPorNombre(String string, Integer idSubtipoContrato);
	public SubtipoContrato getSubtiposContratosPorId(Integer idSubtipoContrato);

}
