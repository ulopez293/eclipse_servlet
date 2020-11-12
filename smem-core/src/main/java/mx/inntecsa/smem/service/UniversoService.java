package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.Estatus;



public interface UniversoService {
	
	public List<UniversoDto> getUniversos();
	public List<UniversoDto> getUniversosPorEstatus(Estatus estatus);
	public List<UniversoDto> getUniversosPorParametros(Integer idUnidadRegional, 
		Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso);
	public List<UniversoDto> getUniversosEnContratoPorParametros(String urct,Integer idEquipo,
		Long identificador, String serie, String inventario);	
	public UniversoDto getUniversoPorIdUniverso(Long idUniverso);
	public UniversoDto getUniversoPorInventario(Integer inventario);
	public UniversoDto getUniversoSICOBIMPorInventario(Integer inventario);
	public List<UniversoDto> getUniversoPorIdCentroTrabajo(Integer idCentroTrabajo);
	public boolean actualizarUniverso(UniversoDto universoDto);
	public UniversoDto guardarUniverso(UniversoDto universoDto);
	public boolean eliminarUniverso(UniversoDto universoDto);
	public List<UniversoDto> getUniversosCompletosPorIdCentroTrabajo(Integer idCentroTrabajo);
	public List<UniversoDto> getUniversosCompletosPorParametros(Integer idUnidadRegional,Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso);
	public List<UniversoDto> getUniversosEnContratoSinServicios(String urct,Integer idEquipo,
			Long identificador, String serie, String inventario);	
	
}
