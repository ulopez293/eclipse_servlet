package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.ClaveDto;
import mx.inntecsa.smem.enums.Estatus;


public interface ClaveService {
	
	public List<ClaveDto> getClaves();
	public List<ClaveDto> getClavesActivas();
	public List<ClaveDto> getClavesByEstatus(Estatus estatus);
	public List<ClaveDto> getClavesConGrupoClavePorIdGrupoYIdEquipo(Integer idEquipo, Integer idGrupo);
	public List<ClaveDto> getClavesConGrupoClavePorIdClave(Integer idClave);
	public ClaveDto guardarClave(ClaveDto claveDto);
	boolean actualizarClave(ClaveDto claveDto);
	boolean eliminarClave(ClaveDto claveDto);
	public List<ClaveDto> getClavesPorNombre(String nombreClave);
	public List<ClaveDto> getClavesPorNombre(String nombreClave, Integer idClave);
	public ClaveDto getClavePorId(Integer idClave);
}
