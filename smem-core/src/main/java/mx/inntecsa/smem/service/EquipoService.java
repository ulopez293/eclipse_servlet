package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.EquipoDto;


public interface EquipoService {

	public List<EquipoDto> getEquipos();
	public List<EquipoDto> getEquiposActivos();
	public EquipoDto guardarEquipo(EquipoDto equipoDto);
	public List<EquipoDto> getEquiposConGrupoClave();
	public boolean actualizarEquipo(EquipoDto equipoDto);
	public boolean eliminarEquipo(EquipoDto equipoDto);

}