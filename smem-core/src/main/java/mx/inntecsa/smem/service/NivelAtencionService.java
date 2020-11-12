package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.NivelAtencionDto;
import mx.inntecsa.smem.enums.Estatus;


public interface NivelAtencionService {
	
	public List<NivelAtencionDto> getNivelesAtencion();
	public List<NivelAtencionDto> getNivelesAtencionByEstatus(Estatus estatus);
	public NivelAtencionDto guardarNivelAtencion(NivelAtencionDto nivelAtencionDto);
	public boolean actualizarNivelAtencion(NivelAtencionDto nivelAtencionDto);
	public boolean eliminarNivelAtencion(NivelAtencionDto nivelAtencionDto);
	public List<NivelAtencionDto> getNivelesAtencionPorDescripcion(String descripcion);
	public NivelAtencionDto getNivelAtencionPorId(Integer idNivelAtencion);
	public List<NivelAtencionDto> getNivelesAtencionPorDescripcion(String descripcion, Integer idNivelAtencion);

}
