package mx.inntecsa.smem.service;


import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.enums.Estatus;



public interface ContratoDetalleService {
	
	public List<ContratoDetalleDto> getContratosDetalles();
	public List<ContratoDetalleDto> getContratosDetallesPorEstatus(Estatus estatus);
	public List<ContratoDetalleDto> getContratosDetallesPorFechaInicio(Date fechaInicio);
	public List<ContratoDetalleDto> getContratosDetallesPorIdContrato(Integer idContrato);
	public ContratoDetalleDto getContratoDetallePorParametros(Integer idContrato, Integer periodo, Long idUniverso);
    public Integer getMaxPeriodoPorIdUniverso(Long idUniverso);	
	public Boolean guardarContratosDetalles(List<ContratoDetalleDto> contratosDetalles);
    public ContratoDetalleDto getContratoDetallePorIdUniverso(Long idUniverso);
	public Integer getConsecutivoVigentePorIdUniverso(Long idUniverso);
	public Boolean actualizarContratoDetalle(ContratoDetalleDto contratoDetalleDto);
	public Boolean eliminarContratoDetalle(ContratoDetalleDto contratoDetalleDto);
	public Boolean guardarContratoDetalle(ContratoDetalleDto contratoDetalleDto);
}
