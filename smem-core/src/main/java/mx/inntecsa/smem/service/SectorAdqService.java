package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.SectorAdqDto;
import mx.inntecsa.smem.enums.Estatus;



public interface SectorAdqService {
	
	public List<SectorAdqDto> getSectoresAdq();
	public List<SectorAdqDto> getSectoresAdqPorEstatus(Estatus estatus);
	SectorAdqDto guardarSectorAdquisicion(SectorAdqDto sectorAdqDto);
	boolean actualizarSectorAdquisicion(SectorAdqDto sectorAdqDto);
	boolean eliminarSectorAdquisicion(SectorAdqDto sectorAdqDto);
	public List<SectorAdqDto> getSectorAdquisicionPorNombre(String nombre);
	public List<SectorAdqDto> getSectorAdquisicionPorNombre(String nombre, Integer idSectorAdq);
	public SectorAdqDto getSectorAdquisicionPorId(Integer idSectorAdq);
}
