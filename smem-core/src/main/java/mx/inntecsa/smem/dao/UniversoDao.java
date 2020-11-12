package mx.inntecsa.smem.dao;


import java.util.List;

import mx.inntecsa.smem.dto.UniversoContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Universo;



@SuppressWarnings("rawtypes")
public interface UniversoDao extends GenericDao {
	
	public List<Universo> getUniversos();
	public List<Universo> getUniversosPorEstatus(Estatus estatus);
	public List<Universo> getUniversosEnContratoPorParametros(String urct, Integer idEquipo, Long identificador, String serie, String inventario);
	public List<Universo> getUniversosEnContratoSinServicios(String urct, Integer idEquipo, Long identificador, String serie, String inventario);
	public List<Universo> getUniversosPorParametros(Integer idUnidadRegional, Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso);
	public List<Universo> getUniversosPorIdCentroTrabajo(Integer idCentroTrabajo);
	public Universo getUniversoPorIdUniverso(Long idUniverso);
	public Universo getUniversoPorInventario(Integer inventario);
	public List<UniversoContratoDto> getUniversosCompletosPorIdCentroTrabajo(Integer idCentroTrabajo);
	public List<UniversoContratoDto> getUniversosCompletosPorParametros(Integer idUnidadRegional,Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso);

}
