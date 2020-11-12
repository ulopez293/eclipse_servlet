package mx.inntecsa.smem.service;


import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.BitacoraDto;


public interface BitacoraService {

	public List<BitacoraDto> getBitacoraPorParametros(String usuario,Date fechaInicio, Date fechaFin, String movimiento);
	public List<BitacoraDto> getBitacora(String usuario);
	public boolean registraBitacora(BitacoraDto bitacora);

}