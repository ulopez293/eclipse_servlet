package mx.inntecsa.smem.service;


import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;



public interface ContratoService {
	
	public ContratoDto getContratoPorNumeroContrato(String numeroContrato);
	public ContratoDto getContratoPorIdContrato(Integer idContrato);
	public List<ContratoDto> getContratoPorEstatusContrato(EstatusContrato estatusContrato);
	public List<ContratoDto> getContratos();
	public List<ContratoDto> getContratosPorEstatus(Estatus estatus);
	public List<ContratoDto> getContratosPorParametros(EstatusContrato estatusContrato, Integer idProveedor, Date fechaInicio, Date fechaFin);
	public ContratoDto guardarContrato(ContratoDto contratoDto);
	public boolean actualizarContrato(ContratoDto contratoDto);
	public boolean eliminarContrato(ContratoDto contratoDto);	

}
