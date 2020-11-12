package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.GrupoDto;
import mx.inntecsa.smem.enums.Estatus;



public interface GrupoService {
	
	public List<GrupoDto> getGrupos();
	public List<GrupoDto> getGruposActivos();
	public List<GrupoDto> getGruposByEstatus(Estatus estatus);
	public List<GrupoDto> getGruposConGrupoClavePorIdEquipo(Integer idEquipo);

	GrupoDto guardarGrupo(GrupoDto grupoDto);
	boolean actualizarGrupo(GrupoDto grupoDto);
	boolean eliminarGrupo(GrupoDto grupoDto);
	public List<GrupoDto> getGruposPorNombre(String nombre);
	public List<GrupoDto> getGruposPorNombre(String nombre, Integer idGrupo);
	public GrupoDto getGrupoPorId(Integer idGrupo);

}
