package mx.inntecsa.smem.service;


import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.UniversoDto;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;



public interface ProgramacionServicioService {
	
	public List<ProgramacionServicioDto> getProgramacionServiciosPorEstatus(String urct, EstatusServicio []estatus);
	
	public List<ProgramacionServicioDto> getProgramacionServiciosPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio);
	
	public ProgramacionServicioDto getProgramacionServicioPorIdContratoDetalle(Integer idContratooDetalle);
	
	public ProgramacionServicioDto getUltimaProgramacioPorUniverso(Long idUniverso);
	
	public boolean getExisteCorrectivoEnProcesoPorUniverso(Long idUniverso);

	public ProgramacionServicioDto guardarProgramacionServicioPreventivo(ProgramacionServicioDto programacionServicioDto);
	
	/**
	 * @param programacionServicioDto la programacion a guardar
	 * @param universoDto el universo asociado a la programacion, para actualizar la funcionalidad
	 * @return programacionServicio con el id generado al guardar 
	 */
	public ProgramacionServicioDto guardarProgramacionServicioCorrectivo(ProgramacionServicioDto programacionServicioDto, UniversoDto universoDto);
	
	public boolean actualizarProgramacionServicio(ProgramacionServicioDto programacionServicioDto);
	
	public boolean getExistenProgramacionesAbiertasPorProveedor(int idProveedor);
	
	public boolean getExistenServiciosPorIdCentroTrabajo(int idCentroTrabajo);
	
	public boolean getServiciosEnProcesoPorUniverso(Long idUniverso);
	
	public String getUltimoConsecutivoFolio(int anio);
}
