package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.EspecialidadDto;
import mx.inntecsa.smem.enums.Estatus;


public interface EspecialidadService {
	
	public List<EspecialidadDto> getEspecialidades();
	public List<EspecialidadDto> getEspecialidadesByEstatus(Estatus estatus);
	public EspecialidadDto guardarEspecialidad(EspecialidadDto especialidadDto);
	public boolean actualizarEspecialidad(EspecialidadDto especialidadDto);
	public boolean eliminarEspecialidad(EspecialidadDto especialidadDto);
	public List<EspecialidadDto> getEspecialidadesPorNombre(String nombre);
	public EspecialidadDto getEspecialidadPorId(Integer idEspecialidad);
	public List<EspecialidadDto> getEspecialidadesPorNombre(String nombre,Integer idEspecialidad);
}
