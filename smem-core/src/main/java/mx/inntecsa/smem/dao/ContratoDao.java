package mx.inntecsa.smem.dao;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.pojo.Contrato;

@SuppressWarnings("rawtypes")
public interface ContratoDao extends GenericDao {
	
	public Contrato getContratoPorNumeroContrato(String numeroContrato);
	public Contrato getContratoPorIdContrato(Integer idContrato);
	public List<Contrato> getContratos();
	public List<Contrato> getContratosPorEstatus(Estatus estatus);
	public List<Contrato> getContratoPorEstatusContrato(EstatusContrato estatusContrato);
	public List<Contrato> getContratosPorParametros(EstatusContrato estatusContrato, Integer idProveedor, Date fechaInicio, Date fechaFin);
}
