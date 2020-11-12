package mx.inntecsa.smem.dao;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.ContratoDetalle;

@SuppressWarnings("rawtypes")
public interface ContratoDetalleDao extends GenericDao {
	
	public List<ContratoDetalle> getContratosDetalles();
	public List<ContratoDetalle> getContratosDetallesPorEstatus(Estatus estatus);
	public List<ContratoDetalle> getContratosDetallesPorFechaInicio(Date fechaInicio);
	public List<ContratoDetalle> getContratosDetallesPorIdContrato(Integer idContrato);
	public ContratoDetalle getContratoDetallePorParametros(Integer idContrato, Integer periodo, Long idUniverso);
	public Integer getMaxPeriodoPorIdUniverso(Long idUniverso);
	public Integer getConsecutivoVigentePorIdUniverso(Long idUniverso);
	/**
	 * Este metodo regresa el contrato detalle del periodo 1 de un contrato vigente, 
	 * en base a un identificador de universo proporcionado
	 * @param idUniverso identificador del universo con el que buscara el numero de contrato detalle
	 * @return Contrato detalle encontrado
	 */
	public ContratoDetalle getContratoDetallePorIdUniverso(Long idUniverso);
	
}
