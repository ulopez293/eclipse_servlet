package mx.inntecsa.smem.service;


import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.ActaEntregaRecepcionDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;


public interface ActaEntregaRecepcionService {
	
	public List<ActaEntregaRecepcionDto> getActasEntregasRecepcion();
	public List<ActaEntregaRecepcionDto> getActasEntregasRecepcionByEstatus(Estatus estatus);
	
	/**
	 * Guarda el acta entrega, actualiza el estaus de la programacion y la funcionalidad del universo
	 * @param actaEntregaDto el acta entrega a guardar, debe de contener una programacion de servicios y un universo 
	 * @return Acta entrega guardada
	 */
	public ActaEntregaRecepcionDto guardarActaEntrega(ActaEntregaRecepcionDto actaEntregaDto);
	public ActaEntregaRecepcionDto getUltimaActaEntregaPorIdProgramacion(int idProgramacionServicio);
	public ActaEntregaRecepcionDto getUltimaActaEntregaPorIdUniverso(long idUniverso);
	public List<ActaEntregaRecepcionDto> getActasEntregaRecepcionPorUrctYEstatus(String urct, EstatusServicio []estatus);
	public List<ActaEntregaRecepcionDto> getActasEntregaRecepcionPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio);
}
