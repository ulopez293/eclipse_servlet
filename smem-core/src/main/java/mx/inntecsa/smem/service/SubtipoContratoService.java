package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.SubtipoContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SubtipoContrato;


public interface SubtipoContratoService {
	
	public List<SubtipoContratoDto> getSubtiposContratos();
	public SubtipoContrato getSubtiposContratosPorId(Integer idSubtipoContrato);
	public List<SubtipoContratoDto> getSubtiposContratosPorEstatus(Estatus estatus);
	public List<SubtipoContratoDto> getSubtipoContratosPorNombre(String string);
	public List<SubtipoContratoDto> getSubtipoContratosPorNombre(String string, Integer idSubtipoContrato);
	public SubtipoContratoDto guardarSubtipoContrato(SubtipoContratoDto subtipoContratoDto);
	public boolean actualizarSubtipoContrato(SubtipoContratoDto subtipoContratoDto);
	public boolean eliminarSubtipoContrato(SubtipoContratoDto subtipoContratoDto);
}

