package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.TipoContratacionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.TipoContratacion;


public interface TipoContratacionService {
	
	public List<TipoContratacionDto> getTiposContratacion();
	public TipoContratacion getTiposContratacionPorId(Integer idTipoContrato);
	public List<TipoContratacionDto> getTiposContratacionPorEstatus(Estatus estatus);
	public List<TipoContratacionDto> getTiposContratacionesPorNombre(String string);
	public List<TipoContratacionDto> getTiposContratacionesPorNombre(String string, Integer idTipoContrato);
	public TipoContratacionDto guardarTipoContratacion(TipoContratacionDto tipoContratacionDto);
	boolean actualizarTipoContratacion(TipoContratacionDto tipoContratacionDto);
	boolean eliminarTipoContratacion(TipoContratacionDto tipoContratacionDto);
	
}
