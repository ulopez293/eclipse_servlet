package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;



public interface UnidadRegionalService {
	
	public List<UnidadRegionalDto> getUnidadesRegionales();
	public List<UnidadRegionalDto> getUnidadesRegionalesByEstatus(Estatus estatus);
	public List<UnidadRegionalDto> getUnidadesRegionalesPorIdEntidad(Integer idEntidad);

}
