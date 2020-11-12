package mx.inntecsa.smem.service;

import mx.inntecsa.smem.dto.ConfiguracionDto;

public interface ConfiguracionService{
	
	public ConfiguracionDto getConfiguracion();
	
	public boolean actualizarConfiguracion(ConfiguracionDto configuracionDto);
}
