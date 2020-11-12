package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.GrupoClaveDto;
import mx.inntecsa.smem.enums.Estatus;



public interface GrupoClaveService {
	
	public List<GrupoClaveDto> getGruposClaves();
	public List<GrupoClaveDto> getGruposClavesByEstatus(Estatus estatus);
	public List<GrupoClaveDto> getGruposClavesPorIdEquipo(Integer idEquipo);
	public List<GrupoClaveDto> getGruposClavesPorIdGrupo(Integer idGrupo);
	public GrupoClaveDto getGrupoClavePorParametros(Integer idEquipo,
		Integer idGrupo, Integer idClave);
	
	GrupoClaveDto guardarGrupoClave(GrupoClaveDto grupoClaveDto);
	boolean actualizarGrupoClave(GrupoClaveDto grupoClaveDto);
	boolean eliminarGrupoClave(GrupoClaveDto grupoClaveDto);
	public List<GrupoClaveDto> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo);
	public List<GrupoClaveDto> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo, Integer idGrupoClave);
	public GrupoClaveDto getGrupoClavePorId(Integer idGrupoClave);
}
