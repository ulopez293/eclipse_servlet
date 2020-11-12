package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.enums.Estatus;


public interface EntidadService {

	public abstract List<EntidadDto> getEntidades();
	public abstract List<EntidadDto> getEntidadesPorEstatus(Estatus estatus);

}