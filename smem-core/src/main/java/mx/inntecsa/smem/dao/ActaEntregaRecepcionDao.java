package mx.inntecsa.smem.dao;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ActaEntregaRecepcion;

@SuppressWarnings("rawtypes")
public interface ActaEntregaRecepcionDao extends GenericDao {
	
	public List<ActaEntregaRecepcion> getActasEntregasRecepcion();
	public List<ActaEntregaRecepcion> getActasEntregasRecepcionByEstatus(Estatus estatus);
	public ActaEntregaRecepcion getUltimaActaEntregaPorIdProgramacion(int idProgramacionServicio);
	public ActaEntregaRecepcion getUltimaActaEntregaPorIdUniverso(long idUniverso);
	public List<ActaEntregaRecepcion> getActasEntregaRecepcionPorUrctYEstatus(String urct, EstatusServicio []estatus);
	public List<ActaEntregaRecepcion> getActasEntregaRecepcionPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio);
}
