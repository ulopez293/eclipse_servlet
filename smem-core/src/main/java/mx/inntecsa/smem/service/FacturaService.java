package mx.inntecsa.smem.service;

import mx.inntecsa.smem.dto.FacturaDto;
import mx.inntecsa.smem.enums.Mes;
import mx.inntecsa.smem.enums.TipoServicio;


public interface FacturaService {

	public FacturaDto getServiciosObjectoPago(int numContrato, Mes mes, TipoServicio tipoServicio);
	public FacturaDto getServiciosConPenalizacion(int numContrato, Mes mes, TipoServicio tipoServicio);
}